package com.seventh.util;

import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AudioLoader {
    public static Clip load(String fileName) {
        try {
            InputStream inputStream = AudioLoader.class.getResourceAsStream("/audio/" + fileName);

            if (inputStream == null) {
                throw new IllegalArgumentException("File not found: " + fileName);
            }

            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(inputStream);

            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);

            return clip;
        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
            System.err.println("Error loading sound file: " + e.getMessage());
        }
        return null;
    }

    public static void play(Clip clip){
        Thread audioThread = new Thread(() -> {
            if (clip != null) {
                clip.stop();
                clip.setFramePosition(0);
                clip.start();
            }
        });
        audioThread.start();
    }
}
