package gomoku.ui;

import gomoku.GameRules;
import gomoku.GomokuBoard;
import gomoku.GomokuBoardState;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.*;
import javax.swing.*;

/**
 * Component representing Gomoku board.
 * 
 * Gomoku board is played on Go board, made of HxW
 * intersections. Paws are put on intersections.
 *
 * @author Amadeusz Juskowiak
 */
public class GomokuUIBoard extends JComponent {
    /**
     *Interspace between lines
     */
    final static int INTERSPACE = 40;
    /**
     * Font size
     */
    final static int FONTSIZE = 15;
    
    /**
     * Circle size
     */
    final static int CIRCLESIZE = 30;
    
    /**
     * Array of numbers
     */
    final static String[] numbers = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19"};
     
    /**
     * Array of letters
     */
    final static String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I",  "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S"};
    
    /**
     * Left margin size
     */
    int leftMargin;
    
    /**
     * Top margin size
     */
    int topMargin;
    
    /**
     * Vertical intersections number
     */
    int vIntersectionsNumber;
    
    /**
     * Horizontal intersections number
     */
    int hIntersectionsNumber;
    
    /**
     * 
     */
    private void getIntersections() {
        GameRules rules = new GameRules(new Rectangle(0, 0, 19, 19), new Rectangle(3, 3, 5, 5), 5);
        Rectangle board;
        board = rules.getSizeRectangle();
        vIntersectionsNumber = board.height;
        hIntersectionsNumber = board.width;
        leftMargin = 20 + (900 - ((hIntersectionsNumber + 2) * INTERSPACE)) / 2;
        topMargin = 20 + (900 - ((vIntersectionsNumber + 2) * INTERSPACE)) / 2;
    }
    
    /**
     * Component paint method. Paints area of component.
     * Use double-buffering!
     * 
     * @param g Graphics context
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        Graphics2D g2b = (Graphics2D) g;
        Graphics2D g2w = (Graphics2D) g;
        
        g.setFont(new Font("TimesRoman", Font.PLAIN, FONTSIZE));
        g.setColor(Color.black);
        
        getIntersections();
        
        for (int i = 1; i <= vIntersectionsNumber; i++) {
            g.drawString(numbers[i - 1], leftMargin - 25, topMargin + 5 + i * INTERSPACE);
            g2d.drawLine(leftMargin, topMargin + i * INTERSPACE, leftMargin + (hIntersectionsNumber + 1) * INTERSPACE, topMargin + i * INTERSPACE);
        }
        for (int i = 1; i <= hIntersectionsNumber; i++) {
            g.drawString(letters[i - 1], leftMargin - 5 + i * INTERSPACE, topMargin - 15);
            g2d.drawLine(leftMargin + i  * INTERSPACE, topMargin, leftMargin + i  * INTERSPACE, topMargin + (vIntersectionsNumber + 1) * INTERSPACE);
        }
        g.setColor(Color.white);
        for (int i = 0; i < hIntersectionsNumber; i ++) {
            for (int j = 0; j < vIntersectionsNumber; j++) {
                g2d.fillOval((leftMargin - CIRCLESIZE/2) + (i + 1)*INTERSPACE, (topMargin - CIRCLESIZE/2) + (j + 1)*INTERSPACE, CIRCLESIZE, CIRCLESIZE); 
                }
            }
        
        
        
        //throw new UnsupportedOperationException("Not supported yet.");
    }
}