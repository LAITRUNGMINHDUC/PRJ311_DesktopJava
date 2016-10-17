/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyGraphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author duclt
 */
public class MyLine extends JPanel {

    ArrayList<Shape> shapes = new ArrayList<>();
    Point p1, p2;
    Color c;

    public MyLine() {

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                p1 = new Point(e.getX(), e.getY());
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                p2 = new Point(e.getX(), e.getY());
                Shape r = new Line2D.Double(p1, p2);
                shapes.add(r);
                p1 = null;
                p2 = null;
                repaint();
            }
        });

    }

    public void setC(Color c) {
        this.c = c;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(c);
        for (Shape x : shapes) {
            g2.draw(x);
        }
    }

}
