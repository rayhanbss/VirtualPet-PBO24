package com.seventh.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.sound.sampled.Clip;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import com.seventh.entities.Pet;
import com.seventh.util.AudioLoader;
import com.seventh.util.FontLoader;
import com.seventh.util.ImageLoader;

public class PetCardUI extends JPanel{
    Pet pet;
    Font buttonIcon;
    
    JPanel main;
    ImageIcon petImage;
    JLabel petImageLabel;
    JPanel imagePanel;

    JPanel petPanel, effectsPanel;
    negativeEffectUI[] negativeEffectUIs;

    JPanel buttonPanel;
    JButton vet, food, drink, play, nap, clean;

    JPanel barPanel;
    RoundedProgressBar healthBar, hungerBar, thirstBar;

    Clip buttonSound;

    Color buttonColor = new Color(0,122,255);
    Color white = new Color(246,246,246);
    Color grey  = new Color(225,225,225);
    Color red = new Color(255,255,150);
    Color green = new Color(150,255,150);
    Color blue = new Color(150,255,255);

    public PetCardUI(Pet pet){
        this.pet = pet;

        // Load Font
        buttonIcon = FontLoader.load(
            "MaterialSymbolsRounded.ttf",
            36f
        );

        // Cards panel
        this.setLayout(new BorderLayout());
        
        buttonSound = AudioLoader.load("button.wav");
        buttonSound.setFramePosition(0);
        
        // >> main Part ---------------------------------------------------
        main = new JPanel();
        main.setPreferredSize(new Dimension(440, 400));
        main.setBackground(null);

        setPetImage(pet.getPetType());
        petImageLabel = new JLabel(petImage);
        petImageLabel.setPreferredSize(new Dimension(petImage.getIconWidth(), petImage.getIconHeight()));

        imagePanel = new JPanel();
        imagePanel.setPreferredSize(new Dimension(petImage.getIconWidth(), petImage.getIconHeight()));
        imagePanel.setBackground(null);
        
        imagePanel.add(petImageLabel);

        petPanel = new JPanel();
        petPanel.setPreferredSize(new Dimension(440, 350));
        petPanel.setBackground(null);
        petPanel.add(imagePanel);
        
        effectsPanel = new JPanel();
        effectsPanel.setPreferredSize(new Dimension(440, 100));
        effectsPanel.setLayout(new FlowLayout(FlowLayout.CENTER,2,0));
        effectsPanel.setBackground(null);
        negativeEffectUIs = new negativeEffectUI[7];
        for (int i = 0; i < 7; i++) {
            negativeEffectUIs[i] = new negativeEffectUI(i, buttonIcon);
        }

        main.add(petPanel);
        main.add(effectsPanel);
        
        // >> Bottom Part ---------------------------------------------------
        
        // >>> Button Panel
        vet = new JButton("\ue11f");
        food = new JButton("\ue56c");
        drink = new JButton("\ue798");
        play = new JButton("\uf7fe");
        nap = new JButton("\uef44");
        clean = new JButton("\uf061");
        
        vet.setToolTipText("Go to Vet");
        food.setToolTipText("Give Food");
        drink.setToolTipText("Give Drink");
        play.setToolTipText("Play Together");
        nap.setToolTipText("Take a Nap");
        clean.setToolTipText("Clean it");
        
        setStyle(vet);
        setStyle(food);
        setStyle(drink);
        setStyle(play);
        setStyle(nap);
        setStyle(clean);
        
        buttonListener(vet, 1);
        buttonListener(food, 2);
        buttonListener(drink, 3);
        buttonListener(play, 4);
        buttonListener(nap, 5);
        buttonListener(clean, 6);

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
        healthBar.setValue(100);
        
        // >>>> Hunger Bar
        hungerBar = new RoundedProgressBar();
        hungerBar.setPreferredSize(new Dimension(400, 30));
        hungerBar.setForeground(red);
        hungerBar.setBackground(grey);
        hungerBar.setValue(100);
        
        // >>>> Thirst Bar
        thirstBar = new RoundedProgressBar();
        thirstBar.setPreferredSize(new Dimension(400, 30));
        thirstBar.setForeground(blue);
        thirstBar.setBackground(grey);
        thirstBar.setValue(100);
        
        barPanel = new JPanel();
        barPanel.setPreferredSize(new Dimension(440, 110));
        barPanel.setLayout(new BoxLayout(barPanel, BoxLayout.Y_AXIS));
        barPanel.setBorder(BorderFactory.createEmptyBorder(0, 8, 0, 8));
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

        Timer statusTimer = new Timer(60000, _ -> {
            pet.updateStatus();
        });
        statusTimer.start();
        
        Timer barTimer = new Timer(500, _ -> {
            pet.updateNegativeEffects();
            updateEffectsPanel();
            updateBars();
            updateButton();
        });
        barTimer.start();
    }

    public final void buttonListener(JButton button, int type){
        button.addActionListener((ActionEvent _) -> {
            AudioLoader.play(buttonSound);
            pet.action(type);
        });
    }
    
    private void setStyle(JButton button){
        button.setPreferredSize(new Dimension(65,50));
        button.setEnabled(false);
        button.setFont(buttonIcon);
        button.setForeground(white);
        button.setBackground(buttonColor);
    }

    private void updateButton() {
        vet.setEnabled(!pet.isDead() && pet.isSick());
        food.setEnabled(!pet.isDead() && pet.isHungry());
        drink.setEnabled(!pet.isDead() && pet.isThirsty());
        play.setEnabled(!pet.isDead() && pet.isSad());
        nap.setEnabled(!pet.isDead() && pet.isTired());
        clean.setEnabled(!pet.isDead() && pet.isDirty());
    }

    private void updateEffectsPanel(){
        effectsPanel.removeAll();
        SwingUtilities.invokeLater(() -> {
            if(pet.isDead()){
                effectsPanel.add(negativeEffectUIs[0]);
            }else{
                if(pet.isSick()) effectsPanel.add(negativeEffectUIs[1]);
                if(pet.isTired()) effectsPanel.add(negativeEffectUIs[2]);
                if(pet.isHungry()) effectsPanel.add(negativeEffectUIs[3]);
                if(pet.isThirsty()) effectsPanel.add(negativeEffectUIs[4]);
                if(pet.isSad()) effectsPanel.add(negativeEffectUIs[5]);
                if(pet.isDirty()) effectsPanel.add(negativeEffectUIs[6]);
            }
            effectsPanel.revalidate();
            effectsPanel.repaint();
        });
    }

    private void setPetImage(int type){
        switch (type) {
            case 1 -> petImage = ImageLoader.load("cat.png");
            case 2 -> petImage = ImageLoader.load("dog.png");
            case 3 -> petImage = ImageLoader.load("hamster.png");
            case 4 -> petImage = ImageLoader.load("parrot.jpg");
            case 5 -> petImage = ImageLoader.load("rabbit.jpg");
            case 6 -> petImage = ImageLoader.load("turtle.jpg");
            default -> {System.out.println(type); throw new AssertionError();}
        }
    }

    private void updateBars(){
        SwingUtilities.invokeLater(() -> {
            healthBar.setValue((int) ((pet.getHealth() / pet.getMaxHealth()) * 100));
            healthBar.setToolTipText("Health Bar" + healthBar.getValue() + " / 100");
            hungerBar.setValue((int) pet.getHunger());
            hungerBar.setToolTipText("Hunger Bar" + hungerBar.getValue() + " / 100");
            thirstBar.setValue((int) pet.getThirst());
            thirstBar.setToolTipText("Thirst Bar" + thirstBar.getValue() + " / 100");

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
