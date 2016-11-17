package mirrg.helium.swing.phosphorus.canvas.game.view;

public class ViewSkewed extends ViewBase<DataViewSkewed> implements IViewZoomXY
{

	public ViewSkewed(DataViewSkewed data, IViewContext viewContext)
	{
		super(data, viewContext);
	}

	@Override
	public double getZoomX()
	{
		return data.zoomX;
	}

	@Override
	public void setZoomX(double zoomX)
	{
		viewContext.onViewChangePre();
		data.zoomX = zoomX;
		viewContext.onViewChangePost();
	}

	@Override
	public double getZoomY()
	{
		return data.zoomY;
	}

	@Override
	public void setZoomY(double zoomY)
	{
		viewContext.onViewChangePre();
		data.zoomY = zoomY;
		viewContext.onViewChangePost();
	}

}
