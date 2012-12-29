package gomoku.ui;

import gomoku.*;
import java.awt.Point;
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
 * Gomoku board is played on Go board, made of HxW intersections. Paws are put
 * on intersections.
 *
 * @author Amadeusz Juskowiak
 */
public class GomokuUIBoard extends JComponent {

    /**
     * Interspace between lines
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
    final static String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S"};
    /**
     * Left margin size
     */
    int leftMargin;
    /**
     * Mouse pressing indicator - 1 for true, 0 for false
     */
    int mousePressed;
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
    public int mousePositionX;
    public int mousePositionY;
    Font smallFont;
    public boolean paused = false;

    public GomokuUIBoard() {
        smallFont = new Font("Georgia", Font.BOLD, FONTSIZE);
        addMouseMotionListener(new MyMouseMotionListener());
        addMouseListener(new pieceAreaListener());
        //  createIntersections(new Rectangle(3,3));
    }

    /**
     * Intersection measure method. Used by paint method. It checks vertival and
     * horizontal intersections numbers and establishes left and top margin.
     *
     * @param board Size rectangle
     */
    public final void createIntersections(Rectangle board) {
        //GameRules rules = new GameRules(new Rectangle(0, 0, 5, 5), new Rectangle(3, 3, 5, 5), 5);
        vIntersectionsNumber = board.width;
        hIntersectionsNumber = board.height;
        leftMargin = 20 + (900 - ((hIntersectionsNumber + 2) * INTERSPACE)) / 2;
        topMargin = 20 + (900 - ((vIntersectionsNumber + 2) * INTERSPACE)) / 2;
        paused = false;
    }

    /**
     * Component paint method. Paints area of component. Use double-buffering!
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
            g.drawLine(leftMargin + i * INTERSPACE, topMargin, leftMargin + i * INTERSPACE, topMargin + (vIntersectionsNumber + 1) * INTERSPACE);
        }

        if (mousePositionX != -1 && mousePositionY != -1 && Gomoku.ui.list.isEnabled()) {
            if (Gomoku.game.referee.rules.getSizeRectangle().contains(new Point(mousePositionY, mousePositionX))) {
                drawCircle(g);
            }
        }

        if (!paused) {
            try {
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
                            case WINNING:
                                g.setColor(Color.red);
                                break;
                            default:
                                continue;
                        }
                        g.fillOval((leftMargin - CIRCLESIZE / 2) + (p.y + 1) * INTERSPACE, (topMargin - CIRCLESIZE / 2) + (p.x + 1) * INTERSPACE, CIRCLESIZE, CIRCLESIZE);
                    }
                }
            } catch (NullPointerException ex) {
            }
        } else {
            g.setColor(Color.pink);
            g.fillRect(0, 0, this.getSize().height, this.getSize().width);
        }
    }

    public void drawCircle(Graphics g) {
        switch (mousePressed) {
            case 1:
                g.setColor(Gomoku.game.getCurrentPlayer() == 1 ? Color.black : Color.white);
                g.fillOval((leftMargin - CIRCLESIZE / 2) + (mousePositionX + 1) * INTERSPACE, (topMargin - CIRCLESIZE / 2) + (mousePositionY + 1) * INTERSPACE, CIRCLESIZE, CIRCLESIZE);
                break;
            case 0:
                g.setColor(Gomoku.game.getCurrentPlayer() == 1 ? Color.getHSBColor(0f, 0f, 0.1f) : Color.getHSBColor(0f, 0f, 0.9f));
                g.fillOval((leftMargin - CIRCLESIZE / 2) + (mousePositionX + 1) * INTERSPACE, (topMargin - CIRCLESIZE / 2) + (mousePositionY + 1) * INTERSPACE, CIRCLESIZE, CIRCLESIZE);
                break;
        }
    }

    public class pieceAreaListener implements MouseListener {
        //where initialization occurs:
        //Register for mouse events on blankArea and the panel.

        @Override
        public void mouseEntered(MouseEvent e) {
            //("Mouse entered", e);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            //saySomething("Mouse exited", e);
        }

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            mousePressed = 1;
            if (mousePositionX != -1 && mousePositionY != -1) {
                //if (Gomoku.game.)
                try {
                    Gomoku.game.forceMove(new Point(mousePositionY, mousePositionX));
                } catch (NullPointerException exc) {
                }
                repaint();
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            mousePressed = 0;
        }
    }

    public class MyMouseMotionListener implements MouseMotionListener {

        @Override
        public void mouseDragged(MouseEvent e) {
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            showMousePos(e);
        }

        private void showMousePos(MouseEvent e) {
            if (!paused) {
                Point coordinates = new Point(e.getX(), e.getY());
                coordinatesCalculator(coordinates);
                if (coordinates.x != mousePositionX || coordinates.y != mousePositionY) {
                    mousePositionX = coordinates.x;
                    mousePositionY = coordinates.y;
                    repaint();
                }
            }
        }

        private Point coordinatesCalculator(Point p) {
            Point temp = new Point(p);
            p.x = -1;
            p.y = -1;
            for (int i = 0; i < vIntersectionsNumber; i++) {
                if (temp.x >= ((leftMargin - CIRCLESIZE / 2) + (i + 1) * INTERSPACE)
                        && temp.x <= ((leftMargin - CIRCLESIZE / 2) + (i + 1) * INTERSPACE + CIRCLESIZE)) {
                    p.x = i;
                }
            }
            for (int i = 0; i < hIntersectionsNumber; i++) {
                if (temp.y >= ((topMargin - CIRCLESIZE / 2) + (i + 1) * INTERSPACE)
                        && temp.y <= ((topMargin - CIRCLESIZE / 2) + (i + 1) * INTERSPACE + CIRCLESIZE)) {
                    p.y = i;
                }
            }
            return p;
        }
    }
}