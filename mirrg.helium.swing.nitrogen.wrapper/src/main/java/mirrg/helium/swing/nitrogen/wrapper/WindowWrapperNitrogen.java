package mirrg.helium.swing.nitrogen.wrapper;

import java.awt.Window;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import mirrg.helium.standard.hydrogen.event.EventManager;
import mirrg.helium.swing.nitrogen.wrapper.artifacts.logging.HLog;

public class WindowWrapperNitrogen
{

	private EventManager<EventNitrogen> eventManager;
	private Window window;

	public WindowWrapperNitrogen(JFrame window)
	{
		this(window, new EventManager<>());
	}

	public WindowWrapperNitrogen(JDialog window)
	{
		this(window, new EventManager<>());
	}

	public WindowWrapperNitrogen(JFrame window, EventManager<EventNitrogen> eventManager)
	{
		this((Window) window, eventManager);
	}

	public WindowWrapperNitrogen(JDialog window, EventManager<EventNitrogen> eventManager)
	{
		this((Window) window, eventManager);
	}

	private WindowWrapperNitrogen(Window window, EventManager<EventNitrogen> eventManager)
	{
		this.window = window;
		this.eventManager = eventManager;
		init();
	}

	public EventManager<EventNitrogen> getEventManager()
	{
		return eventManager;
	}

	private volatile boolean isInitialized = false;

	public void init()
	{
		window.addComponentListener(new ComponentListener() {

			@Override
			public void componentShown(ComponentEvent e)
			{
				visible = true;

				if (!isInitialized) {
					isInitialized = true;
					eventManager.post(new EventNitrogen.Initialized(window, WindowWrapperNitrogen.this, e));
				}
				eventManager.post(new EventNitrogen.Shown(window, WindowWrapperNitrogen.this, e));
			}

			@Override
			public void componentResized(ComponentEvent e)
			{

			}

			@Override
			public void componentMoved(ComponentEvent e)
			{

			}

			@Override
			public void componentHidden(ComponentEvent e)
			{
				visible = false;

				eventManager.post(new EventNitrogen.Hidden(window, WindowWrapperNitrogen.this, e));
			}

		});
		window.addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e)
			{

			}

			@Override
			public void windowIconified(WindowEvent e)
			{

			}

			@Override
			public void windowDeiconified(WindowEvent e)
			{

			}

			@Override
			public void windowDeactivated(WindowEvent e)
			{

			}

			@Override
			public void windowClosing(WindowEvent e)
			{

			}

			@Override
			public void windowClosed(WindowEvent e)
			{
				isDisposed = true;

				if (visible) {
					eventManager.post(new EventNitrogen.Hidden(window, WindowWrapperNitrogen.this, e));
				}
				eventManager.post(new EventNitrogen.Disposed(window, WindowWrapperNitrogen.this, e));
			}

			@Override
			public void windowActivated(WindowEvent e)
			{

			}

		});
	}

	public void prepareFrame(int defaultCloseOperation)
	{
		if (window instanceof JFrame) {
			((JFrame) window).setDefaultCloseOperation(defaultCloseOperation);
		} else if (window instanceof JDialog) {
			((JDialog) window).setDefaultCloseOperation(defaultCloseOperation);
		} else {
			HLog.processExceptionWarning(
				new ClassCastException(window.toString() + " is not a JFrame or JDialog."));
		}

		window.pack();
		window.setLocationByPlatform(true);
	}

	public void prepareFrame()
	{
		prepareFrame(WindowConstants.DISPOSE_ON_CLOSE);
	}

	////////////////////////////////////////////////

	private boolean visible = false;

	private volatile boolean isDisposed = false;

	/**
	 * スレッドセーフ
	 */
	public boolean isDisposed()
	{
		return isDisposed;
	}

}
