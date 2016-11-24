package mirrg.helium.swing.phosphorus.canvas.game.view;

public class View extends ViewBase<DataView> implements IViewZoom
{

	public View(DataView data, IViewContext viewContext)
	{
		super(data, viewContext);
	}

	@Override
	public double getZoom()
	{
		return data.zoom;
	}

	@Override
	public void setZoom(double zoom)
	{
		viewContext.onViewChangePre();
		data.zoom = zoom;
		viewContext.onViewChangePost();
	}

	@Override
	public double getZoomX()
	{
		return data.zoom;
	}

	@Override
	public double getZoomY()
	{
		return data.zoom;
	}

}
