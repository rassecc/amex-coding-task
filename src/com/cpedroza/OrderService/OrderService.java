package com.cpedroza.OrderService;

import java.util.*;

public class OrderService {
    private Map<String, Double> inv = new HashMap<>();

    public OrderService(){}

    /**
     * Returns the total cost calculated from the user inputted items
     * Calls findOffers to before going through userItems
     * Does not check for lower case matches
     *
     * @return      totalCost
     */
    public double calculateUserTotal(ArrayList<String> userItems){
        double totalCost = 0.0;

        findOffers(userItems);

        for (String item : userItems){
            if (inv.containsKey(item)){
                totalCost += inv.get(item);
            } else {
                System.out.printf("\nItem %s was not found" , item);
            }
        }

        return Math.round(totalCost * 100.0) / 100.0;
    }


    public void findOffers(ArrayList<String> userItems){
        int countApples = Collections.frequency(userItems, "Apple");
        int countOranges = Collections.frequency(userItems, "Orange");

        while (countApples >= 2){
            //apply the bogo by removing an apple entry from userItems
            userItems.remove("Apple");
            System.out.println("Apple BOGO offer found!");

            countApples -= 2;
        }

        while (countOranges >= 3){
            //apply the bogo by removing an apple entry from userItems
            userItems.remove("Orange");
            System.out.println("Orange 3 for the price of 2 offer found!");

            countOranges -= 3;
        }
    }


    public void addToInventory(String itemName, double itemPrice) {
        inv.put(itemName, itemPrice);
    }

    public ArrayList<String> getListOfInventoryItems(){
        return new ArrayList<>(inv.keySet());
    }
}