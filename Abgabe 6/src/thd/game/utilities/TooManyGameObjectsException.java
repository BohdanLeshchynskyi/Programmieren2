package thd.game.utilities;

/**
 * Exception indicating that there are too many game objects in the game.
 */
public class TooManyGameObjectsException extends RuntimeException{
    /**
     * Constructor of {@link TooManyGameObjectsException}.
     * @param message error message to be shown
     */
    public TooManyGameObjectsException(String message) {
        super(message);
    }
}
