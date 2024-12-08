package com.seventh.domain;

import com.seventh.entities.Pet;

public interface PetRepositories {
    // Create
    public void createPet(String name, int type);

    // Read
    public double getPetHealth(Pet pet);
    public double getPetEnergy(Pet pet);
    public double getPetHunger(Pet pet);
    public double getPetThrirst(Pet pet);
    public double getPetHappiness(Pet pet);
    public double getPetCleanness(Pet pet);
}
