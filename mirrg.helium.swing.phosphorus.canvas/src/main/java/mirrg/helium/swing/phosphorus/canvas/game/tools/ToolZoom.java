package mirrg.helium.swing.phosphorus.canvas.game.tools;

import mirrg.helium.swing.phosphorus.canvas.EventPhosphorusCanvas;
import mirrg.helium.swing.phosphorus.canvas.game.PhosphorusGame;
import mirrg.helium.swing.phosphorus.canvas.game.existence.Tool;
import mirrg.helium.swing.phosphorus.canvas.game.render.PointScreen;
import mirrg.helium.swing.phosphorus.canvas.game.view.IViewXY;
import mirrg.helium.swing.phosphorus.canvas.game.view.IViewZoom;
import mirrg.helium.swing.phosphorus.canvas.game.view.IViewZoomXY;

public class ToolZoom extends Tool<PhosphorusGame<?, ?>>
{

	public double deltaZoom = 1.1;
	private PointScreen point;

	public ToolZoom(PhosphorusGame<?, ?> game)
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
		double x = game.getView().getX();
		double y = game.getView().getY();

		x += (point.x - game.canvas.getWidth() / 2) * game.getView().getZoomX();
		y += (point.y - game.canvas.getHeight() / 2) * game.getView().getZoomY();

		if (game.getView() instanceof IViewZoom) {
			IViewZoom view = (IViewZoom) game.getView();

			view.setZoom(view.getZoom() * rate);
		} else if (game.getView() instanceof IViewZoomXY) {
			IViewZoomXY view = (IViewZoomXY) game.getView();

			view.setZoomX(view.getZoomX() * rate);
			view.setZoomY(view.getZoomY() * rate);
		}

		x -= (point.x - game.canvas.getWidth() / 2) * game.getView().getZoomX();
		y -= (point.y - game.canvas.getHeight() / 2) * game.getView().getZoomY();

		if (game.getView() instanceof IViewXY) {
			IViewXY view = (IViewXY) game.getView();

			view.setX(x);
			view.setY(y);
		}

	}

}
