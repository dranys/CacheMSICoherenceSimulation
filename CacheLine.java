/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cachecoherencesimulation;

/**
 *
 * @author daniel
 */
public class CacheLine {

    private String state;
    private int data;

    public CacheLine(String state, int data) {

        this.state = state;
        this.data = data;

    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        System.out.println("Recording data: "+data);
        this.data = data;
    }
    
    

}
