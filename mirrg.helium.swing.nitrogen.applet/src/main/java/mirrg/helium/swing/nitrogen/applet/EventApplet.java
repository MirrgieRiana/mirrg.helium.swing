package mirrg.helium.swing.nitrogen.applet;

import java.applet.Applet;
import java.awt.Graphics;

public class EventApplet extends EventNitrogen
{

	public Applet applet;

	public EventApplet(Applet applet)
	{
		this.applet = applet;
	}

	public static class Init extends EventApplet
	{

		public Init(Applet applet)
		{
			super(applet);
		}

	}

	public static class Start extends EventApplet
	{

		public Start(Applet applet)
		{
			super(applet);
		}

	}

	public static class Stop extends EventApplet
	{

		public Stop(Applet applet)
		{
			super(applet);
		}

	}

	public static class Destroy extends EventApplet
	{

		public Destroy(Applet applet)
		{
			super(applet);
		}

	}

	/**
	 * @see EventApplet.Start
	 * @see EventComponent.Resized
	 */
	public static class Rebuffer extends EventApplet
	{

		public int width;
		public int height;

		public Rebuffer(Applet applet, int width, int height)
		{
			super(applet);
			this.width = width;
			this.height = height;
		}

	}

	//

	public static class EventCancelable extends EventApplet
	{

		public boolean cancelled = false;

		public EventCancelable(Applet applet)
		{
			super(applet);
		}

	}

	public static class EventPaintBase extends EventApplet
	{

		public Graphics graphics;

		public EventPaintBase(Applet applet, Graphics graphics)
		{
			super(applet);
			this.graphics = graphics;
		}

	}

	public static class Paint extends EventPaintBase
	{

		public Paint(Applet applet, Graphics graphics)
		{
			super(applet, graphics);
		}

		public static class Pre extends EventCancelable
		{

			public Pre(Applet applet)
			{
				super(applet);
			}

		}

	}

	public static class Update extends EventPaintBase
	{

		public Update(Applet applet, Graphics graphics)
		{
			super(applet, graphics);
		}

		public static class Pre extends EventCancelable
		{

			public Pre(Applet applet)
			{
				super(applet);
			}

		}

	}

}
