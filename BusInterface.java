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
public class BusInterface{
    
    private int dataLine, directionLine;
    private boolean busRd, busWr;
    private boolean run;
    private String name;
    private MainMemory memory;
    private int processorIdLine;

    public BusInterface(String name) {
        
        this.dataLine = 0;
        this.directionLine = 0;
        this.busRd = false;
        this.busWr = false;
        this.run = true;
        this.name = name;
        this.processorIdLine = 0;
        memory = new MainMemory();
        
        

    }

    public boolean getBusRd() {
        return busRd;
    }

    public boolean getBusWr() {
        return busWr;
    }

    public void setBusWr(boolean busWr) {
        this.busWr = busWr;
    }
    
    private void memoryWrite(int processorId, int direction, int data){
        memory.recordData(direction, data, processorId);
        this.busWr = true;
    }
    
    private int memoryRead(int direction){
        int data = memory.readData(direction);
        this.busWr = true;
        return data;
        
    }
    public void busClean(){
        this.busRd = false;
        this.busWr = false;
    }

    
    public int busPetition(int processorId, int direction, int data, String requestType){
        int result = 0;
        this.processorIdLine = processorId;
        this.directionLine = direction;
        this.dataLine = data;
        if(requestType.equals("BW")){
            memoryWrite(processorId, direction, data);
        }
        else{
            result = memoryRead(direction);
        }
        return result;
        
        
    }
    
    
    
}
