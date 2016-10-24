package mirrg.helium.swing.phosphorus.canvas.game.existence;

import java.util.function.Consumer;

import mirrg.helium.swing.phosphorus.canvas.EventPhosphorusCanvas;
import mirrg.helium.swing.phosphorus.canvas.game.PhosphorusGame;

public class Tool<G extends PhosphorusGame<?>> extends Existence<G>
{

	public Tool(G game)
	{
		super(game);
	}

	protected <T extends EventPhosphorusCanvas<?>> void registerEvent(Class<T> clazz, Consumer<T> listener)
	{
		game.canvas.event().registerRemovable(clazz, e -> {
			if (isDisposed) return false;

			listener.accept(e);

			return true;
		});
	}

}
