package com.cpedroza;

import com.cpedroza.Domains.Item;
import com.cpedroza.Domains.OrderSubmittedEvent;
import com.cpedroza.KafkaProducer.KafkaProducerImpl;
import com.cpedroza.NotificationService.OrderListenerServiceImpl;
import com.cpedroza.OrderService.OrderService;
import org.junit.jupiter.api.Order;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

//    Step 1: Build an Orders Service
//        • Build a service that’s able to receive simple orders of shopping goods from the command line
//        • Apples cost 60 cents and oranges cost 25 cents
//        • The service should be able to calculate that:
//                    • [ Apple, Apple, Orange, Apple ] => $2.05
//                    • Make reasonable assumptions about the inputs to your solution; for example,
//            candidates may take a list of strings as input
//        • Add unit tests that validate your code

//    Step 2: Simple offer
//        • The shop decides to introduce two new offers
//        • buy one get one free on Apples
//        • 3 for the price of 2 on Oranges
//        • Update your functions & unit tests accordingly

//    Step 3: Build a Customer Notification Service
//        • Customers complained that they don’t know if their orders made it through or not as there is no notification of success
//        • Build a service that listens for when orders are complete and sends a notification to the customer regarding its status and estimated delivery time
//        • The Mail service subscribes to events from the Orders service and publishes an appropriate event that the customer (you) is able to read from the terminal

    public static void main(String[] args) {
        OrderService os = new OrderService();

        os.addToInventory("Apple", new Item("Apple", 0.6, 5));
        os.addToInventory("Orange", new Item("Orange", 0.25, 6));

        KafkaProducerImpl kf = new KafkaProducerImpl();
        OrderListenerServiceImpl ols = new OrderListenerServiceImpl();

        //will notify customer of order received
        //will now send same message to Kafka Topic
        ols.addMyEventListener((str) -> {
            OrderSubmittedEvent event = new OrderSubmittedEvent(str);
            kf.sendRecord(event);

            System.out.println(str);
        });



        Scanner in = new Scanner(System.in);
        System.out.printf("Enter items you want to buy or enter q to quit: ");
        String input = in.nextLine();
        ArrayList<String> userItems = new ArrayList<>();

        while (!input.equals("q")){
            if (input.contains(",")){
                //list seperated by ,
                userItems = new ArrayList<>(Arrays.asList(input.split("\\s*,\\s*")));
            }
            else {
                //single item
                userItems.add(input);
            }

            double costOfItems = os.calculateUserTotal(userItems);

            if (costOfItems != 0.00){
                System.out.printf("Total cost of your cart came out to be : $%.2f", costOfItems);
                ols.notify(String.format("\nOrder received. Order will be complete at: %s",
                        LocalTime.now().plusMinutes(userItems.size()).toString()));
            }
            System.out.printf("\nEnter items you want to buy or enter q to quit: ");

            input = in.nextLine();
            userItems.clear();
        }

        System.out.println("thanks for shopping!");
    }
}