package mirrg.helium.swing.phosphorus.canvas.game;

import java.util.ArrayList;

import mirrg.helium.swing.phosphorus.canvas.game.existence.DataEntity;
import mirrg.helium.swing.phosphorus.canvas.game.view.DataView;
import mirrg.helium.swing.phosphorus.canvas.game.view.DataViewBase;

public class Data<G extends PhosphorusGame<?, ?>> implements IData<G>
{

	public ArrayList<DataEntity<? super G>> entities = new ArrayList<>();
	public DataViewBase view = new DataView();

	@Override
	public void initialize(G game)
	{
		entities.forEach(e -> e.initialize(game));
		view.initialize(game);
	}

	@Override
	public void dispose()
	{
		entities.forEach(e -> e.getEntity().dispose());
		view.dispose();
	}

}
