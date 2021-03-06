package mirrg.helium.swing.phosphorus.canvas.game;

import java.util.function.Consumer;

import mirrg.helium.game.carbon.base.ControllerCarbon;
import mirrg.helium.game.carbon.base.ModelCarbon;
import mirrg.helium.swing.phosphorus.canvas.game.view.ModelViewBase;

public class ModelPhosphorus<G extends GamePhosphorus<?, ?, ?>, V extends ModelViewBase> extends ModelCarbon<G>
{

	private V view;

	public ModelPhosphorus(V view)
	{
		this.view = view;
	}

	@Override
	public void getChildModels(Consumer<ModelCarbon<? super G>> dest)
	{
		super.getChildModels(dest);
		dest.accept(view);
	}

	@Override
	protected ControllerPhosphorus createController(G game)
	{
		return new ControllerPhosphorus(game);
	}

	@Override
	public ControllerPhosphorus getController()
	{
		return (ControllerPhosphorus) super.getController();
	}

	public class ControllerPhosphorus extends ControllerCarbon<G>
	{

		public ControllerPhosphorus(G game)
		{
			super(game);
		}

		public V getView()
		{
			return view;
		}

		public void setView(V view)
		{
			ModelPhosphorus.this.view = view;
		}

	}

}
