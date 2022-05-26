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
