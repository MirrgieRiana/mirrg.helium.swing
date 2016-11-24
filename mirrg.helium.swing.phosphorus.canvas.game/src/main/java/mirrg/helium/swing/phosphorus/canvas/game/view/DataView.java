package mirrg.helium.swing.phosphorus.canvas.game.view;

public class DataView extends DataViewBase
{

	public double zoom = 1;

	@Override
	protected IView createView(IViewContext viewContext)
	{
		return new View(this, viewContext);
	}

}
