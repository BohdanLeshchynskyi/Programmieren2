package thd.game.level;

/**
 * Represents a parent class or game`s levels.
 */
public class Level {
    /**
     * Enumeration that represents different game`s difficulties.
     */
    public enum Difficulty {
        /**
         * different difficulties.
         */
        EASY, STANDARD;
    }

    /**
     * level number.
     */
    public int levelNumber;

    /**
     * level`s difficulty.
     */
    protected Difficulty difficulty;

    /**
     * determines the speed of enemy helicopters in a level.
     */
    public double speedHelicopterEnemy;

    /**
     * determines the speed of enemy jets in a level.
     */
    public double speedJetEnemy;

    /**
     * determines the speed of allie cars in a level.
     */
    public double speedCarFriend;

    /**
     * constructor of {@link Level}.
     * @param difficulty level`s difficulty
     */
    public Level(Difficulty difficulty) {
        this.difficulty = difficulty;

    }
}
