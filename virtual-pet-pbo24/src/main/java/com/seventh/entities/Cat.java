package com.seventh.entities;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.seventh.domain.Action;

public class Cat extends Pet implements Action{
    private final double health;
    private final double MAX_HEALTH;
    protected boolean isDead, isSick;
    public Cat(String name){
        super(name);
        MAX_HEALTH = 70;
        health = MAX_HEALTH;
    }

    public void setLife(){
        isDead = (health == 0);
        isSick = (health < 50);
    }
    @Override
    public void setHungry(double bounds){
        isHungry = getHunger() < (bounds - 10) || 
                  (getHunger() >= (bounds - 10) && 
                   getHunger() <= bounds && 
                   new Random().nextInt(10) != 1);
    }

    @Override
    public void updateStatus(){
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        Runnable updateStatus = () -> {
            setHunger(-0.2);
            setThirst(-0.5);
            setHappiness(-0.25);
            setCleanness(-0.1);
            updateAge();
            setLife();
            setTired(60);
            setHungry(80);
            setThrirsty(70);
            setSad(60);
            setDirty(07);
        };

        scheduler.scheduleAtFixedRate(updateStatus, 0, 1, TimeUnit.MINUTES);
    }
    @Override
    public void giveFood() {
        setHunger(5);
        setEnergy(-2);
    }
    @Override
    public void giveDrink() {
        setThirst(5);
        setEnergy(-2);
    }
    @Override
    public void playWith() {
        setHappiness(15);
        setEnergy(-15);
    }
    @Override
    public void walkWith() {
        setHappiness(25);
        setEnergy(-25);
    }
    @Override
    public void takeNap() {
        setEnergy(20);   
    }
    @Override
    public void givePet() {
        setHappiness(10);    
    }
    @Override
    public void clean() {
        setCleanness(15);
    }
}
