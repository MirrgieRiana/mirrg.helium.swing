package mirrg.helium.swing.phosphorus.canvas.game.view;

import mirrg.helium.swing.phosphorus.canvas.game.GamePhosphorus;

public class ModelViewFixed extends ModelViewBase
{

	@Override
	public ControllerViewFixed getController()
	{
		return (ControllerViewFixed) super.getController();
	}

	@Override
	protected ControllerViewFixed createController(GamePhosphorus<?, ?, ?> game)
	{
		return new ControllerViewFixed(game);
	}

	public class ControllerViewFixed extends ControllerViewBase
	{

		public ControllerViewFixed(GamePhosphorus<?, ?, ?> game)
		{
			super(game);
		}

		//

		@Override
		public double getX()
		{
			return getScreenWidth() / 2;
		}

		@Override
		public double getY()
		{
			return getScreenHeight() / 2;
		}

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
