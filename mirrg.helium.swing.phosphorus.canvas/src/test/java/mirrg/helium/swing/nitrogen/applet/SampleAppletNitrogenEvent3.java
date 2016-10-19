package mirrg.helium.swing.nitrogen.applet;

import mirrg.helium.swing.nitrogen.applet.modules.rendering.ModuleTripleBuffer;
import mirrg.helium.swing.nitrogen.applet.modules.threading.EventGameThread;
import mirrg.helium.swing.nitrogen.applet.modules.threading.ModuleGameThread;

public class SampleAppletNitrogenEvent3 extends AppletNitrogen
{

	private static final long serialVersionUID = 6370904732290917883L;

	private Game1 game;

	public SampleAppletNitrogenEvent3()
	{
		ModuleGameThread moduleGameThread = new ModuleGameThread(this);
		moduleGameThread.objectiveFPS = 50;

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

		ModuleComponentEvent moduleComponentEvent = new ModuleComponentEvent(this);
		ModuleTripleBuffer moduleTripleBuffer = new ModuleTripleBuffer(this, moduleComponentEvent, moduleGameThread);

		getEventManager().register(EventApplet.Paint.class, event -> {

			game.paint(moduleTripleBuffer.getBufferDirty().getGraphics());
			moduleTripleBuffer.flip();

			event.graphics.drawImage(moduleTripleBuffer.getBufferSafety().getBuffer(), 0, 0, this);
		});
	}

}
