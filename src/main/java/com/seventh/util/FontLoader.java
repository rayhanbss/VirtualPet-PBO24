package com.seventh.util;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;

public class FontLoader{
    public static Font load (Font materialFont, String path, float size) {
        try {
            materialFont = Font.createFont(Font.TRUETYPE_FONT, new File(path));
            materialFont = materialFont.deriveFont(size);

        } catch (IOException | FontFormatException e) {
            System.out.println("Failed load font");
        }

        System.out.println("font loaded");
        return materialFont;
    }
}
