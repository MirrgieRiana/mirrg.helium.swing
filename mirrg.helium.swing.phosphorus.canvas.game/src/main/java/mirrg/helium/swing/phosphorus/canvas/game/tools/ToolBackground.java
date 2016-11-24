package mirrg.helium.swing.phosphorus.canvas.game.tools;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Optional;

import mirrg.helium.swing.phosphorus.canvas.game.PhosphorusGame;
import mirrg.helium.swing.phosphorus.canvas.game.existence.Tool;
import mirrg.helium.swing.phosphorus.canvas.game.render.Layer;
import mirrg.helium.swing.phosphorus.canvas.game.render.RectangleCoordinate;

public class ToolBackground extends Tool<PhosphorusGame<?, ?>>
{

	public Color color = Color.white;

	public ToolBackground(PhosphorusGame<?, ?> game)
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
			graphics.setBackground(color);
			graphics.clearRect(0, 0, game.canvas.getWidth(), game.canvas.getHeight());
		}
	}

	@Override
	public Optional<RectangleCoordinate> getOpticalBounds(Layer layer)
	{
		if (layer == game.layerBack) return Optional.of(game.getView().getCoordinateRectangle());
		return super.getOpticalBounds(layer);
	}

}
