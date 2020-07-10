package com.cpedroza.NotificationService;

import java.util.ArrayList;
import java.util.List;

public class OrderListenerServiceImpl {
    private List<OrderListenerService> eventListeners;

    public OrderListenerServiceImpl() {
        this.eventListeners = new ArrayList<OrderListenerService>();
    }

    public void addMyEventListener(OrderListenerService orderListenerService) {
        this.eventListeners.add(orderListenerService);
    }

    public void notify(String val) {
        eventListeners.forEach((el) -> el.onMyEvent(val));
    }
}