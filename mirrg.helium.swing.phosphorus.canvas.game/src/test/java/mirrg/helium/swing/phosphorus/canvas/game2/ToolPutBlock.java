package mirrg.helium.swing.phosphorus.canvas.game2;

import mirrg.helium.swing.phosphorus.canvas.EventPhosphorusCanvas;
import mirrg.helium.swing.phosphorus.canvas.game.EventGamePhosphorus;
import mirrg.helium.swing.phosphorus.canvas.game.entity.ModelLiving.Entity;
import mirrg.helium.swing.phosphorus.canvas.game.render.PointCoordinate;
import mirrg.helium.swing.phosphorus.canvas.game.render.PointScreen;

public class ToolPutBlock extends Entity<Game2>
{

	public ToolPutBlock(Game2 game, int button)
	{
		super(game);

		registerEvent(EventPhosphorusCanvas.EventMouse.Pressed.class, e -> {
			if (e.event.getButton() == button) {
				PointCoordinate point = game.getView().getController().convert(new PointScreen(e.event.getPoint()));

				game.event().registerRemovable(EventGamePhosphorus.Move.Post.class, e2 -> {
					game.getModel().addEntity(new ModelEntityPlace(game, point));
					return false;
				});

			}
		});

	}

}
