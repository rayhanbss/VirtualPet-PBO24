package com.seventh.util;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class FontLoader {
    public static Font load(String path, float size) {
        try {
            URL fontUrl = FontLoader.class.getResource("/font/" + path);
            
            if (fontUrl == null) {
                System.out.println("Font file not found: " + path);
                return null;
            }
            
            Font font = Font.createFont(Font.TRUETYPE_FONT, new File(fontUrl.toURI()));
            return font.deriveFont(size);

        } catch (IOException | FontFormatException | java.net.URISyntaxException e) {
            System.out.println("Failed to load font: " + path);
        }
        return null;
    }
}
