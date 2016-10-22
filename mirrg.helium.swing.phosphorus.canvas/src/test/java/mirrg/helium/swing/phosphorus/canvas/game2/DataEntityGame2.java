package mirrg.helium.swing.phosphorus.canvas.game2;

import java.awt.event.KeyEvent;

import com.thoughtworks.xstream.XStream;

import mirrg.helium.swing.phosphorus.canvas.EventPhosphorusCanvas;
import mirrg.helium.swing.phosphorus.canvas.game.Data;
import mirrg.helium.swing.phosphorus.canvas.game.DataEntity;

public class DataEntityGame2 extends DataEntity<Game2>
{

	@Override
	protected Entity createEntity(Game2 game)
	{
		return new EntityTileGame2(game);
	}

	public class EntityTileGame2 extends Entity
	{

		private String xml;

		public EntityTileGame2(Game2 game)
		{
			super(game);
			game.canvas.event().registerRemovable(EventPhosphorusCanvas.EventKey.Released.class, e -> {
				if (isDisposed) return false;

				if (e.event.getKeyCode() == KeyEvent.VK_F3) {
					xml = getXStream().toXML(game.getData());
					System.out.println(xml);
				} else if (e.event.getKeyCode() == KeyEvent.VK_F4) {
					if (xml != null) {
						game.setData((Data<Game2>) getXStream().fromXML(xml));
					}
				}

				return true;
			});
		}

		private XStream getXStream()
		{
			XStream xStream = new XStream();
			xStream.autodetectAnnotations(true);
			return xStream;
		}

	}

}
