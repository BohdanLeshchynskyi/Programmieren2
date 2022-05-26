package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.gameobjects.base.GameObject;
import thd.gameobjects.movable.HelicopterPlayer;
import thd.gameview.GameView;

import java.awt.*;

/**
 * Keeps track of the game`s score.
 */
public class Score extends GameObject {
    private int currentScore;

    /**
     * Constructor of {@link HelicopterPlayer}.
     * @param gameView a shared {@link GameView} reference
     * @param gamePlayManager a shared {@link GamePlayManager} reference
     * @param currentScore score to be set at the start of the game
     */
    public Score(GameView gameView, GamePlayManager gamePlayManager, int currentScore) {
        super(gameView, gamePlayManager);
        this.currentScore = currentScore;
    }

    /**
     * Increases game`s score by the given amount.
     * @param addScore score to be added
     */
    public void increaseScoreBy(int addScore) {
        currentScore += addScore;
    }

    @Override
    public void addToCanvas() {
        gameView.addTextToCanvas(String.valueOf(this.currentScore), 300, 0, 30, Color.YELLOW, 0);
    }

    @Override
    public void updateStatus() {

    }
}
