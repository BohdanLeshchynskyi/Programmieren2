package game;

public class Position {
    public double x;
    public double y;

    Position(double x, double y) {
        this.x = x;
        this.y = y;
    }

    Position() {
        this(0, 0);
    }

    public void right() {
        x++;
    }
    public void right(double dx) {
        x += dx;
    }

    public void left() {
        x--;
    }

    public void left(double dy) {
        x -= dy;
    }

    public void up() {
        y--;
    }

    public void up(double dy) {
        y -= dy;
    }

    public void down() {
        y++;
    }

    public void down(double dy) {
        y += dy;
    }

    @Override
    public String toString() {
        return "Position (" + (int) Math.round(x) + ", " + (int) Math.round(y) + ")";
    }
}
