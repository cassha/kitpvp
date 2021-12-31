package dev.notcacha.kitpvp.api.service;

public interface Service {

    /**
     * Start the service.
     */

    void start();

    /**
     * Stop the service.
     */

    default void stop() {

    }
}
