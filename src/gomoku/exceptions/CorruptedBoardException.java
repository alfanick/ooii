package gomoku.exceptions;

import java.awt.Point;

/**
 * Player (especially AI player and its author) may try to alter board
 * or change any field on board. If so, CorruptedBoardException is thrown,
 * and the game is ended (oponnent is a winner).
 *
 * @author Amadeusz Juskowiak
 */
public class CorruptedBoardException extends GameEndedException {
    /**
     * Creates the exception first position altered.
     * 
     * @param position First position that was altered. 
     */
    public CorruptedBoardException(Point position) {
        super(position);
    }
}
