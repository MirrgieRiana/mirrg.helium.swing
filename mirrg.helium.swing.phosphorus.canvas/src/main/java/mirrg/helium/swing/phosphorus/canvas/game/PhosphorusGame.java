package mirrg.helium.swing.phosphorus.canvas.game;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.stream.Stream;

import mirrg.helium.swing.phosphorus.canvas.EventPhosphorusCanvas;
import mirrg.helium.swing.phosphorus.canvas.PhosphorusCanvas;

public class PhosphorusGame<SELF extends PhosphorusGame<SELF>> implements IGame
{

	public final PhosphorusCanvas canvas;
	private ArrayList<Layer> layers = new ArrayList<>();
	private ArrayList<Tool<? super SELF>> tools = new ArrayList<>();
	private Data<SELF> data;

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

	public Data<SELF> getData()
	{
		return data;
	}

	public synchronized void setData(Data<SELF> data)
	{
		this.data.dispose(getThis());
		this.data = data;
		data.entities.forEach(e -> e.touch(getThis()));
	}

	public View getView()
	{
		return data.view.getView(this);
	}

	public void addLayer(Layer layer)
	{
		layers.add(layer);
	}

	public void addTool(Tool<? super SELF> tool)
	{
		tools.add(tool);
	}

	public void addEntity(DataEntity<? super SELF> entity)
	{
		data.entities.add(entity);
		entity.touch(getThis());
	}

	private ArrayList<Runnable> doLater = new ArrayList<>();

	public void doLater(Runnable runnable)
	{
		doLater.add(runnable);
	}

	@Override
	public synchronized void move()
	{
		data.entities.stream()
			.forEach(e -> e.getEntity(getThis()).move());
		tools.stream()
			.forEach(e -> e.move());

		{
			ArrayList<Runnable> doLater2 = doLater;
			doLater = new ArrayList<>();
			doLater2.forEach(Runnable::run);
		}
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

	public void onViewChange()
	{
		layers.forEach(l -> l.dirty());
	}

	public Layer createLayer()
	{
		return new Layer(canvas.createImageLayer(BufferedImage.TYPE_INT_ARGB));
	}

	@SuppressWarnings("unchecked")
	public SELF getThis()
	{
		return (SELF) this;
	}

}
