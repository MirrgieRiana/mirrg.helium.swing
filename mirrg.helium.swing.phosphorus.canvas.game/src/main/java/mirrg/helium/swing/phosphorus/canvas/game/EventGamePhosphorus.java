package mirrg.helium.swing.phosphorus.canvas.game;

import mirrg.helium.game.carbon.base.EventGameCarbon;

public class EventGamePhosphorus extends EventGameCarbon
{

	public static class Move extends EventGamePhosphorus
	{

		public static class Pre extends Move
		{

		}

		public static class Post extends Move
		{

		}

	}

	public static class Render extends EventGamePhosphorus
	{

		public static class Pre extends Render
		{

		}

		public static class Post extends Render
		{

		}

	}

	public static class ChangeViewStatus extends EventGamePhosphorus
	{

		public static class Pre extends ChangeViewStatus
		{

		}

		public static class Post extends ChangeViewStatus
		{

		}

	}

}
