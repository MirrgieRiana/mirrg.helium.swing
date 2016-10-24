package mirrg.helium.swing.phosphorus.canvas.game.render;

public class RectangleCoordinate extends RectangleBase<PointCoordinate>
{

	public RectangleCoordinate(double x, double y, double width, double height)
	{
		super(x, y, width, height);
	}

	@Override
	public PointCoordinate getCenter()
	{
		return new PointCoordinate(x + width / 2, y + height / 2);
	}

}
