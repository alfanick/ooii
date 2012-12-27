package gomoku.exceptions;

import java.awt.Point;

/**
 * Game end because of draw (all fields are occupied).
 * 
 * @author Amadeusz Juskowiak
 */
public class DrawGameEndedException extends GameEndedException {
    /**
     * Last move
     * 
     * @param position Last position
     */
    public DrawGameEndedException(Point position) {
        super(position);
    }
}
