package com.seventh.entities;

public class Hamster extends Pet {
    
    public Hamster(String name) {
        super(name, 50);
    }

     // Special hungry level for hamster
    // easily full at hunger > 50, max health 50
    @Override
    public void updateNegativeEffects() {
        updateEffect("dead", health == 0);
        updateEffect("sick", health < 50);
        updateEffect("tired", energy < 60);
        updateEffect("hungry", getHunger() <= 50); 
        updateEffect("thirsty", thirst < 70);
        updateEffect("sad", happiness < 60);
        updateEffect("dirty", cleanness < 7);
    }
}
