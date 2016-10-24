package mirrg.helium.swing.phosphorus.canvas.game2;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.Optional;

import mirrg.helium.swing.phosphorus.canvas.game.existence.DataEntity;
import mirrg.helium.swing.phosphorus.canvas.game.existence.Entity;
import mirrg.helium.swing.phosphorus.canvas.game.render.Layer;
import mirrg.helium.swing.phosphorus.canvas.game.render.PointCoordinate;
import mirrg.helium.swing.phosphorus.canvas.game.render.RectangleCoordinate;
import mirrg.helium.swing.phosphorus.canvas.game.render.RectangleScreen;

public class DataEntityPlace extends DataEntity<Game2>
{

	public PointCoordinate point;

	public DataEntityPlace(PointCoordinate point)
	{
		this.point = point;
	}

	@Override
	protected Entity<Game2> createEntity(Game2 game)
	{
		return new EntityPlace(game);
	}

	public class EntityPlace extends Entity<Game2>
	{

		public EntityPlace(Game2 game)
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

		protected double getRadius()
		{
			return 20;
		}

		@Override
		public void render(Layer layer)
		{
			super.render(layer);

			Graphics2D graphics = layer.getImageLayer().getGraphics();
			if (layer == game.layerBack) {
				graphics.setColor(Color.black);

				RectangleScreen rectangle = game.getView().convert(getOpticalBounds(layer).get());
				graphics.draw(new Ellipse2D.Double(rectangle.x, rectangle.y, rectangle.width, rectangle.height));

			}
		}

		@Override
		public Optional<RectangleCoordinate> getOpticalBounds(Layer layer)
		{
			if (layer == game.layerBack) return Optional.of(new RectangleCoordinate(
				point.x - getRadius(),
				point.y - getRadius(),
				getRadius() * 2,
				getRadius() * 2));
			return super.getOpticalBounds(layer);
		}

	}

}
