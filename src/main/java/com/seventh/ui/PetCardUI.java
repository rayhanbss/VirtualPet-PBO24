package com.seventh.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import com.seventh.entities.Pet;
import com.seventh.util.FontLoader;

public class PetCardUI extends JPanel{
    Pet pet;
    FontLoader fontLoader;
    Font buttonIcon;
    
    JPanel main;

    JPanel buttonPanel;
    JButton vet, food, drink, play, nap, clean;

    JPanel barPanel;
    RoundedProgressBar healthBar, hungerBar, thirstBar;
    

    Color buttonColor = new Color(0,122,255);
    Color white = new Color(246,246,246);
    Color grey  = new Color(225,225,225);
    Color red = new Color(255,255,150);
    Color green = new Color(150,255,150);
    Color blue = new Color(150,255,255);

    public PetCardUI(Pet pet){
        this.pet = pet;

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
        food = new JButton("\ue56c");
        drink = new JButton("\ue798");
        play = new JButton("\uf7fe");
        nap = new JButton("\uef44");
        clean = new JButton("\uf061");
        
        setStyle(vet);
        setStyle(food);
        setStyle(drink);
        setStyle(play);
        setStyle(nap);
        setStyle(clean);
        
        mouseListener(vet);
        mouseListener(food);
        mouseListener(drink);
        mouseListener(play);
        mouseListener(nap);
        mouseListener(clean);
        
        // >>> Bar Panel
        // >>>> Health Bar
        healthBar = new RoundedProgressBar();
        healthBar.setPreferredSize(new Dimension(400, 30));
        healthBar.setForeground(green);
        healthBar.setBackground(grey);
        healthBar.setValue(50);
        
        // >>>> Hunger Bar
        hungerBar = new RoundedProgressBar();
        hungerBar.setPreferredSize(new Dimension(400, 30));
        hungerBar.setForeground(red);
        hungerBar.setBackground(grey);
        hungerBar.setValue(50);
        
        // >>>> Thirst Bar
        thirstBar = new RoundedProgressBar();
        thirstBar.setPreferredSize(new Dimension(400, 30));
        thirstBar.setForeground(blue);
        thirstBar.setBackground(grey);
        thirstBar.setValue(50);
        
        barPanel = new JPanel();
        barPanel.setPreferredSize(new Dimension(440, 110));
        barPanel.setLayout(new BoxLayout(barPanel, BoxLayout.Y_AXIS));
        barPanel.setBorder(BorderFactory.createEmptyBorder(0, 8, 0, 8));
        barPanel.setBackground(null);
        barPanel.setBackground(null);
        barPanel.add(healthBar);
        barPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        barPanel.add(hungerBar);
        barPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        barPanel.add(thirstBar);
        
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

        Timer timer = new Timer(1000, e -> {
            pet.updateStatus();
            updateBars();
        });
        timer.start();
    }
    
    private void setStyle(JButton button){
        button.setPreferredSize(new Dimension(65,50));
        button.setFont(buttonIcon);
        button.setForeground(white);
        button.setBackground(buttonColor);
    }

    private void updateBars(){
        SwingUtilities.invokeLater(() -> {
            healthBar.setValue((int) ((pet.getHealth() / pet.getMaxHealth()) * 100));
            healthBar.setToolTipText("Health Bar" + healthBar.getValue() + " / 100");
            hungerBar.setValue((int) pet.getHunger());
            hungerBar.setToolTipText("Health Bar" + hungerBar.getValue() + " / 100");
            thirstBar.setValue((int) pet.getThirst());
            thirstBar.setToolTipText("Health Bar" + thirstBar.getValue() + " / 100");

            healthBar.revalidate();
            healthBar.repaint();
            hungerBar.revalidate();
            hungerBar.repaint();
            thirstBar.revalidate();
            thirstBar.repaint();
        });
    }

    private void mouseListener(JButton button){
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setFont(buttonIcon.deriveFont(40f));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setFont(buttonIcon);
            }
        });
    }
}
