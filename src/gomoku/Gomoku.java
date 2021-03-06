package gomoku;

import gomoku.player.TestPlayer;
import gomoku.ui.*;
import java.awt.Rectangle;

/**
 * Main game class. Creates the threads,
 *
 * @author Amadeusz Juskowiak
 */
public class Gomoku {
    /**
     * Game logic thread.
     */
    public static Game game;
    
    /**
     * Game GUI.
     */
    public static GomokuUI ui;
    
    /**
     * UI Thread
     */
    public static Thread uiThread;
    
    /**
     * Game Thread
     */
    public static Thread gameThread;

    /**
     * Main program loop. Creates game logic and GUI.
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {

       Gomoku.ui = new GomokuUI();
       Gomoku.uiThread = new Thread(Gomoku.ui);

        
        // This should be done by UI when game starts
        // ---
        

        Gomoku.uiThread.start();



        

        System.out.println("Hello, World!");
    }
}
