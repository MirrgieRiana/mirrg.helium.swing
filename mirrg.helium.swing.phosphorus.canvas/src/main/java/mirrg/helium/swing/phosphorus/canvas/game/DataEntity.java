package mirrg.helium.swing.phosphorus.canvas.game;

import java.util.Optional;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

public class DataEntity<G extends PhosphorusGame<?>>
{

	@XStreamOmitField
	private Entity entity;

	public synchronized Entity getEntity(G game)
	{
		if (entity == null) entity = createEntity(game);
		return entity;
	}

	protected Entity createEntity(G game)
	{
		return new Entity(game);
	}

	public class Entity
	{

		protected final G game;
		protected boolean isDisposed = false;

		public Entity(G game)
		{
			this.game = game;
		}

		public void move()
		{

		}

		public void dispose()
		{
			isDisposed = true;
		}

		public double getZOrder()
		{
			return 0;
		}

		public void render(Layer layer)
		{

		}

		protected void dirty(Layer layer)
		{
			Optional<Rectangle> oRectangle = getOpticalBounds(layer);
			if (oRectangle.isPresent()) {
				Rectangle rectangle = oRectangle.get();
				if (game.getView().getRegion().contains(rectangle)) {
					layer.dirty();
				}
			}
		}

		public Optional<Rectangle> getOpticalBounds(Layer layer)
		{
			return Optional.empty();
		}

	}

}
