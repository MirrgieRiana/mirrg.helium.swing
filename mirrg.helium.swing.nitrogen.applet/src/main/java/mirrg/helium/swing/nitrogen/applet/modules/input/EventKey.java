package mirrg.helium.swing.nitrogen.applet.modules.input;

import java.awt.event.KeyEvent;

import mirrg.helium.swing.nitrogen.applet.EventNitrogen;

public class EventKey extends EventNitrogen
{

	public KeyEvent keyEvent;

	public EventKey(KeyEvent keyEvent)
	{
		this.keyEvent = keyEvent;
	}

	public static class Typed extends EventKey
	{

		public Typed(KeyEvent keyEvent)
		{
			super(keyEvent);
		}

	}

	public static class Pressed extends EventKey
	{

		public Pressed(KeyEvent keyEvent)
		{
			super(keyEvent);
		}

	}

	public static class Released extends EventKey
	{

		public Released(KeyEvent keyEvent)
		{
			super(keyEvent);
		}

	}

}
