package com.seventh.ui;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.seventh.util.FontLoader;

public class AddCardUI extends JPanel{
    FontLoader fontLoader;
    Font buttonIcon, buttonFocusedIcon;

    JButton addButton;
    public AddCardUI(){
        fontLoader = new FontLoader();

        buttonIcon = fontLoader.load(
            buttonIcon, 
            "src/main/resources/MaterialSymbolsRounded.ttf",
            36f
        );

        buttonFocusedIcon = fontLoader.load(
            buttonFocusedIcon, 
            "src/main/resources/MaterialSymbolsRounded.ttf",
            48f
        );

        this.setLayout(new FlowLayout());
        this.setPreferredSize(new Dimension(440, 570));
        this.setBackground(null);

        addButton = new JButton("\ue147");
        addButton.setContentAreaFilled(false);
        addButton.setFocusPainted(false);
        addButton.setForeground(Color.BLACK);
        addButton.setBackground(null);
        addButton.setBorder(null);
        addButton.setFont(buttonIcon);
        
        this.add(addButton);

        addButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                addButton.setFont(buttonFocusedIcon);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                addButton.setFont(buttonIcon);
            }
        });

    }
}
