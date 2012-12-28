package gomoku.player;

import gomoku.*;
import java.awt.Point;

/**
 * Base class for all bots.
 * 
 * @author Amadeusz Juskowiak
 */
public class AIPlayer implements PlayerInterface {
    
    /**
     * Creates bot, initializes position with (0,0)
     * 
     * @see #position
     */
    public AIPlayer() {
        this.position = new Point(0,0);
    }
    
    /**
     * Time for current move (in seconds)
     */
    protected float time;

    /**
     * Sets time for current move. Bot should now
     * start thinking - set some flag for thread and 
     * start thinking.
     * 
     * Thinking should happen in #run() only!
     * 
     * @param time 
     * @see #run() 
     */
    @Override
    public void willMoveFor(float time) {
        this.time = time;
    }

    /**
     * Bot should stop thinking now - set some
     * flag for thread, stop thinking and store position.
     */
    @Override
    public void shouldMoveNow() {
        System.out.println("I should move now!");
    }
    
    /**
     * Position of move
     */
    protected Point position;

    /**
     * Bot must stop thinking now!
     * 
     * @return Position of move
     */
    @Override
    public Point didMoveNow() {
        return this.position;
    }

    /**
     * Main thread of bot - everything should be done here.
     * If bot stops thinking, it should leave turn.
     * 
     * @see Game#playerDone() 
     */
    @Override
    public void run() {
    }

    /**
     * Rules of game
     */
    protected GameRules rules;
    
    /**
     * Current board
     */
    protected GomokuBoard board;
    
    /**
     * Bot gets current rules. Should store them.
     */
    @Override
    public void withBoardAndRules(GomokuBoard board, GameRules rules) {
        System.out.println("Got board and rules - bot");
        this.rules = rules;
        this.board = board;
    }
    
}
