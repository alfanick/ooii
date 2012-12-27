package gomoku;

import gomoku.exceptions.*;
import gomoku.player.PlayerInterface;
import java.awt.Point;
import java.util.Stack;

/**
 * Gomoku referee. For every move it checks if
 * move is possible (pawn is put on empty field and
 * not forbidden), if board hasn't changed (AI may try
 * to move without acceptance of refereee), holds 
 * the history and tells the result.
 *
 * @author Amadeusz Juskowiak
 */
public class GomokuReferee {
    /**
     * Game rules that players and referee must obey.
     */
    public GameRules rules;
    
    /**
     * History of moves (positions of last played pawns).
     */
    public Stack<Point> history;
    
    /**
     * Creates Gomoku referee that obey given rules.
     * 
     * @param rules Rules to obey
     */
    public GomokuReferee(GameRules rules) {
        
        this.rules = new GameRules(rules.getSizeRectangle(), rules.getFirstMoveRectangle(), rules.getInRowToWin());
    }
    
    /**
     * Previous board, that referee rembembers, in case player would swap it.
     */
    public GomokuBoard previousBoard;
    
    /**
     * Checks if move by given player is acceptable. If move makes M-in-a-row,
     * referee ends the game.
     * 
     * Move must be proper, it must be put on empty field, inside allowed 
     * rectangle, to be accepted. Board cannot be changed by player!
     * 
     * If referee accepts move, then move can be made and it is put on history
     * stack.
     * 
     * @param player Current player index
     * @param position Proposed position
     * @return TRUE if moved is accepted, FALSE otherwise.
     * @throws IllegalMoveException Player tries to make illegal move (outside allowed rectangle or on not empty field).
     * @throws CorruptedBoardException Player did alter the board, game is finished.
     * @throws GameEndedException Player made M-in-a-row! Game is finished.
     */
    public boolean canMove(int player, Point position) throws
            IllegalMoveException,
            CorruptedBoardException,
            GameEndedException {
        
        if(!previousBoard.equals(Gomoku.game.board)){
            throw new CorruptedBoardException(position);            
        }
        
        if((position.x > 0) && (position.x < rules.getSizeRectangle().x) && (position.y > 0) && (position.y < rules.getSizeRectangle().y)){
            if(Gomoku.game.board.get(position)==GomokuBoardState.EMPTY){
                previousBoard = Gomoku.game.board;
                
                                /*wywolac sprawdzanie wygranej*/
                return true;
            }
        }
        throw new IllegalMoveException(position);
    }
    
    /**
     * Moves back last move.
     * 
     * @see #moveBack(int)
     */
    public void moveBack() {
        this.moveBack(1);
    }
    
    /**
     * Moves back n moves. Does alter the board.
     * 
     * @param n Number of moves to revert.
     */
    public void moveBack(int n) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    private boolean puttedInRow(Point position, int inRow, int player){
               
        
        return false;
    }
}
