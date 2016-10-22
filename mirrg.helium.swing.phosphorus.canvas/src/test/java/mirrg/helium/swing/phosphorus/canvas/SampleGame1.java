package mirrg.helium.swing.phosphorus.canvas;

import java.util.function.Function;

import mirrg.helium.swing.phosphorus.canvas.game.IGame;
import mirrg.helium.swing.phosphorus.canvas.game1.Game1;
import mirrg.helium.swing.phosphorus.canvas.util.FrameCanvas;
import mirrg.helium.swing.phosphorus.canvas.util.IntervalThread;

public class SampleGame1
{

	public static void main(String[] args)
	{
		doGame(c -> new Game1(c, 100), 600, 600, 60, 60);
	}

	public static void doGame(
		Function<PhosphorusCanvas, IGame> supplierGame,
		int width, int height,
		double fpsRender, double fpsMove)
	{
		FrameCanvas frame = new FrameCanvas(width, height);
		IGame iGame = supplierGame.apply(frame.canvas);
		frame.setVisible(true);

		new IntervalThread(fpsRender, () -> {
			iGame.render(frame.canvas.getLayer().getGraphics());
			frame.canvas.repaint();
		}).start();
		new IntervalThread(fpsMove, iGame::move).start();
	}

}
