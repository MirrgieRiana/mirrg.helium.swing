package mirrg.helium.swing.nitrogen.applet;

import java.awt.Graphics2D;

public class SampleAppletNitrogenEvent extends AppletNitrogen
{

	private static final long serialVersionUID = 6370904732290917883L;

	private Game1 game;
	private Thread thread;

	public SampleAppletNitrogenEvent()
	{
		getEventManager().register(EventApplet.Init.class, event -> {
			game = new Game1(this);
		});
		getEventManager().register(EventApplet.Start.class, event -> {

			thread = new Thread(() -> {

				while (true) {
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						break;
					}

					game.move();
					repaint();
				}

			});

			game.init();
			thread.start();
		});
		getEventManager().register(EventApplet.Stop.class, event -> {
			thread.interrupt();
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
