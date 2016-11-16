package mirrg.helium.swing.phosphorus.canvas.game.tools;

import mirrg.helium.swing.phosphorus.canvas.EventPhosphorusCanvas;
import mirrg.helium.swing.phosphorus.canvas.game.PhosphorusGame;
import mirrg.helium.swing.phosphorus.canvas.game.existence.Tool;
import mirrg.helium.swing.phosphorus.canvas.game.render.PointScreen;

public class ToolScroll extends Tool<PhosphorusGame<?>>
{

	private PointScreen point;

	public ToolScroll(PhosphorusGame<?> game, int button)
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
				game.getView().setX(game.getView().getX() - game.getView().getZoom() * (e.event.getPoint().x - point.x));
				game.getView().setY(game.getView().getY() - game.getView().getZoom() * (e.event.getPoint().y - point.y));
				point = new PointScreen(e.event.getPoint());
			}
		});

	}

}
