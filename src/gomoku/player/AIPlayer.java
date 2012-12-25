package gomoku.player;

import gomoku.*;
import java.awt.Point;

/**
 * Base class for all bots.
 * 
 * @author Amadeusz Juskowiak
 */
public class AIPlayer implements PlayerInterface {
    public AIPlayer() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void willMoveFor(float time) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void shouldMoveNow() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Point didMoveNow() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void withBoardAndRules(GomokuBoard board, GameRules rules) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
