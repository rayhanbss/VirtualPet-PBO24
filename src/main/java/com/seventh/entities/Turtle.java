package com.seventh.entities;
public class Turtle extends Pet{
    
    public Turtle(String name){
        super(name, getMediumMaxHealth(), 6);
    }

    // Special hungry level for Turtle
    // easily stinks at < 80, max health 70

    @Override
    public void updateNegativeEffects() {
        updateEffect("dead", health == 0);
        updateEffect("sick", health < health/2);
        updateEffect("tired", energy < 60);
        updateEffect("hungry", getHunger() < 60);
        updateEffect("thirsty", thirst < 70);
        updateEffect("sad", happiness < 60);
        updateEffect("dirty", cleanness < 80);
    }

}