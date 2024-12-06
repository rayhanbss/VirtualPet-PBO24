package com.seventh.repositories;

import java.util.ArrayList;
import java.util.List;

import com.seventh.domain.PetRepositories;
import com.seventh.entities.Cat;
import com.seventh.entities.Pet;

public class PetRepositoriesImp implements PetRepositories{
    private final List<Pet> petList = new ArrayList<>();


    @Override
    public void createPet(String name, int type) {
        Pet newPet;
        switch (type) {
            case 1 -> newPet = new Cat(name);
            default -> throw new AssertionError();
        }
        petList.add(newPet);
    }

    @Override
    public void action(Pet pet, int type) {
        switch (type) {
            case 1 -> pet.goToVet();
            case 2 -> pet.giveFood();
            case 3 -> pet.giveDrink();
            case 4 -> pet.playWith();
            case 5 -> pet.takeNap();
            case 6 -> pet.clean();
            default -> throw new AssertionError();
        }
    }

    @Override
    public void getInformation(Pet pet) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Pet> getPetList() {
        return petList;
    }

}
