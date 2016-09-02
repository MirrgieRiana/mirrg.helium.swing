package mirrg.helium.swing.nitrogen.applet;

import java.awt.event.ComponentEvent;

public class EventComponent extends EventNitrogen
{

	public ComponentEvent componentEvent;

	public EventComponent(ComponentEvent componentEvent)
	{
		this.componentEvent = componentEvent;
	}

	public static class Shown extends EventComponent
	{

		public Shown(ComponentEvent componentEvent)
		{
			super(componentEvent);
		}

	}

	public static class Resized extends EventComponent
	{

		public Resized(ComponentEvent componentEvent)
		{
			super(componentEvent);
		}

	}

	public static class Moved extends EventComponent
	{

		public Moved(ComponentEvent componentEvent)
		{
			super(componentEvent);
		}

	}

	public static class Hidden extends EventComponent
	{

		public Hidden(ComponentEvent componentEvent)
		{
			super(componentEvent);
		}

	}

}
