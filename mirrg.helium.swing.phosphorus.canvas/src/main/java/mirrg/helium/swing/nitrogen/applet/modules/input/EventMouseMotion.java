package mirrg.helium.swing.nitrogen.applet.modules.input;

import java.awt.event.MouseEvent;

import mirrg.helium.swing.nitrogen.applet.EventNitrogen;

public class EventMouseMotion extends EventNitrogen
{

	public MouseEvent mouseEvent;

	public EventMouseMotion(MouseEvent mouseEvent)
	{
		this.mouseEvent = mouseEvent;
	}

	public static class Dragged extends EventMouseMotion
	{

		public Dragged(MouseEvent mouseEvent)
		{
			super(mouseEvent);
		}

	}

	public static class Moved extends EventMouseMotion
	{

		public Moved(MouseEvent mouseEvent)
		{
			super(mouseEvent);
		}

	}

}
