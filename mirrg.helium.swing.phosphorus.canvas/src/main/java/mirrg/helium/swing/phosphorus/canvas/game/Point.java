package mirrg.helium.swing.phosphorus.canvas.game;

import java.awt.geom.Point2D;

public class Point
{

	public final double x;
	public final double y;

	public Point(double x, double y)
	{
		this.x = x;
		this.y = y;
	}

	public Point(Point2D.Double point)
	{
		this(point.x, point.y);
	}

	public Point2D.Double toShape()
	{
		return new Point2D.Double(x, y);
	}

}
