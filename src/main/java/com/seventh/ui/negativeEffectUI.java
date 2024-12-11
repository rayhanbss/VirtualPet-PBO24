package com.seventh.ui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;

public class negativeEffectUI extends JLabel{
    public negativeEffectUI(int type, Font font){
        this.setLayout(new FlowLayout());
        this.setForeground(new Color(0,122,255));
        this.setFont(font.deriveFont(32f));
        switch (type) {
            case 0 -> { this.setText("\uf89a"); this.setToolTipText("Pet is already Dead\nR.I.P");}
            case 1 -> { this.setText("\uf220"); this.setToolTipText("Pet is Sick\nGo to Vet");}
            case 2 -> { this.setText("\uef44"); this.setToolTipText("Pet is Tired\nTake a Nap");}
            case 3 -> { this.setText("\ue110"); this.setToolTipText("Pet is Hungry\nGive Food");}
            case 4 -> { this.setText("\uf164"); this.setToolTipText("Pet is Thirst\nGive Drink");}
            case 5 -> { this.setText("\ue811"); this.setToolTipText("Pet is Sad\nPlay Together" );}
            case 6 -> { this.setText("\uf6a2"); this.setToolTipText("Pet is Dirty\nClean it");}
            default -> throw new AssertionError();
        }
    }
}
