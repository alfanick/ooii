package gomoku;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.List;

/**
 * Gomoku board (intersections) representation.
 *
 * @author Amadeusz Juskowiak
 */
public class GomokuBoard {
    /**
     * Realisation of Gomoku board. Each field can
     * be empty, forbidden (if outside of first move rectangle)
     * or occupied by one of two players.
     * 
     * @see GomokuBoardState
     * @see GameRules#firstMoveRectangle
     */
    private GomokuBoardState board[][];
    
    /**
     * Size of board.
     * 
     * @see GameRules#sizeRectangle
     */
    private Rectangle size;
    
    
    /**
     * Constructor. Creates clean board by given size.
     * 
     * @param size Size of board (number of intersections). 
     */
    public GomokuBoard(Rectangle size) {
        this.size = size;
        this.board = new GomokuBoardState[size.width][size.height];
        
        this.clean();
        
        
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    /**
     * Cleans the board - makes all fields empty.
     * 
     * @see GomokuBoardState#EMPTY
     */
    public final void clean() {
        this.sweep(GomokuBoardState.EMPTY);
    }
    
    /**
     * Sets all fields on board to given state.
     * 
     * @param state State of board
     */
    public final void sweep(GomokuBoardState state) {
        for (int x = 0; x < this.size.width; x++) {
            for (int y = 0; y < this.size.height; y++) {
                this.board[x][y] = state;
            }
        }
    }
    
    /**
     * Lists empty fields.
     * 
     * @return Positions of empty fields.
     */
    public List<Point> emptyFields() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    /**
     * Lists occupied fields.
     * 
     * @return Positions of occupied fields. 
     */
    public List<Point> occupiedFields() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    /**
     * Lists fields of given state.
     * 
     * @param state State to find
     * @return Positions of given state.
     */
    public List<Point> with(GomokuBoardState state) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    /**
     * Puts state (pawn) on given position on board.
     * 
     * @param where Position
     * @param state State
     * @return Previous state on this field.
     */
    public GomokuBoardState set(Point where, GomokuBoardState state) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    /**
     * Get state on given position on board.
     * 
     * @param where Position
     * @return State on this field.
     */
    public GomokuBoardState get(Point where) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    /**
     * Checks if there is *any* given state on board.
     * 
     * @param state State
     * @return TRUE if there is at least one field with given state, FALSE otherwise.
     */
    public boolean any(GomokuBoardState state) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    /**
     * Checks if *all* of the fields are of given state.
     * 
     * @param state State
     * @return TRUE if all fields are given state, FALSE otherwise.
     */
    public boolean all(GomokuBoardState state) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
