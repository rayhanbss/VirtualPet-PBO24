package com.seventh.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class negativeEffectUI extends JPanel{
    private final int height = 10;
    private final int width = 10;
    private final Color color;

    public negativeEffectUI(Color color){
        this.color = color;
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.fillOval(0,0,width, height);
        g2d.setPaint(color);
    }

}
