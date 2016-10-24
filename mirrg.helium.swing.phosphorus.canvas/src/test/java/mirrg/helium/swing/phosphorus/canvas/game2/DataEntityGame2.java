package mirrg.helium.swing.phosphorus.canvas.game2;

import mirrg.helium.swing.phosphorus.canvas.game.existence.DataEntity;
import mirrg.helium.swing.phosphorus.canvas.game.existence.Entity;

public class DataEntityGame2 extends DataEntity<Game2>
{

	public DataEntityTile[][] tiles = new DataEntityTile[10][10];

	@Override
	protected Entity<Game2> createEntity(Game2 game)
	{
		return new EntityGame2(game);
	}

	public class EntityGame2 extends Entity<Game2>
	{

		public EntityGame2(Game2 game)
		{
			super(game);

			game.dataEntityGame2 = DataEntityGame2.this;
		}

	}

}
