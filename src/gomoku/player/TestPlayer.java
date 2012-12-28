package gomoku.player;

import gomoku.*;
import java.awt.Point;
import java.util.Random;

/**
 * Implementation of human player. It interacts with GUI.
 * 
 * @see PlayerInterface
 * @author Amadeusz Juskowiak
 */
public final class TestPlayer implements PlayerInterface {
    private Point position;
    private GameRules rules;
    private GomokuBoard board;
    
    public TestPlayer() {
        System.out.println("Created player");
        
        this.position = new Point(0,0);
    }

    @Override
    public void willMoveFor(float time) {
        System.out.printf("Got some time: %fs\n", time);
    }

    @Override
    public void shouldMoveNow() {
        System.out.println("I should move now!");
    }

    @Override
    public Point didMoveNow() {
        System.out.println("I moved now!");
        
        return this.position;
    }

    @Override
    public void run() {
        Random rnd = new Random();
        
        while (!Thread.interrupted()) {
            //System.out.print('.');
            do {
                this.position.x = rnd.nextInt(this.rules.getSizeRectangle().width);
                this.position.y = rnd.nextInt(this.rules.getSizeRectangle().height);
            } while (board.get(this.position) != GomokuBoardState.EMPTY);
            
            //Gomoku.gameThread.interrupt();
        }
    }

    @Override
    public void withBoardAndRules(GomokuBoard board, GameRules rules) {
        System.out.println("Got board and rules");
        this.rules = rules;
        this.board = board;
    }
    
}
