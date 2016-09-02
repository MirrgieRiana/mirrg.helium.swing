package mirrg.helium.swing.nitrogen.applet.modules.input;

import java.awt.event.MouseWheelEvent;

import mirrg.helium.swing.nitrogen.applet.EventNitrogen;

public class EventMouseWheel extends EventNitrogen
{

	public MouseWheelEvent mouseWheelEvent;

	public EventMouseWheel(MouseWheelEvent mouseWheelEvent)
	{
		this.mouseWheelEvent = mouseWheelEvent;
	}

	public static class Moved extends EventMouseWheel
	{

		public Moved(MouseWheelEvent mouseWheelEvent)
		{
			super(mouseWheelEvent);
		}

	}

}
