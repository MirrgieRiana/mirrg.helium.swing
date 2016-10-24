package mirrg.helium.swing.phosphorus.canvas.game;

import java.util.ArrayList;

import mirrg.helium.swing.phosphorus.canvas.game.existence.DataEntity;
import mirrg.helium.swing.phosphorus.canvas.game.view.DataView;

public class Data<G extends PhosphorusGame<?>>
{

	public ArrayList<DataEntity<? super G>> entities = new ArrayList<>();
	public DataView view = new DataView();

	public void dispose(G game)
	{
		entities.forEach(e -> e.getEntity(game).dispose());
	}

}
