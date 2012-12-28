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
     
     
     static final int LEFT_MARGIN = 50;
     
     static final int TOP_MARGIN = 100;

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
	"Human",
        "Bot 1", "Bot 2", 
	"Bot 3", "Bot 4",
        "Bot 5", "Bot 6"
        };
        
        Font smallFont = new Font("Verdana", Font.BOLD, 18);
        Font mediumFont = new Font("Verdana", Font.BOLD, 24);
        Font headerFont = new Font("Georgia", Font.BOLD, 32);
        
        JPanel panel = new JPanel();
        panel.setLayout(null);
        getContentPane().add(panel);
        
        JPanel player1Panel = new JPanel();
        JPanel player2Panel = new JPanel();
        
        gomokuHeader = new JLabel("GOMOKU", JLabel.CENTER);
        gomokuHeader.setFont(headerFont);
        gomokuHeader.setBounds(LEFT_MARGIN/2, TOP_MARGIN, 200, 100);
        
        player1 = new JLabel("White", JLabel.CENTER);
        player1.setFont(smallFont);
        player1.setForeground(Color.black);
        player1Panel.setBackground(Color.white);
        player1Panel.add(player1);
        player1.setBounds(LEFT_MARGIN, TOP_MARGIN + 200, 140, 50);
        player1Panel.setBounds(LEFT_MARGIN, TOP_MARGIN + 200, 140, 90);
        
        player2 = new JLabel("Black", JLabel.CENTER);
        player2.setFont(smallFont);
        player2.setForeground(Color.white);
        player2Panel.setBackground(Color.black);
        player2Panel.add(player2);
        player2.setBounds(LEFT_MARGIN, TOP_MARGIN + 280, 140, 50);
        player2Panel.setBounds(LEFT_MARGIN, TOP_MARGIN + 280, 140, 90);
        
        combobox1 = new JComboBox(players);
        combobox1.setSelectedIndex(0);
        combobox1.setBounds(LEFT_MARGIN, TOP_MARGIN + 250 , 140, 30);
        
        combobox2 = new JComboBox(players);
        combobox2.setSelectedIndex(0);
        combobox2.setBounds(LEFT_MARGIN, TOP_MARGIN + 330 , 140, 30);
        
        progressbar = new JProgressBar();
        progressbar.setBounds(LEFT_MARGIN, TOP_MARGIN + 470 , 140, 30);
        
        startButton = new JButton("Start");
        startButton.setBounds(LEFT_MARGIN, TOP_MARGIN + 520, 140, 50);
        startButton.addActionListener(new startButtonListener());
        
        stopButton = new JButton("Stop");
        stopButton.setBounds(LEFT_MARGIN + 100, TOP_MARGIN + 590, 40, 50);
        
        pauseButton = new JButton("Pause");
        pauseButton.setBounds(LEFT_MARGIN, TOP_MARGIN + 590, 100, 50);
        
        timeLabel = new JLabel("Time", JLabel.CENTER);
        timeLabel.setFont(mediumFont);
        timeLabel.setBounds(LEFT_MARGIN, TOP_MARGIN + 420, 140, 50);
        
        gomokuUIBoard = new GomokuUIBoard();
        gomokuUIBoard.setBounds(300, 0, 900, 900);
        
        panel.add(gomokuHeader);
        panel.add(combobox1);
        panel.add(combobox2);
        panel.add(player1);
        panel.add(player1Panel);
        panel.add(player2);
        panel.add(player2Panel);
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
            JTextField field1 = new JTextField();  
            JTextField field2 = new JTextField();  
            JTextField field3 = new JTextField();  
            JTextField field4 = new JTextField();  
            JTextField field5 = new JTextField();  
            Object[] message = {
                "RULES",
                "Board width:", field1,  
                "Board height:", field2,  
                "M-in-row:", field3,  
                "Time for white player:", field4,  
                "Time for nigger:", field5,  
            };  
            //class startPopUp 
            int option = JOptionPane.showConfirmDialog(new JPanel(), message, "Star Game", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);  
            
            if (option == JOptionPane.OK_OPTION) {
                String boardWidth = field1.getText();  
                String boardHeight = field2.getText();  
                String mInRow = field3.getText();  
                String timeWhite = field4.getText();  
                String timeBlack = field5.getText();  
            }
         }
     }
      
    
    
    
    /**
     * Thread
     */
    @Override
    public void run() {
        GomokuUI gomoku = new GomokuUI();
        gomoku.setVisible(true);
    }
}
