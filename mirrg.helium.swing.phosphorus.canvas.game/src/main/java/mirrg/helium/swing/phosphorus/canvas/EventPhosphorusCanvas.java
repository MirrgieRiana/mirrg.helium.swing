package mirrg.helium.swing.phosphorus.canvas;

import java.awt.AWTEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class EventPhosphorusCanvas<T extends AWTEvent>
{

	public final T event;

	public EventPhosphorusCanvas(T event)
	{
		this.event = event;
	}

	public static class EventComponent extends EventPhosphorusCanvas<ComponentEvent>
	{

		public EventComponent(ComponentEvent event)
		{
			super(event);
		}

		public static class Resized extends EventComponent
		{

			public Resized(ComponentEvent event)
			{
				super(event);
			}

		}

		public static class Moved extends EventComponent
		{

			public Moved(ComponentEvent event)
			{
				super(event);
			}

		}

		public static class Shown extends EventComponent
		{

			public Shown(ComponentEvent event)
			{
				super(event);
			}

		}

		public static class Hidden extends EventComponent
		{

			public Hidden(ComponentEvent event)
			{
				super(event);
			}

		}

	}

	public static class EventMouse extends EventPhosphorusCanvas<MouseEvent>
	{

		public EventMouse(MouseEvent event)
		{
			super(event);
		}

		public static class Clicked extends EventMouse
		{

			public Clicked(MouseEvent event)
			{
				super(event);
			}

		}

		public static class Pressed extends EventMouse
		{

			public Pressed(MouseEvent event)
			{
				super(event);
			}

		}

		public static class Released extends EventMouse
		{

			public Released(MouseEvent event)
			{
				super(event);
			}

		}

		public static class Entered extends EventMouse
		{

			public Entered(MouseEvent event)
			{
				super(event);
			}

		}

		public static class Exited extends EventMouse
		{

			public Exited(MouseEvent event)
			{
				super(event);
			}

		}

	}

	public static class EventMouseMotion extends EventPhosphorusCanvas<MouseEvent>
	{

		public EventMouseMotion(MouseEvent event)
		{
			super(event);
		}

		public static class Dragged extends EventMouseMotion
		{

			public Dragged(MouseEvent event)
			{
				super(event);
			}

		}

		public static class Moved extends EventMouseMotion
		{

			public Moved(MouseEvent event)
			{
				super(event);
			}

		}

	}

	public static class EventWheel extends EventPhosphorusCanvas<MouseWheelEvent>
	{

		public EventWheel(MouseWheelEvent event)
		{
			super(event);
		}

		public static class Moved extends EventWheel
		{

			public Moved(MouseWheelEvent event)
			{
				super(event);
			}

		}

	}

	public static class EventKey extends EventPhosphorusCanvas<KeyEvent>
	{

		public EventKey(KeyEvent event)
		{
			super(event);
		}

		public static class Pressed extends EventKey
		{

			public Pressed(KeyEvent event)
			{
				super(event);
			}

		}

		public static class Released extends EventKey
		{

			public Released(KeyEvent event)
			{
				super(event);
			}

		}

		public static class Typed extends EventKey
		{

			public Typed(KeyEvent event)
			{
				super(event);
			}

		}

	}

}
