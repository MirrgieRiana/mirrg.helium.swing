package mirrg.helium.swing.phosphorus.canvas;

import mirrg.helium.swing.phosphorus.canvas.game.Data;
import mirrg.helium.swing.phosphorus.canvas.game2.Game2;

public class SampleGame2
{

	public static void main(String[] args)
	{
		SampleGame1.doGame(c -> new Game2(c, new Data<>()), 600, 600, 60, 60);
	}

}
