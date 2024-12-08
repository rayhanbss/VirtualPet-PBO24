package com.seventh.entities;

import java.util.Random;
public class Parrot extends Pet{
    
    public Parrot(String name){
        super(name, getParrotMaxHealth());
    }

    // Special hungry level for parrot
    // hard get full 60-80 random, > 80 guaranteed, max health 70
    @Override
    public void updateNegativeEffects() {
        updateEffect("dead", health == 0);
        updateEffect("sick", health < 70);
        updateEffect("tired", energy < 60);
        updateEffect("hungry", getHunger() < 60 || (getHunger() >= (60) && getHunger() <= 80 && new Random().nextInt(10) != 1));
        updateEffect("thirsty", thirst < 70);
        updateEffect("sad", happiness < 60);
        updateEffect("dirty", cleanness < 7);
    }

}