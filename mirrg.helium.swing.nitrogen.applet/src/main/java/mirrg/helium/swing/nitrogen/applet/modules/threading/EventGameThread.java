package mirrg.helium.swing.nitrogen.applet.modules.threading;

import mirrg.helium.swing.nitrogen.applet.EventNitrogen;

public class EventGameThread extends EventNitrogen
{

	public ModuleGameThread moduleGameThread;

	public EventGameThread(ModuleGameThread moduleGameThread)
	{
		this.moduleGameThread = moduleGameThread;
	}

	public static class Init extends EventGameThread
	{

		public Init(ModuleGameThread moduleGameThread)
		{
			super(moduleGameThread);
		}

	}

	public static class Sleep extends EventGameThread
	{

		/**
		 * 本来のSleepの処理を行わない場合にtrue。
		 */
		public boolean cancelled = false;

		/**
		 * InterruptedExceptionなどでループを抜けなければならない場合にtrue。
		 */
		public boolean interrupted = false;

		public Sleep(ModuleGameThread moduleGameThread)
		{
			super(moduleGameThread);
		}

	}

	public static class Tick extends EventGameThread
	{

		public Tick(ModuleGameThread moduleGameThread)
		{
			super(moduleGameThread);
		}

	}

	public static class PostTick extends EventGameThread
	{

		public PostTick(ModuleGameThread moduleGameThread)
		{
			super(moduleGameThread);
		}

	}

	public static class Render extends EventGameThread
	{

		public Render(ModuleGameThread moduleGameThread)
		{
			super(moduleGameThread);
		}

	}

}
