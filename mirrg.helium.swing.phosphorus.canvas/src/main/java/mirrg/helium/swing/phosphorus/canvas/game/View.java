package mirrg.helium.swing.phosphorus.canvas.game;

public class View
{

	private final PhosphorusGame<?> game;

	public View(PhosphorusGame<?> game)
	{
		this.game = game;
	}

	private DataView getViewData()
	{
		return game.getData().view;
	}

	public double getX()
	{
		return getViewData().x;
	}

	public void setX(double x)
	{
		game.onViewChange();
		getViewData().x = x;
	}

	public double getY()
	{
		return getViewData().y;
	}

	public void setY(double y)
	{
		game.onViewChange();
		getViewData().y = y;
	}

	public double getZoom()
	{
		return getViewData().zoom;
	}

	public void setZoom(double zoom)
	{
		game.onViewChange();
		getViewData().zoom = zoom;
	}

	public Rectangle getRegion()
	{
		return new Rectangle(
			getCoordinateX(0),
			getCoordinateY(0),
			getCoordinateWidth(),
			getCoordinateHeight());
	}

	public double getScreenWidth()
	{
		return game.getCanvas().getWidth();
	}

	public double getScreenHeight()
	{
		return game.getCanvas().getHeight();
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

	public Rectangle getCoordinateRectangle(Rectangle rectangle)
	{
		return new Rectangle(
			getCoordinateX(rectangle.x),
			getCoordinateY(rectangle.y),
			rectangle.width * getZoom(),
			rectangle.height * getZoom());
	}

	public Rectangle getScreenRectangle(Rectangle rectangle)
	{
		return new Rectangle(
			getScreenX(rectangle.x),
			getScreenY(rectangle.y),
			rectangle.width / getZoom(),
			rectangle.height / getZoom());
	}

}
