package com.seventh.ui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicProgressBarUI;

class RoundedProgressBarUI extends BasicProgressBarUI {
    private final int arcSize;

    public RoundedProgressBarUI(int arcSize) {
        this.arcSize = arcSize;
    }

    @Override
    protected void paintDeterminate(Graphics g, JComponent c) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = progressBar.getWidth();
        int height = progressBar.getHeight();

        // Draw rounded background
        g2d.setColor(progressBar.getBackground());
        RoundRectangle2D backgroundShape = new RoundRectangle2D.Double(
            0, 0, width, height, arcSize, arcSize);
        g2d.fill(backgroundShape);

        // Draw rounded progress
        double percentComplete = progressBar.getValue() / (double) progressBar.getMaximum();
        g2d.setColor(progressBar.getForeground());
        RoundRectangle2D progressShape = new RoundRectangle2D.Double(
            0, 0, width * percentComplete, height, arcSize, arcSize);
        g2d.fill(progressShape);

        g2d.dispose();
        
        // Ensure text is still painted if it was enabled
        if (progressBar.isStringPainted()) {
            paintString(g, 0, 0, width, height, width, null);
        }
    }
}

