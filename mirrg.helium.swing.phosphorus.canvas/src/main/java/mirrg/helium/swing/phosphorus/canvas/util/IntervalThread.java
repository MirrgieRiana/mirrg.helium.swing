package mirrg.helium.swing.phosphorus.canvas.util;

public class IntervalThread extends Thread
{

	public int fps;
	private Runnable listener;

	public IntervalThread(int fps, Runnable listener)
	{
		this.fps = fps;
		this.listener = listener;
	}

	@Override
	public void run()
	{
		while (true) {
			try {
				Thread.sleep(1000 / fps);
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
