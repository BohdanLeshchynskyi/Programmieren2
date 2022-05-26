package thd.gameobjects.base;

import thd.gameview.GameView;
/**
 * represents a position (x,y) on a 960x540 window. {@link GameView#WIDTH},
 *                                                  {@link GameView#HEIGHT},
 *                                                  {@link GameView}
 * where (0,0) is the left upper corner and (959,539) is right bottom corner
 * @see GameView
 */
public class Position {
    private double x;
    private double y;

    /**
     * Construcor of {@link Position}.
     * @param x x coordinate of a position
     * @param y y coordinate of a position
     */
    public Position(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Alternative construcor of {@link Position} with default parameters x = 0, y = 0.
     */
    public Position() {
        this(0, 0);
    }

    /**
     * Increments x coordinate of the {@link Position} by 1.
     */
    public void right() {
        x++;
    }

    /**
     * Increments x coordinate of the {@link Position} by the given amount.
     * @param dx size of increment of the x coordinarte of the {@link Position}
     */
    public void right(double dx) {
        x += dx;
    }

    /**
     * Decrements x coordinate of the {@link Position} by 1.
     */
    public void left() {
        x--;
    }

    /**
     * Decrements x coordinate of the {@link Position} by the given amount.
     * @param dx size of decrement of the x coordinarte of the {@link Position}
     */
    public void left(double dx) {
        x -= dx;
    }

    /**
     * Decrements y coordinate of the {@link Position} by 1.
     */
    public void up() {
        y--;
    }

    /**
     * Decrements y coordinate of the {@link Position} by the given amount.
     * @param dy size of decrement of the y coordinarte of the {@link Position}
     */
    public void up(double dy) {
        y -= dy;
    }

    /**
     * Increments y coordinate of the {@link Position} by 1.
     */
    public void down() {
        y++;
    }

    /**
     * Increments y coordinate of the {@link Position} by the given amount.
     * @param dy
     */
    public void down(double dy) {
        y += dy;
    }

    /**
     * @return current x coordinate
     */
    public double getX() {
        return x;
    }

    /**
     * @return current y coordinate
     */
    public double getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Position (" + (int) Math.round(x) + ", " + (int) Math.round(y) + ")";
    }
}
