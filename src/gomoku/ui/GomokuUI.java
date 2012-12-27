package gomoku.ui;

import java.awt.*;

/**
 * Whole Gomoku GUI. Creates board with given size,
 * allow to select players, limit their times, start,
 * pause and stop (abandon) game. Show history of moves
 * and allow to move back. Show messages in case of
 * exceptions.
 * 
 * @author Amadeusz Juskowiak
 * @see http://www.eng.auburn.edu/~rayh/java/java/AWT.Introduction.html
 */
public class GomokuUI extends Frame implements Runnable  {
    /**
     * Start button
     */
    private Button startButton;
    
    /**
     * Stop button
     */
    private Button stopButton;
    
    /**
     * Pause button
     */
    private Button pauseButton;
    
    /**
     * Time of given player.
     */
    private Label timeLabel;
    
    /**
     * Board
     */
    private GomokuUIBoard gomokuUIBoard;
    
    /**
     * Thread
     */
    @Override
    public void run() {
        
        throw new UnsupportedOperationException("Not supported yet.");
    }
}