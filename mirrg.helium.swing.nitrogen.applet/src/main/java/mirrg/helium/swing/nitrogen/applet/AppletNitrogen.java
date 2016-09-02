package mirrg.helium.swing.nitrogen.applet;

import java.awt.Graphics;

import javax.swing.JApplet;

import mirrg.helium.standard.hydrogen.event.EventManager;

/**
 * コンストラクタ内やインスタンスの生成時で以下のことようなをすると、
 * イベントを受け取れる。
 *
 * <pre>
 * getEventManager().register(NitrogenEventApplet.Init.class, event -&gt; {
 * 	System.out.println(&quot;on init&quot;);
 * });
 * </pre>
 */
public class AppletNitrogen extends JApplet
{

	private static final long serialVersionUID = 155419117197430297L;

	public AppletNitrogen()
	{
		eventManager.register(EventApplet.Start.class, event -> {
			eventManager.post(new EventApplet.Rebuffer(
				this,
				event.applet.getWidth(),
				event.applet.getHeight()));
		});
		eventManager.register(EventComponent.Resized.class, event -> {
			eventManager.post(new EventApplet.Rebuffer(
				this,
				event.componentEvent.getComponent().getWidth(),
				event.componentEvent.getComponent().getHeight()));
		});
	}

	//

	private EventManager<EventNitrogen> eventManager = new EventManager<>();

	public EventManager<EventNitrogen> getEventManager()
	{
		return eventManager;
	}

	//

	@Override
	public final void init()
	{
		getEventManager().post(new EventApplet.Init(this));
	}

	@Override
	public final void start()
	{
		isRunning = true;
		getEventManager().post(new EventApplet.Start(this));
	}

	@Override
	public final void stop()
	{
		isRunning = false;
		getEventManager().post(new EventApplet.Stop(this));
	}

	@Override
	public final void destroy()
	{
		getEventManager().post(new EventApplet.Destroy(this));
	}

	//

	private boolean isRunning = false;

	@Override
	public final void paint(Graphics graphics)
	{
		if (!isRunning) {
			super.paint(graphics);
			return;
		}

		EventApplet.Paint.Pre event = new EventApplet.Paint.Pre(this);
		getEventManager().post(event);

		if (!event.cancelled) super.paint(graphics);

		getEventManager().post(new EventApplet.Paint(this, graphics));
	}

	@Override
	public final void update(Graphics graphics)
	{
		if (!isRunning) {
			super.update(graphics);
			return;
		}

		EventApplet.Update.Pre event = new EventApplet.Update.Pre(this);
		getEventManager().post(event);

		if (!event.cancelled) super.update(graphics);

		getEventManager().post(new EventApplet.Update(this, graphics));
	}

}
