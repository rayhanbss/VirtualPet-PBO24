package com.seventh.entities;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.seventh.domain.Action;

public class Pet implements Action {

    private final String name;
    private final LocalDate birthDate;
    private String age;

    protected final double MAX_HEALTH;
    private static final double DOG_MAX_HEALTH = 100;
    private static final double CAT_MAX_HEALTH = 70;
    private static final double HAMSTER_MAX_HEALTH = 50;

    private static final double MAX_STATS = 100;
    protected double health, energy, hunger, thirst, happiness, cleanness;
    protected boolean isDead, isSick, isTired, isHungry, isThristy, isSad, isDirty;

    public Pet(String name, double upperBound) {
        this.name = name;
        this.birthDate = LocalDate.now();
        this.MAX_HEALTH = upperBound;

        // Initialize stats
        this.health = MAX_HEALTH;
        this.energy = 100;
        this.hunger = this.thirst = this.happiness = this.cleanness = 80;

        // Initialize negative effects
        resetNegativeEffects();
    }

    private void resetNegativeEffects() {
        isDead = isSick = isTired = isHungry = isThristy = isSad = isDirty = false;
    }

    // Update age
    public void updateAge() {
        double days = ChronoUnit.DAYS.between(birthDate, LocalDate.now());
        age = (days > 365.25) 
            ? String.format("%.2f Years", days / 365.25)
            : String.format("%.0f Weeks", days / 7);
    }

    // General setter for stats
    private void updateStat(String stat, double amount, double max) {
        switch (stat) {
            case "health" -> health = Math.min(health + amount, max);
            case "energy" -> energy = Math.min(energy + amount, max);
            case "hunger" -> hunger = Math.min(hunger + amount, max);
            case "thirst" -> thirst = Math.min(thirst + amount, max);
            case "happiness" -> happiness = Math.min(happiness + amount, max);
            case "cleanness" -> cleanness = Math.min(cleanness + amount, max);
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
        updateEffect("dead", health == 0);
        updateEffect("sick", health < 50);
        updateEffect("tired", energy < 60);
        updateEffect("hungry", hunger < 80);
        updateEffect("thirsty", thirst < 70);
        updateEffect("sad", happiness < 60);
        updateEffect("dirty", cleanness < 7);
    }

    // Status update method
    @Override
    public void updateStatus() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        Runnable task = () -> {
            updateAge();
            updateStat("hunger", -0.2, MAX_STATS);
            updateStat("thirst", -0.5, MAX_STATS);
            updateStat("happiness", -0.25, MAX_STATS);
            updateStat("cleanness", -0.1, MAX_STATS);
            updateNegativeEffects();
        };
        scheduler.scheduleAtFixedRate(task, 0, 1, TimeUnit.MINUTES);
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
    }
    @Override
    public void takeNap() { updateStat("energy", 20, MAX_STATS); }
    @Override
    public void clean() { updateStat("cleanness", 15, MAX_STATS); }
    @Override
    public void goToVet() { updateStat("health", 50, MAX_HEALTH); }

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

    public static double getDogMaxHealth() { return DOG_MAX_HEALTH; }
    public static double getCatMaxHealth() { return CAT_MAX_HEALTH; }
    public static double getHamsterMaxHealth() { return HAMSTER_MAX_HEALTH; }
}
