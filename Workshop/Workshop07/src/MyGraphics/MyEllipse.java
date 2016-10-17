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
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author duclt
 */
public class MyEllipse extends JPanel {

    ArrayList<Shape> shapes = new ArrayList<Shape>();
    Point p1, p2;
    Color c;
    boolean isFilled;

    public MyEllipse() {        
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                p1 = new Point(e.getX(), e.getY());
                
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                p2 = new Point(e.getX(), e.getY());
                int left = p1.x < p2.x ? p1.x : p2.x;
                int top = p1.y < p2.y ? p1.y : p2.y;
                int width = p1.x - p2.x;
                int height = p1.y - p2.y;

                width = Math.abs(width);
                height = Math.abs(height);
                Shape r = new Ellipse2D.Double(left, top, width, height);
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

    public void setIsFilled(boolean isFilled) {
        this.isFilled = isFilled;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(c);
        for (Shape x : shapes) {
            if (isFilled) {
                g2.fill(x);
            } else {
                g2.draw(x);
            }
        }
        repaint();
    }

}
