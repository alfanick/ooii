package gomoku;

import java.awt.Rectangle;

/**
 * Contains rules definition. It could be subclassed
 * or read from file.
 *
 * Gomoku rules differs by board size, length of chain
 * needed to win and first rectangle in which player can
 * make games first move.
 * 
 * @author Amadeusz Juskowiak
 */
public class GameRules {
    /**
     * Board size (intersections).
     * (X,Y) must be (0,0).
     */
    private Rectangle sizeRectangle;
    
    /**
     * @see #sizeRectangle
     * @return Board size
     */
    public Rectangle getSizeRectangle() {
        return this.sizeRectangle;
    }
    
    /**
     * Area of allowance for the first move.
     */
    private Rectangle firstMoveRectangle;
    
    /**
     * @see #firstMoveRectangle
     * @return Area of allowance 
     */
    public Rectangle getFirstMoveRectangle() {
        return this.firstMoveRectangle;
    }
    
    /**
     * Number of same colored pawns in a row, 
     * needed to win a game.
     */
    private int inRowToWin;
    
    /**
     * @see #inRowToWin
     * @return Number of same colored pawns to win. 
     */
    public int getInRowToWin() {
        return this.inRowToWin;
    }
    
    /**
     * Create rules with given board size and M-in-a-row. Rules cannot be
     * changed while in game.
     * 
     * @param size Size of baord
     * @param firstMove Rectangle for first move
     * @param inRowToWin M-in-a-row
     */
    public GameRules(Rectangle size, Rectangle firstMove, int inRowToWin) {
        this.sizeRectangle = size;
        this.firstMoveRectangle = firstMove;
        this.inRowToWin = inRowToWin;
    }
}
