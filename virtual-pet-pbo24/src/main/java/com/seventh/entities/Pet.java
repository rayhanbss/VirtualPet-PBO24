package com.seventh.entities;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


public abstract class Pet {
    private final String name;
    private final LocalDate birthDate;
    private String age;
    private double energy, hunger, thirst, happiness, cleanness;
    private final double MAX_STATS;
    protected boolean isTired, isHungry, isThristy, isSad, isDirty;

    public Pet(String name){
        this.name = name;
        this.birthDate = LocalDate.now();
        MAX_STATS = 100;
        this.isTired = this.isHungry = this.isThristy = 
        this.isSad = this.isDirty = false;
    }
    
    // updateAge
    public void updateAge() {
        LocalDate currentDate = LocalDate.now();
        double calAgeInDays = ChronoUnit.DAYS.between(birthDate, currentDate) / 7.0;
        double ageInYears = calAgeInDays / 365.25;
        double ageInWeeks = calAgeInDays / 7.0;
    
        if (ageInWeeks > 52) {
            age = String.format("%.0f Weeks", ageInWeeks);
        } else {
            age = String.format("%.2f Years", ageInYears);
        }
    }
    
    // Setter for Status
    private double adjustStat(double current, double amount) {
        return Math.min(current + amount, MAX_STATS);
    }
    public void setEnergy(double amount){
        energy = adjustStat(energy, amount);
    }
    public void setHunger(double amount){
        hunger = adjustStat(hunger, amount);
    }
    public void setThirst(double amount){
        thirst = adjustStat(thirst, amount);
    }
    public void setHappiness(double amount){
        happiness = adjustStat(happiness, amount);
    }
    public void setCleanness(double amount){
        cleanness = adjustStat(cleanness, amount);
    }
    
    // Setter for mood
    public void setTired(double bounds){
        isTired = (energy < bounds);
    }
    public void setHungry(double bounds){
        isHungry = (hunger < bounds);
    }
    public void setThrirsty(double bounds){
        isThristy = (thirst < bounds);
    }
    public void setSad(double bounds){
        isSad = (happiness < bounds);
    }
    public void setDirty(double bounds){
        isDirty = (cleanness < bounds);
    }
    
    // Getter
    public String getName(){return name;}
    public String getAge(){return age;}
    public LocalDate getBirthDate(){return birthDate;}
    public double getMAX_STATS(){return MAX_STATS;}
    public double getHunger(){return hunger;}
    public double getThirst(){return thirst;}
    public double getHappiness(){return happiness;}
    public double getLeanness(){return cleanness;}
    public boolean getHungry(){return isHungry;}
}
