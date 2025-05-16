package com.example.app;

import java.util.ArrayList;
import java.util.List;

/**
 * The global order object so different files can interact with the order object and only one needs to be instantiated
 */
public final class AllOrdersSingleton {
    private static AllOrdersSingleton AllOrdersSingleton;
    private List<Order> orderList = new ArrayList<>();
    private AllOrdersSingleton() {       }

    /**
     * Gets the one instance of the order object
     * @return
     */
    public static synchronized AllOrdersSingleton getInstance() {
        if (AllOrdersSingleton == null) {
            AllOrdersSingleton = new AllOrdersSingleton();
        }
        return AllOrdersSingleton;
    }

    /**
     * Returns the order list
     * @return
     */
    public List<Order> getOrderList() {
        return orderList;
    }


}