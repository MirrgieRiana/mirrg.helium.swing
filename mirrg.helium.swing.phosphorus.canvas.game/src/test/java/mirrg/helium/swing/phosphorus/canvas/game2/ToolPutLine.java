package mirrg.helium.swing.phosphorus.canvas.game2;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.ToDoubleFunction;
import java.util.stream.Stream;

import mirrg.helium.standard.hydrogen.struct.Tuple;
import mirrg.helium.standard.hydrogen.util.HLambda;
import mirrg.helium.swing.phosphorus.canvas.EventPhosphorusCanvas;
import mirrg.helium.swing.phosphorus.canvas.game.EventGamePhosphorus;
import mirrg.helium.swing.phosphorus.canvas.game.entity.ModelLiving.Entity;
import mirrg.helium.swing.phosphorus.canvas.game.render.PointCoordinate;
import mirrg.helium.swing.phosphorus.canvas.game.render.PointScreen;

public class ToolPutLine extends Entity<Game2>
{

	private Optional<ModelEntityPlace> begin = Optional.empty();

	public ToolPutLine(Game2 game, int button)
	{
		super(game);

		registerEvent(EventPhosphorusCanvas.EventMouse.Pressed.class, e -> {
			if (e.event.getButton() == button) {
				PointCoordinate point = game.getView().getController().convert(new PointScreen(e.event.getPoint()));
				Optional<ModelEntityPlace> oPlace = sorted(HLambda.filter(game.getModel().getEntities(), ModelEntityPlace.class),
					a -> Point2D.distanceSq(point.x, point.y, a.point.x, a.point.y))
						.findFirst();

				begin = oPlace;
			}
		});
		registerEvent(EventPhosphorusCanvas.EventMouse.Released.class, e -> {
			if (e.event.getButton() == button) {
				PointCoordinate point = game.getView().getController().convert(new PointScreen(e.event.getPoint()));
				Optional<ModelEntityPlace> oPlace = sorted(HLambda.filter(game.getModel().getEntities(), ModelEntityPlace.class),
					a -> Point2D.distanceSq(point.x, point.y, a.point.x, a.point.y))
						.findFirst();

				Optional<ModelEntityPlace> begin = this.begin;
				Optional<ModelEntityPlace> end = oPlace;

				if (begin.isPresent() && end.isPresent()) {

					game.event().registerRemovable(EventGamePhosphorus.Move.Post.Post.class, e2 -> {
						game.getModel().addEntity(new ModelEntityWay(game, begin.get(), end.get()));
						return false;
					});

				}
				this.begin = Optional.empty();
			}
		});

	}

	//TODO mirrg
	public static <T> Stream<T> of(Consumer<Consumer<T>> ts)
	{
		ArrayList<T> list = new ArrayList<>();
		ts.accept(list::add);
		return list.stream();
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
