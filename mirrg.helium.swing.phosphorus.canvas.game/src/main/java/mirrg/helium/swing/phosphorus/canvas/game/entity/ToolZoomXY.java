package mirrg.helium.swing.phosphorus.canvas.game.entity;

import mirrg.helium.swing.phosphorus.canvas.game.GamePhosphorus;
import mirrg.helium.swing.phosphorus.canvas.game.view.ModelViewXYZoomXY;
import mirrg.helium.swing.phosphorus.canvas.game.view.ModelViewXYZoomXY.ControllerViewXYZoomXY;

public class ToolZoomXY extends ToolZoomBase<ModelViewXYZoomXY>
{

	public ToolZoomXY(GamePhosphorus<?, ?, ModelViewXYZoomXY> game)
	{
		super(game);
	}

	@Override
	protected void applyZoom(double rate)
	{
		ControllerViewXYZoomXY view = (ControllerViewXYZoomXY) game.getView().getController();
		view.setZoomX(view.getZoomX() * rate);
		view.setZoomY(view.getZoomY() * rate);
	}

}
