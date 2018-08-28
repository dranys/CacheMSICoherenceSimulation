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
public class CacheController{

    Cache cache;
    private int missCounter;
    private final String name;

    public CacheController(String name) {
        this.name = name;
        this.missCounter = 0;
        this.cache = new Cache("cacheUnit");
    }

    private int control(int direction, String state, String action, int data) {
        int response = 0;//0 for nothing, 1 for bus write(BW) and 2 for bus read(BR)
        switch (state) {
            case "M":
                if (action.equals("PW")) {
                    cache.setCacheData(direction, data);
                    response = 1;
                } else if (action.equals("PR")) {//no hace nada

                } else if (action.equals("BW")) {
                    cache.setCacheState(direction, "I");
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
                    cache.setCacheData(direction, data);
                    response = 1;
                    
                }
                break;
            case "I":
                if (action.equals("PW")) {
                    cache.setCacheState(direction, "M");
                    cache.setCacheData(direction, data);
                    response = 1;
                } else if (action.equals("PR")) {
                    cache.setCacheState(direction, "S");
                    missCounter++;
                    response = 2;
                } else if (action.equals("BR") || action.equals("BW")) {
                }
                break;
            case "null":
                //System.out.println("controlador osioso");
                break;
        }
        return response;

    }

    public int connectCacheCpu(String operation, int direction, int data) {
        //System.out.println("direction: "+direction+" state: "+cache.getCacheState(direction)+" operation: "+operation+" data: "+data);
        int response = control(direction, cache.getCacheState(direction), operation, data);
        return response;
    }

    public void busRead(boolean BW, boolean BR,int direction) {
        String operation="null";
        if(BW) operation = "BW";
        else if(BR) operation = "BR";
        control(direction, cache.getCacheState(direction), operation, 0);
        
        
    }
    
    public void recordOnBR(int direction, int data){
        cache.setCacheData(direction, data);
    }
    
    public int getMissCounter(){
        return missCounter;
    }
}
