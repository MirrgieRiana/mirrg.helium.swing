package mirrg.helium.swing.phosphorus.canvas.game.view;

public class DataViewSkewed extends DataViewBase
{

	public double zoomX = 1;
	public double zoomY = 1;

	@Override
	protected IView createView(IViewContext viewContext)
	{
		return new ViewSkewed(this, viewContext);
	}

}
