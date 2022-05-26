package thd.game.utilities;

import thd.gameobjects.base.Position;
import thd.gameview.GameView;

import java.util.*;


class Sorter {
    void naturalOrder(ArrayList<Position> positions) {
        Collections.sort(positions);
    }

    void xOrder(ArrayList<Position> positions) {
        class XCompare implements Comparator<Position> {
            @Override
            public int compare (Position a, Position b){
                return Double.compare(a.x - b.x, 0d);
            }
        }
        Collections.sort(positions, new XCompare());
    }

    void yOrder(ArrayList<Position> positions) {
        Comparator<Position> yCompare = new Comparator<>() {
            @Override
            public int compare (Position a, Position b){
                return Double.compare(a.y - b.y, 0d);
            }
        };
        Collections.sort(positions, yCompare);
    }

    void centricOrder(ArrayList<Position> positions) {
        Position gameViewCentre = new Position(GameView.WIDTH / 2d, GameView.HEIGHT / 2d);
        Comparator<Position> centredGameView = (a, b) -> Double.compare((a.distance(gameViewCentre) - b.distance(gameViewCentre)), 0d);
        Collections.sort(positions, centredGameView);
    }

    public static void main(String[] args) {
        Random random = new Random();
        Sorter sorter = new Sorter();
        ArrayList<Position> positions = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            positions.add(new Position(random.nextDouble((double) GameView.WIDTH), random.nextDouble((double) GameView.HEIGHT)));
        }

        sorter.naturalOrder(positions);
        System.out.println(positions);
        sorter.xOrder(positions);
        System.out.println(positions);
        sorter.yOrder(positions);
        System.out.println(positions);
        sorter.centricOrder(positions);
        System.out.println(positions);
    }
}