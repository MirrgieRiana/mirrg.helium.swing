package mirrg.helium.swing.phosphorus.canvas.game2;

import java.awt.Graphics2D;
import java.awt.RenderingHints;

import mirrg.helium.swing.phosphorus.canvas.PhosphorusCanvas;
import mirrg.helium.swing.phosphorus.canvas.game.Data;
import mirrg.helium.swing.phosphorus.canvas.game.Layer;
import mirrg.helium.swing.phosphorus.canvas.game.PhosphorusGame;

public class Game2 extends PhosphorusGame<Game2>
{

	public final Layer layerMain;
	public final Layer layerOverlay;

	public String xml;
	public DataEntityGame2 dataEntityGame2;

	public Game2(PhosphorusCanvas canvas, Data<Game2> data)
	{
		super(canvas, data);

		addLayer(layerMain = createLayer());
		addLayer(layerOverlay = createLayer());

		addTool(new ToolGame2(this));

		DataEntityGame2 dataEntityGame2 = new DataEntityGame2();
		addEntity(dataEntityGame2);

		for (int y = 0; y < dataEntityGame2.tiles.length; y++) {
			for (int x = 0; x < dataEntityGame2.tiles[y].length; x++) {
				DataEntityTile tile = new DataEntityTile(x, y);

				dataEntityGame2.tiles[y][x] = tile;
				addEntity(tile);
			}
		}

	}

	@Override
	public void render(Graphics2D g)
	{
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		super.render(g);
	}

}
