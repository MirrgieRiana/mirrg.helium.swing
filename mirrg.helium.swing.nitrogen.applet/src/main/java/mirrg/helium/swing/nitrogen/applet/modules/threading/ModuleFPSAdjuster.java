package mirrg.helium.swing.nitrogen.applet.modules.threading;

import mirrg.helium.swing.nitrogen.applet.AppletNitrogen;
import mirrg.helium.swing.nitrogen.applet.Module;

public class ModuleFPSAdjuster extends Module
{

	private long previousTimeMs = 0;
	private double smoothedLoadFactor = 0;
	private double smoothedFPS = 0;
	private ModuleGameThread moduleGameThread;

	/** スムージング定数 */
	private final static double K = 0.10;

	public ModuleFPSAdjuster(AppletNitrogen applet, ModuleGameThread moduleGameThread)
	{
		super(applet);
		this.moduleGameThread = moduleGameThread;

		applet.getEventManager().register(EventGameThread.Sleep.class, event -> {
			event.cancelled = true;

			try {
				waitForNextTick();
			} catch (Exception e) {
				event.interrupted = true;
			}

		});
	}

	private final void sleepForNextTickFirst()
	{
		smoothedFPS = getObjectiveFPS();
		smoothedLoadFactor = 1.0;
		previousTimeMs = System.currentTimeMillis();
	}

	public void waitForNextTick() throws InterruptedException
	{
		// 初回呼出しである場合、別のルーチンを実行
		if (previousTimeMs == 0) {
			sleepForNextTickFirst();
			return;
		}

		//          処理       Sleep
		//    PTM          ms
		//     `-----------'
		//          PLTM   |
		//     `-----------|----------'
		//                dms
		//                 |

		// 現在の時刻
		long ms = System.currentTimeMillis();

		// 直前のSleep終了から現在までの長さ
		long previousLoopTimeMs = ms - previousTimeMs;

		// 適正１ループ時間
		long dms = (long) (1000 / getObjectiveFPS());

		{

			// ######################### Wait ########################
			// Sleepするべき時間ぶんだけSleep
			if (dms - previousLoopTimeMs - 2 >= 0) {
				Thread.sleep(dms - previousLoopTimeMs - 2);
			}

			// 微調整
			while (System.currentTimeMillis() < previousTimeMs + dms)
				;
			// #######################################################

		}

		//          処理       Sleep
		//    PTM          ms        ms2
		//     `-----------'          |
		//          PLTM              |
		//     `----------------------'
		//                dms         |
		//                            |

		// 現在の時刻
		long ms2 = System.currentTimeMillis();

		// 直前の計測からのFPS
		double fps = (ms2 - previousTimeMs == 0) ? 1000 : (1000.0 / (ms2 - previousTimeMs));

		// FPSを滑らかに反映
		smoothedFPS = (1 - K) * smoothedFPS + K * fps;

		// 負荷率
		double loadFactor = (double) previousLoopTimeMs / dms;

		// 負荷率を反映
		smoothedLoadFactor = (1 - K) * smoothedLoadFactor + K * loadFactor;

		previousTimeMs = ms2;
	}

	public double getLoadFactor()
	{
		if (smoothedLoadFactor == 0) return 1.0;

		return smoothedLoadFactor;
	}

	public double getFPS()
	{
		if (smoothedFPS == 0) return getObjectiveFPS();

		return smoothedFPS;
	}

	public double getObjectiveFPS()
	{
		return moduleGameThread.objectiveFPS;
	}

}
