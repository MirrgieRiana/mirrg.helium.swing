package mirrg.helium.swing.nitrogen.applet;

import java.awt.Graphics2D;

import mirrg.helium.swing.nitrogen.applet.modules.threading.EventGameThread;
import mirrg.helium.swing.nitrogen.applet.modules.threading.ModuleGameThread;

public class SampleAppletNitrogenEvent2 extends AppletNitrogen
{

	private static final long serialVersionUID = 6370904732290917883L;

	private Game1 game;

	public SampleAppletNitrogenEvent2()
	{
		ModuleGameThread moduleGameThread = new ModuleGameThread(this);
		moduleGameThread.objectiveFPS = 20;

		getEventManager().register(EventApplet.Init.class, event -> {
			game = new Game1(this);
		});
		getEventManager().register(EventGameThread.Init.class, event -> {
			game.init();
		});
		getEventManager().register(EventGameThread.Tick.class, event -> {
			game.move();
		});
		getEventManager().register(EventGameThread.Render.class, event -> {
			repaint();
		});

		//

		getEventManager().register(EventApplet.Paint.Pre.class, event -> {
			event.cancelled = true;
		});
		getEventManager().register(EventApplet.Paint.class, event -> {
			game.paint((Graphics2D) event.graphics);
		});
	}

}
