package com.cpedroza;

import java.util.*;

public class Main {

//    Step 1: Build an Orders Service
//        • Build a service that’s able to receive simple orders of shopping goods from the command line
//        • Apples cost 60 cents and oranges cost 25 cents
//        • The service should be able to calculate that:
//                    • [ Apple, Apple, Orange, Apple ] => $2.05
//                    • Make reasonable assumptions about the inputs to your solution; for example,
//            candidates may take a list of strings as input
//        • Add unit tests that validate your code

    public static void main(String[] args) {
        OrderService os = new OrderService();

        os.addToInventory("Apple", 0.6);
        os.addToInventory("Orange", 0.25);

        os.getUserOrder();

        System.out.printf("\nTotal cost of your cart came out to be : $%.2f", os.calculateUserTotal());
    }
}
