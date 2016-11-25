package mirrg.helium.swing.phosphorus.canvas.game2;

import java.awt.event.KeyEvent;

import com.thoughtworks.xstream.XStream;

import mirrg.helium.swing.phosphorus.canvas.EventPhosphorusCanvas;
import mirrg.helium.swing.phosphorus.canvas.game.EventGamePhosphorus;
import mirrg.helium.swing.phosphorus.canvas.game.entity.ModelLiving.Entity;

public class ToolGame2 extends Entity<Game2>
{

	public ToolGame2(Game2 game)
	{
		super(game);

		// セーブロード
		registerEvent(EventPhosphorusCanvas.EventKey.Released.class, e -> {
			if (e.event.getKeyCode() == KeyEvent.VK_F3) {
				game.xml = getXStream().toXML(game.getModel());
				System.out.println(game.xml);
			} else if (e.event.getKeyCode() == KeyEvent.VK_F4) {
				if (game.xml != null) {
					game.event().registerRemovable(EventGamePhosphorus.Move.Post.class, e2 -> {
						game.setModel((ModelGame2) getXStream().fromXML(game.xml));
						return false;
					});
				}
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
