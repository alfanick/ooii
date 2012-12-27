package gomoku.ui;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    
    
    private JProgressBar progressbar;
    
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
        
        JPanel player1Panel = new JPanel();
        JPanel player2Panel = new JPanel();
        
        JLabel gomokuHeader = new JLabel("GOMOKU", JLabel.CENTER);
        gomokuHeader.setFont(new Font("Verdana", Font.BOLD, 32));
        gomokuHeader.setBounds(50, 100, 200, 100);
        
        JLabel player1 = new JLabel("Player 1", JLabel.CENTER);
        player1.setFont(new Font("Verdana", Font.BOLD, 18));
        player1.setForeground(Color.white);
        player1Panel.setBackground(Color.black);
        player1Panel.add(player1);
        player1.setBounds(80, 300, 140, 50);
        player1Panel.setBounds(80, 300, 140, 50);
        
        JLabel player2 = new JLabel("Player 2", JLabel.CENTER);
        player2.setFont(new Font("Verdana", Font.BOLD, 18));
        player2Panel.setBackground(Color.white);
        player2Panel.add(player2);
        player2.setBounds(80, 380, 140, 50);
        player2Panel.setBounds(80, 380, 140, 50);
        
        combobox1 = new JComboBox(players);
        combobox1.setSelectedIndex(-1);
        combobox1.setBounds(80, 350 , 140, 30);
        
        combobox2 = new JComboBox(players);
        combobox2.setSelectedIndex(-1);
        combobox2.setBounds(80, 430 , 140, 30);
        
        JProgressBar progressbar = new JProgressBar();
        progressbar.setBounds(80, 570 , 140, 30);
        
        JButton startButton = new JButton("Start");
<<<<<<< HEAD
        startButton.setBounds(80, 600, 140, 50);
        startButton.addActionListener(new startButtonListener());
=======
        startButton.setBounds(80, 620, 140, 50);
>>>>>>> b3cde620554458d0a5868175fea1c3b838f3d4c5
        
        JButton stopButton = new JButton("Stop");
        stopButton.setBounds(80, 690, 140, 50);
        
        JButton pauseButton = new JButton("Pause");
        pauseButton.setBounds(80, 760, 140, 50);
        
        JLabel timeLabel = new JLabel("Time", JLabel.CENTER);
        timeLabel.setFont(new Font("Verdana", Font.BOLD, 24));
        timeLabel.setBounds(80, 520, 140, 50);
        
        GomokuUIBoard gomokuUIBoard = new GomokuUIBoard();
        gomokuUIBoard.setBounds(300, 0, 900, 900);
        
        panel.add(gomokuHeader);
        panel.add(player1);
        panel.add(player1Panel);
        panel.add(player2);
        panel.add(player2Panel);
        panel.add(combobox1);
        panel.add(combobox2);
        panel.add(progressbar);
        panel.add(startButton);
        panel.add(stopButton);
        panel.add(pauseButton);
        panel.add(timeLabel);
        panel.add(gomokuUIBoard);
              
        show();
    }
    
    /**
     * Listener class.
     */
    class startButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evnt) {
            JOptionPane.showMessageDialog(new JPanel(),"Penis ja pierdziele \n chuj mi w cycki alalalalaltwset4neoridynhioeryoirdnhioergre", "Start game", JOptionPane.PLAIN_MESSAGE);
        }
    }
    
  //  class startPopUp 
    
    
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
