package gomoku.ui;

import gomoku.*;
import gomoku.GameRules;
import gomoku.GomokuBoard;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

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
    
    int mousePositionX;
    int mousePositionY;
    
  
    
    Font smallFont;
    
    public GomokuUIBoard() {
        smallFont = new Font("Georgia", Font.BOLD, FONTSIZE);
        addMouseMotionListener(new MyMouseMotionListener());
        addMouseListener(new pieceAreaListener());
      //  createIntersections(new Rectangle(3,3));
    }
    
    /**
     * Intersection measure method.
     * Used by paint method.
     * It checks vertival and horizontal intersections numbers and establishes left and top margin.
     * 
     * @param board Size rectangle
     */
    
    public final void createIntersections(Rectangle board) {
        //GameRules rules = new GameRules(new Rectangle(0, 0, 5, 5), new Rectangle(3, 3, 5, 5), 5);
        vIntersectionsNumber = board.width;
        hIntersectionsNumber = board.height;
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
        
        g.setFont(smallFont);
        g.setColor(Color.black);
        
        
        for (int i = 1; i <= vIntersectionsNumber; i++) {
            g.drawString(numbers[i - 1], leftMargin - 25, topMargin + 5 + i * INTERSPACE);
            g.drawLine(leftMargin, topMargin + i * INTERSPACE, leftMargin + (hIntersectionsNumber + 1) * INTERSPACE, topMargin + i * INTERSPACE);
        }
        for (int i = 1; i <= hIntersectionsNumber; i++) {
            g.drawString(letters[i - 1], leftMargin - 5 + i * INTERSPACE, topMargin - 15);
            g.drawLine(leftMargin + i  * INTERSPACE, topMargin, leftMargin + i  * INTERSPACE, topMargin + (vIntersectionsNumber + 1) * INTERSPACE);
        }
        
        if(mousePositionX != -1 && mousePositionY != -1) {
            g.setColor(Color.pink);
            g.fillOval((leftMargin - CIRCLESIZE/2) + (mousePositionY + 1)*INTERSPACE, (topMargin - CIRCLESIZE/2) + (mousePositionX+ 1)*INTERSPACE, CIRCLESIZE, CIRCLESIZE);
        }  else {
            
        }

        try{
            Point p = new Point();
            for (p.x = 0; p.x < vIntersectionsNumber; p.x++) {
                for (p.y = 0; p.y < hIntersectionsNumber; p.y++) {
                    switch (Gomoku.game.board.get(p)) {
                        case A:
                            g.setColor(Color.white);
                            break;
                        case B:
                            g.setColor(Color.black);
                            break;
                        case FORBIDDEN:
                            g.setColor(Color.lightGray);
                            break;
                        default:
                            continue;
                    }
                    g.fillOval((leftMargin - CIRCLESIZE/2) + (p.y + 1)*INTERSPACE, (topMargin - CIRCLESIZE/2) + (p.x + 1)*INTERSPACE, CIRCLESIZE, CIRCLESIZE); 
                }
            }
        } catch (NullPointerException ex) {
            
        }
    }
    
     public class pieceAreaListener implements MouseListener {
        //where initialization occurs:
        //Register for mouse events on blankArea and the panel.

        public void mouseEntered(MouseEvent e) {
            //("Mouse entered", e);
        }
        
        public void mouseExited(MouseEvent e) {
            //saySomething("Mouse exited", e);
        }

        public void mouseClicked(MouseEvent e) {
            
        }
        
        public void mousePressed(MouseEvent e) {
            
        }
        
        public void mouseReleased(MouseEvent e) {
            
        }
     }
     
     class MyMouseMotionListener implements MouseMotionListener  {
          public void mouseDragged(MouseEvent e) {
            showMousePos(e);
          }

          public void mouseMoved(MouseEvent e) {
            showMousePos(e);
          }

          private void showMousePos(MouseEvent e) {
      //      JLabel src = (JLabel)e.getComponent();
            PointerInfo pointerInfo = MouseInfo.getPointerInfo();
            Point point = pointerInfo.getLocation();
          //  src.setText(point.toString());
            for (int i = 0; i < vIntersectionsNumber; i++) {
                for (int j = 0; j < hIntersectionsNumber; j++) {
                if (point.x >= ((leftMargin - CIRCLESIZE/2) + (i + 1)*INTERSPACE) && 
                        point.x <= ((leftMargin - CIRCLESIZE/2) + (i + 1)*INTERSPACE + CIRCLESIZE) && 
                        point.y >= ((topMargin - CIRCLESIZE/2) + (j + 1)*INTERSPACE) &&
                        point.y <= ((topMargin - CIRCLESIZE/2) + (j+ 1)*INTERSPACE + CIRCLESIZE)) {
                    highlightPiece(point.x, point.y);
                }   else {
                    mousePositionX = -1;
                    mousePositionY = -1;
                }
             }
           }
          }
     }
          
          public void highlightPiece(int x, int y) {
              mousePositionX = x;
              mousePositionY = y;
        //      Graphics2D g = new Graphics();
//g.setColor(Color.pink);
  //            g.fillOval((leftMargin - CIRCLESIZE/2) + (y + 1)*INTERSPACE, (topMargin - CIRCLESIZE/2) + (x + 1)*INTERSPACE, CIRCLESIZE, CIRCLESIZE);
          }
          
  /*  public class pieceArea extends JLabel {
        int x, y;
        final int startXInPixel = (leftMargin - CIRCLESIZE/2) + (y + 1)*INTERSPACE;
        final int startYInPixel = (topMargin - CIRCLESIZE/2) + (x + 1)*INTERSPACE;
      // final int width = CIRCLESIZE;
        //final int height = CIRCLESIZE;
      /*  final int leftPx = (leftMargin - CIRCLESIZE/2) + (y + 1)*INTERSPACE;
        final int rightPx = (leftMargin + CIRCLESIZE/2) + (y + 1)*INTERSPACE ;
        final int topPx = (topMargin - CIRCLESIZE/2) + (x + 1)*INTERSPACE;
        final int bottomPx =(topMargin + CIRCLESIZE/2) + (x + 1)*INTERSPACE;
*/
    /*    public pieceArea(int x, int y) {
            setOpaque(true);
            setBounds(startXInPixel, startYInPixel, CIRCLESIZE, CIRCLESIZE);
            addMouseListener(new pieceAreaListener());
            setVisible(false);
        }
    }
    */
    
            
       
        //throw new UnsupportedOperationException("Not supported yet.");
    
}