package com.seventh.util;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;

public class FontLoader {
    public static Font load(String path, float size) {
        try {
            InputStream inputStream = FontLoader.class.getClassLoader().getResourceAsStream("font/" + path);
            
            if (inputStream == null) {
                System.out.println("Font file not found: " + path);
                return null;
            }
            
            Font font = Font.createFont(Font.TRUETYPE_FONT, inputStream);
            return font.deriveFont(size);

        } catch (IOException | FontFormatException e) {
            System.out.println("Failed to load font: " + path);
        }
        return null;
    }
}