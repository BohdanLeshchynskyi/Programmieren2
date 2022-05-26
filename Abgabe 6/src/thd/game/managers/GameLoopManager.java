package thd.game.managers;

import thd.gameview.GameView;


/**
 * Manages the game course of the game.
 */
public class GameLoopManager {
    private final GameView gameView;
    private final GameObjectManager gameObjectManager;
    private final GamePlayManager gamePlayManager;
    private final InputManager inputManager;

    /**
     * Constructor of {@link GameLoopManager}.
     */
    public GameLoopManager() {
        this.gameView = new GameView();
        this.gameView.setStatusText("Bohdan Leshchynskyi - Java Programmierung SS 2022");
        this.gameView.setWindowTitle("Chopper Command");
        this.gameView.setWindowIcon("ChopperCommandIcon.png");
        this.gamePlayManager = new GamePlayManager(gameView);
        this.gameObjectManager = new GameObjectManager(gameView, gamePlayManager);
        gamePlayManager.setGameObjectManager(gameObjectManager);
        this.inputManager = new InputManager(gameView, gameObjectManager.helicopterPlayer);
    }

    /**
     * Starts the game course.
     */
    public void startGame() {
        while(true) { // Der "Game-Loop"
            gamePlayManager.updateGamePlay();
            inputManager.updateUserInputs();
            gameObjectManager.updateGameObjects();
            gameView.printCanvas(); // Es werden maximal 120 Bilder pro Sekunde angezeigt.
        }
    }


}
