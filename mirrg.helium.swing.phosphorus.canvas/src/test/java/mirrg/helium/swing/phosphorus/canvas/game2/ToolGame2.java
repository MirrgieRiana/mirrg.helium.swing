package mirrg.helium.swing.phosphorus.canvas.game2;

import java.awt.event.KeyEvent;

import com.thoughtworks.xstream.XStream;

import mirrg.helium.swing.phosphorus.canvas.EventPhosphorusCanvas;
import mirrg.helium.swing.phosphorus.canvas.game.Data;
import mirrg.helium.swing.phosphorus.canvas.game.EventPhosphorusGame;
import mirrg.helium.swing.phosphorus.canvas.game.existence.Tool;

public class ToolGame2 extends Tool<Game2>
{

	@SuppressWarnings("unchecked")
	public ToolGame2(Game2 game)
	{
		super(game);

		// セーブロード
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

	}

	private XStream getXStream()
	{
		XStream xStream = new XStream();
		xStream.autodetectAnnotations(true);
		return xStream;
	}

}
