package thd.game.utilities;

import thd.gameobjects.base.Position;
import thd.gameview.GameView;

import java.util.*;


class Sorter {
    private void naturalOrder(ArrayList<Position> positions) {
        Collections.sort(positions);
    }

    private void xOrder(ArrayList<Position> positions) {
        class XCompare implements Comparator<Position> {
            @Override
            public int compare (Position a, Position b){
                return Double.compare(a.x - b.x, 0d);
            }
        }
        Collections.sort(positions, new XCompare());
    }

    private void yOrder(ArrayList<Position> positions) {
        Comparator<Position> yCompare = new Comparator<>() {
            @Override
            public int compare (Position a, Position b){
                return Double.compare(a.y - b.y, 0d);
            }
        };
        Collections.sort(positions, yCompare);
    }

    private void centricOrder(ArrayList<Position> positions) {
        Position gameViewCentre = new Position(GameView.WIDTH / 2d, GameView.HEIGHT / 2d);
        Comparator<Position> centredGameView = (a, b) -> Double.compare((a.distance(gameViewCentre) - b.distance(gameViewCentre)), 0d);
        Collections.sort(positions, centredGameView);
    }

}