package mirrg.helium.swing.phosphorus.canvas.game2;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;

import mirrg.helium.swing.phosphorus.canvas.PhosphorusCanvas;
import mirrg.helium.swing.phosphorus.canvas.game.GamePhosphorus;
import mirrg.helium.swing.phosphorus.canvas.game.entity.ToolScroll;
import mirrg.helium.swing.phosphorus.canvas.game.entity.ToolZoom;
import mirrg.helium.swing.phosphorus.canvas.game.render.Layer;
import mirrg.helium.swing.phosphorus.canvas.game.view.ModelViewXYZoom;

public class Game2 extends GamePhosphorus<Game2, ModelGame2, ModelViewXYZoom>
{

	public final Layer layerMain;
	public final Layer layerOverlay;

	public String xml;

	public Game2(PhosphorusCanvas canvas)
	{
		super(canvas);

		addLayer(layerMain = createLayer());
		addLayer(layerOverlay = createLayer());

		addTool(new ToolGame2(this));

		addTool(new ToolScroll(this, MouseEvent.BUTTON2));
		addTool(new ToolZoom(this));
		addTool(new ToolPutBlock(this, MouseEvent.BUTTON1));
		addTool(new ToolPutLine(this, MouseEvent.BUTTON3));

	}

	@Override
	public void render(Graphics2D g)
	{
		getLayers().forEach(l -> l.getImageLayer().getGraphics().setRenderingHint(
			RenderingHints.KEY_ANTIALIASING,
			RenderingHints.VALUE_ANTIALIAS_ON));
		super.render(g);
	}

}
