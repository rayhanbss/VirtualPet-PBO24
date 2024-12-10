package com.seventh.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.seventh.repositories.PetRepositoriesImp;

public class GameSaver {
    public static void savePetRepository(PetRepositoriesImp petRepo, String filename) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(petRepo);
        }
    }

    public static PetRepositoriesImp loadPetRepository(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            return (PetRepositoriesImp) in.readObject();
        }
    }
}

