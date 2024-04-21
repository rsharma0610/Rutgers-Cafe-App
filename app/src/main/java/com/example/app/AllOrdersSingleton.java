package com.example.app;

import java.util.ArrayList;
import java.util.List;

public final class AllOrdersSingleton {
    private static AllOrdersSingleton AllOrdersSingleton;
    private List<Order> orderList = new ArrayList<>();
    private AllOrdersSingleton() {       }
    public static synchronized AllOrdersSingleton getInstance() {
        if (AllOrdersSingleton == null) {
            AllOrdersSingleton = new AllOrdersSingleton();
        }
        return AllOrdersSingleton;
    }

    public List<Order> getOrderList() {
        return orderList;
    }


}