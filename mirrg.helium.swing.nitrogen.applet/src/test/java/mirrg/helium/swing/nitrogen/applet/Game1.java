package mirrg.helium.swing.nitrogen.applet;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.util.ArrayList;

class Game1
{

	protected Component parent;
	protected ArrayList<double[]> points;

	public Game1(Component parent)
	{
		this.parent = parent;
	}

	public void init()
	{
		points = new ArrayList<>();
		for (int i = 0; i < 200; i++) {
			points.add(createPoint());
		}
	}

	protected double[] createPoint()
	{
		double theta = Math.random() * 2 * Math.PI;
		double speed = Math.random() * 0.01 + 0.005;

		return new double[] {
			0.5,
			0.5,
			speed * Math.cos(theta),
			speed * Math.sin(theta),
		};
	}

	public void move()
	{
		for (int i = 0; i < points.size(); i++) {
			double[] point = points.get(i);

			point[0] += point[2];
			point[1] += point[3];

			if (point[0] < 0) points.set(i, createPoint());
			else if (point[0] > 1) points.set(i, createPoint());
			else if (point[1] < 0) points.set(i, createPoint());
			else if (point[1] > 1) points.set(i, createPoint());

		}
	}

	public void paint(Graphics2D graphics)
	{
		int width = parent.getWidth();
		int height = parent.getHeight();

		graphics.setColor(Color.black);
		graphics.fillRect(0, 0, width, height);

		graphics.setColor(Color.red);
		for (double[] point : points) {
			graphics.fillRect(
				(int) (point[0] * width) - 2,
				(int) (point[1] * height) - 2,
				5, 5);
		}
	}

}
