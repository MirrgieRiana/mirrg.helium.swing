package mirrg.helium.swing.phosphorus.canvas.game2;

import mirrg.helium.swing.phosphorus.canvas.EventPhosphorusCanvas;
import mirrg.helium.swing.phosphorus.canvas.game.EventPhosphorusGame;
import mirrg.helium.swing.phosphorus.canvas.game.existence.Tool;
import mirrg.helium.swing.phosphorus.canvas.game.render.PointCoordinate;
import mirrg.helium.swing.phosphorus.canvas.game.render.PointScreen;

public class ToolPutBlock extends Tool<Game2>
{

	public ToolPutBlock(Game2 game, int button)
	{
		super(game);

		registerEvent(EventPhosphorusCanvas.EventMouse.Pressed.class, e -> {
			if (e.event.getButton() == button) {
				PointCoordinate point = game.getView().convert(new PointScreen(e.event.getPoint()));

				game.event().registerRemovable(EventPhosphorusGame.Move.Post.class, e2 -> {
					game.addEntity(new DataEntityPlace(point));
					return false;
				});

			}
		});

	}

}
