package mirrg.helium.swing.phosphorus.canvas.game2;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.Optional;

import mirrg.helium.swing.phosphorus.canvas.game.entity.ModelEntity;
import mirrg.helium.swing.phosphorus.canvas.game.render.Layer;
import mirrg.helium.swing.phosphorus.canvas.game.render.PointScreen;
import mirrg.helium.swing.phosphorus.canvas.game.render.RectangleCoordinate;

public class ModelEntityTile extends ModelEntity<Game2>
{

	public int x;
	public int y;
	public double i;

	public ModelEntityTile(Game2 game, int x, int y)
	{
		this.x = x;
		this.y = y;
		initialize(game);
	}

	@Override
	protected Entity<Game2> createController(Game2 game)
	{
		return new EntityTile(game);
	}

	public class EntityTile extends Entity<Game2>
	{

		public EntityTile(Game2 game)
		{
			super(game);
		}

		@Override
		public void move()
		{
			i += Math.random() * 0.01;
			i -= (int) i;

			dirty(game.layerMain);
		}

		@Override
		public double getZOrder()
		{
			return 1;
		}

		@Override
		public void render(Layer layer)
		{
			super.render(layer);

			Graphics2D graphics = layer.getImageLayer().getGraphics();
			if (layer == game.layerBack) {
				graphics.setColor(Color.black);

				graphics.fill(game.getView().getController().convert(getOpticalBounds(layer).get()).toShape());

			} else if (layer == game.layerMain) {
				graphics.setColor(Color.white);
				graphics.setFont(graphics.getFont().deriveFont((float) (20 / game.getView().getController().getZoomX())));

				PointScreen point = game.getView().getController().convert(getOpticalBounds(layer).get()).getCenter();
				String string = "" + (int) (i * 99);
				Rectangle2D bounds = graphics.getFontMetrics().getStringBounds(string, graphics);
				graphics.drawString(string,
					(float) (point.x - bounds.getWidth() / 2),
					(float) (point.y + bounds.getHeight() / 2));

			}
		}

		@Override
		public Optional<RectangleCoordinate> getOpticalBounds(Layer layer)
		{
			if (layer == game.layerBack) return Optional.of(new RectangleCoordinate(x * 32, y * 32, 30, 30));
			if (layer == game.layerMain) return Optional.of(new RectangleCoordinate(x * 32, y * 32, 30, 30));
			return super.getOpticalBounds(layer);
		}

	}

}
