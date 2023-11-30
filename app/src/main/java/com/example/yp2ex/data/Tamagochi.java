package com.example.yp2ex.data;

import java.io.Serializable;

public class Tamagochi implements Serializable {
    int id;
    int hunger;
    int tired;
    int bore;
    int happy;
    int dead;
    int days;

    public Tamagochi(int id, int hunger, int tired, int bore, int happy, int days, int dead) {
        this.id = id;
        this.hunger = hunger;
        this.tired = tired;
        this.bore = bore;
        this.happy = happy;
        this.dead = dead;
        this.days = days;
    }

    public Tamagochi(){

    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public int getDead() {
        return dead;
    }

    public void setDead(int dead) {
        this.dead = dead;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHunger() {
        return hunger;
    }

    public void setHunger(int hunger) {
        this.hunger = hunger;
    }

    public int getTired() {
        return tired;
    }

    public void setTired(int tired) {
        this.tired = tired;
    }

    public int getBore() {
        return bore;
    }

    public void setBore(int bore) {
        this.bore = bore;
    }

    public int getHappy() {
        return happy;
    }

    public void setHappy(int happy) {
        this.happy = happy;
    }
}
