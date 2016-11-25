package mirrg.helium.swing.phosphorus.canvas.game2;

import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.stream.Stream;

import mirrg.helium.game.carbon.base.ModelCarbon;
import mirrg.helium.swing.phosphorus.canvas.game.ModelPhosphorus;
import mirrg.helium.swing.phosphorus.canvas.game.entity.ModelLiving;
import mirrg.helium.swing.phosphorus.canvas.game.view.ModelViewDefault;

public class ModelGame2 extends ModelPhosphorus<Game2, ModelViewDefault>
{

	private ArrayList<ModelLiving<? super Game2>> entities = new ArrayList<>();
	public ModelEntityTile[][] tiles = new ModelEntityTile[10][10];

	public ModelGame2(ModelViewDefault view)
	{
		super(view);
	}

	public void addEntity(ModelLiving<? super Game2> entity)
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

	public Stream<ModelLiving<? super Game2>> getEntities()
	{
		return entities.stream();
	}

}
