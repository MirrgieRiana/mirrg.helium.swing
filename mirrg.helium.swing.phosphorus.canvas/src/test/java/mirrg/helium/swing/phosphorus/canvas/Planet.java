package mirrg.helium.swing.phosphorus.canvas;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

public class Planet
{

	protected double x;
	protected double xx;
	protected double y;
	protected double yy;
	protected double z;
	protected double zz;

	protected double weight;

	public Planet()
	{
		x = Math.random();
		y = Math.random();
		z = Math.random();

		weight = Math.random() * Math.random();
	}

	public void move()
	{
		x += xx;
		y += yy;
		z += zz;
	}

	public void effect(ArrayList<Planet> planets, int start)
	{
		for (int i = start; i < planets.size(); i++) {
			Planet planet = planets.get(i);
			double x = planet.x - this.x;
			double y = planet.y - this.y;
			double z = planet.z - this.z;
			double dist2 = x * x + y * y + z * z;

			double r = getRadius() + planet.getRadius();
			if (dist2 < r * r) {

				double xx = planet.xx - this.xx;
				double yy = planet.yy - this.yy;
				double zz = planet.zz - this.zz;

				this.xx += xx * 0.1 * planet.weight;
				this.yy += yy * 0.1 * planet.weight;
				this.zz += zz * 0.1 * planet.weight;
				planet.xx -= xx * 0.1 * this.weight;
				planet.yy -= yy * 0.1 * this.weight;
				planet.zz -= zz * 0.1 * this.weight;

			} else {

				this.xx += x / dist2 * 0.000001 * planet.weight;
				this.yy += y / dist2 * 0.000001 * planet.weight;
				this.zz += z / dist2 * 0.000001 * planet.weight;
				planet.xx -= x / dist2 * 0.000001 * this.weight;
				planet.yy -= y / dist2 * 0.000001 * this.weight;
				planet.zz -= z / dist2 * 0.000001 * this.weight;

			}
		}
	}

	public void render(Graphics2D g, int width, int height)
	{
		int b = 255 - (int) (128 * (z + 0.5));
		if (b < 10) b = 10;
		if (b > 255) b = 255;
		g.setColor(new Color(b, b, b));
		double r = 2000 * getRadius() / (z + 0.5);
		g.fill(new Ellipse2D.Double(x * width - r, y * height - r, 1 + 2 * r, 1 + 2 * r));
	}

	protected double getRadius()
	{
		return Math.cbrt(weight) * 0.005;
	}

}
