package mirrg.helium.swing.phosphorus.canvas.game.view;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

public class DataView
{

	public double x = 0;
	public double y = 0;
	public double zoom = 1;

	@XStreamOmitField
	private View view;

	public View getView(IViewContext viewContext)
	{
		if (view == null) view = createView(viewContext);
		return view;
	}

	protected View createView(IViewContext viewContext)
	{
		return new View(this, viewContext);
	}

}
