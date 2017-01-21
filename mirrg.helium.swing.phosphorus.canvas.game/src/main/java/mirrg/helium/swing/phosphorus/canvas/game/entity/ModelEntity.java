package mirrg.helium.swing.phosphorus.canvas.game.entity;

import java.util.Optional;
import java.util.function.Consumer;

import mirrg.helium.game.carbon.base.ControllerCarbon;
import mirrg.helium.game.carbon.base.ModelCarbon;
import mirrg.helium.swing.phosphorus.canvas.EventPhosphorusCanvas;
import mirrg.helium.swing.phosphorus.canvas.game.GamePhosphorus;
import mirrg.helium.swing.phosphorus.canvas.game.render.Layer;
import mirrg.helium.swing.phosphorus.canvas.game.render.RectangleCoordinate;

public class ModelEntity<G extends GamePhosphorus<?, ?, ?>> extends ModelCarbon<G>
{

	@Override
	public Entity<G> getController()
	{
		return (Entity<G>) super.getController();
	}

	public static class Entity<G extends GamePhosphorus<?, ?, ?>> extends ControllerCarbon<G>
	{

		public Entity(G game)
		{
			super(game);
		}

		protected <T extends EventPhosphorusCanvas<?>> void registerEvent(Class<T> clazz, Consumer<T> listener)
		{
			registerEvent(game.canvas.event(), clazz, listener);
		}

		public void move()
		{

		}

		public double getZOrder()
		{
			return 0;
		}

		public void render(Layer layer)
		{

		}

		public void dirty()
		{
			game.getLayers()
				.forEach(this::dirty);
		}

		public void dirty(Layer layer)
		{
			Optional<RectangleCoordinate> oRectangle = getOpticalBounds(layer);
			if (oRectangle.isPresent()) {
				RectangleCoordinate rectangle = oRectangle.get();
				if (game.getView().getController().getCoordinateRectangle().contains(rectangle)) {
					layer.dirty();
				}
			}
		}

		public Optional<RectangleCoordinate> getOpticalBounds(Layer layer)
		{
			return Optional.empty();
		}

	}

}
