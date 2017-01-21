package mirrg.helium.swing.phosphorus.canvas.game2;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.Optional;

import mirrg.helium.swing.phosphorus.canvas.game.entity.ModelEntity;
import mirrg.helium.swing.phosphorus.canvas.game.render.Layer;
import mirrg.helium.swing.phosphorus.canvas.game.render.RectangleCoordinate;
import mirrg.helium.swing.phosphorus.canvas.game.view.ModelViewDefault.ControllerViewDefault;

public class ModelEntityWay extends ModelEntity<Game2>
{

	public ModelEntityPlace begin;
	public ModelEntityPlace end;

	public ModelEntityWay(Game2 game, ModelEntityPlace begin, ModelEntityPlace end)
	{
		this.begin = begin;
		this.end = end;
		initialize(game);
	}

	@Override
	protected Entity<Game2> createController(Game2 game)
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

				ControllerViewDefault controller = game.getModel().getController().getView().getController();
				graphics.draw(new Line2D.Double(
					controller.getScreenX(begin.point.x),
					controller.getScreenY(begin.point.y),
					controller.getScreenX(end.point.x),
					controller.getScreenY(end.point.y)));

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
