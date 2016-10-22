package mirrg.helium.swing.phosphorus.canvas.game;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import mirrg.helium.swing.phosphorus.canvas.EventPhosphorusCanvas;
import mirrg.helium.swing.phosphorus.canvas.PhosphorusCanvas;

public class PhosphorusGame<SELF extends PhosphorusGame<SELF>> implements IGame
{

	public final PhosphorusCanvas canvas;
	private ArrayList<Layer> layers = new ArrayList<>();
	private Data<SELF> data;

	public final Layer layerBack;

	public PhosphorusGame(PhosphorusCanvas canvas, Data<SELF> data)
	{
		this.canvas = canvas;
		this.data = data;

		canvas.event().register(EventPhosphorusCanvas.EventComponent.Resized.class, e -> layers.forEach(Layer::dirty));

		addLayer(layerBack = createLayer());
		data.entities.add(new DataEntityBackground());

	}

	public Data<SELF> getData()
	{
		return data;
	}

	public synchronized void setData(Data<SELF> data)
	{
		this.data.dispose(getThis());
		this.data = data;
	}

	public View getView()
	{
		return data.view.getView(this);
	}

	public void addLayer(Layer layer)
	{
		layers.add(layer);
	}

	public void addEntity(DataEntity<SELF> entity)
	{
		data.entities.add(entity);
	}

	@Override
	public synchronized void move()
	{
		data.entities.stream()
			.forEach(e -> e.getEntity(getThis()).move());
	}

	@Override
	public synchronized void render(Graphics2D g)
	{
		layers.forEach(l -> {
			l.paint(g, () -> {
				data.entities.stream()
					.sorted((a, b) -> (int) Math.signum(a.getEntity(getThis()).getZOrder() - b.getEntity(getThis()).getZOrder()))
					.forEach(e -> e.getEntity(getThis()).render(l));
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