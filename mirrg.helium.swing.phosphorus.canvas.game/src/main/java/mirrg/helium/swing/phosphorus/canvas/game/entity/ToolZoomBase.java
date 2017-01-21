package mirrg.helium.swing.phosphorus.canvas.game.entity;

import mirrg.helium.swing.phosphorus.canvas.EventPhosphorusCanvas;
import mirrg.helium.swing.phosphorus.canvas.game.GamePhosphorus;
import mirrg.helium.swing.phosphorus.canvas.game.entity.ModelEntity.Entity;
import mirrg.helium.swing.phosphorus.canvas.game.render.PointScreen;
import mirrg.helium.swing.phosphorus.canvas.game.view.ModelViewXYBase;

public abstract class ToolZoomBase<VIEW extends ModelViewXYBase> extends Entity<GamePhosphorus<?, ?, VIEW>>
{

	public double deltaZoom = 1.1;
	private PointScreen point;

	public ToolZoomBase(GamePhosphorus<?, ?, VIEW> game)
	{
		super(game);

		registerEvent(EventPhosphorusCanvas.EventWheel.Moved.class, e -> {
			doZoom(e.event.getWheelRotation());
		});
		registerEvent(EventPhosphorusCanvas.EventMouseMotion.Moved.class, e -> {
			point = new PointScreen(e.event.getPoint());
		});
		registerEvent(EventPhosphorusCanvas.EventMouseMotion.Dragged.class, e -> {
			point = new PointScreen(e.event.getPoint());
		});

	}

	public void doZoom(int scrollAmount)
	{
		double rate = Math.pow(deltaZoom, scrollAmount);
		double x = game.getView().getController().getX();
		double y = game.getView().getController().getY();

		x += (point.x - game.canvas.getWidth() / 2) * game.getView().getController().getZoomX();
		y += (point.y - game.canvas.getHeight() / 2) * game.getView().getController().getZoomY();

		applyZoom(rate);

		x -= (point.x - game.canvas.getWidth() / 2) * game.getView().getController().getZoomX();
		y -= (point.y - game.canvas.getHeight() / 2) * game.getView().getController().getZoomY();

		game.getView().getController().setX(x);
		game.getView().getController().setY(y);

	}

	protected abstract void applyZoom(double rate);

}
