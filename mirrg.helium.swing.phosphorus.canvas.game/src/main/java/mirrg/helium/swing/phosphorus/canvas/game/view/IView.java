package mirrg.helium.swing.phosphorus.canvas.game.view;

import mirrg.helium.swing.phosphorus.canvas.game.render.PointCoordinate;
import mirrg.helium.swing.phosphorus.canvas.game.render.PointScreen;
import mirrg.helium.swing.phosphorus.canvas.game.render.RectangleCoordinate;
import mirrg.helium.swing.phosphorus.canvas.game.render.RectangleScreen;

public interface IView
{

	public double getWidth();

	public double getHeight();

	public double getX();

	public double getY();

	public double getZoomX();

	public double getZoomY();

	public default RectangleScreen getScreenRectangle()
	{
		return new RectangleScreen(
			getScreenX(0),
			getScreenY(0),
			getScreenWidth(),
			getScreenHeight());
	}

	public default PointScreen getScreenCenter()
	{
		return new PointScreen(getScreenCenterX(), getScreenCenterY());
	}

	public default PointScreen getScreenTopLeft()
	{
		return new PointScreen(0, 0);
	}

	public default PointScreen getScreenBottomRight()
	{
		return new PointScreen(getScreenWidth(), getScreenHeight());
	}

	public default double getScreenWidth()
	{
		return getWidth();
	}

	public default double getScreenHeight()
	{
		return getHeight();
	}

	public default double getScreenCenterX()
	{
		return getScreenWidth() / 2;
	}

	public default double getScreenCenterY()
	{
		return getScreenHeight() / 2;
	}

	public default double getScreenX(double coordinateX)
	{
		double offsetCoordinateX = coordinateX - getX();
		double offsetScreenX = offsetCoordinateX / getZoomX();
		double screenX = getScreenCenterX() + offsetScreenX;
		return screenX;
	}

	public default double getScreenY(double coordinateY)
	{
		double offsetCoordinateY = coordinateY - getY();
		double offsetScreenY = offsetCoordinateY / getZoomY();
		double screenY = getScreenCenterY() + offsetScreenY;
		return screenY;
	}

	public default RectangleCoordinate getCoordinateRectangle()
	{
		return new RectangleCoordinate(
			getCoordinateX(0),
			getCoordinateY(0),
			getCoordinateWidth(),
			getCoordinateHeight());
	}

	public default PointCoordinate getCoordinateCenter()
	{
		return new PointCoordinate(getCoordinateCenterX(), getCoordinateCenterY());
	}

	public default PointCoordinate getCoordinateTopLeft()
	{
		return new PointCoordinate(getCoordinateX(0), getCoordinateY(0));
	}

	public default PointCoordinate getCoordinateBottomRight()
	{
		return new PointCoordinate(getCoordinateX(getScreenWidth()), getCoordinateY(getScreenHeight()));
	}

	public default double getCoordinateWidth()
	{
		return getScreenWidth() * getZoomX();
	}

	public default double getCoordinateHeight()
	{
		return getScreenHeight() * getZoomY();
	}

	public default double getCoordinateCenterX()
	{
		return getX();
	}

	public default double getCoordinateCenterY()
	{
		return getY();
	}

	public default double getCoordinateX(double screenX)
	{
		double offsetScreenX = screenX - getScreenCenterX();
		double offsetCoordinateX = offsetScreenX * getZoomX();
		double coordinateX = getX() + offsetCoordinateX;
		return coordinateX;
	}

	public default double getCoordinateY(double screenY)
	{
		double offsetScreenY = screenY - getScreenCenterY();
		double offsetCoordinateY = offsetScreenY * getZoomY();
		double coordinateY = getY() + offsetCoordinateY;
		return coordinateY;
	}

	public default RectangleCoordinate convert(RectangleScreen rectangle)
	{
		return new RectangleCoordinate(
			getCoordinateX(rectangle.x),
			getCoordinateY(rectangle.y),
			rectangle.width * getZoomX(),
			rectangle.height * getZoomY());
	}

	public default RectangleScreen convert(RectangleCoordinate rectangle)
	{
		return new RectangleScreen(
			getScreenX(rectangle.x),
			getScreenY(rectangle.y),
			rectangle.width / getZoomX(),
			rectangle.height / getZoomY());
	}

	public default PointCoordinate convert(PointScreen point)
	{
		return new PointCoordinate(
			getCoordinateX(point.x),
			getCoordinateY(point.y));
	}

	public default PointScreen convert(PointCoordinate point)
	{
		return new PointScreen(
			getScreenX(point.x),
			getScreenY(point.y));
	}

}
