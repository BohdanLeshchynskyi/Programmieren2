package thd.game.level;

/**
 * Level 2.
 */
public class Level2 extends Level{
    /**
     * constructor of {@link Level2}.
     * @param difficulty level`s difficulty
     */
    public Level2(Difficulty difficulty) {
        super(difficulty);
        this.levelNumber = 2;
        switch (this.difficulty) {
            case EASY :
                this.speedHelicopterEnemy = 1.5;
                this.speedJetEnemy = 2;
                this.speedCarFriend = 1;
                break;
            case STANDARD :
                this.speedHelicopterEnemy = 2;
                this.speedJetEnemy = 3;
                this.speedCarFriend = 1;
                break;
            default: break;
        }
    }
}
