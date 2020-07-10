package com.cpedroza;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyTests {

    @Test
    public void calculateUserTotal_TwoUserItems_Success() {
        OrderService os = new OrderService();

        os.addToInventory("Apple", 0.5);
        os.addToInventory("Orange", 0.25);

        os.addToUserItems("Apple");
        os.addToUserItems("Apple");

        assertEquals(1.0, os.calculateUserTotal(), "0.5 * 2 = 1.0");
    }

    @Test
    public void calculateUserTotal_ItemNameInLowerCase_Fail() {
        OrderService os = new OrderService();

        os.addToInventory("Apple", 0.5);

        os.addToUserItems("apple");

        assertEquals(0.0, os.calculateUserTotal(), "Apple != apple, calculated no total");
    }

    @Test
    public void addToInventory_AddTwo_Success() {
        OrderService os = new OrderService(); // MyClass is tested

        os.addToInventory("Apple", 0.5);
        os.addToInventory("Orange", 0.25);

        assertEquals(2, os.getListOfInventoryItems().size());
    }
}