package com.cpedroza.Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.cpedroza.Domains.Item;
import com.cpedroza.OrderService.OrderService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class MyTests {

    @Test
    public void calculateUserTotal_TwoUserItems_Success() {
        OrderService os = new OrderService();
        ArrayList<String> userItems = new ArrayList<>();

        os.addToInventory("Apple", new Item("Apple", 0.6, 5));
        os.addToInventory("Orange", new Item("Orange", 0.25,  6));

        userItems.add("Apple");
        userItems.add("Apple");

        assertEquals(0.6, os.calculateUserTotal(userItems), "0.5 * 2 = 1.0");
    }

    @Test
    public void calculateUserTotal_ItemNameInLowerCase_Fail() {
        OrderService os = new OrderService();
        ArrayList<String> userItems = new ArrayList<>();

        os.addToInventory("Apple", new Item("Apple", 0.6, 5));

        userItems.add("apple");

        assertEquals(0.0, os.calculateUserTotal(userItems), "Apple != apple, calculated no total");
    }

    @Test
    public void calculateUserTotal_AppleOfferFound_Success() {
        OrderService os = new OrderService();
        ArrayList<String> userItems = new ArrayList<>();

        os.addToInventory("Apple", new Item("Apple", 0.6, 5));

        userItems.add("Apple");
        userItems.add("Apple");
        userItems.add("Apple");
        userItems.add("Apple");
        userItems.add("Apple");

        assertEquals(1.8, os.calculateUserTotal(userItems), "2 BOGOs, 1.0 + 0.5");
    }

    @Test
    public void calculateUserTotal_MultipleOffersFound_Success() {
        OrderService os = new OrderService();
        ArrayList<String> userItems = new ArrayList<>();

        os.addToInventory("Apple", new Item("Apple", 0.6, 5));
        os.addToInventory("Orange", new Item("Orange", 0.25, 6));

        userItems.add("Apple");
        userItems.add("Apple");
        userItems.add("Orange");
        userItems.add("Orange");
        userItems.add("Orange");

        assertEquals(1.1, os.calculateUserTotal(userItems), "Apples : 0.6 + Oranges : 0.5");
    }

    @Test
    public void addToInventory_AddTwo_Success() {
        OrderService os = new OrderService(); // MyClass is tested

        os.addToInventory("Apple", new Item("Apple", 0.6, 5));
        os.addToInventory("Orange", new Item("Orange", 0.25, 6));

        assertEquals(2, os.getListOfInventoryItems().size());
    }
}