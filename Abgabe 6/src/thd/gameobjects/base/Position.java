package thd.gameobjects.base;

import thd.gameview.GameView;
import java.math.*;
import java.util.Objects;

/**
 * represents a position (x,y) on a 960x540 window. {@link GameView#WIDTH},
 *                                                  {@link GameView#HEIGHT},
 *                                                  {@link GameView}
 * where (0,0) is the left upper corner and (959,539) is right bottom corner
 * @see GameView
 */
public class Position implements Cloneable{
    /**
     * x coordinate.
     */
    public double x;
    /**
     * y coordinate.
     */
    public double y;

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
     * @param dy amount of incrementing
     */
    public void down(double dy) {
        y += dy;
    }

    /**
     * calculates distance to another Position.
     * @param other other Position
     * @return distance between using Pythagorean
     */
    public double distance(Position other) {
        return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
    }

    @Override
    public String toString() {
        return "Position (" + (int) Math.round(x) + ", " + (int) Math.round(y) + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null || o.getClass() != getClass()) {
            return false;
        }
        Position other = (Position) o;
        return this.x == other.x && this.y == other.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.x, this.y);
    }

    @Override
    public Position clone() {
        Position other = null;
        try{
            other = (Position) super.clone();
        } catch (CloneNotSupportedException ignored) {}
        return other;
    }
}
