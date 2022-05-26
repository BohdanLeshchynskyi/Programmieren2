package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.gameobjects.base.GameObject;
import thd.gameview.GameView;

import java.awt.*;

public class Score extends GameObject {
    private int currentScore;

    public Score(GameView gameView, GamePlayManager gamePlayManager, int currentScore) {
        super(gameView, gamePlayManager);
        this.currentScore = currentScore;
    }

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
