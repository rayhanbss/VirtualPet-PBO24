package com.seventh.entities;


import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import com.seventh.domain.Action;

public class Pet implements Action, Serializable {
    private static final long serialVersionUID = 1L; 

    private final String name;
    private final LocalDate birthDate;
    private String age;
    
    protected final double MAX_HEALTH;
    
    private static final double LARGE_MAX_HEALTH = 100;
    private static final double MEDIUM_MAX_HEALTH = 70;
    private static final double SMALL_MAX_HEALTH = 50;
    
    
    private static final double MAX_STATS = 100;
    protected double health, energy, hunger, thirst, happiness, cleanness;
    protected boolean isDead, isSick, isTired, isHungry, isThristy, isSad, isDirty;
    
    public Pet(String name, double upperBound) {
        this.name = name;
        this.birthDate = LocalDate.now();
        this.age = "Just Born";
        this.MAX_HEALTH = upperBound > 0 ? upperBound : 100;
        
        // Initialize stats
        this.health = MAX_HEALTH;
        this.energy = this.hunger = this.thirst = this.happiness = this.cleanness = MAX_STATS;
        
        // Initialize negative effects
        resetNegativeEffects();
    }
    
    private void resetNegativeEffects() {
        isDead = isSick = isTired = isHungry = isThristy = isSad = isDirty = false;
    }
    
    // Update age
    public void updateAge() {
        double week = ChronoUnit.DAYS.between(birthDate, LocalDate.now());
        if(isDead) {
            age = "Dead";
        }else if (week > 52) {
            age = String.format("%.2f Years", week / 52);
        } else {
            age = String.format("%.0f Weeks", week);
        } 
    }
    
    // General setter for stats
    private void updateStat(String stat, double amount, double max) {
        switch (stat) {
            case "health" -> health = Math.max(Math.min(health + amount, max), 0 );
            case "energy" -> energy = Math.max(Math.min(energy + amount, max), 0 );
            case "hunger" -> hunger = Math.max(Math.min(hunger + amount, max), 0 );
            case "thirst" -> thirst = Math.max(Math.min(thirst + amount, max), 0 );
            case "happiness" -> happiness = Math.max(Math.min(happiness + amount, max), 0 );
            case "cleanness" -> cleanness = Math.max(Math.min(cleanness + amount, max), 0 );
        }
    }
    
    // Negative effect setters
    protected  void updateEffect(String effect, boolean condition) {
        switch (effect) {
            case "dead" -> isDead = condition;
            case "sick" -> isSick = condition;
            case "tired" -> isTired = condition;
            case "hungry" -> isHungry = condition;
            case "thirsty" -> isThristy = condition;
            case "sad" -> isSad = condition;
            case "dirty" -> isDirty = condition;
        }
    }
    
    public void updateNegativeEffects() {
        
        updateEffect("dead", health <= 0);
        updateEffect("sick", health < 50);
        updateEffect("tired", energy < 60);
        updateEffect("hungry", hunger < 80);
        updateEffect("thirsty", thirst < 70);
        updateEffect("sad", happiness < 60);
        updateEffect("dirty", cleanness < 70);
    }
    
    // Status update method
    @Override
    public void updateStatus() {
        updateAge();
        if(isDead) energy = hunger = thirst = happiness = cleanness = 0;
        if(isHungry || isThristy || isTired) updateStat("health", -0.1, MAX_HEALTH);
        if(isSick) updateStat("health", -0.2, MAX_HEALTH);
        updateStat("hunger", -0.2, MAX_STATS);
        updateStat("thirst", -0.2, MAX_STATS);
        updateStat("happiness", -0.25, MAX_STATS);
        updateStat("cleanness", -0.1, MAX_STATS);
    }
    
    
    
    // Implement actions
    @Override
    public void giveFood() {
        updateStat("hunger", 5, MAX_STATS); 
        updateStat("energy", -2, MAX_STATS); 
    }
    @Override
    public void giveDrink() { 
        updateStat("thirst", 5, MAX_STATS); 
        updateStat("energy", -2, MAX_STATS); 
    }
    @Override
    public void playWith() { 
        updateStat("happiness", 15, MAX_STATS); 
        updateStat("energy", -15, MAX_STATS); 
        updateStat("cleanness", -20, MAX_STATS);
    }
    @Override
    public void takeNap() { updateStat("energy", 100, MAX_STATS); }
    @Override
    public void clean() { updateStat("cleanness", 100, MAX_STATS); }
    @Override
    public void goToVet() { if(health > 0) updateStat("health", 50, MAX_HEALTH); }
    
    @Override
    public void action(int type) {
        switch (type) {
            case 1 -> goToVet();
            case 2 -> giveFood();
            case 3 -> giveDrink();
            case 4 -> playWith();
            case 5 -> takeNap();
            case 6 -> clean();
            default -> throw new AssertionError();
        }
    }
    
    // Getters
    public String getName() { return name; }
    public String getAge() { return age; }
    public LocalDate getBirthDate() { return birthDate; }
    public double getHealth() { return health; }
    public double getEnergy() { return energy; }
    public double getHunger() { return hunger; }
    public double getThirst() { return thirst; }
    public double getHappiness() { return happiness; }
    public double getCleanness() { return cleanness; }
    public boolean isDead() { return isDead; }
    public boolean isSick() { return isSick; }
    public boolean isTired() { return isTired; }
    public boolean isHungry() { return isHungry; }
    public boolean isThirsty() { return isThristy; }
    public boolean isSad() { return isSad; }
    public boolean isDirty() { return isDirty; }
    public static long getSerialVersionUID() { return serialVersionUID; }
    
    public double getMaxHealth () { return MAX_HEALTH; };

    public static double getLargeMaxHealth() { return LARGE_MAX_HEALTH; }
    public static double getMediumMaxHealth() { return MEDIUM_MAX_HEALTH; }
    public static double getSmallMaxHealth() { return SMALL_MAX_HEALTH; }

}
