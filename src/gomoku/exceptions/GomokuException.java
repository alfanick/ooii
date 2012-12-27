package gomoku.exceptions;

import java.awt.Point;

/**
 * Common Gomoku exception.
 *
 * @author Amadeusz Juskowiak
 */
public class GomokuException extends Exception {
    /**
     * Position of move, that made the exception
     */
    protected Point position;
    
    /**
     * Creates exception caused by given position.
     * 
     * @param position Cause.
     */
    public GomokuException(Point position) {
        this.position = position;   
    }
    
    /**
     * Returns position that caused the exception.
     * 
     * @return Position of cause
     */
    public Point getPosition() {
        return this.position;
    }
}
