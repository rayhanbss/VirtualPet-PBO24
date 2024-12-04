package com.seventh.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;

public class MainUI {
    JFrame frame;
    JPanel main, top, center, bottom;

    JPanel rightBound, leftBound;
    JButton rightButton, leftButton;
    JPanel h_petName, h_petAge;
    JTextField petName, petAge;
    Font mainFont, subFont;

    private final int width = 540;
    private final int height = 720;

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

                // Right bound with button ---------------------------------------
                rightButton = new JButton(">");
                rightButton.setBackground(null);
                rightButton.setFont(mainFont);

                rightBound = new JPanel();
                rightBound.setPreferredSize(new Dimension(50, height));
                rightBound.setLayout(new BorderLayout());
                rightBound.setBackground(Color.BLUE);
                rightBound.add(rightButton, BorderLayout.CENTER);

                // Right bound with button ---------------------------------------
                leftButton = new JButton("<");
                leftButton.setBackground(null);
                leftButton.setFont(mainFont);

                leftBound = new JPanel();
                leftBound.setPreferredSize(new Dimension(50, height));
                leftBound.setLayout(new BorderLayout());
                leftBound.setBackground(Color.GREEN);
                leftBound.add(leftButton, BorderLayout.CENTER);

                // ----2
                petName = new JTextField("Pet Name");
                petName.setHorizontalAlignment(JTextField.CENTER);
                petName.setDisabledTextColor(Color.WHITE);
                // petName.setBackground(null);
                petName.setEnabled(false);
                petName.setFont(mainFont);
                
                h_petName = new JPanel();
                h_petName.setPreferredSize(new Dimension(440,60));
                h_petName.setLayout(new BorderLayout());
                h_petName.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
                h_petName.setBackground(Color.gray);
                h_petName.add(petName, BorderLayout.SOUTH,0);

                petAge = new JTextField("?? Month ?? Years");
                petAge.setHorizontalAlignment(JTextField.CENTER);
                petAge.setDisabledTextColor(Color.WHITE);
                // petAge.setBackground(null);
                petAge.setEnabled(false);
                petAge.setFont(subFont);
                
                h_petAge = new JPanel();
                h_petAge.setPreferredSize(new Dimension(440,40));
                h_petAge.setLayout(new BorderLayout());
                h_petAge.setBorder(null);
                h_petAge.setBackground(Color.WHITE);
                h_petAge.add(petAge, BorderLayout.NORTH,0);

                top = new JPanel();
                top.setPreferredSize(new Dimension(440, 100));
                top.setBorder(null);
                top.setBackground(Color.ORANGE);
                top.setLayout(new BorderLayout(0,0));
                top.add(h_petName, BorderLayout.NORTH,0);
                top.add(h_petAge, BorderLayout.SOUTH,0);
                

                center = new JPanel();
                center.setPreferredSize(new Dimension(440, 300));
                center.setBackground(Color.PINK);

                bottom = new JPanel();
                bottom.setPreferredSize(new Dimension(440, 220));
                bottom.setBackground(Color.CYAN);


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
