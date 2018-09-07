/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cachecoherencesimulation;

/**
 * Implements a memory line module
 * @author daniel
 */
public class MemoryLine {
   
    private int idProcessor;//it has a processor's id
    private int data;//writed data
    
    /**
     * Constructor of the memory line
     * @param idProcessor set new processor's id
     * @param data set new data into memory line
     */
    public MemoryLine(int idProcessor, int data){
        
        this.idProcessor = idProcessor;
        this.data = data;
    
    }

    /**
     * Processor's id getter of a memory line
     * @return processor's id
     */
    public int getIdProcessor() {
        return idProcessor;
    }

    /**
     * setter of processor's id
     * @param idProcessor 
     */
    public void setIdProcessor(int idProcessor) {
        this.idProcessor = idProcessor;
    }

    /**
     * getter of data into memory line
     * @return the data
     */
    public int getData() {
        return data;
    }

    /**
     * setter of memory line data
     * @param data 
     */
    public void setData(int data) {
        this.data = data;
    }
    
    
    
    
    
}
