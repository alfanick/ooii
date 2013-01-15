package gomoku.player;

import gomoku.*;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Implementation of AI bot using alpha-beta algorithm.
 * 
 * @see PlayerInterface
 * @author asiron
 */
public class Bot extends AIPlayer{
    
    /**
     * AlphaBeta optimizer
     */
    public AlphaBeta ab;
    
    public final int MAXDEPTH;
    
    public Bot(){
        super();
        System.out.println("Alpha-beta bot created!");
        MAXDEPTH = 6;
        System.out.printf("Setting MAXDEPTH to %d\n", MAXDEPTH);
    }
    
    
    @Override
    public void run(){
    
        while(!Thread.interrupted()){
            
            if(board.occupiedFields().isEmpty()){
                position = new Point(rules.getFirstMoveRectangle().x+1,rules.getFirstMoveRectangle().y+1);
                System.out.println("Finished calculating, yielding cpu..");
                Gomoku.game.playerDone();  
            }else{
                ab = new AlphaBeta(new Node(new GomokuBoard(board), board.lastMove(), board.get(board.lastMove())), board.get(board.lastMove()));
                System.out.println("Starting Iterative Deepening of Alpha-Beta pruning");
                for(int i=6; i<=MAXDEPTH; ++i) {
                    position = ab.algorithm(i) ;
                    System.out.printf("Iterative Deepening finished! - depth %d, Position found (%d,%d)\n", i, position.x, position.y);            
                }

                System.out.println("Finished calculating, yielding cpu..");
                Gomoku.game.playerDone();
            }
        }
    }
    
    
    /**
     * Transforms GomokuBoard which is obsolete for search-like algorithms into
     * a Map of Points and GomokuBoardState enums, that are effectively players
     * @return mapping of Points to Players
     */
    private Map<Point,GomokuBoardState> transformBoard(){
        Map<Point,GomokuBoardState> trBoard = new HashMap<>();
        for(Point p : board.with(GomokuBoardState.A))
            trBoard.put(p, GomokuBoardState.A);
        for(Point p : board.with(GomokuBoardState.B))
            trBoard.put(p, GomokuBoardState.B);
        
        return trBoard;
    }
}
