package mirrg.helium.swing.phosphorus.canvas.game.view;

import mirrg.helium.game.carbon.base.ControllerCarbon;
import mirrg.helium.game.carbon.base.ModelCarbon;
import mirrg.helium.swing.phosphorus.canvas.game.GamePhosphorus;
import mirrg.helium.swing.phosphorus.canvas.game.render.PointCoordinate;
import mirrg.helium.swing.phosphorus.canvas.game.render.PointScreen;
import mirrg.helium.swing.phosphorus.canvas.game.render.RectangleCoordinate;
import mirrg.helium.swing.phosphorus.canvas.game.render.RectangleScreen;

public abstract class ModelViewBase extends ModelCarbon<GamePhosphorus<?, ?, ?>>
{

	@Override
	public ControllerViewBase getController()
	{
		return (ControllerViewBase) super.getController();
	}

	public abstract class ControllerViewBase extends ControllerCarbon<GamePhosphorus<?, ?, ?>>
	{

		public ControllerViewBase(GamePhosphorus<?, ?, ?> game)
		{
			super(game);
		}

		//

		public double getWidth()
		{
			return game.canvas.getWidth();
		}

		public double getHeight()
		{
			return game.canvas.getHeight();
		}

		public abstract double getX();

		public abstract double getY();

		public abstract double getZoomX();

		public abstract double getZoomY();

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
			return getWidth();
		}

		public double getScreenHeight()
		{
			return getHeight();
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
			double offsetScreenX = offsetCoordinateX / getZoomX();
			double screenX = getScreenCenterX() + offsetScreenX;
			return screenX;
		}

		public double getScreenY(double coordinateY)
		{
			double offsetCoordinateY = coordinateY - getY();
			double offsetScreenY = offsetCoordinateY / getZoomY();
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
			return getScreenWidth() * getZoomX();
		}

		public double getCoordinateHeight()
		{
			return getScreenHeight() * getZoomY();
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
			double offsetCoordinateX = offsetScreenX * getZoomX();
			double coordinateX = getX() + offsetCoordinateX;
			return coordinateX;
		}

		public double getCoordinateY(double screenY)
		{
			double offsetScreenY = screenY - getScreenCenterY();
			double offsetCoordinateY = offsetScreenY * getZoomY();
			double coordinateY = getY() + offsetCoordinateY;
			return coordinateY;
		}

		public RectangleCoordinate convert(RectangleScreen rectangle)
		{
			return new RectangleCoordinate(
				getCoordinateX(rectangle.x),
				getCoordinateY(rectangle.y),
				rectangle.width * getZoomX(),
				rectangle.height * getZoomY());
		}

		public RectangleScreen convert(RectangleCoordinate rectangle)
		{
			return new RectangleScreen(
				getScreenX(rectangle.x),
				getScreenY(rectangle.y),
				rectangle.width / getZoomX(),
				rectangle.height / getZoomY());
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

}
