package game;

public class GameLoopManager {
    private GameView gameView;
    private JetEnemy jetEnemy;

    public GameLoopManager() {
        this.gameView = new GameView();
        this.jetEnemy = new JetEnemy(gameView);
    }

    public void startGame() {
        while(true) { // Der "Game-Loop"
            jetEnemy.updatePosition();
            jetEnemy.addToCanvas();
            gameView.printCanvas(); // Es werden maximal 120 Bilder pro Sekunde angezeigt.
        }
    }
}
