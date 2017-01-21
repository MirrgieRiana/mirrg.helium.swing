package mirrg.helium.swing.phosphorus.canvas.game.view;

import mirrg.helium.swing.phosphorus.canvas.game.EventGamePhosphorus;
import mirrg.helium.swing.phosphorus.canvas.game.GamePhosphorus;

public class ModelViewXYZoomXY extends ModelViewXYBase
{

	public double zoomX = 1;
	public double zoomY = 1;

	@Override
	public ControllerViewXYZoomXY getController()
	{
		return (ControllerViewXYZoomXY) super.getController();
	}

	@Override
	protected ControllerViewXYZoomXY createController(GamePhosphorus<?, ?, ?> game)
	{
		return new ControllerViewXYZoomXY(game);
	}

	public class ControllerViewXYZoomXY extends ControllerViewXYBase
	{

		public ControllerViewXYZoomXY(GamePhosphorus<?, ?, ?> game)
		{
			super(game);
		}

		@Override
		public double getZoomX()
		{
			return zoomX;
		}

		@Override
		public double getZoomY()
		{
			return zoomY;
		}

		public void setZoomX(double zoomX)
		{
			game.event().post(new EventGamePhosphorus.ChangeViewStatus.Pre());
			ModelViewXYZoomXY.this.zoomX = zoomX;
			game.event().post(new EventGamePhosphorus.ChangeViewStatus.Post());
		}

		public void setZoomY(double zoomY)
		{
			game.event().post(new EventGamePhosphorus.ChangeViewStatus.Pre());
			ModelViewXYZoomXY.this.zoomY = zoomY;
			game.event().post(new EventGamePhosphorus.ChangeViewStatus.Post());
		}

	}

}
