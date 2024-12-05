package com.seventh.ui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JProgressBar;

public class RoundedProgressBar extends JProgressBar {
    private int arcWidth = 30;
    private int arcHeight = 30;

    public RoundedProgressBar() {
        setOpaque(false);
    }

    public RoundedProgressBar(int min, int max) {
        super(min, max);
        setOpaque(false);
    }

    public void setArcSize(int width, int height) {
        this.arcWidth = width;
        this.arcHeight = height;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                              RenderingHints.VALUE_ANTIALIAS_ON);

        // Create rounded background
        g2d.setColor(getBackground());
        g2d.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 
                                              arcWidth, arcHeight));

        // Calculate progress width
        int progressWidth = (int) (getWidth() * ((double) getValue() / getMaximum()));

        // Create rounded progress fill
        g2d.setColor(getForeground());
        g2d.fill(new RoundRectangle2D.Double(0, 0, progressWidth, getHeight(), 
                                              arcWidth, arcHeight));

        g2d.dispose();
    }
}
