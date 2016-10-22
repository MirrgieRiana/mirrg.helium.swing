package mirrg.helium.swing.phosphorus.canvas.game;

public class DataView
{

	public double x = 0;
	public double y = 0;
	public double zoom = 1;

	public View createView(PhosphorusGame<?> game)
	{
		return new View(game);
	}

}
