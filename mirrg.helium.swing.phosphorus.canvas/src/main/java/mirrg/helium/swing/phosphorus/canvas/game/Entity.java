package mirrg.helium.swing.phosphorus.canvas.game;

public class Entity<G extends PhosphorusGame<?>> extends Existence<G>
{

	protected boolean isDisposed = false;

	public Entity(G game)
	{
		super(game);
	}

	public void dispose()
	{
		isDisposed = true;
	}

}
