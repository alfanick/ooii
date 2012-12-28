package gomoku.player;

import gomoku.*;
import java.awt.Point;

/**
 * Implementation of human player. It interacts with GUI.
 * 
 * @see PlayerInterface
 * @author Amadeusz Juskowiak
 */
public final class HumanPlayer implements PlayerInterface {
    /**
     * Temporary field for keeping point
     * received from UI
     */
    private Point point;
    
    /**
     * Saves received point from UI to private field
     * and then interrupts thread
     * 
     * @param p 
     */
    public void clickedAt(Point p){
        point = p;
        Gomoku.game.playerDone();
    }
    
    /**
     * Creates HumanPlayer that receives signals from UI
     * and initializes its values
     */
    public HumanPlayer() {
        System.out.println("Human player created!");
        point.x = 0;
        point.y = 0;
    }

    @Override
    public void willMoveFor(float time) {
        System.out.printf("Human player: I will move in %fs\n", time);
    }

    @Override
    public void shouldMoveNow() {
        System.out.println("Human player: I should move now!");
    }

    /**
     * Human player has just moved pawn and move is being passed to referee
     * 
     * @return Position where player put his pawn
     */
    @Override
    public Point didMoveNow() {
        System.out.println("Human player: I moved now!");
        return point;
    }

    /**
     * Main thread method, waits for signal from UI 
     */
    @Override
    public void run() {
        //while(!Thread.interrupted());
    }
    
    /**
     * Receives board and rules and passes it to player
     * @param board Board to think
     * @param rules Rules to obey
     */
    @Override
    public void withBoardAndRules(GomokuBoard board, GameRules rules) {
        System.out.println("Human player: I got board and rules");
    }
    
}
