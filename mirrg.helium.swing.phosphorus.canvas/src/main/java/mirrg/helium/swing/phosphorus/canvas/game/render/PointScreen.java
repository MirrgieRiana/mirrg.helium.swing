package mirrg.helium.swing.phosphorus.canvas.game.render;

import java.awt.Point;
import java.awt.geom.Point2D;

public class PointScreen extends PointBase
{

	public PointScreen(double x, double y)
	{
		super(x, y);
	}

	public PointScreen(Point2D.Double point)
	{
		super(point.x, point.y);
	}

	public PointScreen(Point point)
	{
		super(point.x, point.y);
	}

	public Point2D.Double toShape()
	{
		return new Point2D.Double(x, y);
	}

}
