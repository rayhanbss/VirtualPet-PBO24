package com.seventh.entities;

import java.util.Random;

public class Rabbit extends Pet {

    public Rabbit(String name) {
        super(name, getSmallMaxHealth());
    }

    // Special hungry level for rabbit
    // hard get happy 60-80 random, > 80 guaranteed
    @Override
    public void updateNegativeEffects() {
        updateEffect("dead", health == 0);
        updateEffect("sick", health < 50);
        updateEffect("tired", energy < 60);
        updateEffect("hungry", hunger < 60);
        updateEffect("thirsty", thirst < 70);
        updateEffect("sad", getHappiness() < 60 || (happiness >= 60 && happiness <= 80 && new Random().nextInt(10) != 0));
        updateEffect("dirty", cleanness < 70);
    }
}
