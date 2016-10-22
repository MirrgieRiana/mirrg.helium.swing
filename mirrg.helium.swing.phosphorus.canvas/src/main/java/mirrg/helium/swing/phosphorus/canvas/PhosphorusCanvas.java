package mirrg.helium.swing.phosphorus.canvas;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import mirrg.helium.standard.hydrogen.event.EventManager;

public class PhosphorusCanvas extends JPanel
{

	private ImageLayer layer;
	private EventManager<EventPhosphorusCanvas<?>> eventManager = new EventManager<>();

	public PhosphorusCanvas()
	{
		layer = createImageLayer(BufferedImage.TYPE_INT_RGB);

		addComponentListener(new ComponentListener() {

			@Override
			public void componentResized(ComponentEvent e)
			{
				eventManager.post(new EventPhosphorusCanvas.EventComponent.Resized(e));
			}

			@Override
			public void componentMoved(ComponentEvent e)
			{
				eventManager.post(new EventPhosphorusCanvas.EventComponent.Moved(e));
			}

			@Override
			public void componentShown(ComponentEvent e)
			{
				eventManager.post(new EventPhosphorusCanvas.EventComponent.Shown(e));
			}

			@Override
			public void componentHidden(ComponentEvent e)
			{
				eventManager.post(new EventPhosphorusCanvas.EventComponent.Hidden(e));
			}

		});
		addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e)
			{
				eventManager.post(new EventPhosphorusCanvas.EventMouse.Clicked(e));
			}

			@Override
			public void mousePressed(MouseEvent e)
			{
				eventManager.post(new EventPhosphorusCanvas.EventMouse.Pressed(e));
			}

			@Override
			public void mouseReleased(MouseEvent e)
			{
				eventManager.post(new EventPhosphorusCanvas.EventMouse.Released(e));
			}

			@Override
			public void mouseEntered(MouseEvent e)
			{
				eventManager.post(new EventPhosphorusCanvas.EventMouse.Entered(e));
			}

			@Override
			public void mouseExited(MouseEvent e)
			{
				eventManager.post(new EventPhosphorusCanvas.EventMouse.Exited(e));
			}

		});
		addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseDragged(MouseEvent e)
			{
				eventManager.post(new EventPhosphorusCanvas.EventMouseMotion.Dragged(e));
			}

			@Override
			public void mouseMoved(MouseEvent e)
			{
				eventManager.post(new EventPhosphorusCanvas.EventMouseMotion.Moved(e));
			}

		});
		addMouseWheelListener(new MouseWheelListener() {

			@Override
			public void mouseWheelMoved(MouseWheelEvent e)
			{
				eventManager.post(new EventPhosphorusCanvas.EventWheel.Moved(e));
			}

		});
		addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e)
			{
				eventManager.post(new EventPhosphorusCanvas.EventKey.Pressed(e));
			}

			@Override
			public void keyReleased(KeyEvent e)
			{
				eventManager.post(new EventPhosphorusCanvas.EventKey.Released(e));
			}

			@Override
			public void keyTyped(KeyEvent e)
			{
				eventManager.post(new EventPhosphorusCanvas.EventKey.Typed(e));
			}

		});
	}

	public ImageLayer createImageLayer(int type)
	{
		return new ImageLayer(this, type, Math.max(getWidth(), 1), Math.max(getHeight(), 1));
	}

	@Override
	public void paint(Graphics g)
	{
		layer.paint((Graphics2D) g);
	}

	public ImageLayer getLayer()
	{
		return layer;
	}

	public EventManager<EventPhosphorusCanvas<?>> event()
	{
		return eventManager;
	}

}
