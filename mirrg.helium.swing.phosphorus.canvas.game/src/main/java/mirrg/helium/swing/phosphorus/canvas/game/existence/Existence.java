package mirrg.helium.swing.phosphorus.canvas.game.existence;

import java.util.Optional;
import java.util.function.Consumer;

import mirrg.helium.standard.hydrogen.event.EventManager;
import mirrg.helium.swing.phosphorus.canvas.EventPhosphorusCanvas;
import mirrg.helium.swing.phosphorus.canvas.game.EventPhosphorusGame;
import mirrg.helium.swing.phosphorus.canvas.game.PhosphorusGame;
import mirrg.helium.swing.phosphorus.canvas.game.render.Layer;
import mirrg.helium.swing.phosphorus.canvas.game.render.RectangleCoordinate;

public class Existence<G extends PhosphorusGame<?, ?>>
{

	protected final G game;

	protected boolean isDisposed = false;

	public Existence(G game)
	{
		this.game = game;
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

	protected void dirty(Layer layer)
	{
		Optional<RectangleCoordinate> oRectangle = getOpticalBounds(layer);
		if (oRectangle.isPresent()) {
			RectangleCoordinate rectangle = oRectangle.get();
			if (game.getView().getCoordinateRectangle().contains(rectangle)) {
				layer.dirty();
			}
		}
	}

	public Optional<RectangleCoordinate> getOpticalBounds(Layer layer)
	{
		return Optional.empty();
	}

	public void dispose()
	{
		isDisposed = true;
	}

	protected <T> void registerEvent(EventManager<? super T> eventManager, Class<T> clazz, Consumer<T> listener)
	{
		eventManager.registerRemovable(clazz, e -> {
			if (isDisposed) return false;

			listener.accept(e);

			return true;
		});
	}

	protected <T extends EventPhosphorusCanvas<?>> void registerEvent(Class<T> clazz, Consumer<T> listener)
	{
		registerEvent(game.canvas.event(), clazz, listener);
	}

	protected <T extends EventPhosphorusGame> void registerGameEvent(Class<T> clazz, Consumer<T> listener)
	{
		registerEvent(game.event(), clazz, listener);
	}

}
