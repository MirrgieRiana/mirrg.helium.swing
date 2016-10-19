package mirrg.helium.swing.phosphorus.canvas.util;

import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import mirrg.helium.swing.phosphorus.canvas.PhosphorusCanvas;

public class FrameCanvas extends JFrame
{

	public PhosphorusCanvas canvas;

	public FrameCanvas(int width, int height)
	{
		setLayout(new CardLayout());

		{
			canvas = new PhosphorusCanvas();
			canvas.setPreferredSize(new Dimension(width, height));
			add(canvas);
		}

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		pack();
	}

}
