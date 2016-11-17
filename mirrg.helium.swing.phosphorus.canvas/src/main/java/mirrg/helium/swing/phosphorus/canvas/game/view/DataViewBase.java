package mirrg.helium.swing.phosphorus.canvas.game.view;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

public abstract class DataViewBase
{

	public double x = 0;
	public double y = 0;

	@XStreamOmitField
	private IView view;

	public IView getView(IViewContext viewContext)
	{
		if (view == null) view = createView(viewContext);
		return view;
	}

	protected abstract IView createView(IViewContext viewContext);

}
