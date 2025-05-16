package com.example.app;

/**
 * Creates one global instance of the order object to be shared by the different files
 */
public final class CurrentOrderSingleton {
    private static CurrentOrderSingleton currentOrderSingleton;
    private Order currentOrder = new Order();
    private CurrentOrderSingleton() {       }

    /**
     * Allows the different files to retrieve the global order object
     * @return
     */
    public static synchronized CurrentOrderSingleton getInstance() {
        if (currentOrderSingleton == null) {
            currentOrderSingleton = new CurrentOrderSingleton();
        }
        return currentOrderSingleton;
    }

    /**
     * Gets the current order object
     * @return
     */
    public Order getCurrentOrder() {
        return currentOrder;
    }

    /**
     * Resets the singleton to a new order object
     */
    public void resetCurrentOrder() {
        currentOrder = new Order(); // Reset currentOrder to a new Order object
    }

}