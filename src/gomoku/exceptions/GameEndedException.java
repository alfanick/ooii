package gomoku.exceptions;

import java.awt.Point;

/**
 * Referee found M-in-a-row so the game ends.
 *
 * @author Amadeusz Juskowiak
 */
public class GameEndedException extends GomokuException {
    /**
     * Creates the exception with position that made the game end.
     * 
     * @param position Last position.
     */
    public GameEndedException(Point position) {
        super(position);
    }
}
