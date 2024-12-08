package com.seventh.ui;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.seventh.entities.Pet;
import com.seventh.repositories.PetRepositoriesImp;
import com.seventh.util.FontLoader;

public class MainUI {
    private PetRepositoriesImp petRepositoriesImp;
    private Pet currentPet;
    private int currentIndex;
    private int cardLength;

    JFrame frame;
    
    CardLayout cardLayout;
    AddCardUI addCard;

    JPanel main, top, middle, bottom;
    JPanel h_petName, h_petAge;
    JTextField petName, petAge;
    List<JTextField> bullets;
    
    JPanel rightBound, leftBound;
    JButton rightButton, leftButton;

    
    Color black = new Color(29,29,29);
    FontLoader fontLoader;
    Font mainFont, subFont, buttonIcon;

    private final int width = 540;
    private final int height = 720;

    public MainUI(){
        petRepositoriesImp = new PetRepositoriesImp();
        cardLength = 1;
        currentIndex = 0;

        fontLoader = new FontLoader();
        mainFont = new Font("Open Sans",Font.BOLD, 24);
        subFont = new Font("Open Sans",Font.PLAIN, 12);
        buttonIcon = fontLoader.load(
            buttonIcon, 
            "src/main/resources/MaterialSymbolsRounded.ttf",
            36f
        );

        try {
            UIManager.setLookAndFeel(new FlatMacLightLaf());
            // Create and display your GUI
            SwingUtilities.invokeLater(() -> {
                frame = new JFrame("Virtual Pet");
                frame.setLayout(new BorderLayout());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(width, height);
                frame.setLocationRelativeTo(null);
                frame.setResizable(false);

                cardLayout = new CardLayout();

                // > Right bound with button ---------------------------------------
                rightButton = new JButton("\ue315");
                rightButton.setContentAreaFilled(false);
                rightButton.setFocusPainted(false);
                rightButton.setForeground(black);
                rightButton.setBackground(null);
                rightButton.setBorder(null);
                rightButton.setFont(buttonIcon);
                mouseListener(rightButton);
                
                
                rightBound = new JPanel();
                rightBound.setPreferredSize(new Dimension(50, height));
                rightBound.setLayout(new BoxLayout(rightBound, BoxLayout.Y_AXIS));
                rightBound.setBackground(null);
                rightBound.add(Box.createVerticalGlue()); 
                rightBound.add(rightButton);
                rightBound.add(Box.createVerticalGlue());
                
                // > Left bound with button ----------------------------------------
                leftButton = new JButton("\ue314");
                leftButton.setContentAreaFilled(false);
                leftButton.setFocusPainted(false);
                leftButton.setForeground(black);
                leftButton.setBackground(null);
                leftButton.setBorder(null);
                leftButton.setFont(buttonIcon);
                mouseListener(leftButton);
                
                leftBound = new JPanel();
                leftBound.setPreferredSize(new Dimension(50, height));
                leftBound.setLayout(new BoxLayout(leftBound, BoxLayout.Y_AXIS));
                leftBound.setBackground(null);
                leftBound.add(Box.createVerticalGlue()); 
                leftBound.add(leftButton);
                leftBound.add(Box.createVerticalGlue());
                
                // Main Panel
                main = new JPanel();
                main.setPreferredSize(new Dimension(440, 720));
                main.setLayout(new BorderLayout());

                // >> Top part ------------------------------------------------------
                
                // >>> Pet Name
                petName = new JTextField("Add Pet");
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
                petAge = new JTextField("");
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
                top.setBackground(null);
                top.setLayout(new BorderLayout());
                top.add(h_petName, BorderLayout.NORTH);
                top.add(h_petAge, BorderLayout.SOUTH);
                
                // Midle
                // > Card ---------------------------------------------------------
                
                middle = new JPanel();
                middle.setPreferredSize(new Dimension(440, 570));
                middle.setLayout(cardLayout);
                middle.setBackground(null);

                addCard = new AddCardUI(petRepositoriesImp, this, middle);
                middle.add(addCard, "AddCard");

                // Bottom
                // >>> Bullet
                bullets = new ArrayList<>();
                
                bottom = new JPanel();
                bottom.setLayout(new FlowLayout());
                bottom.setPreferredSize(new Dimension(400, 50));
                bottom.setBackground(null);
                bottom.setBorder(null);
                bottom.setAlignmentX(JPanel.CENTER_ALIGNMENT);
                bottom.setAlignmentY(JPanel.CENTER_ALIGNMENT);
                updateCardNavigation();
            
            main.add(top, BorderLayout.NORTH);
            main.add(middle, BorderLayout.CENTER);
            main.add(bottom, BorderLayout.SOUTH);
            
            frame.add(leftBound, BorderLayout.WEST);
            frame.add(main, BorderLayout.CENTER);
            frame.add(rightBound, BorderLayout.EAST);
            frame.setVisible(true);

            rightButton.addActionListener((ActionEvent _) -> {
                cardLayout.next(middle);
                cardLength = petRepositoriesImp.getPetList().size() + 1;
                currentIndex = (currentIndex + 1) % cardLength;
                
                if(currentIndex == 0){
                    petName.setText("Add Pet");
                    petAge.setText("");
                } else {
                    // Pastikan ada pet sebelum mencoba mengakses
                    if (currentIndex - 1 < petRepositoriesImp.getPetList().size()) {
                        currentPet = petRepositoriesImp.getPetList().get(currentIndex - 1);
                        petName.setText(currentPet.getName());
                        petAge.setText(currentPet.getAge());
                    }
                }
                updateCardNavigation();
                updateBullets();
            });
            
            leftButton.addActionListener((ActionEvent _) -> {
                cardLayout.previous(middle);
                cardLength = petRepositoriesImp.getPetList().size() + 1;
                currentIndex = (currentIndex - 1 + cardLength) % cardLength;
                
                if(currentIndex == 0){
                    petName.setText("Add Pet");
                    petAge.setText("");
                } else {
                    // Pastikan ada pet sebelum mencoba mengakses
                    if (currentIndex - 1 < petRepositoriesImp.getPetList().size()) {
                        currentPet = petRepositoriesImp.getPetList().get(currentIndex - 1);
                        petName.setText(currentPet.getName());
                        petAge.setText(currentPet.getAge());
                    }
                }
                updateCardNavigation();
                updateBullets();

            });

            
            Timer timer = new Timer(60000, _ -> {
                if(currentPet != null){
                    petAge.setText(currentPet.getAge());
                }
            });
            timer.start();
            
            
        });
    } catch (UnsupportedLookAndFeelException e) {
            System.out.println("Look and feel error");
        }
        
    }

    private void updateBullets() {
        // Ensure the current index is within bounds
        if (currentIndex < 0 || currentIndex >= bullets.size()) {
            currentIndex = 0;
        }
        
        // Update bullet colors
        for (int i = 0; i < bullets.size(); i++) {
            JTextField bullet = bullets.get(i);
            
            // Highlight the current bullet
            if (i == currentIndex) {
                bullet.setDisabledTextColor(Color.BLACK);
                bullet.setText("\ue837");
            } else {
                bullet.setDisabledTextColor(Color.GRAY);
            }
        }
    }

    protected  void updateCardNavigation() {
        cardLength = petRepositoriesImp.getPetList().size() + 1;
        
        // Hapus bullets lama
        bottom.removeAll();
        bullets.clear();
        
        // Buat bullets baru
        for(int i = 0; i < cardLength; i++){
            JTextField bullet = new JTextField("\ue836");
            bullet.setHorizontalAlignment(JTextField.CENTER);
            bullet.setPreferredSize(new Dimension(15,11));
            bullet.setEnabled(false);
            bullet.setFont(buttonIcon.deriveFont(12f));
            bullet.setDisabledTextColor(Color.GRAY);
            bullet.setBackground(null);
            bullet.setBorder(null);
            bullets.add(bullet);
            bottom.add(bullet);
        }
        
        bottom.revalidate();
        bottom.repaint();
        
        updateBullets();
    }

    private void mouseListener(JButton button){
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setFont(buttonIcon.deriveFont(48f));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setFont(buttonIcon);
            }
            
        });
    }
}
