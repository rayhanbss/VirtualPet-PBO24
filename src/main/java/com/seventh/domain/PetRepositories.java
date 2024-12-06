package com.seventh.domain;

import com.seventh.entities.Pet;

public interface PetRepositories {
    // Create
    public void createPet(String name, int type);
    // Update
    public void action(Pet pet, int type);

    // Read
    public boolean  getPetHealth(Pet pet);
    public void getInformation(Pet pet);
    // Delete ?
}
