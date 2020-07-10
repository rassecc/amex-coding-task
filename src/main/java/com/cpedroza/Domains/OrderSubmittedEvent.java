package com.cpedroza.Domains;

public class OrderSubmittedEvent {
    private String message;

    public OrderSubmittedEvent(String message){
        this.message = message;
    }

    @Override public String toString() {
        return "OrderSubmittedEvent(" + message + ")\n";
    }
}
