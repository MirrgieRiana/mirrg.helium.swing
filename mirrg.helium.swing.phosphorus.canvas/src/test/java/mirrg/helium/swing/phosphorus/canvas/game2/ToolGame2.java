package mirrg.helium.swing.phosphorus.canvas.game2;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import com.thoughtworks.xstream.XStream;

import mirrg.helium.swing.phosphorus.canvas.EventPhosphorusCanvas;
import mirrg.helium.swing.phosphorus.canvas.game.Data;
import mirrg.helium.swing.phosphorus.canvas.game.EventPhosphorusGame;
import mirrg.helium.swing.phosphorus.canvas.game.existence.Tool;
import mirrg.helium.swing.phosphorus.canvas.game.render.PointCoordinate;
import mirrg.helium.swing.phosphorus.canvas.game.render.PointScreen;

public class ToolGame2 extends Tool<Game2>
{

	private PointScreen point;

	@SuppressWarnings("unchecked")
	public ToolGame2(Game2 game)
	{
		super(game);

		registerEvent(EventPhosphorusCanvas.EventKey.Released.class, e -> {
			if (e.event.getKeyCode() == KeyEvent.VK_F3) {
				game.xml = getXStream().toXML(game.getData());
				System.out.println(game.xml);
			} else if (e.event.getKeyCode() == KeyEvent.VK_F4) {
				if (game.xml != null) {
					game.event().registerRemovable(EventPhosphorusGame.Move.Post.class, e2 -> {
						game.setData((Data<Game2>) getXStream().fromXML(game.xml));
						return false;
					});
				}
			}
		});

		registerEvent(EventPhosphorusCanvas.EventMouse.Pressed.class, e -> {
			if (e.event.getButton() == MouseEvent.BUTTON1) {
				PointCoordinate point = game.getView().convert(new PointScreen(e.event.getPoint()));
				game.event().registerRemovable(EventPhosphorusGame.Move.Post.class, e2 -> {
					game.addEntity(new DataEntityPlace(point));
					return false;
				});
			}
		});

		registerEvent(EventPhosphorusCanvas.EventWheel.Moved.class, e -> {
			game.getView().setZoom(game.getView().getZoom() * Math.pow(1.1, e.event.getWheelRotation()));
		});

		registerEvent(EventPhosphorusCanvas.EventMouse.Pressed.class, e -> {
			if (e.event.getButton() == MouseEvent.BUTTON2) {
				point = new PointScreen(e.event.getPoint());
			}
		});
		registerEvent(EventPhosphorusCanvas.EventMouse.Released.class, e -> {
			if (e.event.getButton() == MouseEvent.BUTTON2) {
				point = null;
			}
		});
		registerEvent(EventPhosphorusCanvas.EventMouseMotion.Dragged.class, e -> {
			if (point != null) {
				game.getView().setX(game.getView().getX() - game.getView().getZoom() * (e.event.getPoint().x - point.x));
				game.getView().setY(game.getView().getY() - game.getView().getZoom() * (e.event.getPoint().y - point.y));
				point = new PointScreen(e.event.getPoint());
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
