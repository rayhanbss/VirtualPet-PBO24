package com.seventh.domain;

public interface Action {
    public void action(int type);
    public void updateStatus();
    public void goToVet();          // incrase health
    public void giveFood();         // incrase hunger
    public void giveDrink();        // incrase thrirst
    public void playWith();         // incrase happiness
    public void takeNap();          // incrase energy
    public void clean();            // incrase clean
}
