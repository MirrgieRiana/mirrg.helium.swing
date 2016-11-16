package mirrg.helium.swing.phosphorus.canvas.game.view;

import mirrg.helium.swing.phosphorus.canvas.game.render.PointCoordinate;
import mirrg.helium.swing.phosphorus.canvas.game.render.PointScreen;
import mirrg.helium.swing.phosphorus.canvas.game.render.RectangleCoordinate;
import mirrg.helium.swing.phosphorus.canvas.game.render.RectangleScreen;

public class View
{

	private final DataView data;
	private final IViewContext viewContext;

	public View(DataView data, IViewContext viewContext)
	{
		this.data = data;
		this.viewContext = viewContext;
	}

	public double getX()
	{
		return data.x;
	}

	public void setX(double x)
	{
		viewContext.onViewChangePre();
		data.x = x;
		viewContext.onViewChangePost();
	}

	public double getY()
	{
		return data.y;
	}

	public void setY(double y)
	{
		viewContext.onViewChangePre();
		data.y = y;
		viewContext.onViewChangePost();
	}

	public double getZoom()
	{
		return data.zoom;
	}

	public void setZoom(double zoom)
	{
		viewContext.onViewChangePre();
		data.zoom = zoom;
		viewContext.onViewChangePost();
	}

	public RectangleScreen getScreenRectangle()
	{
		return new RectangleScreen(
			getScreenX(0),
			getScreenY(0),
			getScreenWidth(),
			getScreenHeight());
	}

	public PointScreen getScreenCenter()
	{
		return new PointScreen(getScreenCenterX(), getScreenCenterY());
	}

	public PointScreen getScreenTopLeft()
	{
		return new PointScreen(0, 0);
	}

	public PointScreen getScreenBottomRight()
	{
		return new PointScreen(getScreenWidth(), getScreenHeight());
	}

	public double getScreenWidth()
	{
		return viewContext.getWidth();
	}

	public double getScreenHeight()
	{
		return viewContext.getHeight();
	}

	public double getScreenCenterX()
	{
		return getScreenWidth() / 2;
	}

	public double getScreenCenterY()
	{
		return getScreenHeight() / 2;
	}

	public double getScreenX(double coordinateX)
	{
		double offsetCoordinateX = coordinateX - getX();
		double offsetScreenX = offsetCoordinateX / getZoom();
		double screenX = getScreenCenterX() + offsetScreenX;
		return screenX;
	}

	public double getScreenY(double coordinateY)
	{
		double offsetCoordinateY = coordinateY - getY();
		double offsetScreenY = offsetCoordinateY / getZoom();
		double screenY = getScreenCenterY() + offsetScreenY;
		return screenY;
	}

	public RectangleCoordinate getCoordinateRectangle()
	{
		return new RectangleCoordinate(
			getCoordinateX(0),
			getCoordinateY(0),
			getCoordinateWidth(),
			getCoordinateHeight());
	}

	public PointCoordinate getCoordinateCenter()
	{
		return new PointCoordinate(getCoordinateCenterX(), getCoordinateCenterY());
	}

	public PointCoordinate getCoordinateTopLeft()
	{
		return new PointCoordinate(getCoordinateX(0), getCoordinateY(0));
	}

	public PointCoordinate getCoordinateBottomRight()
	{
		return new PointCoordinate(getCoordinateX(getScreenWidth()), getCoordinateY(getScreenHeight()));
	}

	public double getCoordinateWidth()
	{
		return getScreenWidth() * getZoom();
	}

	public double getCoordinateHeight()
	{
		return getScreenHeight() * getZoom();
	}

	public double getCoordinateCenterX()
	{
		return getX();
	}

	public double getCoordinateCenterY()
	{
		return getY();
	}

	public double getCoordinateX(double screenX)
	{
		double offsetScreenX = screenX - getScreenCenterX();
		double offsetCoordinateX = offsetScreenX * getZoom();
		double coordinateX = getX() + offsetCoordinateX;
		return coordinateX;
	}

	public double getCoordinateY(double screenY)
	{
		double offsetScreenY = screenY - getScreenCenterY();
		double offsetCoordinateY = offsetScreenY * getZoom();
		double coordinateY = getY() + offsetCoordinateY;
		return coordinateY;
	}

	public RectangleCoordinate convert(RectangleScreen rectangle)
	{
		return new RectangleCoordinate(
			getCoordinateX(rectangle.x),
			getCoordinateY(rectangle.y),
			rectangle.width * getZoom(),
			rectangle.height * getZoom());
	}

	public RectangleScreen convert(RectangleCoordinate rectangle)
	{
		return new RectangleScreen(
			getScreenX(rectangle.x),
			getScreenY(rectangle.y),
			rectangle.width / getZoom(),
			rectangle.height / getZoom());
	}

	public PointCoordinate convert(PointScreen point)
	{
		return new PointCoordinate(
			getCoordinateX(point.x),
			getCoordinateY(point.y));
	}

	public PointScreen convert(PointCoordinate point)
	{
		return new PointScreen(
			getScreenX(point.x),
			getScreenY(point.y));
	}

}
