package mirrg.helium.swing.phosphorus.canvas.game2;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import com.thoughtworks.xstream.XStream;

import mirrg.helium.swing.phosphorus.canvas.EventPhosphorusCanvas;
import mirrg.helium.swing.phosphorus.canvas.game.Data;
import mirrg.helium.swing.phosphorus.canvas.game.Tool;

public class ToolGame2 extends Tool<Game2>
{

	private Point point;

	@SuppressWarnings("unchecked")
	public ToolGame2(Game2 game)
	{
		super(game);
		game.canvas.event().register(EventPhosphorusCanvas.EventKey.Released.class, e -> {
			if (e.event.getKeyCode() == KeyEvent.VK_F3) {
				game.xml = getXStream().toXML(game.getData());
				System.out.println(game.xml);
			} else if (e.event.getKeyCode() == KeyEvent.VK_F4) {
				if (game.xml != null) {
					game.doLater(() -> {
						game.setData((Data<Game2>) getXStream().fromXML(game.xml));
					});
				}
			}
		});
		game.canvas.event().register(EventPhosphorusCanvas.EventWheel.Moved.class, e -> {
			game.getView().setZoom(game.getView().getZoom() * Math.pow(1.1, e.event.getWheelRotation()));
		});
		game.canvas.event().register(EventPhosphorusCanvas.EventMouse.Pressed.class, e -> {
			if (e.event.getButton() == MouseEvent.BUTTON2) {
				point = e.event.getPoint();
			}
		});
		game.canvas.event().register(EventPhosphorusCanvas.EventMouse.Released.class, e -> {
			if (e.event.getButton() == MouseEvent.BUTTON2) {
				point = null;
			}
		});
		game.canvas.event().register(EventPhosphorusCanvas.EventMouseMotion.Dragged.class, e -> {
			if (point != null) {
				game.getView().setX(game.getView().getX() - game.getView().getZoom() * (e.event.getPoint().x - point.x));
				game.getView().setY(game.getView().getY() - game.getView().getZoom() * (e.event.getPoint().y - point.y));
				point = e.event.getPoint();
			}
		});

	}

	private XStream getXStream()
	{
		XStream xStream = new XStream();
		xStream.autodetectAnnotations(true);
		return xStream;
	}

}
