package mirrg.helium.swing.phosphorus.canvas.game.existence;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

import mirrg.helium.swing.phosphorus.canvas.game.IData;
import mirrg.helium.swing.phosphorus.canvas.game.PhosphorusGame;

public class DataEntity<G extends PhosphorusGame<?>> implements IData<G>
{

	@XStreamOmitField
	private Entity<G> entity;

	@Override
	public void initialize(G game)
	{
		entity = createEntity(game);
	}

	@Override
	public void dispose()
	{
		entity.dispose();
	}

	public Entity<G> getEntity()
	{
		return entity;
	}

	protected Entity<G> createEntity(G game)
	{
		return new Entity<>(game);
	}

}
