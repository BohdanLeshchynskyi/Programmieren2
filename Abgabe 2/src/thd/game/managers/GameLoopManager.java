package thd.game.managers;

import thd.gameview.GameView;

/**
 * Manages the game course of the game.
 */
public class GameLoopManager {
    private final GameView gameView;
    private GameObjectManager gameObjectManager;

    /**
     * Constructor of {@link GameLoopManager}.
     */
    public GameLoopManager() {
        this.gameView = new GameView();
        this.gameView.setStatusText("Bohdan Leshchynskyi - Java Programmierung SS 2022");
        this.gameView.setWindowTitle("Chopper Command");
        this.gameView.setWindowIcon("ChopperCommandIcon.png");
        this.gameObjectManager = new GameObjectManager(gameView);
    }

    /**
     * Starts the game course.
     */
    public void startGame() {
        while(true) { // Der "Game-Loop"
            gameObjectManager.updateGameObjects();
            gameView.printCanvas(); // Es werden maximal 120 Bilder pro Sekunde angezeigt.
        }
    }
}
