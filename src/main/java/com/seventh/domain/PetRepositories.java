package com.seventh.domain;

public interface PetRepositories {
    // Create
    public void createPet(String name, Enum Type);
    // Update
    public void action(Enum Type);
    // Read
    public void getInformation();
    // Delete ?
}
