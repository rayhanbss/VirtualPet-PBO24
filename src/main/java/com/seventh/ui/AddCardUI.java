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
import com.seventh.util.FontLoader;

public class AddCardUI extends JPanel {
    MainUI mainUI;
    PetRepositoriesImp petRepositoriesImp;
    JPanel cardPanel;
    FontLoader fontLoader;
    Font buttonIcon;

    JButton addButton;

    public AddCardUI(PetRepositoriesImp petRepositoriesImp, MainUI mainUI, JPanel cardPanel) {
        this.mainUI = mainUI;
        this.petRepositoriesImp = petRepositoriesImp;
        this.cardPanel = cardPanel;
        fontLoader = new FontLoader();

        buttonIcon = fontLoader.load(
                buttonIcon,
                "src/main/resources/MaterialSymbolsRounded.ttf",
                48f
        );

        // Mengatur layout menjadi BoxLayout (vertical)
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

        // Mengatur posisi tombol ke tengah
        addButton.setAlignmentX(CENTER_ALIGNMENT);
        addButton.setAlignmentY(CENTER_ALIGNMENT);

        // Tambahkan glue di atas dan bawah untuk menempatkan tombol di tengah
        this.add(Box.createRigidArea(new Dimension(440, 206)));
        this.add(addButton);
        this.add(Box.createVerticalGlue());

        addButton.addActionListener((ActionEvent e) -> {
            // Membuat panel untuk input nama dan tipe hewan
            JPanel panel = new JPanel();
            panel.setPreferredSize(new Dimension(240, 60));
            panel.setLayout(new GridLayout(2, 2)); // Mengatur layout menjadi 2 baris dan 2 kolom

            // Label dan input untuk nama pet
            JLabel nameLabel = new JLabel("Pet Name");
            JTextField nameField = new JTextField(20);

            // Label dan dropdown untuk memilih tipe hewan
            JLabel typeLabel = new JLabel("Tipe Hewan");
            String[] animalTypes = {"Cat", "Dog"};
            JComboBox<String> typeComboBox = new JComboBox<>(animalTypes);

            // Menambahkan elemen-elemen ke dalam panel
            panel.add(nameLabel);
            panel.add(nameField);
            panel.add(typeLabel);
            panel.add(typeComboBox);

            // Menampilkan input dialog dalam bentuk panel
            JOptionPane optionPane = new JOptionPane(panel, JOptionPane.PLAIN_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
            JDialog dialog = optionPane.createDialog("Masukkan Informasi Pet");

            // Centering the OK button
            
            dialog.setLayout(new BorderLayout());
            JPanel buttonPanel = (JPanel) optionPane.getComponent(1); // Get the button panel
            buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER)); // Center the buttons

            dialog.setVisible(true);

            int option = (int) optionPane.getValue();

            // Handle the case where the dialog is closed by clicking the "X" button (CLOSED_OPTION)
            switch (option) {
                case JOptionPane.CLOSED_OPTION -> {
                    System.out.println("Dialog closed by user (X button)");
                    return;  // Exit the method gracefully
                }
                case JOptionPane.OK_OPTION -> {
                    int type = 0;
                    if ("Cat".equals((String) typeComboBox.getSelectedItem())) type = 1;
                    if ("Dog".equals((String) typeComboBox.getSelectedItem())) type = 2;
                    if (nameField.getText().trim().isEmpty() || type == 0) {
                        JOptionPane.showMessageDialog(null, "Name and Pet Type cannot be empty!");
                    } else {
                        petRepositoriesImp.createPet(nameField.getText(), type);
                        PetCardUI petCard = new PetCardUI(petRepositoriesImp.getPetList().getLast());
                        cardPanel.add(petCard, "PetCard" + (petRepositoriesImp.getPetList().size() - 1));
                        mainUI.updateCardNavigation();
                    }
                }
                default -> System.out.println("close");
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
