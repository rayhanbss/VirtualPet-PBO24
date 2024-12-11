package com.seventh.util;

import java.io.BufferedInputStream;
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
            InputStream inputStream = AudioLoader.class.getClassLoader().getResourceAsStream("audio/" + fileName);
            if (inputStream == null) {
                System.err.println("File audio tidak ditemukan: " + fileName);
                throw new IllegalArgumentException("File not found: " + fileName);
            }

            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(bufferedInputStream);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);

            System.out.println("Audio berhasil dimuat: " + fileName);
            return clip;

        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException e) {
            System.err.println("Error loading sound file: " + e.getMessage());
        }
        return null;
    }

    public static void play(Clip clip) {
        if (clip != null) {
            Thread audioThread = new Thread(() -> {
                clip.stop();
                clip.setFramePosition(0);
                clip.start();
            });
            audioThread.start();
        } else {
            System.err.println("Clip is null. Cannot play audio.");
        }
    }
}
