package com.seventh.repositories;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.seventh.domain.PetRepositories;
import com.seventh.entities.Cat;
import com.seventh.entities.Dog;
import com.seventh.entities.Hamster;
import com.seventh.entities.Parrot;
import com.seventh.entities.Pet;
import com.seventh.entities.Rabbit;
import com.seventh.entities.Turtle;
public class PetRepositoriesImp implements PetRepositories, Serializable{
    private static final long serialVersionUID = 1L;

    private final List<Pet> petList = new ArrayList<>();
    private LocalDateTime lastSavedTime;

    @Override
    public void createPet(String name, int type) {
        Pet newPet;
        switch (type) {
            case 1 -> newPet = new Cat(name);
            case 2 -> newPet = new Dog(name);
            case 3 -> newPet = new Hamster(name);
            case 4 -> newPet = new Parrot(name);
            case 5 -> newPet = new Rabbit(name);
            case 6 -> newPet = new Turtle(name);
            default -> throw new AssertionError();
        }
        petList.add(newPet);
    }

    public List<Pet> getPetList() {
        return petList;
    }

    @Override
    public double getPetHealth(Pet pet) {
        return pet.getHealth();
    }

    @Override
    public double getPetEnergy(Pet pet) {
        return pet.getEnergy();
    }

    @Override
    public double getPetHunger(Pet pet) {
        return pet.getHunger();
    }

    @Override
    public double getPetThrirst(Pet pet) {
        return pet.getThirst();
    }

    @Override
    public double getPetCleanness(Pet pet) {
        return pet.getCleanness();
    }

    @Override
    public double getPetHappiness(Pet pet) {
        return pet.getHappiness();
    }

    public void setLastSavedTime(){ lastSavedTime = LocalDateTime.now(); }

    public LocalDateTime getLastSavedTime(){ return lastSavedTime; }
    
}
