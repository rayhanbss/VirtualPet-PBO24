package com.seventh.entities;

import com.seventh.domain.Action;

public class Cat extends Pet implements Action{
    private final int health;
    private final int MAX_HEALTH;
    public Cat(String name){
        super(name);
        MAX_HEALTH = 70;
        health = MAX_HEALTH;
    }
    @Override
    public void giveFood() {
        setHunger();
    }
    @Override
    public void giveDrink() {
        setThrist();
    }
    @Override
    public void playWith() {
        setHappiness();
    }
    @Override
    public void walkWith() {
        setHappiness();
    }
    @Override
    public void takeNap() {
        setHappiness();    
    }
    @Override
    public void givePet() {
        setHappiness();    
    }
    @Override
    public void clean() {
        setCleanness();
    }
}
