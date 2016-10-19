package mirrg.helium.swing.nitrogen.applet;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics2D;

class Game2 extends Game1
{

	private static final double playerR = 0.01;
	private double[] player;
	private int score;
	private int scoreMax;

	public Game2(Component parent)
	{
		super(parent);
	}

	@Override
	public void init()
	{
		super.init();

		player = new double[] {
			0.5,
			0.75,
		};
	}

	@Override
	public void move()
	{
		super.move();

		for (int i = 0; i < points.size(); i++) {
			double[] point = points.get(i);

			double dist2 = pow2(point[0] - player[0]) + pow2(point[1] - player[1]);

			if (dist2 < pow2(playerR)) {
				delete(player[0], player[1], pow2(0.5));

				if (score > scoreMax) scoreMax = score;
				score = 0;

				break;
			}

		}

		score++;
	}

	/**
	 * @param r2
	 *            r^2
	 */
	private void delete(double x, double y, double r2)
	{
		for (int i = 0; i < points.size(); i++) {
			double[] point = points.get(i);

			double dist2 = pow2(point[0] - x) + pow2(point[1] - y);
			if (dist2 < r2) {

				points.set(i, createPoint());

			}

		}
	}

	@Override
	protected double[] createPoint()
	{
		double theta = Math.random() * 2 * Math.PI;
		double speed = Math.random() * 0.01 + 0.005;

		return new double[] {
			0.5,
			0.25,
			speed * Math.cos(theta),
			speed * Math.sin(theta),
		};
	}

	private double pow2(double value)
	{
		return value * value;
	}

	public void setPlayerPosition(double x, double y)
	{
		player[0] = x;
		player[1] = y;
	}

	@Override
	public void paint(Graphics2D graphics)
	{
		super.paint(graphics);

		int width = parent.getWidth();
		int height = parent.getHeight();

		graphics.setColor(Color.white);
		graphics.fillOval(
			(int) ((player[0] - playerR) * width),
			(int) ((player[1] - playerR) * height),
			(int) (playerR * 2 * width),
			(int) (playerR * 2 * height));

		graphics.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 24));
		graphics.drawString("Score: " + score,
			0, graphics.getFont().getSize());
		graphics.drawString("HiScore: " + scoreMax,
			0, graphics.getFont().getSize() * 2);
	}

}
