/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cachecoherencesimulation;

/**
 * This class implements a cache line module
 * @author daniel
 */
public class CacheLine {

    private String state;//state of the the cache line
    private int data;//data in the cache line

    /**
     * Constructor of cache line
     * @param state //state value
     * @param data //data value 
     */
    public CacheLine(String state, int data) {

        this.state = state;//setting initial values
        this.data = data;

    }

    /**
     * Getter of the state of the line
     * @return the line's state
     */
    public String getState() {
        return state;
    }

    /**
     * setter of the line state
     * @param state the new state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * getter of the data of the cache line
     * @return the line data
     */
    public int getData() {
        return data;
    }

    /**
     * setter of the line data
     * @param data the new data
     */
    public void setData(int data) {
        this.data = data;
    }
    
    

}
