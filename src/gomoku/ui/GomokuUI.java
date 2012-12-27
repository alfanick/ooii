package gomoku.ui;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Font;
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
     * Header (or it can be changed for img)
     */
    private JLabel gomokuHeader;
    
    /**
     * Player 1 and Plaayer 2
     */
    private JLabel player1;
    private JLabel player2;
    
    /**
     * Combo boxes for player 1 and 2
     */
    private JComboBox combobox1;
    private JComboBox combobox2;
    
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
        //Color myColor = Color.decode("#ffffcc");
        //setFont(new Font("TimesRoman", Font.BOLD, 24));
        //setBackground(myColor);
        setLocation(350, 75);
        setSize(H_SIZE, V_SIZE);
        //resize(H_SIZE,V_SIZE);
        setResizable(false);
        
        String[] players = {
	"Human 1", "Human 2", 
        "Bot 1", "Bot 2", 
	"Bot 3", "Bot 4",
        "Bot 5", "Bot 6"
        };
        
        JPanel panel = new JPanel();
        panel.setLayout(null);
        getContentPane().add(panel);
        
        JLabel player1 = new JLabel("Player 1", JLabel.CENTER);
        player1.setFont(new Font("Verdana", Font.BOLD, 18));
        player1.setBounds(80, 300, 140, 50);
        
        JLabel player2 = new JLabel("Player 2", JLabel.CENTER);
        player2.setFont(new Font("Verdana", Font.BOLD, 18));
        player2.setBounds(80, 380, 140, 50);
        
        combobox1 = new JComboBox(players);
        combobox1.setSelectedIndex(-1);
        combobox1.setBounds(80, 350 , 140, 30);
        
        combobox2 = new JComboBox(players);
        combobox2.setSelectedIndex(-1);
        combobox2.setBounds(80, 430 , 140, 30);
        
        JButton startButton = new JButton("Start");
        startButton.setBounds(80, 600, 140, 50);
        
        JButton stopButton = new JButton("Stop");
        stopButton.setBounds(80, 680, 140, 50);
        
        JButton pauseButton = new JButton("Pause");
        pauseButton.setBounds(80, 760, 140, 50);
        
        JLabel timeLabel = new JLabel("Time", JLabel.CENTER);
        timeLabel.setFont(new Font("Verdana", Font.BOLD, 24));
        timeLabel.setBounds(80, 540, 140, 50);
        
        GomokuUIBoard gomokuUIBoard = new GomokuUIBoard();
        gomokuUIBoard.setBounds(300, 0, 900, 900);
        
        panel.add(player1);
        panel.add(player2);
        panel.add(combobox1);
        panel.add(combobox2);
        panel.add(startButton);
        panel.add(stopButton);
        panel.add(pauseButton);
        panel.add(timeLabel);
        panel.add(gomokuUIBoard);
              
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
