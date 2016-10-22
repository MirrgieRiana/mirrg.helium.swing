package mirrg.helium.swing.phosphorus.canvas.game;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

public class DataView
{

	public double x = 0;
	public double y = 0;
	public double zoom = 1;

	@XStreamOmitField
	private View view;

	public View getView(PhosphorusGame<?> game)
	{
		if (view == null) view = createView(game);
		return view;
	}

	protected View createView(PhosphorusGame<?> game)
	{
		return new View(game);
	}

}
