package mirrg.helium.swing.nitrogen.wrapper;

import java.awt.Window;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowEvent;

public class EventNitrogen
{

	public Window window;
	public WindowWrapperNitrogen windowWrapper;

	public EventNitrogen(Window window, WindowWrapperNitrogen neon)
	{
		this.window = window;
		this.windowWrapper = neon;
	}

	public static class EventNeonWindow extends EventNitrogen
	{

		public WindowEvent event;

		public EventNeonWindow(Window window, WindowWrapperNitrogen neon, WindowEvent event)
		{
			super(window, neon);
			this.event = event;
		}

	}

	public static class EventNeonComponent extends EventNitrogen
	{

		public ComponentEvent event;

		public EventNeonComponent(Window window, WindowWrapperNitrogen neon, ComponentEvent event)
		{
			super(window, neon);
			this.event = event;
		}

	}

	/**
	 * 初回open時
	 */
	public static class Initialized extends EventNeonComponent
	{

		public Initialized(Window window, WindowWrapperNitrogen neon, ComponentEvent event)
		{
			super(window, neon, event);
		}

	}

	/**
	 * 初回も含めてウィンドウが開くとき
	 */
	public static class Shown extends EventNeonComponent
	{

		public Shown(Window window, WindowWrapperNitrogen neon, ComponentEvent event)
		{
			super(window, neon, event);
		}

	}

	/**
	 * disposeも含めてウィンドウが閉じるとき
	 */
	public static class Hidden extends EventNeonComponent
	{

		public Hidden(Window window, WindowWrapperNitrogen neon, ComponentEvent event)
		{
			super(window, neon, event);
		}

	}

	/**
	 * disposeするとき
	 */
	public static class Disposed extends EventNeonWindow
	{

		public Disposed(Window window, WindowWrapperNitrogen neon, WindowEvent event)
		{
			super(window, neon, event);
		}

	}

}
