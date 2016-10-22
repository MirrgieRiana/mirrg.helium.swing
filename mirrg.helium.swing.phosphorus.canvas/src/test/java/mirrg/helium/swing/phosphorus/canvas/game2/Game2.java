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

	public DataEntityTile[][] tiles = new DataEntityTile[10][10];

	public Game2(PhosphorusCanvas canvas, Data<Game2> data)
	{
		super(canvas, data);

		addLayer(layerMain = createLayer());
		addLayer(layerOverlay = createLayer());

		for (int y = 0; y < tiles.length; y++) {
			for (int x = 0; x < tiles[y].length; x++) {
				DataEntityTile tile = new DataEntityTile(x, y);

				tiles[y][x] = tile;
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
