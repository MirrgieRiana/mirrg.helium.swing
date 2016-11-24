package mirrg.helium.swing.phosphorus.canvas.game.existence;

import mirrg.helium.swing.phosphorus.canvas.game.PhosphorusGame;

public class Entity<G extends PhosphorusGame<?, ?>> extends Existence<G>
{

	public Entity(G game)
	{
		super(game);
	}

}
