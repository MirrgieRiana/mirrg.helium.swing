package mirrg.helium.swing.phosphorus.canvas.util;

public class IntervalThread extends Thread
{

	public double fps;
	private Runnable listener;

	public IntervalThread(double fps, Runnable listener)
	{
		this.fps = fps;
		this.listener = listener;
	}

	@Override
	public void run()
	{
		while (true) {
			try {
				double time = 1000.0 / fps;
				int ms = (int) time;
				int ns = (int) ((time - ms) * 1000);
				Thread.sleep(ms, ns);
			} catch (InterruptedException e) {
				break;
			}

			onTimerEvent();
		}
	}

	public void onTimerEvent()
	{
		listener.run();
	}

}
