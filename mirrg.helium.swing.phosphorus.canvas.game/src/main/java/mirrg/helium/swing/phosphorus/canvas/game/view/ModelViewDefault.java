package mirrg.helium.swing.phosphorus.canvas.game.view;

import mirrg.helium.swing.phosphorus.canvas.game.EventGamePhosphorus;
import mirrg.helium.swing.phosphorus.canvas.game.GamePhosphorus;

public class ModelViewDefault extends ModelView
{

	public double zoom = 1;

	@Override
	protected ControllerViewDefault createController(GamePhosphorus<?, ?, ?> game)
	{
		return new ControllerViewDefault(game);
	}

	@Override
	public ControllerViewDefault getController()
	{
		return (ControllerViewDefault) super.getController();
	}

	public class ControllerViewDefault extends ControllerView
	{

		public ControllerViewDefault(GamePhosphorus<?, ?, ?> game)
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
			ModelViewDefault.this.zoom = zoom;
			game.event().post(new EventGamePhosphorus.ChangeViewStatus.Post());
		}

	}

}
