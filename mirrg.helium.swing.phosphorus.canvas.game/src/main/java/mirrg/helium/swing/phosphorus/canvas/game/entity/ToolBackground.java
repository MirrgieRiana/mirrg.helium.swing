package mirrg.helium.swing.phosphorus.canvas.game.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Optional;

import mirrg.helium.swing.phosphorus.canvas.game.GamePhosphorus;
import mirrg.helium.swing.phosphorus.canvas.game.entity.ModelLiving.Entity;
import mirrg.helium.swing.phosphorus.canvas.game.render.Layer;
import mirrg.helium.swing.phosphorus.canvas.game.render.RectangleCoordinate;

public class ToolBackground extends Entity<GamePhosphorus<?, ?, ?>>
{

	public Color color = Color.white;

	public ToolBackground(GamePhosphorus<?, ?, ?> game)
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
		if (layer == game.layerBack) return Optional.of(game.getView().getController().getCoordinateRectangle());
		return super.getOpticalBounds(layer);
	}

}
