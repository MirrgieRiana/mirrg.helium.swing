package mirrg.helium.swing.phosphorus.canvas.game;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

public class DataEntity<G extends PhosphorusGame<?>>
{

	@XStreamOmitField
	private Entity<G> entity;

	public synchronized Entity<G> getEntity(G game)
	{
		if (entity == null) entity = createEntity(game);
		return entity;
	}

	protected Entity<G> createEntity(G game)
	{
		return new Entity<>(game);
	}

	public void touch(G game)
	{
		if (entity == null) entity = createEntity(game);
	}

}
