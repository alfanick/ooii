package gomoku.exceptions;

import java.awt.Point;
import java.util.ArrayList;

/**
 * Referee found M-in-a-row so the game ends.
 *
 * @author Amadeusz Juskowiak
 */
public class GameEndedException extends GomokuException {
    
    /**
     * List of game winning points
     */
    private ArrayList<Point> winningPoints;
    
    /**
     * Creates the exception with position that made the game end,
     * but without points that ended game, meaning game was probably ended
     * inconclusively
     * 
     * @param position Last position.
     */
    public GameEndedException(Point position) {
        super(position);
    }
    
    /**
     * Creates the exception with position that made the game end, and 
     * list of points that ended game
     * @param position Last position
     * @param gameWinningPoints list of game winning points 
     */
    public GameEndedException(Point position, ArrayList<Point> gameWinningPoints) {
        super(position);
        winningPoints = gameWinningPoints;
    }
    
    /**
     * Returns current list of winning points
     * 
     * @return winning points list
     */
    public ArrayList<Point> getPoints(){
        return winningPoints;
    }
}
