package com.cpedroza;

import java.util.*;

public class OrderService {
    private Map<String, Double> inv = new HashMap<>();
    private ArrayList<String> userItems = new ArrayList<>();

    public OrderService(){}

    public double calculateUserTotal(){
        double totalCost = 0.0;

        for (String item : userItems){
            if (inv.containsKey(item)){
                totalCost += inv.get(item);
            } else {
                System.out.printf("\nItem %s was not found" , item);
            }
        }

        return totalCost;
    }

    public void getUserOrder(){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter items you want to buy or enter q to quit:  ");
        String input = in.nextLine();

        if (input.contains(",")){
            //list seperated by ,
            userItems = new ArrayList<>(Arrays.asList(input.split("\\s*,\\s*")));
        }
        else {
            //single item
            userItems.add(input);
        }
    }

    public void addToInventory(String itemName, double itemPrice) {
        inv.put(itemName, itemPrice);
    }

    public void addToUserItems(String itemName) {
        userItems.add(itemName);
    }

    public ArrayList<String> getListOfInventoryItems(){
        return new ArrayList<>(inv.keySet());
    }
}
