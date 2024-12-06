package com.seventh.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.seventh.util.FontLoader;

public class PetCardUI extends JPanel{
    FontLoader fontLoader;
    Font buttonIcon;
    
    JPanel main;

    JPanel buttonPanel;
    JButton vet, food, drink, play, nap, clean;

    JPanel barPanel;
    RoundedProgressBar healthBar, hungerBar, thrirstBar;
    

    Color black = new Color(29,29,29);
    Color white = new Color(246,246,246);
    Color grey  = new Color(225,225,225);
    Color red = new Color(255,255,150);
    Color green = new Color(150,255,150);
    Color blue = new Color(150,255,255);

    public PetCardUI(){
        // Load Font
        fontLoader = new FontLoader();
        buttonIcon = fontLoader.load(
            buttonIcon, 
            "src/main/resources/MaterialSymbolsRounded.ttf",
            36f
        );

        // Cards panel
        this.setLayout(new BorderLayout());
        
        
        
        // >> main Part ---------------------------------------------------
        main = new JPanel();
        main.setPreferredSize(new Dimension(440, 400));
        main.setBackground(null);
        
        // >> Bottom Part ---------------------------------------------------
        
        // >>> Button Panel
        vet = new JButton("\ue11f");
        setStyle(vet, green);
        food = new JButton("\ue56c");
        setStyle(food, red);
        drink = new JButton("\ue798");
        setStyle(drink, blue);
        play = new JButton("\uf7fe");
        setStyle(play, green);
        nap = new JButton("\uef44");
        setStyle(nap, green);
        clean = new JButton("\uf061");
        setStyle(clean, green);
        
        // >>> Bar Panel
        // >>>> Health Bar
        healthBar = new RoundedProgressBar();
        healthBar.setPreferredSize(new Dimension(400, 30));
        // healthBar.setBorder(null);
        healthBar.setForeground(green);
        healthBar.setBackground(grey);
        healthBar.setValue(50);
        
        // >>>> Hunger Bar
        hungerBar = new RoundedProgressBar();
        hungerBar.setPreferredSize(new Dimension(400, 30));
        hungerBar.setBorder(null);
        hungerBar.setForeground(red);
        hungerBar.setBackground(grey);
        hungerBar.setValue(50);
        
        // >>>> Thrirst Bar
        thrirstBar = new RoundedProgressBar();
        thrirstBar.setPreferredSize(new Dimension(400, 30));
        // thrirstBar.setBorder(null);
        thrirstBar.setForeground(blue);
        thrirstBar.setBackground(grey);
        thrirstBar.setValue(50);
        
        barPanel = new JPanel();
        barPanel.setPreferredSize(new Dimension(440, 110));
        barPanel.setLayout(new BorderLayout(5,10));
        barPanel.setAlignmentY(JPanel.CENTER_ALIGNMENT);
        barPanel.setBackground(null);
        barPanel.add(healthBar, BorderLayout.NORTH);
        barPanel.add(hungerBar, BorderLayout.CENTER);
        barPanel.add(thrirstBar, BorderLayout.SOUTH);
        
        buttonPanel  = new JPanel();
        buttonPanel.setPreferredSize(new Dimension(400, 60));
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER,5,5));
        buttonPanel.setBackground(null);
        buttonPanel.add(vet);
        buttonPanel.add(food);
        buttonPanel.add(drink);
        buttonPanel.add(play);
        buttonPanel.add(nap);
        buttonPanel.add(clean);
        
        this.add(main, BorderLayout.NORTH);
        this.add(barPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }
    
    private void setStyle(JButton button, Color color){
        button.setPreferredSize(new Dimension(65,50));
        button.setFont(buttonIcon);
        // button.setBorder(null);
        button.setForeground(white);
        button.setBackground(color);
    }

}
