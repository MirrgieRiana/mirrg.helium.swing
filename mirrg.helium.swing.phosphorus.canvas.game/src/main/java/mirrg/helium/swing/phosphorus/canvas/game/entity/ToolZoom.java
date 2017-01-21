package mirrg.helium.swing.phosphorus.canvas.game.entity;

import mirrg.helium.swing.phosphorus.canvas.EventPhosphorusCanvas;
import mirrg.helium.swing.phosphorus.canvas.game.GamePhosphorus;
import mirrg.helium.swing.phosphorus.canvas.game.entity.ModelEntity.Entity;
import mirrg.helium.swing.phosphorus.canvas.game.render.PointScreen;
import mirrg.helium.swing.phosphorus.canvas.game.view.ModelView.ControllerView;
import mirrg.helium.swing.phosphorus.canvas.game.view.ModelViewDefault.ControllerViewDefault;
import mirrg.helium.swing.phosphorus.canvas.game.view.ModelViewSkewed.ControllerViewSkewed;

public class ToolZoom extends Entity<GamePhosphorus<?, ?>>
{

	public double deltaZoom = 1.1;
	private PointScreen point;

	public ToolZoom(GamePhosphorus<?, ?> game)
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
		ControllerView controller = game.getModel().getController().getView().getController();
		double rate = Math.pow(deltaZoom, scrollAmount);
		double x = controller.getX();
		double y = controller.getY();

		x += (point.x - game.canvas.getWidth() / 2) * controller.getZoomX();
		y += (point.y - game.canvas.getHeight() / 2) * controller.getZoomY();

		if (controller instanceof ControllerViewDefault) {
			ControllerViewDefault view = (ControllerViewDefault) controller;

			view.setZoom(view.getZoom() * rate);
		} else if (controller instanceof ControllerViewSkewed) {
			ControllerViewSkewed view = (ControllerViewSkewed) controller;

			view.setZoomX(view.getZoomX() * rate);
			view.setZoomY(view.getZoomY() * rate);
		}

		x -= (point.x - game.canvas.getWidth() / 2) * controller.getZoomX();
		y -= (point.y - game.canvas.getHeight() / 2) * controller.getZoomY();

		controller.setX(x);
		controller.setY(y);

	}

}
