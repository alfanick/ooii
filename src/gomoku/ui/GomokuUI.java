package gomoku.ui;

import gomoku.*;
import gomoku.player.TestPlayer;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;
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
    private JList list;
    
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
    //private JButton pauseButton;
    
    /**
     * Time of given player.
     */
    private JLabel timeLabel;
    
    /**
     * Time of given player.
     */
    private JLabel historyLabel;
    
    /**
     * Model for history
     */
    public DefaultListModel historyModel;
    
    /**  setDefaultCloseOperation(Frame.EXIT_ON_CLOSE);
     * Board
     */
    public GomokuUIBoard gomokuUIBoard;
    
    /**
     * The constructor
     */
    public GomokuUI() {
        historyModel = new DefaultListModel();
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
        
        /**
         * Zapis historii ruchow graczy/botow 
         */
        String[] history = {
            "New game"
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
        progressbar.setBounds(LEFT_MARGIN, TOP_MARGIN + 470 , 90, 30);
        
        list = new JList(history);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setModel(historyModel);
        
        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int index = list.locationToIndex(e.getPoint());
                    
                    Gomoku.game.referee.moveBack(index+1);
                    
                    repaint();
                    
                    if (index > 0) {
                        historyModel.removeRange(0, index-1);
                    } else {
                        historyModel.removeElementAt(0);
                    }
                    list.clearSelection();
                    
                    Gomoku.gameThread.start();
                }
            }
        });
        
        JScrollPane pane = new JScrollPane();
        pane.getViewport().add(list);
        pane.setBounds(LEFT_MARGIN + 120, TOP_MARGIN + 470, 100, 150);
        
        startButton = new JButton("Start");
        startButton.setBounds(LEFT_MARGIN, TOP_MARGIN + 520, 90, 40);
        startButton.addActionListener(new startButtonListener());
        
        stopButton = new JButton("Stop");
        stopButton.setBounds(LEFT_MARGIN, TOP_MARGIN + 580, 90, 40);
        
        //pauseButton = new JButton("Pause");
        //pauseButton.setBounds(LEFT_MARGIN, TOP_MARGIN + 590, 100, 50);
        
        timeLabel = new JLabel("Time", JLabel.CENTER);
        timeLabel.setFont(mediumFont);
        timeLabel.setBounds(LEFT_MARGIN, TOP_MARGIN + 420, 90, 50);
        
        historyLabel = new JLabel("History", JLabel.CENTER);
        historyLabel.setFont(mediumFont);
        historyLabel.setBounds(LEFT_MARGIN + 120, TOP_MARGIN + 420, 100, 50);
                
        
        
        GameRules rules = new GameRules(new Rectangle(19,19), new Rectangle(7,7,5,5), 5);
        
        gomokuUIBoard = new GomokuUIBoard();
        gomokuUIBoard.createIntersections(rules.getSizeRectangle());
        gomokuUIBoard.setBounds(300, 0, 900, 900);
        
        panel.add(gomokuHeader);
        panel.add(combobox1);
        panel.add(combobox2);
        panel.add(player1);
        panel.add(player1Panel);
        panel.add(player2);
        panel.add(player2Panel);
        panel.add(progressbar);
        panel.add(pane);
        panel.add(startButton);
        panel.add(stopButton);
        //panel.add(pauseButton);
        panel.add(timeLabel);
        panel.add(historyLabel);
        panel.add(gomokuUIBoard);
              
        //setVisible(true);
        //show();
    }
    
    /**
     * Progress timer
     */
    private Timer progressTimer;
    
    /**
     * Starts moving progress bar
     * 
     * @param time Time for 100%
     */
    public void startTicking(float time) {
        progressbar.setValue(0);
        
        progressTimer = new Timer();
        progressTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                progressbar.setValue(Math.min(progressbar.getValue() + 1, 100));
            } 
        }, 0l, Math.round(time*10));
    }
    
    /**
     * Stops moving progress bar
     */
    public void stopTicking() {
        try {
            progressTimer.cancel();
        } catch (NullPointerException e) {
            
        }
    }
    
    /**
     * Listener class.
     */
    class startButtonListener implements ActionListener {
        int boardWidth, boardHeight, firstMoveX, firstMoveY, firstMoveBoardWidth, firstMoveBoardHeight, mInRow;
        float timeWhite, timeBlack;
        @Override
        public void actionPerformed(ActionEvent evnt) {

            boolean excep;
            JTextField field1 = new JTextField("19");  
            JTextField field2 = new JTextField("19");
            JTextField field3 = new JTextField("5");  
            JTextField field4 = new JTextField("0.1");  
            JTextField field5 = new JTextField("0.1");            
            JTextField field6 = new JTextField("7");  
            JTextField field7 = new JTextField("7");
            JTextField field8 = new JTextField("5");  
            JTextField field9 = new JTextField("5");  

            Object[] message = {
                "RULES",
                "Board width:", field1,  
                "Board height:", field2,  
                "M-in-row:", field3,  
                "Time for white player:", field4,  
                "Time for nigger:", field5,  
                "First move x:", field6,
                "First move y:", field7,
                "First move board width:", field8,
                "First move board height:", field9
            };  
            
            do {
                excep = false;
                int option = JOptionPane.showConfirmDialog(new JPanel(), message, "Star Game", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);  
            

                if (option == JOptionPane.OK_OPTION) {
                    try {
                       boardWidth = Integer.parseInt(field1.getText()); 
                       boardHeight = Integer.parseInt(field2.getText());  
                       mInRow = Integer.parseInt(field3.getText());  
                       timeWhite = Float.parseFloat(field4.getText());  
                       timeBlack = Float.parseFloat(field5.getText());  
                       firstMoveX = Integer.parseInt(field6.getText()); 
                       firstMoveY = Integer.parseInt(field7.getText()); 
                       firstMoveBoardWidth = Integer.parseInt(field8.getText()); 
                       firstMoveBoardHeight = Integer.parseInt(field9.getText()); 
                        if  (boardWidth > 19 || boardHeight > 19 || boardWidth < 3 || boardHeight < 3) {
                            excep = true;
                            JOptionPane.showMessageDialog(new JPanel(), "Board's maximal size is 19x19!", "Wrong board size!", JOptionPane.PLAIN_MESSAGE);
                       }    else if (firstMoveBoardWidth > boardWidth || firstMoveBoardHeight > boardHeight) {
                            excep = true;
                            JOptionPane.showMessageDialog(new JPanel(), "Board's first move space cannot be bigger than board's size!", "Wrong first move board size!", JOptionPane.PLAIN_MESSAGE); 
                       }    else if (mInRow > Math.min(boardHeight, boardWidth)) {
                            excep = true;
                            JOptionPane.showMessageDialog(new JPanel(), "M-in-row must be less or equal to shorter side", "Wrong M-in-row length!", JOptionPane.PLAIN_MESSAGE);    
                       }
                    }   catch (NumberFormatException exception) {
                            excep = true;
                            JOptionPane.showMessageDialog(new JPanel(), "Not all fields are right numbers!", "Wrong input!", JOptionPane.PLAIN_MESSAGE);
                         }
                   }
               } while (excep);
                startButton.setText("Pause");          
                historyModel.clear();
                historyModel.addElement("Game started");
                progressbar.setValue(0);
                
                GameRules rules = new GameRules(new Rectangle(boardHeight, boardWidth), 
                                                new Rectangle(firstMoveX, firstMoveY, firstMoveBoardHeight, firstMoveBoardWidth), mInRow);
                
                gomokuUIBoard.createIntersections(rules.getSizeRectangle());
                stopTicking();
                Gomoku.game = new Game(new TestPlayer(), timeWhite, new TestPlayer(), timeBlack, rules);
                //refresh();
                gomokuUIBoard.repaint();
                
                Gomoku.gameThread = new Thread(Gomoku.game);
        
                Gomoku.gameThread.start();
         }
   }
    
    /**
     * Updates UI and history
     */
    public void refresh() {
        Point p = Gomoku.game.board.lastMove();
        if (p.x != -1) {
            StringBuilder s = new StringBuilder();
            s.append(Gomoku.game.board.get(p) == GomokuBoardState.A ? "White" : "Black");
            s.append(' ');
            s.append(GomokuUIBoard.letters[p.y]);
            s.append(GomokuUIBoard.numbers[p.x]);
            
            if (!((String) this.historyModel.getElementAt(0)).equals(s.toString())) {
                this.historyModel.add(0, s.toString());
            }
        }
       
        repaint();
    }
    
      
    
    
    
    /**
     * Thread
     */
    @Override
    public void run() {
        //GomokuUI gomoku = new GomokuUI();
        //gomoku.setVisible(true);
        Gomoku.ui.setVisible(true);
    }
}
