package gomoku.ui;

import gomoku.GameRules;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.*;

/**
 * Component representing Gomoku board.
 * 
 * Gomoku board is played on Go board, made of HxW
 * intersections. Paws are put on intersections.
 *
 * @author Amadeusz Juskowiak
 */
public class GomokuUIBoard extends Component {
    /**
     * Component paint method. Paints area of component.
     * Use double-buffering!
     * 
     * @param g Graphics context
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        GameRules rules = new GameRules(new Rectangle(0,0,9,9), new Rectangle(3,3,5,5), 5);
        Rectangle board;
        Graphics2D g2d = (Graphics2D) g;
        board = rules.getSizeRectangle();
        for (int i = 0; i <= board.height; i++) {
        g2d.drawLine(350, 50 + i*50, 850, 50 + i*50);    
        }
        
        
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
