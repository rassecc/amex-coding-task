package com.cpedroza.NotificationService;

import java.util.EventListener;

public interface OrderListenerService extends EventListener {
    void onMyEvent(String str);
}