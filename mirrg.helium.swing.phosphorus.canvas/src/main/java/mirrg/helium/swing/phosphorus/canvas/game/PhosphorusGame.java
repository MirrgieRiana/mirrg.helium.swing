package mirrg.helium.swing.phosphorus.canvas.game;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.stream.Stream;

import mirrg.helium.standard.hydrogen.event.EventManager;
import mirrg.helium.swing.phosphorus.canvas.EventPhosphorusCanvas;
import mirrg.helium.swing.phosphorus.canvas.PhosphorusCanvas;
import mirrg.helium.swing.phosphorus.canvas.game.existence.DataEntity;
import mirrg.helium.swing.phosphorus.canvas.game.existence.Tool;
import mirrg.helium.swing.phosphorus.canvas.game.render.Layer;
import mirrg.helium.swing.phosphorus.canvas.game.tools.ToolBackground;
import mirrg.helium.swing.phosphorus.canvas.game.view.IViewContext;
import mirrg.helium.swing.phosphorus.canvas.game.view.View;

public class PhosphorusGame<SELF extends PhosphorusGame<SELF>> implements IGame
{

	public final Layer layerBack;
	public final ToolBackground toolBackground;

	public PhosphorusGame(PhosphorusCanvas canvas, Data<SELF> data)
	{
		this.canvas = canvas;
		this.data = data;

		canvas.event().register(EventPhosphorusCanvas.EventComponent.Resized.class, e -> layers.forEach(Layer::dirty));

		addLayer(layerBack = createLayer());
		addTool(toolBackground = new ToolBackground(this));

	}

	//

	private EventManager<EventPhosphorusGame> eventManager = new EventManager<>();

	public EventManager<EventPhosphorusGame> event()
	{
		return eventManager;
	}

	//

	public final PhosphorusCanvas canvas;

	//

	private Data<SELF> data;

	public Object getData()
	{
		return data;
	}

	public synchronized void setData(Data<SELF> data)
	{
		this.data.dispose(getThis());
		this.data = data;

		data.entities.forEach(e -> e.touch(getThis()));

		view = null;
		layers.forEach(l -> l.dirty());
	}

	@SuppressWarnings("unchecked")
	private SELF getThis()
	{
		return (SELF) this;
	}

	//

	private ArrayList<Tool<? super SELF>> tools = new ArrayList<>();

	public void addTool(Tool<? super SELF> tool)
	{
		tools.add(tool);
	}

	public Stream<DataEntity<? super SELF>> getEntities()
	{
		return data.entities.stream();
	}

	public void addEntity(DataEntity<? super SELF> entity)
	{
		data.entities.add(entity);
		entity.touch(getThis());

		layers.forEach(l -> {
			if (entity.getEntity(getThis()).getOpticalBounds(l).isPresent()) l.dirty();
		});
	}

	@Override
	public synchronized void move()
	{
		eventManager.post(new EventPhosphorusGame.Move.Pre());

		data.entities.stream()
			.forEach(e -> e.getEntity(getThis()).move());
		tools.stream()
			.forEach(e -> e.move());

		eventManager.post(new EventPhosphorusGame.Move.Post());
	}

	@Override
	public synchronized void render(Graphics2D g)
	{
		layers.forEach(l -> {
			l.paint(g, () -> {
				Stream.concat(
					data.entities.stream()
						.map(e -> e.getEntity(getThis())),
					tools.stream())
					.sorted((a, b) -> (int) Math.signum(a.getZOrder() - b.getZOrder()))
					.forEach(e -> e.render(l));
			});
		});
	}

	//

	private ArrayList<Layer> layers = new ArrayList<>();

	protected void addLayer(Layer layer)
	{
		layers.add(layer);
	}

	protected Layer createLayer()
	{
		return new Layer(canvas.createImageLayer(BufferedImage.TYPE_INT_ARGB));
	}

	protected Stream<Layer> getLayers()
	{
		return layers.stream();
	}

	//

	private View view;

	public View getView()
	{
		if (view == null) view = data.view.getView(new IViewContext() {

			@Override
			public void onViewChange()
			{
				layers.forEach(l -> l.dirty());
				eventManager.post(new EventPhosphorusGame.ViewChange());
			}

			@Override
			public double getWidth()
			{
				return canvas.getWidth();
			}

			@Override
			public double getHeight()
			{
				return canvas.getHeight();
			}

		});
		return view;
	}

}
