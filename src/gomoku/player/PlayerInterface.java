package gomoku.player;

import gomoku.*;
import java.awt.Point;

/**
 * Interface the players must implement.
 * 
 * Player is lives for one game only.
 * Move cycle:
 *   0. Player gets board and rules.
 *   1. Player gets to know how much time he has for his move.
 *   2. Player thinks about its move.
 *   3. Player gets notice that he should finish his thinking now. No more
 *      time is added.
 *   4. Player must tell its move! Otherwise random move is made.
 *
 * @author Amadeusz Juskowiak
 */
public interface PlayerInterface extends Runnable {
    /**
     * Delegates board and rules to player.
     * 
     * @param board Board to think
     * @param rules Rules to obey
     */
    public void withBoardAndRules(GomokuBoard board, GameRules rules);
    
    /**
     * Player get to know how much time he has to think.
     * Now it should start thinking.
     * 
     * @param time Time limit
     */
    public void willMoveFor(float time);

    /**
     * Player need to stop thinking now.
     */
    public void shouldMoveNow();
    
    /**
     * Player now "moves". It proposes position of move to referee.
     * 
     * @return Position where player wants to put its pawn.
     */
    public Point didMoveNow();
    
    /**
     * Main thread method. Player should do all his stuff in here and
     * react to signal send by other methods.
     */
    @Override
    public void run();
}
