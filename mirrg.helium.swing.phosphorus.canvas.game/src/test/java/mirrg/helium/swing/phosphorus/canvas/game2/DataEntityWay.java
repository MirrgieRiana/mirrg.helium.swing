package mirrg.helium.swing.phosphorus.canvas.game2;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.Optional;

import mirrg.helium.swing.phosphorus.canvas.game.existence.DataEntity;
import mirrg.helium.swing.phosphorus.canvas.game.existence.Entity;
import mirrg.helium.swing.phosphorus.canvas.game.render.Layer;
import mirrg.helium.swing.phosphorus.canvas.game.render.RectangleCoordinate;

public class DataEntityWay extends DataEntity<Game2>
{

	public DataEntityPlace begin;
	public DataEntityPlace end;

	public DataEntityWay(DataEntityPlace begin, DataEntityPlace end)
	{
		this.begin = begin;
		this.end = end;
	}

	@Override
	protected Entity<Game2> createEntity(Game2 game)
	{
		return new EntityWay(game);
	}

	public class EntityWay extends Entity<Game2>
	{

		public EntityWay(Game2 game)
		{
			super(game);
		}

		@Override
		public void move()
		{

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

				graphics.draw(new Line2D.Double(
					game.getView().getScreenX(begin.point.x),
					game.getView().getScreenY(begin.point.y),
					game.getView().getScreenX(end.point.x),
					game.getView().getScreenY(end.point.y)));

			}
		}

		@Override
		public Optional<RectangleCoordinate> getOpticalBounds(Layer layer)
		{
			if (layer == game.layerBack) return Optional.of(new RectangleCoordinate(
				Math.min(begin.point.x, end.point.x),
				Math.min(begin.point.y, end.point.y),
				Math.abs(begin.point.x - end.point.x),
				Math.abs(begin.point.y - end.point.y)));
			return super.getOpticalBounds(layer);
		}

	}

}
