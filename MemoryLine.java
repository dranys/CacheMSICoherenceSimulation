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
public class MemoryLine {
   
    private int idProcessor;
    private int data;
    
    public MemoryLine(int idProcessor, int data){
        
        this.idProcessor = idProcessor;
        this.data = data;
    
    }

    public int getIdProcessor() {
        return idProcessor;
    }

    public void setIdProcessor(int idProcessor) {
        this.idProcessor = idProcessor;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }
    
    
    
    
    
}
