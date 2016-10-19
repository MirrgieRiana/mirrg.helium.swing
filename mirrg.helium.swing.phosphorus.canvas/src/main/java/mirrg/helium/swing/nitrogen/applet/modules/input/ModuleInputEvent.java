package mirrg.helium.swing.nitrogen.applet.modules.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import mirrg.helium.swing.nitrogen.applet.AppletNitrogen;
import mirrg.helium.swing.nitrogen.applet.Module;

public class ModuleInputEvent extends Module
{

	public ModuleInputEvent(AppletNitrogen applet)
	{
		super(applet);

		applet.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent mouseEvent)
			{
				applet.getEventManager().post(
					new EventMouse.Released(mouseEvent));
			}

			@Override
			public void mousePressed(MouseEvent mouseEvent)
			{
				applet.getEventManager().post(
					new EventMouse.Pressed(mouseEvent));
			}

			@Override
			public void mouseExited(MouseEvent mouseEvent)
			{
				applet.getEventManager().post(
					new EventMouse.Exited(mouseEvent));
			}

			@Override
			public void mouseEntered(MouseEvent mouseEvent)
			{
				applet.getEventManager().post(
					new EventMouse.Entered(mouseEvent));
			}

			@Override
			public void mouseClicked(MouseEvent mouseEvent)
			{
				applet.getEventManager().post(
					new EventMouse.Clicked(mouseEvent));
			}

		});
		applet.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseDragged(MouseEvent mouseEvent)
			{
				applet.getEventManager().post(
					new EventMouseMotion.Dragged(mouseEvent));
			}

			@Override
			public void mouseMoved(MouseEvent mouseEvent)
			{
				applet.getEventManager().post(
					new EventMouseMotion.Moved(mouseEvent));
			}

		});
		applet.addMouseWheelListener(new MouseWheelListener() {

			@Override
			public void mouseWheelMoved(MouseWheelEvent mouseWheelEvent)
			{
				applet.getEventManager().post(
					new EventMouseWheel.Moved(mouseWheelEvent));
			}

		});
		applet.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent keyEvent)
			{
				applet.getEventManager().post(
					new EventKey.Typed(keyEvent));
			}

			@Override
			public void keyPressed(KeyEvent keyEvent)
			{
				applet.getEventManager().post(
					new EventKey.Pressed(keyEvent));
			}

			@Override
			public void keyReleased(KeyEvent keyEvent)
			{
				applet.getEventManager().post(
					new EventKey.Released(keyEvent));
			}

		});
	}

}
