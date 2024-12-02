package com.seventh.entities;
import java.time.LocalDate;


public abstract class Pet {
    private final String name;
    private final LocalDate brithdate;
    private String status;
    private int hunger, thrist, happiness, cleanness;
    private final int MAX_STATS;

    public Pet(String name){
        this.name = name;
        this.brithdate = LocalDate.now();
        MAX_STATS = 100;
    }

    // Setter
    public void setHunger(){
        if(hunger + 5 > MAX_STATS){
            hunger = MAX_STATS;
        }else{
            hunger += 5;
        }
    }
    public void setThrist(){
        if(thrist + 5 > MAX_STATS){
            thrist = MAX_STATS;
        }else{
            thrist += 5;
        }
    }
    public void setHappiness(){
        if(happiness + 5 > MAX_STATS){
            happiness = MAX_STATS;
        }else{
            happiness += 5;
        }
    }
    public void setCleanness(){
        if(cleanness + 5 > MAX_STATS){
            cleanness = MAX_STATS;
        }else{
            cleanness += 5;
        }
    }
    
    // Getter
    public String getName(){return name;}
    public LocalDate getBirthDate(){return brithdate;}
    public String getStatus(){return status;}
    public int getMAX_STATS(){return MAX_STATS;}
    public int getHunger(){return hunger;}
    public int getThrist(){return thrist;}
    public int getHappiness(){return happiness;}
    public int getLeanness(){return cleanness;}
}
