package mirrg.helium.swing.phosphorus.canvas.game.view;

public abstract class ViewBase<T extends DataViewBase> implements IView, IViewXY
{

	protected final T data;
	protected final IViewContext viewContext;

	public ViewBase(T data, IViewContext viewContext)
	{
		this.data = data;
		this.viewContext = viewContext;
	}

	@Override
	public double getX()
	{
		return data.x;
	}

	@Override
	public void setX(double x)
	{
		viewContext.onViewChangePre();
		data.x = x;
		viewContext.onViewChangePost();
	}

	@Override
	public double getY()
	{
		return data.y;
	}

	@Override
	public void setY(double y)
	{
		viewContext.onViewChangePre();
		data.y = y;
		viewContext.onViewChangePost();
	}

	@Override
	public double getWidth()
	{
		return viewContext.getWidth();
	}

	@Override
	public double getHeight()
	{
		return viewContext.getHeight();
	}

}
