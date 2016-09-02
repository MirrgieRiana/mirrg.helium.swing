package mirrg.helium.swing.nitrogen.applet.modules.rendering;

import java.awt.image.BufferedImage;

import mirrg.helium.standard.hydrogen.event.EventManager;
import mirrg.helium.swing.nitrogen.applet.AppletNitrogen;
import mirrg.helium.swing.nitrogen.applet.EventApplet;
import mirrg.helium.swing.nitrogen.applet.EventNitrogen;
import mirrg.helium.swing.nitrogen.applet.Module;
import mirrg.helium.swing.nitrogen.applet.ModuleComponentEvent;
import mirrg.helium.swing.nitrogen.applet.modules.threading.ModuleGameThread;

public class ModuleTripleBuffer extends Module
{

	private BackBuffer safety = new BackBuffer(BufferedImage.TYPE_INT_RGB);
	private BackBuffer dirty = new BackBuffer(BufferedImage.TYPE_INT_RGB);

	/**
	 * 画面の更新レートと描画の処理レートを違いの吸収のために2枚のバッファを追加します。
	 * <ul>
	 * <li>本来用意されている出力バッファ
	 * <li>任意のタイミングで更新および読み取りが可能なバッファ（safety）
	 * <li>任意のタイミングで初期化及び書き換えが可能で、任意のタイミングでない読み取りが可能なバッファ（dirty）
	 * </ul>
	 */
	public ModuleTripleBuffer(
		AppletNitrogen applet,
		ModuleComponentEvent moduleComponentEvent,
		ModuleGameThread moduleGameThread)
	{
		super(applet);

		applet.getEventManager().register(EventApplet.Paint.Pre.class, event -> {
			event.cancelled = true;
		});
		subscribeEvent(applet.getEventManager());
	}

	public void rebuffer(int width, int height)
	{
		safety.rebuffer(width, height);
		dirty.rebuffer(width, height);
	}

	/**
	 * 読み取り用のバッファ。
	 */
	public synchronized BackBuffer getBufferSafety()
	{
		return safety;
	}

	/**
	 * 書き込み用のグラフィクス。
	 */
	public synchronized BackBuffer getBufferDirty()
	{
		return dirty;
	}

	public synchronized int getWidth()
	{
		return dirty.getWidth();
	}

	public synchronized int getHeight()
	{
		return dirty.getHeight();
	}

	/**
	 * dirtyバッファとsafetyバッファを交換する（高速）。<br>
	 * dirtyは以前のsafetyの内容となる。
	 */
	public synchronized void flip()
	{
		dirty.flip(safety);
	}

	/**
	 * dirtyバッファの内容をsafetyバッファにコピーする。<br>
	 * dirtyは現在のsafetyの内容となる。
	 */
	public void copy()
	{
		safety.getGraphics().drawImage(dirty.getBuffer(), 0, 0, null);
	}

	/**
	 * safetyの内容をdirtyに戻す。
	 */
	public void rollback()
	{
		rollback(0, 0);
	}

	/**
	 * safetyの内容をdirtyに戻す。
	 */
	public void rollback(int x, int y)
	{
		rollback(x, y, getWidth(), getHeight());
	}

	/**
	 * safetyの内容をdirtyに戻す。
	 */
	public void rollback(int x, int y, int width, int height)
	{
		dirty.getGraphics().drawImage(safety.getBuffer(), x, y, width, height, null);
	}

	public void subscribeEvent(EventManager<EventNitrogen> eventManager)
	{
		dirty.subscribeEvent(eventManager);
		safety.subscribeEvent(eventManager);
	}

}
