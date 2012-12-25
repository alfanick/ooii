package gomoku;

import gomoku.ui.*;

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
     * Main program loop. Creates game logic and GUI.
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}
