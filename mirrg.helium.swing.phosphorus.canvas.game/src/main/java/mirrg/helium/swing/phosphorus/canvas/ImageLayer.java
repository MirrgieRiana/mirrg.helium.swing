package mirrg.helium.swing.phosphorus.canvas;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import mirrg.helium.standard.hydrogen.event.EventManager;
import mirrg.helium.swing.phosphorus.canvas.EventPhosphorusCanvas.EventComponent.Resized;
import mirrg.helium.swing.phosphorus.canvas.EventPhosphorusCanvas.EventComponent.Shown;

public class ImageLayer
{

	private int type;
	private int width;
	private int height;
	private BufferedImage image;
	private Graphics2D graphics;

	private EventManager<EventImageLayer> eventManager = new EventManager<>();

	public ImageLayer(PhosphorusCanvas owner, int type, int width, int height)
	{
		this.type = type;

		rebuffer(width, height);

		owner.event().register(Shown.class, e -> rebuffer(owner.getWidth(), owner.getHeight()));
		owner.event().register(Resized.class, e -> rebuffer(owner.getWidth(), owner.getHeight()));
	}

	public void rebuffer(int width, int height)
	{
		if (this.width >= width && this.height >= height) return;

		eventManager.post(new EventImageLayer.Rebuffer.Pre());

		this.width = width;
		this.height = height;

		BufferedImage image2 = new BufferedImage(width, height, type);
		Graphics2D graphics2 = image2.createGraphics();

		graphics2.drawImage(image, 0, 0, null);

		image = image2;
		graphics = graphics2;

		eventManager.post(new EventImageLayer.Rebuffer.Post());
	}

	public void clear()
	{
		graphics.setBackground(new Color(0, 0, 0, 0));
		graphics.clearRect(0, 0, width, height);
	}

	public Graphics2D getGraphics()
	{
		return graphics;
	}

	public BufferedImage getImage()
	{
		return image;
	}

	public void paint(Graphics2D graphics)
	{
		graphics.drawImage(image, 0, 0, null);
	}

	public EventManager<EventImageLayer> event()
	{
		return eventManager;
	}

}
