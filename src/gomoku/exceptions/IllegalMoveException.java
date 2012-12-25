package gomoku.exceptions;

import java.awt.Point;

/**
 * Player (especially {HumanPlayer}) made an illegal move. Player
 * should be allowed to try again.
 *
 * @author Amadeusz Juskowiak
 */
public class IllegalMoveException extends GomokuException {
    /**
     * Creates an exception with position of cause.
     * 
     * @param position Illegal move cause.
     */
    public IllegalMoveException(Point position) {
        super(position);
    }
}
