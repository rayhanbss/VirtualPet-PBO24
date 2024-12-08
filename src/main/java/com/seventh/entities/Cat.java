package com.seventh.entities;

import java.util.Random;
public class Cat extends Pet{
    
    public Cat(String name){
        super(name, getMediumMaxHealth());
    }

    // Special hungry level for cat
    // x < 70 guaranteed hungry, 70 <= x <= 80 chance 10% to hungry, x > 80 guaranteed not hungry 
    @Override
    public void updateNegativeEffects() {
        updateEffect("dead", health == 0);
        updateEffect("sick", health < 50);
        updateEffect("tired", energy < 60);
        updateEffect("hungry", getHunger() < 70 || (getHunger() >= (70) && getHunger() <= 80 && new Random().nextInt(10) != 1));
        updateEffect("thirsty", thirst < 70);
        updateEffect("sad", happiness < 60);
        updateEffect("dirty", cleanness < 70);
    }

}
