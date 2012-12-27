package gomoku;

import gomoku.exceptions.*;
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
        this.previousBoard = new GomokuBoard(rules.getSizeRectangle());
        this.previousBoard.cleanWithForbidden(rules.getFirstMoveRectangle());
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
        
        //checking if boards are equal
        try{
            areBoardsEqual();                   
        }catch(CorruptedBoardException e){
            throw e;
        }
        
        // checking if move is not outside the board
        if((position.x > 0) && (position.x < rules.getSizeRectangle().x) && (position.y > 0) && (position.y < rules.getSizeRectangle().y)){
            
            //checking if field is empty
            if(Gomoku.game.board.get(position) == GomokuBoardState.EMPTY){
                
                //checking if it is first move
                if(history.empty()){
                    previousBoard.clean();
                }
                
                //state of a field depends on which of players takes turn
                previousBoard.set(position, GomokuBoardState.values()[player]);
                
                //checking if one of players won
                if(puttedMInRow(position, rules.getInRowToWin(), player)){
                    throw new GameEndedException(position);
                }
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
        int i;
        Point p;
        
        for(i=0; i<n; i++){
            if(!history.empty()){
                p=history.pop();
                
                previousBoard.set(p, GomokuBoardState.EMPTY);
                Gomoku.game.board.set(p, GomokuBoardState.EMPTY);
            
            }else{
                previousBoard.cleanWithForbidden(rules.getFirstMoveRectangle());
                Gomoku.game.board.cleanWithForbidden(rules.getFirstMoveRectangle());
                break;
            }                
        }
    }
    
    /**
     * Checks if board remembered in GomokuReferee is equal to global board
     * 
     * @return true if it is
     * @throws CorruptedBoardException if it isn't
     */
    private boolean areBoardsEqual() throws CorruptedBoardException{
        int i,j;
        
        for(i=0; i<rules.getSizeRectangle().x; i++){
            for(j=0; j<rules.getSizeRectangle().y; j++){
                Point p = new Point(i,j);
                if(previousBoard.get(p) != Gomoku.game.board.get(p)){
                    throw new CorruptedBoardException(p);
                }
            }
        }
        
        return true;
    }
    
    /**
     * Checks if there is M powns in a row
     * 
     * @param position last move
     * @param inRow     number of powns in a row to win
     * @param player    player that did last move
     * @return          true if there is M powns in a row, false otherwise
     */
    private boolean puttedMInRow(Point position, int inRow, int player){
               
        int[][] count = {{0,0,0},{0,0,0},{0,0,0}}; 
        int i;
        
        for(i=1; i<=inRow; i++){
            if(position.y-i>=0){
                if(Gomoku.game.board.get(new Point(position.x,position.y-i)).ordinal() == player){
                    count[0][1]++;
                }else{
                    break;
                }
            }else{break;}
        }
        
        for(i=1; i<=inRow; i++){
            if(position.y+i < rules.getSizeRectangle().y){
                if(Gomoku.game.board.get(new Point(position.x,position.y+i)).ordinal() == player){
                    count[2][1]++;
                }else{
                    break;
                }
            }else{break;}
        }
        
        for(i=1; i<=inRow; i++){
            if(position.x-i >= 0){
                if(Gomoku.game.board.get(new Point(position.x-i,position.y)).ordinal() == player){
                    count[1][0]++;
                }else{
                    break;
                }
            }else{break;}
        }
        
        for(i=1; i<=inRow; i++){
            if(position.x+i < rules.getSizeRectangle().x){
                if(Gomoku.game.board.get(new Point(position.x+i,position.y)).ordinal() == player){
                    count[1][0]++;
                }else{
                    break;
                }
            }else{break;}
        }
        
        for(i=1; i<=inRow; i++){
            if((position.y-i >= 0) && (position.x-i >= 0)){
                if(Gomoku.game.board.get(new Point(position.x-i,position.y-i)).ordinal() == player){
                    count[0][0]++;
                }else{
                    break;
                }
            }else{break;}
        }
        
        for(i=1; i<=inRow; i++){
            if((position.y+i < rules.getSizeRectangle().y) && (position.x+i < rules.getSizeRectangle().x)){
                if(Gomoku.game.board.get(new Point(position.x+i,position.y+i)).ordinal() == player){
                    count[2][2]++;
                }else{
                    break;
                }
            }else{break;}
        }
        
        for(i=1; i<=inRow; i++){
            if((position.y-i >= 0) && (position.x+i < rules.getSizeRectangle().x)){
                if(Gomoku.game.board.get(new Point(position.x+i,position.y-i)).ordinal() == player){
                    count[0][2]++;
                }else{
                    break;
                }
            }else{break;}
        }
        
        for(i=1; i<=inRow; i++){
            if((position.y+i < rules.getSizeRectangle().y) && (position.x-i >= 0)){
                if(Gomoku.game.board.get(new Point(position.x-i,position.y+i)).ordinal() == player){
                    count[2][0]++;
                }else{
                    break;
                }
            }else{break;}
        }
        
        if((count[0][0] + count[2][2] + 1 >= inRow) ||
           (count[0][1] + count[2][1] + 1 >= inRow) ||
           (count[0][2] + count[2][0] + 1 >= inRow) ||
           (count[1][0] + count[1][2] + 1 >= inRow)){
            
            return true; 
        }
        
        return false;
    }
}
