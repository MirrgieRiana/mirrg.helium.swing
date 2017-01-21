package mirrg.helium.swing.phosphorus.canvas.game.view;

import mirrg.helium.swing.phosphorus.canvas.game.EventGamePhosphorus;
import mirrg.helium.swing.phosphorus.canvas.game.GamePhosphorus;

public class ModelViewXYZoom extends ModelViewXYBase
{

	public double zoom = 1;

	@Override
	public ControllerViewXYZoom getController()
	{
		return (ControllerViewXYZoom) super.getController();
	}

	@Override
	protected ControllerViewXYZoom createController(GamePhosphorus<?, ?, ?> game)
	{
		return new ControllerViewXYZoom(game);
	}

	public class ControllerViewXYZoom extends ControllerViewXYBase
	{

		public ControllerViewXYZoom(GamePhosphorus<?, ?, ?> game)
		{
			super(game);
		}

		@Override
		public double getZoomX()
		{
			return zoom;
		}

		@Override
		public double getZoomY()
		{
			return zoom;
		}

		public double getZoom()
		{
			return zoom;
		}

		public void setZoom(double zoom)
		{
			game.event().post(new EventGamePhosphorus.ChangeViewStatus.Pre());
			ModelViewXYZoom.this.zoom = zoom;
			game.event().post(new EventGamePhosphorus.ChangeViewStatus.Post());
		}

	}

}
