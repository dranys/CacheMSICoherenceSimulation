/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cachecoherencesimulation;

/**
 * this class implements the single bus comunication between all processors unit
 * block the bus to others processors when a processor unit is using to avoid bus contention.
 * @author daniel 
 */
public class BusInterface{
    
    private int dataLine, directionLine;//variables of direction and data of a line
    private boolean busRd, busWr;//variables for bus write and bus read
    private boolean run;//variable to check when the bus is used
    private String name;//name of the bus instance
    MainMemory memory;//instance of main memory
    private int processorIdLine;// id of processor using the bus

    /**
     * Constructor of bus interface
     * @param name 
     */
    public BusInterface(String name) {
        
        this.dataLine = 0;//sets intial values as cero 
        this.directionLine = 0;
        this.busRd = false;
        this.busWr = false;
        this.run = true;
        this.name = name;
        this.processorIdLine = 0;
        memory = new MainMemory();//create an instance of memory
        
        

    }

    /**
     * Write from bus to memory when it is used.
     * @param processorId the processors Identifier
     * @param direction direction of the memory to write
     * @param data data to write
     */
    private void memoryWrite(int processorId, int direction, int data){
        memory.recordData(direction, data, processorId);
        this.busWr = true;
    }
    
    /**
     * Read on specific position of memory
     * @param direction
     * @return the read data
     */
    private int memoryRead(int direction){
        int data = memory.readData(direction);
        this.busRd = true;
        return data;
        
    }

    /**
     * This method create a petition of bus usage.
     * This is shared between the four processor units to execute bus write or bus read 
     * @param processorId id of processor that is requesting the bus
     * @param direction the direction of the read or write
     * @param data the data to write 0 if read
     * @param requestType choose between bus write (BW) or bus read (BR)
     * @return return the value read data, cero if writes
     */
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
