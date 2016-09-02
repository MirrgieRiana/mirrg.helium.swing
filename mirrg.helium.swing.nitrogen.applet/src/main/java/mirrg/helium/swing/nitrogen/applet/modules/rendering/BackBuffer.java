package mirrg.helium.swing.nitrogen.applet.modules.rendering;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import mirrg.helium.standard.hydrogen.event.EventManager;
import mirrg.helium.swing.nitrogen.applet.EventApplet;
import mirrg.helium.swing.nitrogen.applet.EventNitrogen;

/**
 * サイズ可変イメージバッファ
 */
public class BackBuffer
{

	private int imageType;
	private BufferedImage buffer;
	private Graphics2D graphics;

	public BackBuffer(int imageType)
	{
		this.imageType = imageType;
	}

	public synchronized void rebuffer(int width, int height)
	{
		if (width <= 0) width = 1;
		if (height <= 0) height = 1;

		BufferedImage buffer = new BufferedImage(width, height, imageType);
		Graphics2D graphics = buffer.createGraphics();

		if (this.buffer != null) {
			graphics.drawImage(this.buffer, 0, 0, null);
		}

		set(buffer, graphics);
	}

	public synchronized void set(BufferedImage bufferSafety, Graphics2D graphicsSafety)
	{
		this.buffer = bufferSafety;
		this.graphics = graphicsSafety;
	}

	/**
	 * 読み取り用のバッファ。
	 */
	public synchronized BufferedImage getBuffer()
	{
		return buffer;
	}

	public synchronized Graphics2D getGraphics()
	{
		return graphics;
	}

	public synchronized int getWidth()
	{
		return buffer.getWidth();
	}

	public synchronized int getHeight()
	{
		return buffer.getHeight();
	}

	/**
	 * バッファを空にする。
	 */
	public void clear()
	{
		getGraphics().clearRect(0, 0, getWidth(), getHeight());
	}

	public synchronized void flip(BackBuffer other)
	{
		{
			BufferedImage tmp = this.buffer;
			this.buffer = other.buffer;
			other.buffer = tmp;
		}
		{
			Graphics2D tmp = this.graphics;
			this.graphics = other.graphics;
			other.graphics = tmp;
		}
	}

	/**
	 * @see EventApplet.Rebuffer
	 */
	public void subscribeEvent(EventManager<EventNitrogen> eventManager)
	{
		eventManager.register(EventApplet.Rebuffer.class, event -> {
			rebuffer(event.width, event.height);
		});
	}

}
