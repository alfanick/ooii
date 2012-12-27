package gomoku.ui;

import javax.swing.*;
//import java.awt.*;

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
public class GomokuUI extends JFrame implements Runnable  {
    /**
     * Horizonstal size
     */
     static final int H_SIZE = 1200;
     
     /**
      * Vertical size
      */
     static final int V_SIZE = 900;

    /**
     * Start button
     */
    private JButton startButton;
    
    /**
     * Stop button
     */
    private JButton stopButton;
    
    /**
     * Pause button
     */
    private JButton pauseButton;
    
    /**
     * Time of given player.
     */
    private JLabel timeLabel;
    
    /**  setDefaultCloseOperation(Frame.EXIT_ON_CLOSE);
     * Board
     */
    private GomokuUIBoard gomokuUIBoard;
    
    /**
     * The constructor
     */
    public GomokuUI() {
        initUI();
    }
    
    /**
     * UI initialization
     */
    public final void initUI() {
        setTitle("Gomoku");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      //  Color myColor = Color.decode("#ffffcc");
       // setFont(new Font("TimesRoman", Font.BOLD, 24));
        //setBackground(myColor);
        setLocation(350, 75);
        setSize(H_SIZE, V_SIZE);
//        resize(H_SIZE,V_SIZE);
        setResizable(false);
        show();
    }
    
    /**
     * Thread
     */
    @Override
    public void run() {
       // throw new UnsupportedOperationException("Not supported yet.");
        GomokuUI gomoku = new GomokuUI();
        gomoku.setVisible(true);
    }
}
