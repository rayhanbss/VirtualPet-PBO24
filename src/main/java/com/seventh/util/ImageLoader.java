package com.seventh.util;

import java.net.URL;

import javax.swing.ImageIcon;

public class ImageLoader {
    public static ImageIcon load(String path){
        try{
            URL imageUrl = ImageLoader.class.getResource("/image/" + path);
            if (imageUrl != null) {
                ImageIcon image = new ImageIcon(imageUrl);
                System.out.println("Loaded image details:");
                System.out.println("Width: " + image.getIconWidth());
                System.out.println("Height: " + image.getIconHeight());
                return image;
            } else {
                System.out.println("Image URL not found: " + path);
            }
        } catch(Exception e){
            System.out.println("Failed to load image: " + path);
        }
        return null;
    }
}
