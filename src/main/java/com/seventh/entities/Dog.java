package com.seventh.entities;

public class Dog extends Pet {
    public Dog(String name) {
        super(name, getLargeMaxHealth(), 2);
    }

    // Special happiness condition for Dog
    // Easily sad at happiness < 70
    @Override
    public void updateNegativeEffects() {
        updateEffect("dead", health == 0);
        updateEffect("sick", health < health/2);
        updateEffect("tired", energy < 60);
        updateEffect("hungry", hunger < 60);
        updateEffect("thirsty", thirst < 70);
        updateEffect("sad", happiness < 70); // Dog gets sad more easily if happiness < 70
        updateEffect("dirty", cleanness < 70);
    }
}
