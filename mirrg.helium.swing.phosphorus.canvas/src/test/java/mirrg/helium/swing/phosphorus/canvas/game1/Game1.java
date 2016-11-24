package mirrg.helium.swing.phosphorus.canvas.game1;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;

import mirrg.helium.swing.phosphorus.canvas.PhosphorusCanvas;

public class Game1
{

	private PhosphorusCanvas canvas;
	private ArrayList<Planet> planets = new ArrayList<>();

	public Game1(PhosphorusCanvas canvas, int count)
	{
		this.canvas = canvas;

		for (int i = 0; i < count; i++) {
			planets.add(new Planet());
		}
	}

	public void move()
	{
		planets.forEach(Planet::move);

		for (int i = 0; i < planets.size(); i++) {
			planets.get(i).effect(planets, i + 1);
		}
	}

	public void render(Graphics2D g)
	{
		g.setBackground(Color.black);
		g.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		planets.stream()
			.sorted((a, b) -> (int) Math.signum(b.z - a.z))
			.forEach(p -> p.render(g, canvas.getWidth(), canvas.getHeight()));
	}

}
