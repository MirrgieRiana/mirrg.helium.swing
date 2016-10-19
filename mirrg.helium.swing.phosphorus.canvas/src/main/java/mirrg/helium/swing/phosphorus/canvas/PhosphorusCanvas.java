package mirrg.helium.swing.phosphorus.canvas;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class PhosphorusCanvas extends JPanel
{

	public BufferedImage image;
	public Graphics2D graphics;

	public PhosphorusCanvas()
	{
		reset(1, 1);
		addComponentListener(new ComponentListener() {

			@Override
			public void componentShown(ComponentEvent e)
			{
				reset(getWidth(), getHeight());
			}

			@Override
			public void componentResized(ComponentEvent e)
			{
				reset(getWidth(), getHeight());
			}

			@Override
			public void componentMoved(ComponentEvent e)
			{

			}

			@Override
			public void componentHidden(ComponentEvent e)
			{

			}

		});
	}

	public void reset(int width, int height)
	{
		BufferedImage image2 = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics2 = image2.createGraphics();

		graphics2.drawImage(image, 0, 0, null);

		image = image2;
		graphics = graphics2;
	}

	@Override
	public void paint(Graphics g)
	{
		g.drawImage(image, 0, 0, null);
	}

}
