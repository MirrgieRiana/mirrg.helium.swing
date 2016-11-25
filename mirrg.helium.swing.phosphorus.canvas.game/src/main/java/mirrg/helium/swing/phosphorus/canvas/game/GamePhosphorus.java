package mirrg.helium.swing.phosphorus.canvas.game;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.stream.Stream;

import mirrg.helium.game.carbon.base.EventGameCarbon;
import mirrg.helium.game.carbon.base.GameCarbon;
import mirrg.helium.swing.phosphorus.canvas.EventPhosphorusCanvas;
import mirrg.helium.swing.phosphorus.canvas.PhosphorusCanvas;
import mirrg.helium.swing.phosphorus.canvas.game.entity.ModelLiving.Entity;
import mirrg.helium.swing.phosphorus.canvas.game.entity.ToolBackground;
import mirrg.helium.swing.phosphorus.canvas.game.render.Layer;
import mirrg.helium.swing.phosphorus.canvas.game.view.ModelView;

public class GamePhosphorus<THIS extends GamePhosphorus<THIS, MODEL, VIEW>, MODEL extends ModelPhosphorus<THIS, VIEW>, VIEW extends ModelView>
	extends GameCarbon<THIS, MODEL>
{

	public final PhosphorusCanvas canvas;

	public final Layer layerBack;
	public final ToolBackground toolBackground;

	public GamePhosphorus(MODEL model, PhosphorusCanvas canvas)
	{
		super(model);
		this.canvas = canvas;
		canvas.event().register(EventPhosphorusCanvas.EventComponent.Resized.class, e -> dirty());

		addLayer(layerBack = createLayer());
		addTool(toolBackground = new ToolBackground(this));

		event().register(EventGameCarbon.ChangeModel.Post.class, e -> dirty());
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

	public Stream<Layer> getLayers()
	{
		return layers.stream();
	}

	public void dirty()
	{
		layers.forEach(Layer::dirty);
	}

	//

	@SuppressWarnings("unchecked")
	public void getEntities(Consumer<Entity<? extends THIS>> dest)
	{
		getModel().getAllModels(m -> {
			if (m.getController() instanceof Entity) dest.accept((Entity<? extends THIS>) m.getController());
		});
		getTools().forEach(t -> {
			if (t instanceof Entity) dest.accept((Entity<? extends THIS>) t);
		});
	}

	public void move()
	{
		event().post(new EventGamePhosphorus.Move.Pre());

		of(this::getEntities).forEach(Entity::move);

		event().post(new EventGamePhosphorus.Move.Post());
	}

	public void render(Graphics2D g)
	{
		event().post(new EventGamePhosphorus.Render.Pre());

		layers.forEach(l -> {
			l.paint(g, () -> {
				of(this::getEntities)
					.sorted((a, b) -> (int) Math.signum(a.getZOrder() - b.getZOrder()))
					.forEach(e -> e.render(l));
			});
		});

		event().post(new EventGamePhosphorus.Render.Post());
	}

	//

	public VIEW getView()
	{
		return getModel().getController().getView();
	}

	//TODO mirrg
	public static <T> Stream<T> of(Consumer<Consumer<T>> ts)
	{
		ArrayList<T> list = new ArrayList<>();
		ts.accept(list::add);
		return list.stream();
	}

}
