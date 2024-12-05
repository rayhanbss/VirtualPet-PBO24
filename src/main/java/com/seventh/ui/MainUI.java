package com.seventh.ui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.seventh.entities.Pet;

public class MainUI {
    private Pet currentPet;
    private int indexOfCurrent;

    JFrame frame;
    JPanel main, top, center, bottom;

    JPanel rightBound, leftBound;
    JButton rightButton, leftButton;
    JPanel h_petName, h_petAge;
    JTextField petName, petAge;
    Font mainFont, subFont;

    JPanel buttonPanel;
    JButton vet, food, drink, play, nap, clean;

    JPanel barPanel;
    JProgressBar healthBar;
    JProgressBar hungerBar;
    JProgressBar thrirstBar;

    JPanel bulletPanel;
    List<JTextField> bullets;

    Color black = new Color(29,29,29);
    Color white = new Color(246,246,246);
    Color grey  = new Color(225,225,225);
    Color red = new Color(255,255,150);
    Color green = new Color(150,255,150);
    Color blue = new Color(150,255,255);

    private final int width = 540;
    private final int height = 720;

    private void setStyle(JButton button, Color color){
        button.setPreferredSize(new Dimension(65,50));
        // button.setBorder(null);
        button.setForeground(white);
        button.setBackground(color);
    }


    public MainUI(){
        try {
            UIManager.setLookAndFeel(new FlatMacDarkLaf());  // Or use FlatDarkLaf()
            // Create and display your GUI
            SwingUtilities.invokeLater(() -> {
                frame = new JFrame("Virtual Pet");
                frame.setLayout(new BorderLayout());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(width, height);
                frame.setLocationRelativeTo(null);
                frame.setResizable(false);

                mainFont = new Font("Open Sans",Font.BOLD, 24);
                subFont = new Font("Open Sans",Font.PLAIN, 12);

                // Main Panel ----------------------------------------------------
                main = new JPanel();
                main.setLayout(new BorderLayout());
                main.setPreferredSize(new Dimension(440, height));
                main.setBackground(Color.RED);

                // > Right bound with button ---------------------------------------
                rightButton = new JButton(">");
                rightButton.setForeground(black);
                rightButton.setBackground(null);
                rightButton.setBorder(null);
                rightButton.setFont(mainFont);

                rightBound = new JPanel();
                rightBound.setPreferredSize(new Dimension(50, height));
                rightBound.setLayout(new BorderLayout());
                rightBound.setBackground(white);
                rightBound.add(rightButton, BorderLayout.CENTER);

                // > Left bound with button ----------------------------------------
                leftButton = new JButton("<");
                leftButton.setForeground(black);
                leftButton.setBackground(null);
                leftButton.setBorder(null);
                leftButton.setFont(mainFont);

                leftBound = new JPanel();
                leftBound.setPreferredSize(new Dimension(50, height));
                leftBound.setLayout(new BorderLayout());
                leftBound.setBackground(white);
                leftBound.add(leftButton, BorderLayout.CENTER);

                // >> Top part ------------------------------------------------------

                // >>> Pet Name
                petName = new JTextField("Pet Name");
                petName.setHorizontalAlignment(JTextField.CENTER);
                petName.setDisabledTextColor(black);
                petName.setBorder(null);
                petName.setBackground(null);
                petName.setEnabled(false);
                petName.setFont(mainFont);
                
                h_petName = new JPanel();
                h_petName.setPreferredSize(new Dimension(440,60));
                h_petName.setLayout(new BorderLayout());
                h_petName.setBackground(null);
                h_petName.add(petName, BorderLayout.SOUTH);

                // >>> Pet Age
                petAge = new JTextField("?? Month ?? Years");
                petAge.setHorizontalAlignment(JTextField.CENTER);
                petAge.setDisabledTextColor(black);
                petAge.setBorder(null);
                petAge.setBackground(null);
                petAge.setEnabled(false);
                petAge.setFont(subFont);
                
                h_petAge = new JPanel();
                h_petAge.setPreferredSize(new Dimension(440,40));
                h_petAge.setLayout(new BorderLayout());
                h_petAge.setBorder(null);
                h_petAge.setBackground(null);
                h_petAge.add(petAge, BorderLayout.NORTH);

                top = new JPanel();
                top.setPreferredSize(new Dimension(440, 100));
                top.setBorder(null);
                top.setBackground(white);
                top.setLayout(new BorderLayout());
                top.add(h_petName, BorderLayout.NORTH);
                top.add(h_petAge, BorderLayout.SOUTH);
                
                // >> Center Part ---------------------------------------------------
                center = new JPanel();
                center.setPreferredSize(new Dimension(440, 300));
                center.setBackground(Color.PINK);
                
                // >> Bottom Part ---------------------------------------------------

                // >>> Button Panel
                vet = new JButton("");
                setStyle(vet, green);
                food = new JButton("");
                setStyle(food, red);
                drink = new JButton("");
                setStyle(drink, blue);
                play = new JButton("");
                setStyle(play, green);
                nap = new JButton("");
                setStyle(nap, green);
                clean = new JButton("");
                setStyle(clean, green);
               
                
                buttonPanel  = new JPanel();
                buttonPanel .setPreferredSize(new Dimension(400, 60));
                buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER,5,5));
                buttonPanel.setBackground(null);
                buttonPanel.add(vet);
                buttonPanel.add(food);
                buttonPanel.add(drink);
                buttonPanel.add(play);
                buttonPanel.add(nap);
                buttonPanel.add(clean);

                
                // >>> Bar Panel
                // >>>> Health Bar
                healthBar = new JProgressBar();
                healthBar.setPreferredSize(new Dimension(400, 30));
                // healthBar.setBorder(null);
                healthBar.setUI(new RoundedProgressBarUI(20));
                healthBar.setForeground(green);
                healthBar.setBackground(grey);
                healthBar.setValue(50);

                // >>>> Hunger Bar
                hungerBar = new JProgressBar();
                hungerBar.setPreferredSize(new Dimension(400, 30));
                // hungerBar.setBorder(null);
                hungerBar.setUI(new RoundedProgressBarUI(20));
                hungerBar.setForeground(red);
                hungerBar.setBackground(grey);
                hungerBar.setValue(50);

                // >>>> Thrirst Bar
                thrirstBar = new JProgressBar();
                thrirstBar.setPreferredSize(new Dimension(400, 30));
                // thrirstBar.setBorder(null);
                thrirstBar.setUI(new RoundedProgressBarUI(20));
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
                
                // >>> Bullet
                bullets = new ArrayList<>();
                for(int i = 0; i < 3; i++){
                    JTextField bullet = new JTextField("⦁");
                    bullet.setHorizontalAlignment(JTextField.CENTER);
                    bullet.setPreferredSize(new Dimension(11,11));
                    bullet.setEditable(false);
                    bullet.setFont(mainFont);
                    bullet.setDisabledTextColor(grey);
                    bullet.setBackground(null);
                    bullet.setBorder(null);
                    bullets.add(bullet);
                }

                bulletPanel = new JPanel();
                bulletPanel.setLayout(new FlowLayout());
                bulletPanel.setPreferredSize(new Dimension(400, 50));
                bulletPanel.setBackground(null);
                bulletPanel.setBorder(null);
                bulletPanel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
                bulletPanel.setAlignmentY(JPanel.CENTER_ALIGNMENT);
                for(JTextField bullet : bullets){
                    bulletPanel.add(bullet);
                }


                bottom = new JPanel();
                bottom.setPreferredSize(new Dimension(440, 220));
                bottom.setLayout(new BorderLayout());
                bottom.setAlignmentY(JPanel.CENTER_ALIGNMENT);
                bottom.setBackground(white);
                bottom.add(barPanel, BorderLayout.NORTH);
                bottom.add(buttonPanel , BorderLayout.CENTER);
                bottom.add(bulletPanel, BorderLayout.SOUTH);


                main.add(top, BorderLayout.NORTH);
                main.add(center, BorderLayout.CENTER);
                main.add(bottom, BorderLayout.SOUTH);


                frame.add(leftBound, BorderLayout.WEST);
                frame.add(main, BorderLayout.CENTER);
                frame.add(rightBound, BorderLayout.EAST);
                frame.setVisible(true);
            });
        } catch (UnsupportedLookAndFeelException e) {
            System.out.println("Look and feel error");
        }
        
    }
}
