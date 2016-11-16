package mirrg.helium.swing.phosphorus.canvas.game.existence;

import java.util.Optional;

import mirrg.helium.swing.phosphorus.canvas.game.PhosphorusGame;
import mirrg.helium.swing.phosphorus.canvas.game.render.Layer;
import mirrg.helium.swing.phosphorus.canvas.game.render.RectangleCoordinate;

public class Existence<G extends PhosphorusGame<?>>
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

}
