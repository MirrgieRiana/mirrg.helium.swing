package mirrg.helium.swing.nitrogen.applet;

import mirrg.helium.swing.nitrogen.applet.modules.input.ModuleInputEvent;
import mirrg.helium.swing.nitrogen.applet.modules.input.ModuleInputStatus;
import mirrg.helium.swing.nitrogen.applet.modules.rendering.ModuleTripleBuffer;
import mirrg.helium.swing.nitrogen.applet.modules.threading.ModuleFPSAdjuster;
import mirrg.helium.swing.nitrogen.applet.modules.threading.ModuleGameThread;

public class HAppletNitrogen
{

	public static ResponceApplyStandard applyStandard(AppletNitrogen applet)
	{
		applet.setFocusable(true);

		ResponceApplyStandard responce = new ResponceApplyStandard();

		responce.moduleGameThread = new ModuleGameThread(applet);
		responce.moduleFPSAdjuster = new ModuleFPSAdjuster(applet, responce.moduleGameThread);
		responce.moduleComponentEvent = new ModuleComponentEvent(applet);
		responce.moduleTripleBuffer = new ModuleTripleBuffer(applet, responce.moduleComponentEvent, responce.moduleGameThread);
		responce.moduleInputEvent = new ModuleInputEvent(applet);
		responce.moduleInputStatus = new ModuleInputStatus(applet, responce.moduleInputEvent);

		return responce;
	}

	public static class ResponceApplyStandard
	{

		public ModuleGameThread moduleGameThread;
		public ModuleFPSAdjuster moduleFPSAdjuster;
		public ModuleComponentEvent moduleComponentEvent;
		public ModuleTripleBuffer moduleTripleBuffer;
		public ModuleInputEvent moduleInputEvent;
		public ModuleInputStatus moduleInputStatus;

	}

}
