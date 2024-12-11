package com.seventh.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ImageLoader {
    public static ImageIcon load(String path){
        try {
            InputStream imageStream = ImageLoader.class.getClassLoader().getResourceAsStream("images/" + path);
            
            if (imageStream != null) {
                BufferedImage image = ImageIO.read(imageStream);
                
                if (image != null) {
                    ImageIcon imageIcon = new ImageIcon(image);
                    Image resized = imageIcon.getImage().getScaledInstance(350, 350,  java.awt.Image.SCALE_SMOOTH);
                    imageIcon = new ImageIcon(resized);
                    System.out.println("Loaded image details:");
                    System.out.println("Width: " + imageIcon.getIconWidth());
                    System.out.println("Height: " + imageIcon.getIconHeight());
                    return imageIcon;
                } else {
                    System.out.println("Failed to read image from stream: " + path);
                }
            } else {
                System.out.println("Image file not found: " + path);
            }
        } catch (IOException e) {
            System.out.println("Failed to load image: " + path);
        }
        return null;
    }
}
