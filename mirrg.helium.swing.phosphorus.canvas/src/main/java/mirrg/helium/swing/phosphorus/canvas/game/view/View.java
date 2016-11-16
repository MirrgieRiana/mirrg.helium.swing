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
		data.x = x;
		viewContext.onViewChange();
	}

	public double getY()
	{
		return data.y;
	}

	public void setY(double y)
	{
		data.y = y;
		viewContext.onViewChange();
	}

	public double getZoom()
	{
		return data.zoom;
	}

	public void setZoom(double zoom)
	{
		data.zoom = zoom;
		viewContext.onViewChange();
	}

	public RectangleCoordinate getRegion()
	{
		return new RectangleCoordinate(
			getCoordinateX(0),
			getCoordinateY(0),
			getCoordinateWidth(),
			getCoordinateHeight());
	}

	public double getScreenWidth()
	{
		return viewContext.getWidth();
	}

	public double getScreenHeight()
	{
		return viewContext.getHeight();
	}

	public double getCoordinateWidth()
	{
		return getScreenWidth() * getZoom();
	}

	public double getCoordinateHeight()
	{
		return getScreenHeight() * getZoom();
	}

	public double getCenterScreenX()
	{
		return getScreenWidth() / 2;
	}

	public double getCenterScreenY()
	{
		return getScreenHeight() / 2;
	}

	public double getCenterCoordinateX()
	{
		return getX();
	}

	public double getCenterCoordinateY()
	{
		return getY();
	}

	public double getCoordinateX(double screenX)
	{
		double offsetScreenX = screenX - getCenterScreenX();
		double offsetCoordinateX = offsetScreenX * getZoom();
		double coordinateX = getX() + offsetCoordinateX;
		return coordinateX;
	}

	public double getCoordinateY(double screenY)
	{
		double offsetScreenY = screenY - getCenterScreenY();
		double offsetCoordinateY = offsetScreenY * getZoom();
		double coordinateY = getY() + offsetCoordinateY;
		return coordinateY;
	}

	public double getScreenX(double coordinateX)
	{
		double offsetCoordinateX = coordinateX - getX();
		double offsetScreenX = offsetCoordinateX / getZoom();
		double screenX = getCenterScreenX() + offsetScreenX;
		return screenX;
	}

	public double getScreenY(double coordinateY)
	{
		double offsetCoordinateY = coordinateY - getY();
		double offsetScreenY = offsetCoordinateY / getZoom();
		double screenY = getCenterScreenY() + offsetScreenY;
		return screenY;
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
