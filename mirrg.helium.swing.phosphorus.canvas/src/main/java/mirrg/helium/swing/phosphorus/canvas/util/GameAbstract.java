package mirrg.helium.swing.phosphorus.canvas.util;

import java.awt.Graphics2D;

import mirrg.helium.swing.phosphorus.canvas.PhosphorusCanvas;

public abstract class GameAbstract
{

	protected PhosphorusCanvas canvas;

	public GameAbstract(PhosphorusCanvas canvas)
	{
		this.canvas = canvas;
	}

	public abstract void move();

	public abstract void render(Graphics2D g);

}
