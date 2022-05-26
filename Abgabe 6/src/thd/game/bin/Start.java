package thd.game.bin;

import thd.game.managers.GameLoopManager;

/**
 * Starts the game, entry point.
 */
public class Start {
    /**
     * Entry point.
     * @param args optional console arguments
     */
    public static void main(String[] args) {
        GameLoopManager gameLoopManager = new GameLoopManager();
        gameLoopManager.startGame();
    }
}