package com.example.app;

public final class CurrentOrderSingleton {
    private static CurrentOrderSingleton currentOrderSingleton;
    private Order currentOrder = new Order();
    private CurrentOrderSingleton() {       }
    public static synchronized CurrentOrderSingleton getInstance() {
        if (currentOrderSingleton == null) {
            currentOrderSingleton = new CurrentOrderSingleton();
        }
        return currentOrderSingleton;
    }

    public Order getCurrentOrder() {
        return currentOrder;
    }
    public void resetCurrentOrder() {
        currentOrder = new Order(); // Reset currentOrder to a new Order object
    }

}