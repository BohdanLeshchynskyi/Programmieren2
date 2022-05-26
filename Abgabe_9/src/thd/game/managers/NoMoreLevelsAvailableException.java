package thd.game.managers;

/**
 * Exception indicating that there are no more levels to play.
 */
public class NoMoreLevelsAvailableException extends RuntimeException {
    /**
     * Constructor of {@link NoMoreLevelsAvailableException}.
     * @param message error message to be shown
     */
    public NoMoreLevelsAvailableException(String message) {
        super(message);
    }
}
