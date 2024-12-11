package com.seventh.util;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;

public class FontLoader{
    public static Font load (Font font, String path, float size) {
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File(path));
            font = font.deriveFont(size);

        } catch (IOException | FontFormatException e) {
            System.out.println("Failed load font");
        }
        return font;
    }
}
