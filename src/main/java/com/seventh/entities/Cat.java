package com.seventh.entities;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.seventh.domain.Action;

public class Cat extends Pet implements Action{
    
    public Cat(String name){
        super(name, CAT_MAX_HEALTH());
    }

    // Special hungry level for cat
    // x < 70 guaranteed hungry, 70 <= x <= 80 chance 10% to hungry, x > 80 guaranteed not hungry 
    @Override
    public void setHungry(double lowerBound){
        isHungry = getHunger() < (lowerBound - 10) || 
                  (getHunger() >= (lowerBound - 10) && 
                   getHunger() <= lowerBound && 
                   new Random().nextInt(10) != 1);
    }

    @Override
    public void updateStatus(){
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        Runnable updateStatus = () -> {
            // Update age
            updateAge();

            // Update status level
            setHunger(-0.2);        // -1 hunger each 5 minutes
            setThirst(-0.5);        // -1 thrirst each 2 minnutes
            setHappiness(-0.25);    // -1 happiness each 4 minutes
            setCleanness(-0.1);     // -1 cleanness each 10 minutes

            // Update negative effects with lowerBounds
            // lowerBound: minimum stats level to not get negative effects
            setDead();
            setSick(50);
            setTired(60);
            setHungry(80);
            setThrirsty(70);
            setSad(60);
            setDirty(07);
        };

        // Run each minute
        scheduler.scheduleAtFixedRate(updateStatus, 0, 1, TimeUnit.MINUTES);
    }

    // Implements of Action's methods
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
    public void takeNap() {
        setEnergy(20);   
    }

    @Override
    public void clean() {
        setCleanness(15);
    }

    @Override
    public void goToVet() {
        setHealth(50);
    }
}
