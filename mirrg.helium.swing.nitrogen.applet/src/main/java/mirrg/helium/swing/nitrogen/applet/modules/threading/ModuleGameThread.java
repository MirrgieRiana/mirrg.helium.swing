package mirrg.helium.swing.nitrogen.applet.modules.threading;

import mirrg.helium.swing.nitrogen.applet.AppletNitrogen;
import mirrg.helium.swing.nitrogen.applet.EventApplet;
import mirrg.helium.swing.nitrogen.applet.Module;

public class ModuleGameThread extends Module
{

	public double objectiveFPS = 50;
	private Thread thread;

	/**
	 * @see EventGameThread
	 */
	public ModuleGameThread(AppletNitrogen applet)
	{
		super(applet);

		applet.getEventManager().register(EventApplet.Start.class, event -> {

			thread = new Thread(() -> {

				while (true) {

					{
						EventGameThread.Sleep event2 = new EventGameThread.Sleep(this);
						applet.getEventManager().post(event2);

						if (!event2.cancelled) {
							try {
								Thread.sleep((long) (1000 / this.objectiveFPS));
							} catch (InterruptedException e) {
								break;
							}
						}
						if (event2.interrupted) break;
					}

					applet.getEventManager().post(
						new EventGameThread.Tick(this));

					applet.getEventManager().post(
						new EventGameThread.PostTick(this));

					applet.getEventManager().post(
						new EventGameThread.Render(this));

				}

			});
			thread.setDaemon(true);

			applet.getEventManager().post(
				new EventGameThread.Init(this));

			thread.start();
		});
		applet.getEventManager().register(EventApplet.Stop.class, event -> {
			thread.interrupt();
		});
	}

	public Thread getThread()
	{
		return thread;
	}

}
