package mirrg.helium.swing.phosphorus.canvas.game.view;

import mirrg.helium.swing.phosphorus.canvas.game.EventGamePhosphorus;
import mirrg.helium.swing.phosphorus.canvas.game.GamePhosphorus;

public class ModelViewXYBase extends ModelViewBase
{

	public double x = 0;
	public double y = 0;

	@Override
	public ControllerViewXYBase getController()
	{
		return (ControllerViewXYBase) super.getController();
	}

	public abstract class ControllerViewXYBase extends ControllerViewBase
	{

		public ControllerViewXYBase(GamePhosphorus<?, ?, ?> game)
		{
			super(game);
		}

		//

		@Override
		public double getX()
		{
			return x;
		}

		@Override
		public double getY()
		{
			return y;
		}

		public void setX(double x)
		{
			game.event().post(new EventGamePhosphorus.ChangeViewStatus.Pre());
			ModelViewXYBase.this.x = x;
			game.event().post(new EventGamePhosphorus.ChangeViewStatus.Post());
		}

		public void setY(double y)
		{
			game.event().post(new EventGamePhosphorus.ChangeViewStatus.Pre());
			ModelViewXYBase.this.y = y;
			game.event().post(new EventGamePhosphorus.ChangeViewStatus.Post());
		}

		@Override
		public abstract double getZoomX();

		@Override
		public abstract double getZoomY();

	}

}
