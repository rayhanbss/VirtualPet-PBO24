package com.seventh.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.sound.sampled.Clip;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.seventh.repositories.PetRepositoriesImp;
import com.seventh.util.AudioLoader;
import com.seventh.util.FontLoader;

public class AddCardUI extends JPanel {
    Font buttonIcon;
    JButton addButton;
    Clip buttonSound, errorSound;


    public AddCardUI(PetRepositoriesImp petRepositoriesImp, MainUI mainUI, JPanel cardPanel) {
        buttonIcon = FontLoader.load(
                "MaterialSymbolsRounded.ttf",
                48f
        );

        buttonSound = AudioLoader.load("button.wav");
        buttonSound.setFramePosition(0);

        errorSound = AudioLoader.load("error.wav");
        errorSound.setFramePosition(0);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setPreferredSize(new Dimension(440, 570));
        this.setBackground(null);

        addButton = new JButton("\ue147");
        addButton.setPreferredSize(new Dimension(200, 70));
        addButton.setContentAreaFilled(false);
        addButton.setFocusPainted(false);
        addButton.setForeground(Color.BLACK);
        addButton.setBackground(null);
        addButton.setBorder(null);
        addButton.setFont(buttonIcon);

        addButton.setAlignmentX(CENTER_ALIGNMENT);
        addButton.setAlignmentY(CENTER_ALIGNMENT);

        this.add(Box.createRigidArea(new Dimension(440, 206)));
        this.add(addButton);
        this.add(Box.createVerticalGlue());

        addButton.addActionListener((ActionEvent _) -> {
            AudioLoader.play(buttonSound);
            boolean validInput = false;
            while (!validInput) {
                JPanel panel = new JPanel();
                panel.setPreferredSize(new Dimension(240, 60));
                panel.setLayout(new GridLayout(2, 2)); 

                JLabel nameLabel = new JLabel("Pet Name");
                JTextField nameField = new JTextField(20);

                JLabel typeLabel = new JLabel("Tipe Hewan");
                String[] animalTypes = {"Cat", "Dog", "Hamster", "Parrot", "Rabbit", "Turtle"};
                JComboBox<String> typeComboBox = new JComboBox<>(animalTypes);

                panel.add(nameLabel);
                panel.add(nameField);
                panel.add(typeLabel);
                panel.add(typeComboBox);

                JOptionPane optionPane = new JOptionPane(panel, JOptionPane.PLAIN_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
                JDialog dialog = optionPane.createDialog("Masukkan Informasi Pet");

                dialog.setLayout(new BorderLayout());
                JPanel buttonPanel = (JPanel) optionPane.getComponent(1);
                buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

                dialog.setVisible(true);

                if (optionPane.getValue() == null) {
                    System.out.println("Dialog closed without selection (X button).");
                    return;
                }
                int option = (int) optionPane.getValue();

                switch (option) {
                    case JOptionPane.CLOSED_OPTION -> {
                        AudioLoader.play(errorSound);
                        System.out.println("Dialog closed by user (X button)");
                        return;
                    }
                    case JOptionPane.OK_OPTION -> {
                        if (buttonSound != null) {
                            buttonSound.setFramePosition(0);
                            buttonSound.start();
                        }
                        int type = 0;
                        if ("Cat".equals((String) typeComboBox.getSelectedItem())) type = 1;
                        if ("Dog".equals((String) typeComboBox.getSelectedItem())) type = 2;
                        if ("Hamster".equals((String) typeComboBox.getSelectedItem())) type = 3;
                        if ("Parrot".equals((String) typeComboBox.getSelectedItem())) type = 4;
                        if ("Rabbit".equals((String) typeComboBox.getSelectedItem())) type = 5;
                        if ("Turtle".equals((String) typeComboBox.getSelectedItem())) type = 6;
                        if (nameField.getText().trim().isEmpty() || type == 0) {
                            if (errorSound != null) errorSound.start();
                            JOptionPane.showMessageDialog(null, "Name and Pet Type cannot be empty!");
                        } else {
                            petRepositoriesImp.createPet(nameField.getText(), type);
                            PetCardUI petCard = new PetCardUI(petRepositoriesImp.getPetList().getLast());
                            cardPanel.add(petCard, "PetCard" + (petRepositoriesImp.getPetList().size() - 1));
                            mainUI.updateCardNavigation();
                            validInput = true;
                        }
                    }
                    default -> System.out.println("close");
                }
            }
        });

        addButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                addButton.setFont(buttonIcon.deriveFont(64f));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                addButton.setFont(buttonIcon);
            }
        });
    }
}
