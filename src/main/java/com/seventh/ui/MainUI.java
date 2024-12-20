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
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
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
import com.seventh.util.AudioLoader;
import com.seventh.util.FontLoader;
import com.seventh.util.GameSaver;
import com.seventh.util.ImageLoader;

public class MainUI {
    private PetRepositoriesImp petRepositoriesImp;
    private Pet currentPet;
    private int currentIndex;
    private int cardLength;
    
    JFrame frame;
    ImageIcon appIcon;
    
    CardLayout cardLayout;
    AddCardUI addCard;

    JPanel main, top, middle, bottom;
    JPanel h_petName, h_petAge;
    JTextField petName, petAge;
    List<JTextField> bullets;
    
    JPanel rightBound, leftBound;
    JButton rightButton, leftButton;

    Clip backgroundMusic, buttonSound;
    
     
    Color black = new Color(29,29,29);
    Font mainFont, subFont, buttonIcon;

    private final int width = 540;
    private final int height = 720;

    public MainUI(){
        loadGame();

        cardLength = 1;
        currentIndex = 0;

        mainFont = new Font("Open Sans",Font.BOLD, 24);
        subFont = new Font("Open Sans",Font.PLAIN, 12);
        buttonIcon = FontLoader.load(
            "MaterialSymbolsRounded.ttf",
            36f
        );

        buttonSound = AudioLoader.load("button.wav");
        buttonSound.setFramePosition(0);

        backgroundMusic = AudioLoader.load("bgm.wav");
        FloatControl volume = (FloatControl) backgroundMusic.getControl(FloatControl.Type.MASTER_GAIN);
        volume.setValue(-10.0f);
        backgroundMusic.setFramePosition(0);
        backgroundMusic.loop(Clip.LOOP_CONTINUOUSLY);
        backgroundMusic.start();
        
        try {
            UIManager.setLookAndFeel(new FlatMacLightLaf());
            SwingUtilities.invokeLater(() -> {
                frame = new JFrame("Virtual Pet");
                frame.setLayout(new BorderLayout());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(width, height);
                frame.setLocationRelativeTo(null);
                frame.setResizable(false);

                appIcon = ImageLoader.load("icon.png");
                frame.setIconImage(appIcon.getImage());

                cardLayout = new CardLayout();

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
                
                main = new JPanel();
                main.setPreferredSize(new Dimension(440, 720));
                main.setLayout(new BorderLayout());

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

                middle = new JPanel();
                middle.setPreferredSize(new Dimension(440, 570));
                middle.setLayout(cardLayout);
                middle.setBackground(null);

                addCard = new AddCardUI(petRepositoriesImp, this, middle);
                middle.add(addCard, "AddCard");
                if(petRepositoriesImp.getPetList() != null){
                    for(Pet pet : petRepositoriesImp.getPetList()){
                        PetCardUI petCardUI = new PetCardUI(pet);
                        middle.add(petCardUI);
                    }
                }

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
                AudioLoader.play(buttonSound);
                cardLayout.next(middle);
                cardLength = petRepositoriesImp.getPetList().size() + 1;
                currentIndex = (currentIndex + 1) % cardLength;
                
                if(currentIndex == 0){
                    petName.setText("Add Pet");
                    petAge.setText("");
                } else {
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
                AudioLoader.play(buttonSound);
                cardLayout.previous(middle);
                cardLength = petRepositoriesImp.getPetList().size() + 1;
                currentIndex = (currentIndex - 1 + cardLength) % cardLength;
                
                if(currentIndex == 0){
                    petName.setText("Add Pet");
                    petAge.setText("");
                } else {
                    if (currentIndex - 1 < petRepositoriesImp.getPetList().size()) {
                        currentPet = petRepositoriesImp.getPetList().get(currentIndex - 1);
                        petName.setText(currentPet.getName());
                        petAge.setText(currentPet.getAge());
                    }
                }
                updateCardNavigation();
                updateBullets();

            });
            
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    petRepositoriesImp.setLastSavedTime();
                    GameSaver.savePetRepository(petRepositoriesImp, "save.dat");
                    System.out.println("Pet data saved automatically.");
                } catch (IOException e) {
                   System.out.println("save failed");
                }
            }));

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

    private void loadGame(){
        try {
            petRepositoriesImp = GameSaver.loadPetRepository("save.dat");
            if (petRepositoriesImp.getLastSavedTime() == null) petRepositoriesImp.setLastSavedTime();
            if (petRepositoriesImp.getLastSavedTime() != null){
                Duration duration = Duration.between(petRepositoriesImp.getLastSavedTime(), LocalDateTime.now());
                double minutes = Math.floor(duration.toMinutes());
                System.out.println(minutes);
                for(Pet pet : petRepositoriesImp.getPetList()){
                    for (double i = 0; i < minutes; i++){
                        if(!pet.isDead()) pet.updateStatus();
                    }
                }
            }
            System.out.println("Pet Loaded");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Failed load, create new instead");
            petRepositoriesImp = new PetRepositoriesImp();
        }
    }
    
    private void updateBullets() {
        if (currentIndex < 0 || currentIndex >= bullets.size()) {
            currentIndex = 0;
        }
        
        for (int i = 0; i < bullets.size(); i++) {
            JTextField bullet = bullets.get(i);
            
            if (i == currentIndex) {
                bullet.setDisabledTextColor(Color.BLACK);
                bullet.setText("\ue837");
            } else {
                bullet.setDisabledTextColor(Color.GRAY);
            }
        }
    }

    protected void updateCardNavigation() {
        cardLength = petRepositoriesImp.getPetList().size() + 1;
        
        bottom.removeAll();
        bullets.clear();
        
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
