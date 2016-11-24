package mirrg.helium.swing.phosphorus.canvas;

import java.util.function.Function;

import javax.swing.WindowConstants;

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
		Function<PhosphorusCanvas, Game1> supplierGame,
		int width, int height,
		double fpsRender, double fpsMove)
	{
		FrameCanvas frame = new FrameCanvas(width, height);
		Game1 game = supplierGame.apply(frame.canvas);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);

		new IntervalThread(fpsRender, () -> {
			game.render(frame.canvas.getLayer().getGraphics());
			frame.canvas.repaint();
		}).start();
		new IntervalThread(fpsMove, game::move).start();
	}

}
