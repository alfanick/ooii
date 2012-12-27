package gomoku.player;

import gomoku.*;
import java.awt.Point;

/**
 * Implementation of human player. It interacts with GUI.
 * 
 * @see PlayerInterface
 * @author Amadeusz Juskowiak
 */
public final class TestPlayer implements PlayerInterface {
    private Point position;
    private GameRules rules;
    
    private boolean stopFlag = false;
    
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
        
        this.stopFlag = true;
    }

    @Override
    public Point didMoveNow() {
        System.out.println("I moved now!");
        
        return this.position;
    }

    @Override
    public void run() {
        while (!this.stopFlag) {
            //System.out.print('.');
            this.position.x = (this.position.x+1) % 19;
            this.position.y = (this.position.y+1) % 19;
        }
    }

    @Override
    public void withBoardAndRules(GomokuBoard board, GameRules rules) {
        System.out.println("Got board and rules");
        this.rules = rules;
    }
    
}
