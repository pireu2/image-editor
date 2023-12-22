package org.example.imageeditor.util;

import java.util.HashMap;
import java.util.Map;


/**
 * Singleton class that acts as a mediator for controllers.
 * It provides a way to store and retrieve data between different controllers.
 */
public class ControllerMediator {
    private static ControllerMediator instance;
    private Map<String, Object> dataMap;

    /**
     * Private constructor to prevent instantiation.
     * Initializes the data map.
     */
    private ControllerMediator() {
        dataMap = new HashMap<>();
    }

    /**
     * Returns the singleton instance of ControllerMediator.
     * If the instance does not exist, it is created.
     *
     * @return the singleton instance of ControllerMediator
     */
    public static ControllerMediator getInstance() {
        if (instance == null) {
            instance = new ControllerMediator();
        }
        return instance;
    }

    /**
     * Stores a data object with the specified key.
     *
     * @param key the key under which to store the data
     * @param value the data object to store
     */
    public void put(String key, Object value){
        dataMap.put(key, value);
    }

    /**
     * Retrieves a data object with the specified key.
     * The returned object is cast to the specified type.
     *
     * @param key the key of the data object to retrieve
     * @param type the Class object of the type to cast the returned object to
     * @return the data object cast to the specified type, or null if no object exists for the key
     */
    public <T> T get(String key, Class<T> type){
        return type.cast(dataMap.get(key));
    }
}
