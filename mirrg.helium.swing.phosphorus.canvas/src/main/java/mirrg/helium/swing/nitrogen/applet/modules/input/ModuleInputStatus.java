package mirrg.helium.swing.nitrogen.applet.modules.input;

import mirrg.helium.swing.nitrogen.applet.AppletNitrogen;
import mirrg.helium.swing.nitrogen.applet.Module;

public class ModuleInputStatus extends Module
{

	private static final int MOUSE_BUTTONS_COUNT = 8;
	private static final int KEYS_COUNT = 1024;

	private int mouseX, mouseXPrevious;
	private int mouseY, mouseYPrevious;
	private int mouseW, mouseWPrevious;

	private Button mouseButtons = new Button(MOUSE_BUTTONS_COUNT);
	private Button keyBoard = new Button(KEYS_COUNT);

	public ModuleInputStatus(AppletNitrogen applet, ModuleInputEvent moduleInputEvent)
	{
		super(applet);

		applet.getEventManager().register(EventMouse.Pressed.class, event -> {
			mousePressed(
				event.mouseEvent.getX(),
				event.mouseEvent.getY(),
				event.mouseEvent.getButton());
		});
		applet.getEventManager().register(EventMouse.Released.class, event -> {
			mouseReleased(
				event.mouseEvent.getX(),
				event.mouseEvent.getY(),
				event.mouseEvent.getButton());
		});
		applet.getEventManager().register(EventMouseMotion.class, event -> {
			mouseMotion(
				event.mouseEvent.getX(),
				event.mouseEvent.getY());
		});
		applet.getEventManager().register(EventMouseWheel.Moved.class, event -> {
			mouseWheelMoved(event.mouseWheelEvent.getWheelRotation());
		});
		applet.getEventManager().register(EventKey.Pressed.class, event -> {
			keyPressed(event.keyEvent.getKeyCode());
		});
		applet.getEventManager().register(EventKey.Released.class, event -> {
			keyReleased(event.keyEvent.getKeyCode());
		});
	}

	public int getMouseX()
	{
		return mouseX;
	}

	public int getMouseXPrevious()
	{
		return mouseXPrevious;
	}

	public int getMouseY()
	{
		return mouseY;
	}

	public int getMouseYPrevious()
	{
		return mouseYPrevious;
	}

	public int getMouseW()
	{
		return mouseW;
	}

	public int getMouseWPrevious()
	{
		return mouseWPrevious;
	}

	public Button getMouseButtons()
	{
		return mouseButtons;
	}

	public Button getKeyBoard()
	{
		return keyBoard;
	}

	public void spend()
	{
		mouseXPrevious = mouseX;
		mouseYPrevious = mouseY;
		mouseWPrevious = mouseW;

		mouseButtons.spend();
		keyBoard.spend();
	}

	public void mousePressed(int x, int y, int button)
	{
		mouseX = x;
		mouseY = y;
		mouseButtons.pressed(button);
	}

	public void mouseReleased(int x, int y, int button)
	{
		mouseX = x;
		mouseY = y;
		mouseButtons.released(button);
	}

	public void mouseMotion(int x, int y)
	{
		mouseX = x;
		mouseY = y;
	}

	public void mouseWheelMoved(int wheelRotation)
	{
		mouseW += wheelRotation;
	}

	public void keyPressed(int keyCode)
	{
		keyBoard.pressed(keyCode);
	}

	public void keyReleased(int keyCode)
	{
		keyBoard.released(keyCode);
	}

	public static class Button
	{

		private int[] buttons;

		public Button(int buttonsCount)
		{
			buttons = new int[buttonsCount];
		}

		public void pressed(int index)
		{
			if (buttons[index] <= 0) buttons[index] = 1;
		}

		public void released(int index)
		{
			if (buttons[index] >= 0) buttons[index] = -1;
		}

		public int getState(int index)
		{
			return buttons[index];
		}

		public int getCount()
		{
			return buttons.length;
		}

		public void spend()
		{
			for (int i = 0; i < buttons.length; i++) {
				if (buttons[i] > 0) buttons[i]++;
				else if (buttons[i] < 0) buttons[i]--;
			}
		}

	}

}
