package gomoku;

import gomoku.player.PlayerInterface;

/**
 * Implements Gomoku game logic. Maintains switching between
 * players, checking for game state after move (reacts to UI
 * move change and then asks referee for opinion).
 * 
 * Implements Singleton.
 *
 * @author Amadeusz Juskowiak
 */
public class Game implements Runnable {
    /**
     * Players in game, must be two of them.
     * Every turn the other one plays.
     */
    protected PlayerInterface[] players;

    /**
     * Counts time for players.
     */
    protected float[] playersTime;
    
    /**
     * Maintains index (0 or 1) for active player.
     */
    protected int currentPlayer;
    
    /**
     * Holds the board. It is accessible via singleton for
     * other components of game. Board should be synchronized
     * between threads.
     * 
     * Board is NxN sized, however Gomoku game is played
     * on NxN *intersections*. So board is intersections state
     * for board (N+1)x(N+1).
     */
    public GomokuBoard board;
    
    /**
     * Gomoku referee instance. Referee gives players the rules,
     * checks if move is acceptable, stores moves history (and 
     * moves back) and tells the result.
     */
    public GomokuReferee referee;
    
    
    /**
     * Creates Game thread - ask players for move, maintain their times,
     * asks referee for acceptance, makes the move and alters players.
     */
    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
