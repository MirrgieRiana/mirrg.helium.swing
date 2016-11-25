package mirrg.helium.swing.phosphorus.canvas.game.view;

import mirrg.helium.swing.phosphorus.canvas.game.GamePhosphorus;

public class ModelViewSkewed extends ModelView
{

	public double zoomX = 1;
	public double zoomY = 1;

	@Override
	protected ControllerViewSkewed createController(GamePhosphorus<?, ?, ?> game)
	{
		return new ControllerViewSkewed(game);
	}

	@Override
	public ControllerViewSkewed getController()
	{
		return (ControllerViewSkewed) super.getController();
	}

	public class ControllerViewSkewed extends ControllerView
	{

		public ControllerViewSkewed(GamePhosphorus<?, ?, ?> game)
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
			event().post(new EventView.ChangeView.Pre());
			ModelViewSkewed.this.zoomX = zoomX;
			event().post(new EventView.ChangeView.Post());
		}

		public void setZoomY(double zoomY)
		{
			event().post(new EventView.ChangeView.Pre());
			ModelViewSkewed.this.zoomY = zoomY;
			event().post(new EventView.ChangeView.Post());
		}

	}

}
