package mirrg.helium.swing.phosphorus.canvas.game;

import java.awt.geom.Rectangle2D;

public class Rectangle
{

	public final double x;
	public final double y;
	public final double width;
	public final double height;

	public Rectangle(double x, double y, double width, double height)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public Rectangle(Rectangle2D.Double rectangle)
	{
		this(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
	}

	public Rectangle2D.Double toShape()
	{
		return new Rectangle2D.Double(x, y, width, height);
	}

	public boolean contains(Rectangle rectangle)
	{
		if (rectangle.x > this.x + this.width) return false;
		if (rectangle.y > this.y + this.height) return false;
		if (this.x > rectangle.x + rectangle.width) return false;
		if (this.y > rectangle.y + rectangle.height) return false;
		return true;
	}

	public Point getCenter()
	{
		return new Point(x + width / 2, y + height / 2);
	}

}
