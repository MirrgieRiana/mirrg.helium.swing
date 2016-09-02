package mirrg.helium.swing.nitrogen.applet;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class ModuleComponentEvent extends Module
{

	public ModuleComponentEvent(AppletNitrogen applet)
	{
		super(applet);

		applet.addComponentListener(new ComponentListener() {

			@Override
			public void componentShown(ComponentEvent componentEvent)
			{
				applet.getEventManager().post(
					new EventComponent.Shown(componentEvent));
			}

			@Override
			public void componentResized(ComponentEvent componentEvent)
			{
				applet.getEventManager().post(
					new EventComponent.Resized(componentEvent));
			}

			@Override
			public void componentMoved(ComponentEvent componentEvent)
			{
				applet.getEventManager().post(
					new EventComponent.Moved(componentEvent));
			}

			@Override
			public void componentHidden(ComponentEvent componentEvent)
			{
				applet.getEventManager().post(
					new EventComponent.Hidden(componentEvent));
			}

		});
	}

}
