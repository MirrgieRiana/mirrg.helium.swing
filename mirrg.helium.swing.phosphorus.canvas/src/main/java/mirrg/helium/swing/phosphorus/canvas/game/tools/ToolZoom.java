package mirrg.helium.swing.phosphorus.canvas.game.tools;

import mirrg.helium.swing.phosphorus.canvas.EventPhosphorusCanvas;
import mirrg.helium.swing.phosphorus.canvas.game.PhosphorusGame;
import mirrg.helium.swing.phosphorus.canvas.game.existence.Tool;
import mirrg.helium.swing.phosphorus.canvas.game.render.PointScreen;

public class ToolZoom extends Tool<PhosphorusGame<?>>
{

	public double deltaZoom = 1.1;
	private PointScreen point;

	public ToolZoom(PhosphorusGame<?> game)
	{
		super(game);

		registerEvent(EventPhosphorusCanvas.EventWheel.Moved.class, e -> {
			doZoom(e.event.getWheelRotation());
		});
		registerEvent(EventPhosphorusCanvas.EventMouseMotion.Moved.class, e -> {
			point = new PointScreen(e.event.getPoint());
		});
		registerEvent(EventPhosphorusCanvas.EventMouseMotion.Dragged.class, e -> {
			point = new PointScreen(e.event.getPoint());
		});

	}

	public void doZoom(int scrollAmount)
	{
		double rate = Math.pow(deltaZoom, scrollAmount);
		double x = game.getView().getCenterCoordinateX();
		double y = game.getView().getCenterCoordinateY();

		x += (point.x - game.canvas.getWidth() / 2) * game.getView().getZoom();
		y += (point.y - game.canvas.getHeight() / 2) * game.getView().getZoom();

		game.getView().setZoom(game.getView().getZoom() * rate);

		x -= (point.x - game.canvas.getWidth() / 2) * game.getView().getZoom();
		y -= (point.y - game.canvas.getHeight() / 2) * game.getView().getZoom();

		game.getView().setX(x);
		game.getView().setY(y);
	}

}
