package mirrg.helium.swing.nitrogen.applet;

import java.awt.Font;
import java.awt.Graphics2D;

import mirrg.helium.swing.nitrogen.applet.HAppletNitrogen.ResponceApplyStandard;
import mirrg.helium.swing.nitrogen.applet.modules.threading.EventGameThread;
import mirrg.helium.swing.nitrogen.applet.modules.threading.ModuleFPSAdjuster;

public class SampleAppletNitrogenEvent5 extends AppletNitrogen
{

	private static final long serialVersionUID = 6370904732290917883L;

	private Game2 game;

	public SampleAppletNitrogenEvent5()
	{
		ResponceApplyStandard responce = HAppletNitrogen.applyStandard(this);
		responce.moduleGameThread.objectiveFPS = 30;

		//

		getEventManager().register(EventApplet.Init.class, event -> {
			game = new Game2(this);
		});
		getEventManager().register(EventGameThread.Init.class, event -> {
			game.init();
		});
		getEventManager().register(EventGameThread.Tick.class, event -> {
			game.setPlayerPosition(
				1.0 * responce.moduleInputStatus.getMouseX() / getWidth(),
				1.0 * responce.moduleInputStatus.getMouseY() / getHeight());
			game.move();
		});
		getEventManager().register(EventGameThread.PostTick.class, event -> {
			responce.moduleInputStatus.spend();
		});
		getEventManager().register(EventGameThread.Render.class, event -> {
			repaint();
		});
		getEventManager().register(EventApplet.Paint.class, event -> {

			{
				Graphics2D graphics = responce.moduleTripleBuffer.getBufferDirty().getGraphics();

				game.paint(graphics);

				drawFPS(graphics, responce.moduleFPSAdjuster);
			}

			responce.moduleTripleBuffer.flip();

			event.graphics.drawImage(responce.moduleTripleBuffer.getBufferSafety().getBuffer(), 0, 0, this);
		});

	}

	private void drawFPS(Graphics2D graphics, ModuleFPSAdjuster fpsAdjuster)
	{
		graphics.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
		graphics.drawString(String.format(
			"FPS: %.2f",
			fpsAdjuster.getFPS()),
			0, getHeight());
		graphics.drawString(String.format(
			"CPU: %.2f%%",
			fpsAdjuster.getLoadFactor() * 100),
			0, getHeight() - graphics.getFont().getSize());
	}

}
