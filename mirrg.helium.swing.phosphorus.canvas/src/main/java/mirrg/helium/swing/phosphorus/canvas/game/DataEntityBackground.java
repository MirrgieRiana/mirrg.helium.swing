package mirrg.helium.swing.phosphorus.canvas.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Optional;

public class DataEntityBackground extends DataEntity<PhosphorusGame<?>>
{

	@Override
	public Entity createEntity(PhosphorusGame<?> game)
	{
		return new EntityBackground(game);
	}

	public class EntityBackground extends Entity
	{

		public EntityBackground(PhosphorusGame<?> game)
		{
			super(game);
		}

		@Override
		public double getZOrder()
		{
			return -10000;
		}

		@Override
		public void render(Layer layer)
		{
			super.render(layer);

			Graphics2D graphics = layer.getImageLayer().getGraphics();
			if (layer == game.layerBack) {
				graphics.setBackground(Color.white);
				graphics.clearRect(0, 0, game.getCanvas().getWidth(), game.getCanvas().getHeight());
			}
		}

		@Override
		public Optional<Rectangle> getOpticalBounds(Layer layer)
		{
			if (layer == game.layerBack) return Optional.of(game.getView().getRegion());
			return super.getOpticalBounds(layer);
		}

	}

}
