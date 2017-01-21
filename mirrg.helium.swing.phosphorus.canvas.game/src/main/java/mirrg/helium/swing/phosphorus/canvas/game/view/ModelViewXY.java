package mirrg.helium.swing.phosphorus.canvas.game.view;

import mirrg.helium.swing.phosphorus.canvas.game.GamePhosphorus;

public class ModelViewXY extends ModelViewXYBase
{

	@Override
	public ControllerViewXY getController()
	{
		return (ControllerViewXY) super.getController();
	}

	@Override
	protected ControllerViewXY createController(GamePhosphorus<?, ?, ?> game)
	{
		return new ControllerViewXY(game);
	}

	public class ControllerViewXY extends ControllerViewXYBase
	{

		public ControllerViewXY(GamePhosphorus<?, ?, ?> game)
		{
			super(game);
		}

		//

		@Override
		public double getZoomX()
		{
			return 1;
		}

		@Override
		public double getZoomY()
		{
			return 1;
		}

	}

}
