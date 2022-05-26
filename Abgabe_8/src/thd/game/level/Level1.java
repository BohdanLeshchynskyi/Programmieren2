package thd.game.level;

/**
 * Level 1.
 */
public class Level1 extends Level{

    /**
     * constructor of {@link Level1}.
     * @param difficulty level`s difficulty
     */
    public Level1(Difficulty difficulty) {
        super(difficulty);
        this.levelNumber = 1;
        switch (this.difficulty) {
            case EASY :
                this.speedHelicopterEnemy = 0;
                this.speedJetEnemy = 0;
                this.speedCarFriend = 0;
                break;

            case STANDARD :
                this.speedHelicopterEnemy = 0;
                this.speedJetEnemy = 0;
                this.speedCarFriend = 0;
                break;
            default: break;
        }
    }
}
