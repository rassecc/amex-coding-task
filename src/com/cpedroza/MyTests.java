package com.cpedroza;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class MyTests {

    @Test
    public void calculateUserTotal_TwoUserItems_Success() {
        OrderService os = new OrderService();

        os.addToInventory("Apple", 0.6);
        os.addToInventory("Orange", 0.25);

        os.addToUserItems("Apple");
        os.addToUserItems("Apple");

        assertEquals(0.6, os.calculateUserTotal(), "0.5 * 2 = 1.0");
    }

    @Test
    public void calculateUserTotal_ItemNameInLowerCase_Fail() {
        OrderService os = new OrderService();

        os.addToInventory("Apple", 0.6);

        os.addToUserItems("apple");

        assertEquals(0.0, os.calculateUserTotal(), "Apple != apple, calculated no total");
    }

    @Test
    public void calculateUserTotal_AppleOfferFound_Success() {
        OrderService os = new OrderService();

        os.addToInventory("Apple", 0.6);

        os.addToUserItems("Apple");
        os.addToUserItems("Apple");
        os.addToUserItems("Apple");
        os.addToUserItems("Apple");
        os.addToUserItems("Apple");

        assertEquals(1.8, os.calculateUserTotal(), "2 BOGOs, 1.0 + 0.5");
    }

    @Test
    public void calculateUserTotal_MultipleOffersFound_Success() {
        OrderService os = new OrderService();

        os.addToInventory("Apple", 0.6);
        os.addToInventory("Orange", 0.25);


        os.addToUserItems("Apple");
        os.addToUserItems("Apple");
        os.addToUserItems("Orange");
        os.addToUserItems("Orange");
        os.addToUserItems("Orange");

        assertEquals(1.1, os.calculateUserTotal(), "Apples : 0.6 + Oranges : 0.5");
    }

    @Test
    public void addToInventory_AddTwo_Success() {
        OrderService os = new OrderService(); // MyClass is tested

        os.addToInventory("Apple", 0.6);
        os.addToInventory("Orange", 0.25);

        assertEquals(2, os.getListOfInventoryItems().size());
    }
}