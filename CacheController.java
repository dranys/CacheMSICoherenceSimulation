/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cachecoherencesimulation;

/**
 * This class implements a controller module, to manage the coherence between all 
 * processor unit. The coherence is managed as state machine that change of state 
 * on each event related to writing or reading from the own processor cache and other
 * processor cache. Also have a counter to store misses event.
 * To do all of this uses a connection method with CPU and bus read operation
 * @author daniel
 */
public class CacheController{

    Cache cache;//instance of cache
    private int missCounter;//miss counter
    private final String name;//the name of cache controller unit

    /**
     * Constructor of cache controller
     * @param name 
     */
    public CacheController(String name) {
        this.name = name;//set the name
        this.missCounter = 0;//miss counter initiated as cero
        this.cache = new Cache("cacheUnit");//instance of cache unit
    }

    /**
     * Set the write and read from cache implementing a finit state machine
     * that also set state of each cache line, this is very important to processor unit to maintain 
     * the coherence between all caches in the system
     * @param direction direction of the cache line 
     * @param state current state of the cache line
     * @param action action related (Bus write or Bus read)
     * @param data the data to write (if it is the case)
     * @return response in case the controller need to generate a bus write/read
     * to other controller units
     */
    private int control(int direction, String state, String action, int data) {
        int response = 0;//0 for nothing, 1 for bus write(BW) and 2 for bus read(BR)
        switch (state) {//M-S-I
            case "M":
                if (action.equals("PW")) {
                    cache.setCacheData(direction, data);
                    response = 1;
                } else if (action.equals("PR")) {//no hace nada

                } else if (action.equals("BW")) {//compares the action
                    cache.setCacheState(direction, "I");//set a new state
                } else if (action.equals("BR")) {
                    cache.setCacheState(direction, "S");
                }
                break;

            case "S":
                if ((action.equals("BR")) || (action.equals("PR"))) {
                } else if (action.equals("BW")) {
                    cache.setCacheState(direction, "I");
                } else if (action.equals("PW")) {
                    cache.setCacheState(direction, "M");
                    cache.setCacheData(direction, data);//set the data in case of write to cache
                    response = 1;
                    
                }
                break;
            case "I":
                if (action.equals("PW")) {
                    cache.setCacheState(direction, "M");
                    cache.setCacheData(direction, data);
                    response = 1;//generates a bus write
                } else if (action.equals("PR")) {
                    cache.setCacheState(direction, "S");
                    missCounter++;
                    response = 2;//generate bus read
                } else if (action.equals("BR") || action.equals("BW")) {
                }
                break;
            case "null":
                //unused controller
                break;
        }
        return response;

    }

    /**
     * This method creates a connection to CPU unit to write or read on cache
     * @param operation the operation to execute BW or BR
     * @param direction the direction of cache
     * @param data the data to write (if it is BW)
     * @return return the response (0 nothing, 1 bus write, 2 bus read)
     */
    public int connectCacheCpu(String operation, int direction, int data) {
        int response = control(direction, cache.getCacheState(direction), operation, data);
        return response;
    }

    /**
     * Creates a connection to bus interface to read the bus read and write line
     * @param BW the value of the bus write
     * @param BR the value of the bus read
     * @param direction the direction to read request
     */
    public void busRead(boolean BW, boolean BR,int direction) {
        if(BW || BR){
        String operation="null";
        if(BW) operation = "BW";
        else if(BR) operation = "BR";
        control(direction, cache.getCacheState(direction), operation, 0);}
        
        
    }
    /**
     * Auxiliar method to record a data into cache (from reading of memory) in case of miss event
     * @param direction direction to write the data
     * @param data the value read
     */
    public void recordOnBR(int direction, int data){
        cache.setCacheData(direction, data);
    }
    /**
     * Get of the current miss counter of the processor unit
     * @return 
     */
    public int getMissCounter(){
        return missCounter;
    }
}
