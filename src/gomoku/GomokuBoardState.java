package gomoku;

/**
 * Represents state on Gomoku board. Could be empty, forbidden or pawn.
 *
 * @see GomokuBoard
 * @author Amadeusz Juskowiak
 */
public enum GomokuBoardState {
    /**
     * Assume A is first player, #0 index.
     */
    A,
    
    /**
     * Assume B is second player, #1 index.
     */
    B,
    
    /**
     * Empty field (move is allowed)
     */
    EMPTY,
    
    /**
     * Forbidden field (fields outside of allowed moves rectangle)
     * 
     * @see GameRules
     */
    FORBIDDEN,
    
    /**
     * Winning field, meaning game is already won and this point was part of 
     * the winning streak
     *
     */
    WINNING
}
