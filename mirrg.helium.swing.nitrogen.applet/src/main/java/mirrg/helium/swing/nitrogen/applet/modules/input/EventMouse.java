package mirrg.helium.swing.nitrogen.applet.modules.input;

import java.awt.event.MouseEvent;

import mirrg.helium.swing.nitrogen.applet.EventNitrogen;

public class EventMouse extends EventNitrogen
{

	public MouseEvent mouseEvent;

	public EventMouse(MouseEvent mouseEvent)
	{
		this.mouseEvent = mouseEvent;
	}

	public static class Released extends EventMouse
	{

		public Released(MouseEvent mouseEvent)
		{
			super(mouseEvent);
		}

	}

	public static class Pressed extends EventMouse
	{

		public Pressed(MouseEvent mouseEvent)
		{
			super(mouseEvent);
		}

	}

	public static class Exited extends EventMouse
	{

		public Exited(MouseEvent mouseEvent)
		{
			super(mouseEvent);
		}

	}

	public static class Entered extends EventMouse
	{

		public Entered(MouseEvent mouseEvent)
		{
			super(mouseEvent);
		}

	}

	public static class Clicked extends EventMouse
	{

		public Clicked(MouseEvent mouseEvent)
		{
			super(mouseEvent);
		}

	}

}
