package mirrg.helium.swing.phosphorus.canvas.game;

import java.util.function.Function;

import javax.swing.WindowConstants;

import mirrg.helium.swing.phosphorus.canvas.PhosphorusCanvas;
import mirrg.helium.swing.phosphorus.canvas.game.view.ModelViewXYZoom;
import mirrg.helium.swing.phosphorus.canvas.game2.Game2;
import mirrg.helium.swing.phosphorus.canvas.game2.ModelGame2;
import mirrg.helium.swing.phosphorus.canvas.util.FrameCanvas;
import mirrg.helium.swing.phosphorus.canvas.util.IntervalThread;

public class SampleGame2
{

	public static void main(String[] args)
	{
		doGame(c -> {
			Game2 game = new Game2(c);
			game.setModel(new ModelGame2(new ModelViewXYZoom()));
			game.getModel().getController().initTiles();
			return game;
		}, 600, 600, 60, 60);
	}

	public static void doGame(
		Function<PhosphorusCanvas, GamePhosphorus<?, ?, ?>> supplierGame,
		int width, int height,
		double fpsRender, double fpsMove)
	{
		FrameCanvas frame = new FrameCanvas(width, height);
		GamePhosphorus<?, ?, ?> game = supplierGame.apply(frame.canvas);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);

		new IntervalThread(fpsRender, () -> {
			game.render(frame.canvas.getLayer().getGraphics());
			frame.canvas.repaint();
		}).start();
		new IntervalThread(fpsMove, game::move).start();
	}

}
