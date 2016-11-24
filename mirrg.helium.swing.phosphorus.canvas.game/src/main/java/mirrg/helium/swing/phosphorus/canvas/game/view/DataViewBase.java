package mirrg.helium.swing.phosphorus.canvas.game.view;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

import mirrg.helium.swing.phosphorus.canvas.game.IData;

public abstract class DataViewBase implements IData<Object>
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

	@Override
	public void initialize(Object game)
	{

	}

	@Override
	public void dispose()
	{

	}

}
