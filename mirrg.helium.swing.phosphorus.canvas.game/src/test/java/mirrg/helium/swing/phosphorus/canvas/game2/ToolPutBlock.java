package mirrg.helium.swing.phosphorus.canvas.game2;

import mirrg.helium.swing.phosphorus.canvas.EventPhosphorusCanvas;
import mirrg.helium.swing.phosphorus.canvas.game.EventGamePhosphorus;
import mirrg.helium.swing.phosphorus.canvas.game.entity.ModelEntity.Entity;
import mirrg.helium.swing.phosphorus.canvas.game.render.PointCoordinate;
import mirrg.helium.swing.phosphorus.canvas.game.render.PointScreen;
import mirrg.helium.swing.phosphorus.canvas.game.view.ModelViewDefault.ControllerViewDefault;

public class ToolPutBlock extends Entity<Game2>
{

	public ToolPutBlock(Game2 game, int button)
	{
		super(game);

		registerEvent(EventPhosphorusCanvas.EventMouse.Pressed.class, e -> {
			if (e.event.getButton() == button) {
				ControllerViewDefault controller = game.getModel().getController().getView().getController();
				PointCoordinate point = controller.convert(new PointScreen(e.event.getPoint()));

				game.event().registerRemovable(EventGamePhosphorus.Move.Post.class, e2 -> {
					game.getModel().addEntity(new ModelEntityPlace(game, point));
					return false;
				});

			}
		});

	}

}
