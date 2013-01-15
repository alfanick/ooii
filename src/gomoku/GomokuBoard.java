package gomoku;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
     * Position of last move
     * 
     * @see GomokuBoard#set(java.awt.Point, gomoku.GomokuBoardState) 
     */
    private Point lastMove;
    
    
    /**
     * Constructor. Creates clean board by given size.
     * 
     * @param size Size of board (number of intersections). 
     */
    public GomokuBoard(Rectangle size) {
        this.size = size;
        this.board = new GomokuBoardState[size.width][size.height];
        this.lastMove = new Point(-1,-1);
        
        this.clean();

    }
    
    /**
     * Copy constructor. Creates a new GomokuBoard, which is a copy
     * that given as parameter.
     * 
     * @param board Source GomokuBoard
     */
    public GomokuBoard(GomokuBoard board){
        //this.board = (GomokuBoardState[][]) board.getBoard().clone();
        this.board = new GomokuBoardState[board.getSize().width][board.getSize().height];
        for(int i=0; i<board.getSize().width; ++i){
            for(int j=0; j<board.getSize().height; j++){
                this.board[i][j] = board.getState(i,j);
            }
        }
        
        this.size = new Rectangle(board.getSize());
        this.lastMove = new Point(board.lastMove());
    }    
      
    
    /**
     * Returns 2-D array of GomokuBoardStates - board
     * 
     * @return board
     */
    public GomokuBoardState[][] getBoard(){
        return board;
    }    
    
    /**
     * Returns last move made on board.
     * 
     * @return Last move
     */
    public Point lastMove() {
        return lastMove;
    }
    
    /**
     * Returns size of board.
     * 
     * @return size
     */
    public Rectangle getSize(){
        return size;
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
     * Cleans the board, with FORBIDDEN state margin.
     * Creates the board with FORBIDDEN state on every field
     * except for allowed rectangle (where state is EMPTY).
     * 
     * @param allowed Rectangle of allowence
     * @see GomokuBoardState#FORBIDDEN
     * @see GomokuBoardState#EMPTY
     */
    public final void cleanWithForbidden(Rectangle allowed) {
        this.sweep(GomokuBoardState.FORBIDDEN);
        
        for (int x = 0; x < allowed.width; x++) {
            for (int y = 0; y < allowed.height; y++) {
                this.board[x+allowed.x][y+allowed.y] = GomokuBoardState.EMPTY;
            }
        }
    }
    
    /**
     * Sets all fields on board to given state.
     * 
     * @param state State of board
     */
    public final void sweep(GomokuBoardState state) {
        this.lastMove = new Point(-1,-1);
        
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
        return this.with(GomokuBoardState.EMPTY);
    }
    
    /**
     * Lists occupied fields.
     * 
     * @return Positions of occupied fields. 
     */
    public List<Point> occupiedFields() {
        List<Point> result = this.with(GomokuBoardState.A);
        result.addAll(this.with(GomokuBoardState.B));
        
        return result;
    }
    
    /**
     * Lists fields of given state.
     * 
     * @param state State to find
     * @return Positions of given state.
     */
    public List<Point> with(GomokuBoardState state) {
        List<Point> result = new ArrayList<>();
        
        for (int x = 0; x < this.size.width; x++) {
            for (int y = 0; y < this.size.height; y++) {
                if (this.board[x][y] == state) {
                    result.add(new Point(x, y));
                }
            }
        }
        
        return result;
    }
    
    /**
     * Puts state (pawn) on given position on board.
     * 
     * @param where Position
     * @param state State
     * @return Previous state on this field.
     */
    public GomokuBoardState set(Point where, GomokuBoardState state) {
        GomokuBoardState previous = this.get(where);
        
        this.lastMove = (Point)where.clone();
        
        this.board[where.x][where.y] = state;
        
        return previous;
    }
    
    /**
     * Puts given state on random position within allowed rectangle
     * 
     * @param state State
     * @param allowed Allowed rectangle
     * @return Position
     */
    public Point setRandom(GomokuBoardState state, Rectangle allowed) {
        Point position = new Point();
        Random random = new Random();
        
        do {
            position.x = random.nextInt(allowed.width);
            position.y = random.nextInt(allowed.height);
        } while (this.get(position) != GomokuBoardState.EMPTY);
        
        this.set(position, state);
        
        return position;
    }
    
    /**
     * Returns state on given position on board - access without Point
     * 
     * @param y - y coordinate
     * @param x - x coordinate
     * @return State on this field
     */
    public GomokuBoardState getState(int x, int y){
        try {
            return this.board[x][y];
        } catch (ArrayIndexOutOfBoundsException e) {
            return GomokuBoardState.FORBIDDEN;
        }
    }
    
    /**
     * Get state on given position on board.
     * 
     * @param where Position
     * @return State on this field.
     */
    public GomokuBoardState get(Point where) {
        try {
            return this.board[where.x][where.y];
        } catch (ArrayIndexOutOfBoundsException e) {
            return GomokuBoardState.FORBIDDEN;
        }
    }
    
    /**
     * Checks if there is *any* given state on board.
     * 
     * @param state State
     * @return TRUE if there is at least one field with given state, FALSE otherwise.
     */
    public boolean any(GomokuBoardState state) {
        for (int x = 0; x < this.size.width; x++) {
            for (int y = 0; y < this.size.height; y++) {
                if (this.board[x][y] == state) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    /**
     * Checks if *all* of the fields are of given state.
     * 
     * @param state State
     * @return TRUE if all fields are given state, FALSE otherwise.
     */
    public boolean all(GomokuBoardState state) {
        for (int x = 0; x < this.size.width; x++) {
            for (int y = 0; y < this.size.height; y++) {
                if (this.board[x][y] != state) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    /**
     * Prints boards for debugging
     * 
     * @param t Header to print
     */
    public void print(String t) {
        System.out.println(t);
        for (int x = 0; x < this.size.width; x++) {
            for (int y = 0; y < this.size.height; y++) {
                if (this.board[x][y].ordinal() < 2) {
                    System.out.printf("%d", this.board[x][y].ordinal());
                }
                else {
                    System.out.print('_');
                }
            }
            System.out.println();
        }
    }
}
