package mirrg.helium.swing.phosphorus.canvas.game.render;

import java.awt.Graphics2D;

import mirrg.helium.swing.phosphorus.canvas.ImageLayer;

public class Layer
{

	private final ImageLayer imageLayer;

	private boolean isDirty = true;

	public Layer(ImageLayer imageLayer)
	{
		this.imageLayer = imageLayer;
	}

	public ImageLayer getImageLayer()
	{
		return imageLayer;
	}

	public boolean isDirty()
	{
		return isDirty;
	}

	public void dirty()
	{
		isDirty = true;
	}

	public void paint(Graphics2D g, Runnable onUpdate)
	{
		if (isDirty) {
			isDirty = false;

			imageLayer.clear();

			onUpdate.run();

		}
		imageLayer.paint(g);
	}

}
