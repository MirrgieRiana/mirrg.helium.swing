package mirrg.helium.swing.phosphorus.canvas.game2;

import java.awt.Point;
import java.util.Optional;
import java.util.function.ToDoubleFunction;
import java.util.stream.Stream;

import mirrg.helium.standard.hydrogen.struct.Tuple;
import mirrg.helium.standard.hydrogen.util.HLambda;
import mirrg.helium.swing.phosphorus.canvas.EventPhosphorusCanvas;
import mirrg.helium.swing.phosphorus.canvas.game.EventPhosphorusGame;
import mirrg.helium.swing.phosphorus.canvas.game.existence.Tool;
import mirrg.helium.swing.phosphorus.canvas.game.render.PointCoordinate;
import mirrg.helium.swing.phosphorus.canvas.game.render.PointScreen;

public class ToolPutLine extends Tool<Game2>
{

	private Optional<DataEntityPlace> begin = Optional.empty();

	public ToolPutLine(Game2 game, int button)
	{
		super(game);

		registerEvent(EventPhosphorusCanvas.EventMouse.Pressed.class, e -> {
			if (e.event.getButton() == button) {
				PointCoordinate point = game.getView().convert(new PointScreen(e.event.getPoint()));
				Optional<DataEntityPlace> oPlace = sorted(HLambda.filter(game.getEntities(), DataEntityPlace.class),
					a -> Point.distanceSq(point.x, point.y, a.point.x, a.point.y))
						.findFirst();

				begin = oPlace;
			}
		});
		registerEvent(EventPhosphorusCanvas.EventMouse.Released.class, e -> {
			if (e.event.getButton() == button) {
				PointCoordinate point = game.getView().convert(new PointScreen(e.event.getPoint()));
				Optional<DataEntityPlace> oPlace = sorted(HLambda.filter(game.getEntities(), DataEntityPlace.class),
					a -> Point.distanceSq(point.x, point.y, a.point.x, a.point.y))
						.findFirst();

				Optional<DataEntityPlace> begin = this.begin;
				Optional<DataEntityPlace> end = oPlace;

				if (begin.isPresent() && end.isPresent()) {

					game.event().registerRemovable(EventPhosphorusGame.Move.Post.class, e2 -> {
						game.addEntity(new DataEntityWay(begin.get(), end.get()));
						return false;
					});

				}
				this.begin = Optional.empty();
			}
		});

	}

	//TODO mirrg
	public static <T> Stream<T> sorted(Stream<T> stream, ToDoubleFunction<T> function)
	{
		return stream
			.map(i -> new Tuple<>(i, function.applyAsDouble(i)))
			.sorted((a, b) -> (int) Math.signum(a.getY() - b.getY()))
			.map(Tuple::getX);
	}

}
