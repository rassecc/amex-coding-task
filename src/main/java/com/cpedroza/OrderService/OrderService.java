package com.cpedroza.OrderService;

import com.cpedroza.Domains.Item;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class    OrderService {
    private Map<String, Item> inv = new HashMap<>();

    public OrderService(){}

    /**
     * Returns the total cost calculated from the user inputted items
     * Calls findOffers to before going through userItems
     * Does not check for lower case matches
     * Reverts any modifications to inventory if purchase fails!
     *
     * @return      totalCost
     */
    public double calculateUserTotal(ArrayList<String> userItems){
        double totalCost = 0.0;

        ArrayList<Integer> applesOrangesRemoved =  findOffers(userItems);

        for (String item : userItems){
            if (inv.containsKey(item)){
                if (inv.get(item).getInventory() == 0){
                    System.out.printf("\nYou ordered too many %ss. There is only %d in stock. Please reorder!", item, inv.get(item).getInventory());

                    //restoring inv from failed purchase
                    inv.get("Orange").modifyInventory(applesOrangesRemoved.get(1) * -1);
                    inv.get("Apple").modifyInventory(applesOrangesRemoved.get(0) * -1);

                    return 0.0;
                }
                totalCost += inv.get(item).getCost();
                inv.get(item).modifyInventory(1);

                if (item.equals("Orange")){  applesOrangesRemoved.set(1, applesOrangesRemoved.get(1) + 1); }
                else { applesOrangesRemoved.set(0, applesOrangesRemoved.get(0) + 1); }
            } else {
                System.out.printf("\nItem %s is not in stock" , item);
            }
        }

        return Math.round(totalCost * 100.0) / 100.0;
    }


    public ArrayList<Integer> findOffers(ArrayList<String> userItems){
        int countApples = Collections.frequency(userItems, "Apple");
        int countOranges = Collections.frequency(userItems, "Orange");
        int orangesRemovedFromInv = 0;
        int applesRemovedFromInv = 0;
        ArrayList<Integer> applesOrangesRemoved = new ArrayList<>();


        while (countApples >= 2){
            //apply the bogo by removing an apple entry from userItems
            userItems.remove("Apple");

            //"approving" the deal should deplete inventory of item
            inv.get("Apple").modifyInventory(1);
            System.out.println("Apple BOGO offer found!");

            countApples -= 2;
            applesRemovedFromInv++;
        }

        while (countOranges >= 3){
            userItems.remove("Orange");
            inv.get("Apple").modifyInventory(1);

            System.out.println("Orange 3 for the price of 2 offer found!");

            countOranges -= 3;
            orangesRemovedFromInv++;
        }

        applesOrangesRemoved.add(applesRemovedFromInv);
        applesOrangesRemoved.add(orangesRemovedFromInv);
        return applesOrangesRemoved;

    }

    public void addToInventory(String itemName, Item invItem) { inv.put(itemName, invItem); }

    public ArrayList<String> getListOfInventoryItems(){
        return new ArrayList<>(inv.keySet());
    }
}