package mirrg.helium.swing.phosphorus.canvas.game.render;

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

public class RectangleScreen extends RectangleBase<PointScreen>
{

	public RectangleScreen(double x, double y, double width, double height)
	{
		super(x, y, width, height);
	}

	public RectangleScreen(Rectangle rectangle)
	{
		super(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
	}

	public RectangleScreen(Rectangle2D.Double rectangle)
	{
		super(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
	}

	public Rectangle2D.Double toShape()
	{
		return new Rectangle2D.Double(x, y, width, height);
	}

	@Override
	public PointScreen getCenter()
	{
		return new PointScreen(x + width / 2, y + height / 2);
	}

}
