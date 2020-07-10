package com.cpedroza.Domains;

public class Item {
    private String name;
    private double cost;
    private int inventory;

    public Item(String name, double cost, int inventory){
        this.name = name;
        this.cost = cost;
        this.inventory = inventory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public void modifyInventory(int itemCount) { this.inventory = inventory - itemCount; }
}