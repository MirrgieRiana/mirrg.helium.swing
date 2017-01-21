package mirrg.helium.swing.phosphorus.canvas.game.entity;

import mirrg.helium.swing.phosphorus.canvas.EventPhosphorusCanvas;
import mirrg.helium.swing.phosphorus.canvas.game.GamePhosphorus;
import mirrg.helium.swing.phosphorus.canvas.game.entity.ModelEntity.Entity;
import mirrg.helium.swing.phosphorus.canvas.game.render.PointScreen;
import mirrg.helium.swing.phosphorus.canvas.game.view.ModelViewXYBase;

public class ToolScroll extends Entity<GamePhosphorus<?, ?, ?>>
{

	private PointScreen point;

	public ToolScroll(GamePhosphorus<?, ?, ? extends ModelViewXYBase> game, int button)
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
				game.getView().getController().setX(game.getView().getController().getX() - game.getView().getController().getZoomX() * (e.event.getPoint().x - point.x));
				game.getView().getController().setY(game.getView().getController().getY() - game.getView().getController().getZoomY() * (e.event.getPoint().y - point.y));
				point = new PointScreen(e.event.getPoint());
			}
		});

	}

}
