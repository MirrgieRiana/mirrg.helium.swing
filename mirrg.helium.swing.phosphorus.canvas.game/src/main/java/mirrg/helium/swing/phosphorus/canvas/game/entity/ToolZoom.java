package mirrg.helium.swing.phosphorus.canvas.game.entity;

import mirrg.helium.swing.phosphorus.canvas.game.GamePhosphorus;
import mirrg.helium.swing.phosphorus.canvas.game.view.ModelViewXYZoom;
import mirrg.helium.swing.phosphorus.canvas.game.view.ModelViewXYZoom.ControllerViewXYZoom;

public class ToolZoom extends ToolZoomBase<ModelViewXYZoom>
{

	public ToolZoom(GamePhosphorus<?, ?, ModelViewXYZoom> game)
	{
		super(game);
	}

	@Override
	protected void applyZoom(double rate)
	{
		ControllerViewXYZoom view = (ControllerViewXYZoom) game.getView().getController();
		view.setZoom(view.getZoom() * rate);
	}

}
