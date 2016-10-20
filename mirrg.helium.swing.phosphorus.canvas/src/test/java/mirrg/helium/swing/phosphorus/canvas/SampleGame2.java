package mirrg.helium.swing.phosphorus.canvas;

import mirrg.helium.swing.phosphorus.canvas.game2.Game2;
import mirrg.helium.swing.phosphorus.canvas.util.FrameCanvas;
import mirrg.helium.swing.phosphorus.canvas.util.GameAbstract;
import mirrg.helium.swing.phosphorus.canvas.util.IntervalThread;

public class SampleGame2
{

	public static void main(String[] args)
	{
		FrameCanvas frame = new FrameCanvas(600, 600);
		GameAbstract game = new Game2(frame.canvas);
		frame.setVisible(true);

		new IntervalThread(60, () -> {
			game.render(frame.canvas.graphics);
			frame.canvas.repaint();
		}).start();
		new IntervalThread(60, game::move).start();
	}

}
