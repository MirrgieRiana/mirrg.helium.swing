package mirrg.helium.swing.phosphorus.canvas.game.existence;

import java.util.function.Consumer;

import mirrg.helium.standard.hydrogen.event.EventManager;
import mirrg.helium.swing.phosphorus.canvas.EventPhosphorusCanvas;
import mirrg.helium.swing.phosphorus.canvas.game.EventPhosphorusGame;
import mirrg.helium.swing.phosphorus.canvas.game.PhosphorusGame;

public class Tool<G extends PhosphorusGame<?>> extends Existence<G>
{

	public Tool(G game)
	{
		super(game);
	}

	protected <T> void registerEvent(EventManager<? super T> eventManager, Class<T> clazz, Consumer<T> listener)
	{
		eventManager.registerRemovable(clazz, e -> {
			if (isDisposed) return false;

			listener.accept(e);

			return true;
		});
	}

	protected <T extends EventPhosphorusCanvas<?>> void registerEvent(Class<T> clazz, Consumer<T> listener)
	{
		registerEvent(game.canvas.event(), clazz, listener);
	}

	protected <T extends EventPhosphorusGame> void registerGameEvent(Class<T> clazz, Consumer<T> listener)
	{
		registerEvent(game.event(), clazz, listener);
	}

}
