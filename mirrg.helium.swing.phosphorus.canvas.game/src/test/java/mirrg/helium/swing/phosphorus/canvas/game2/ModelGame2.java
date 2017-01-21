package mirrg.helium.swing.phosphorus.canvas.game2;

import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.stream.Stream;

import mirrg.helium.game.carbon.base.ModelCarbon;
import mirrg.helium.swing.phosphorus.canvas.game.ModelPhosphorus;
import mirrg.helium.swing.phosphorus.canvas.game.entity.ModelEntity;
import mirrg.helium.swing.phosphorus.canvas.game.view.ModelViewXYZoom;

public class ModelGame2 extends ModelPhosphorus<Game2, ModelViewXYZoom>
{

	private ArrayList<ModelEntity<? super Game2>> entities = new ArrayList<>();
	public ModelEntityTile[][] tiles = new ModelEntityTile[10][10];

	public ModelGame2(ModelViewXYZoom view)
	{
		super(view);
	}

	public void addEntity(ModelEntity<? super Game2> entity)
	{
		entities.add(entity);
		entity.getController().dirty();
	}

	@Override
	public void getChildModels(Consumer<ModelCarbon<? super Game2>> dest)
	{
		super.getChildModels(dest);
		entities.forEach(e -> dest.accept(e));
	}

	public Stream<ModelEntity<? super Game2>> getEntities()
	{
		return entities.stream();
	}

	@Override
	protected ControllerGame2 createController(Game2 game)
	{
		return new ControllerGame2(game);
	}

	@Override
	public ControllerGame2 getController()
	{
		return (ControllerGame2) super.getController();
	}

	public class ControllerGame2 extends ControllerPhosphorus
	{

		public ControllerGame2(Game2 game)
		{
			super(game);
		}

		public void initTiles()
		{
			for (int y = 0; y < tiles.length; y++) {
				for (int x = 0; x < tiles[y].length; x++) {
					ModelEntityTile tile = new ModelEntityTile(game, x, y);

					tiles[y][x] = tile;
					addEntity(tile);
				}
			}
		}

	}

}
