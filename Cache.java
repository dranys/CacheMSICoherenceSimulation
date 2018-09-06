/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cachecoherencesimulation;

/**
 * This class implements a physical cache, it has 16 data blocks 
 * @author daniel
 */
public class Cache {
    private String name;//defining name
    private CacheLine line0;//declares of each line of memory
    private CacheLine line1;
    private CacheLine line2;
    private CacheLine line3;
    private CacheLine line4;
    private CacheLine line5;
    private CacheLine line6;
    private CacheLine line7;
    private CacheLine line8;
    private CacheLine line9;
    private CacheLine line10;
    private CacheLine line11;
    private CacheLine line12;
    private CacheLine line13;
    private CacheLine line14;
    private CacheLine line15;
    
    /**
     * Cache Constructor
     * @param name of the new created 
     */
    public Cache(String name){
        this.name = name;//setting the name
        String state = "S";//initial state of all cache lines S: shared
        line0 = new CacheLine(state, 0);//initial value of all cache lines 0
        line1 = new CacheLine(state, 0);
        line2 = new CacheLine(state, 0);
        line3 = new CacheLine(state, 0);
        line4 = new CacheLine(state, 0);
        line5 = new CacheLine(state, 0);
        line6 = new CacheLine(state, 0);
        line7 = new CacheLine(state, 0);
        line8 = new CacheLine(state, 0);
        line9 = new CacheLine(state, 0);
        line10 = new CacheLine(state, 0);
        line11 = new CacheLine(state, 0);
        line12 = new CacheLine(state, 0);
        line13 = new CacheLine(state, 0);
        line14 = new CacheLine(state, 0);
        line15 = new CacheLine(state, 0);
        
    }
    /**
     * Set the data contained in at specific position of cache
     * @param direction Direction of cache
     * @param data Data to write in cache
     */
    public void setCacheData(int direction, int data){
        switch(direction){// choose where to put the data 
            case 0:
                line0.setData(data);//putting the data
                break;
            case 1:
                line1.setData(data);
                break;
            case 2:
                line2.setData(data);
                break;
            case 3:
                line3.setData(data);
                break;
            case 4:
                line4.setData(data);
                break;
            case 5:
                line5.setData(data);
                break;
            case 6:
                line6.setData(data);
                break;
            case 7:
                line7.setData(data);
                break;
            case 8:
                line8.setData(data);
                break;
            case 9:
                line9.setData(data);
                break;
            case 10:
                line10.setData(data);
                break;
            case 11:
                line11.setData(data);
                break;
            case 12:
                line12.setData(data);
                break;
            case 13:
                line13.setData(data);
                break;
            case 14:
                line14.setData(data);
                break;
            case 15:
                line15.setData(data);
                break;
        }
    }
    /**
     * Set the state (S shared, M modified, I invalid) of specified direction
     * @param direction Direction of line of cache
     * @param state The required state to write
     */
    public void setCacheState(int direction, String state){
        switch(direction){//choose the direction
            case 0:
                line0.setState(state);//set the state
                break;
            case 1:
                line1.setState(state);
                break;
            case 2:
                line2.setState(state);
                break;
            case 3:
                line3.setState(state);
                break;
            case 4:
                line4.setState(state);
                break;
            case 5:
                line5.setState(state);
                break;
            case 6:
                line6.setState(state);
                break;
            case 7:
                line7.setState(state);
                break;
            case 8:
                line8.setState(state);
                break;
            case 9:
                line9.setState(state);
                break;
            case 10:
                line10.setState(state);
                break;
            case 11:
                line11.setState(state);
                break;
            case 12:
                line12.setState(state);
                break;
            case 13:
                line13.setState(state);
                break;
            case 14:
                line14.setState(state);
                break;
            case 15:
                line15.setState(state);
                break;
        }
    }
    /**
     * Gets the cache state of specified direction
     * @param cacheDirection direction on cache
     * @return the state (S: shared, M: modified, I: invalid) default value ""
     */
    public String getCacheState(int cacheDirection){
        String cacheState="";//default value
        switch(cacheDirection){//choose the direction
            case 0:
                cacheState = line0.getState();//get the state
                break;
            case 1:
                cacheState = line1.getState();
                break;
            case 2:
                cacheState = line2.getState();
                break;
            case 3:
                cacheState = line3.getState();
                break;
            case 4:
                cacheState = line4.getState();
                break;
            case 5:
                cacheState = line5.getState();
                break;
            case 6:
                cacheState = line6.getState();
                break;
            case 7:
                cacheState = line7.getState();
                break;
            case 8:
                cacheState = line8.getState();
                break;
            case 9:
                cacheState = line9.getState();
                break;
            case 10:
                cacheState = line10.getState();
                break;
            case 11:
                cacheState = line11.getState();
                break;
            case 12:
                cacheState = line12.getState();
                break;
            case 13:
                cacheState = line13.getState();
                break;
            case 14:
                cacheState = line14.getState();
                break;
            case 15:
                cacheState = line15.getState();
                break;
        }
        return cacheState;
    }
    /**
     * Gets the data contained on a specified direction
     * @param cacheDirection direction on cache
     * @return the cache data
     */
    public int getCacheData(int cacheDirection){
        int cacheData = 0;
        switch(cacheDirection){//choose direction
            case 0:
                cacheData = line0.getData();//get direction
                break;
            case 1:
                cacheData = line1.getData();
                break;
            case 2:
                cacheData = line2.getData();
                break;
            case 3:
                cacheData = line3.getData();
                break;
            case 4:
                cacheData = line4.getData();
                break;
            case 5:
                cacheData = line5.getData();
                break;
            case 6:
                cacheData = line6.getData();
                break;
            case 7:
                cacheData = line7.getData();
                break;
            case 8:
                cacheData = line8.getData();
                break;
            case 9:
                cacheData = line9.getData();
                break;
            case 10:
                cacheData = line10.getData();
                break;
            case 11:
                cacheData = line11.getData();
                break;
            case 12:
                cacheData = line12.getData();
                break;
            case 13:
                cacheData = line13.getData();
                break;
            case 14:
                cacheData = line14.getData();
                break;
            case 15:
                cacheData = line15.getData();
                break;
        }
        return cacheData;
    }
}
