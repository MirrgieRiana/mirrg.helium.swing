package mirrg.helium.swing.phosphorus.canvas.game.render;

public abstract class RectangleBase<P extends PointBase>
{

	public final double x;
	public final double y;
	public final double width;
	public final double height;

	public RectangleBase(double x, double y, double width, double height)
	{
		if (width >= 0) {
			this.x = x;
			this.width = width;
		} else {
			this.x = x + width;
			this.width = -width;
		}
		if (height >= 0) {
			this.y = y;
			this.height = height;
		} else {
			this.y = y + height;
			this.height = -height;
		}
	}

	public boolean contains(RectangleBase<P> rectangle)
	{
		if (rectangle.x > this.x + this.width) return false;
		if (rectangle.y > this.y + this.height) return false;
		if (this.x > rectangle.x + rectangle.width) return false;
		if (this.y > rectangle.y + rectangle.height) return false;
		return true;
	}

	public abstract P getCenter();

}
