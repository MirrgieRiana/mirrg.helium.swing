package mirrg.helium.swing.nitrogen.applet;

import mirrg.helium.swing.nitrogen.applet.modules.input.EventMouseMotion;
import mirrg.helium.swing.nitrogen.applet.modules.input.ModuleInputEvent;
import mirrg.helium.swing.nitrogen.applet.modules.rendering.ModuleTripleBuffer;
import mirrg.helium.swing.nitrogen.applet.modules.threading.EventGameThread;
import mirrg.helium.swing.nitrogen.applet.modules.threading.ModuleGameThread;

public class SampleAppletNitrogenEvent4 extends AppletNitrogen
{

	private static final long serialVersionUID = 6370904732290917883L;

	private Game2 game;

	public SampleAppletNitrogenEvent4()
	{
		ModuleGameThread moduleGameThread = new ModuleGameThread(this);
		moduleGameThread.objectiveFPS = 50;

		getEventManager().register(EventApplet.Init.class, event -> {
			game = new Game2(this);
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

		new ModuleInputEvent(this);
		getEventManager().register(EventMouseMotion.class,
			this::setPlayerPosition);

		//

		ModuleComponentEvent moduleComponentEvent = new ModuleComponentEvent(this);
		ModuleTripleBuffer moduleTripleBuffer = new ModuleTripleBuffer(this, moduleComponentEvent, moduleGameThread);

		getEventManager().register(EventApplet.Paint.class, event -> {

			game.paint(moduleTripleBuffer.getBufferDirty().getGraphics());
			moduleTripleBuffer.flip();

			event.graphics.drawImage(moduleTripleBuffer.getBufferSafety().getBuffer(), 0, 0, this);
		});
	}

	public void setPlayerPosition(EventMouseMotion event)
	{
		game.setPlayerPosition(
			1.0 * event.mouseEvent.getX() / getWidth(),
			1.0 * event.mouseEvent.getY() / getHeight());
	}

}
