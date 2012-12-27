package gomoku;

import gomoku.exceptions.CorruptedBoardException;
import gomoku.exceptions.GameEndedException;
import gomoku.exceptions.IllegalMoveException;
import gomoku.player.PlayerInterface;
import java.awt.Point;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implements Gomoku game logic. Maintains switching between
 * players, checking for game state after move (reacts to UI
 * move change and then asks referee for opinion).
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
     * Current player index
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
     * The constructor. Creates game for players and move time and
     * rules.
     * 
     * @param a White player
     * @param aTime White player move time
     * @param b Black player
     * @param bTime Black player move time
     * @param rules Rules of game
     */
    public Game(PlayerInterface a, float aTime, PlayerInterface b, float bTime, GameRules rules) {
        this.referee = new GomokuReferee(rules);
        
        this.players = new PlayerInterface[2];
        this.players[0] = a;
        this.players[1] = b;
        
        this.playersTime = new float[2];
        this.playersTime[0] = aTime;
        this.playersTime[1] = bTime;
        
    }
    
    /**
     * Creates Game thread - ask players for move, maintain their times,
     * asks referee for acceptance, makes the move and alters players.
     */
    @Override
    public void run() {
        // Current player thread
        Thread playerThread;
        
        // WHITE starts
        this.currentPlayer = 0;
        
        // Game loop
        while (true) {
            // Create thread
            playerThread = new Thread(this.players[this.currentPlayer]);
            
            // Give player board to analyse and rules
            this.players[this.currentPlayer].withBoardAndRules(this.board, this.referee.rules);
            
            // Let player think for some time
            this.players[this.currentPlayer].willMoveFor(this.playersTime[currentPlayer]);
            
            // Let player to board
            playerThread.start();
            
            try {
                // Let player thinks for given time 
                Thread.sleep(Math.round(this.playersTime[this.currentPlayer] * 1000));
                System.out.println("Time out!");
            } catch (InterruptedException ex) {
                // This shouldn't happen
            } finally {
                // Time has passed
                this.players[this.currentPlayer].shouldMoveNow();
                
                // Stop thinking!
                playerThread.interrupt();
            }
            
            // Players move
            Point move = this.players[this.currentPlayer].didMoveNow();
            
            try {
                // Player can move
                if (this.referee.canMove(this.currentPlayer, move)) {
                    this.board.set(move, this.currentPlayer == 0 ? GomokuBoardState.A : GomokuBoardState.B);
                    // Let UI draw new pawn
                    // Gomoku.ui.gomokuUIBoard.refresh();
                }
            } catch (IllegalMoveException ex) {
                Random random = new Random();
                
                // Illegal move is made - random move should be done
                // Pawn should be put on random EMPTY field
                while (this.board.get(move) != GomokuBoardState.EMPTY) {
                    move.x = random.nextInt(this.referee.rules.getSizeRectangle().width);
                    move.y = random.nextInt(this.referee.rules.getSizeRectangle().height);
                }
                
                // Put pawn
                this.board.set(move, this.currentPlayer == 0 ? GomokuBoardState.A : GomokuBoardState.B);
            } catch (CorruptedBoardException ex) {
                // Current player corrupted board - that shoudn't happen
                // UI shows some dialog and finishes game
                // Gomoku.ui.showCorruptedBoard(this.currentPlayer, ex);
                
                // Game is finished
                break;
            } catch (GameEndedException ex) {
                // Player won the game!
                // UI shows some dialog and finished game
                // Gomoku.ui.showGameEnded(this.currentPlayer, ex);
                
                // Game is finished
                break;
            } finally {
                System.out.printf("I moved to (%d, %d)\n", move.x, move.y);
            
                // Lets kill thread if someone try to lock the system
                if (playerThread.isAlive()) {
                    playerThread.stop();
                }
            }
            
            // Switch players
            this.currentPlayer = (this.currentPlayer + 1) % 2;
        }
    }
}