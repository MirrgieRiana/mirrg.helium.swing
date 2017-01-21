package mirrg.helium.swing.phosphorus.canvas.game.entity;

import mirrg.helium.swing.phosphorus.canvas.EventPhosphorusCanvas;
import mirrg.helium.swing.phosphorus.canvas.game.GamePhosphorus;
import mirrg.helium.swing.phosphorus.canvas.game.entity.ModelEntity.Entity;
import mirrg.helium.swing.phosphorus.canvas.game.render.PointScreen;
import mirrg.helium.swing.phosphorus.canvas.game.view.ModelView.ControllerView;

public class ToolScroll extends Entity<GamePhosphorus<?, ?>>
{

	private PointScreen point;

	public ToolScroll(GamePhosphorus<?, ?> game, int button)
	{
		super(game);

		registerEvent(EventPhosphorusCanvas.EventMouse.Pressed.class, e -> {
			if (e.event.getButton() == button) {
				point = new PointScreen(e.event.getPoint());
			}
		});
		registerEvent(EventPhosphorusCanvas.EventMouse.Released.class, e -> {
			if (e.event.getButton() == button) {
				point = null;
			}
		});
		registerEvent(EventPhosphorusCanvas.EventMouseMotion.Dragged.class, e -> {
			if (point != null) {
				ControllerView controller = game.getModel().getController().getView().getController();
				controller.setX(controller.getX() - controller.getZoomX() * (e.event.getPoint().x - point.x));
				controller.setY(controller.getY() - controller.getZoomY() * (e.event.getPoint().y - point.y));
				point = new PointScreen(e.event.getPoint());
			}
		});

	}

}
