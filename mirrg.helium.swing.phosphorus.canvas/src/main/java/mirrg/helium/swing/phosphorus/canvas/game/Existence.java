package mirrg.helium.swing.phosphorus.canvas.game;

import java.util.Optional;

public class Existence<G extends PhosphorusGame<?>>
{

	protected final G game;

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
