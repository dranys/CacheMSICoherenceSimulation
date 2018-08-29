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
public class MainMemory {
    
    public MemoryLine line0;
    public MemoryLine line1;
    public MemoryLine line2;
    public MemoryLine line3;
    public MemoryLine line4;
    public MemoryLine line5;
    public MemoryLine line6;
    public MemoryLine line7;
    public MemoryLine line8;
    public MemoryLine line9;
    public MemoryLine line10;
    public MemoryLine line11;
    public MemoryLine line12;
    public MemoryLine line13;
    public MemoryLine line14;
    public MemoryLine line15;
    
    MainMemory(){
    this.line0 = new MemoryLine(0, 0);
    this.line1 = new MemoryLine(0, 0);
    this.line2 = new MemoryLine(0, 0);
    this.line3 = new MemoryLine(0, 0);
    this.line4 = new MemoryLine(0, 0);
    this.line5 = new MemoryLine(0, 0);
    this.line6 = new MemoryLine(0, 0);
    this.line7 = new MemoryLine(0, 0);
    this.line8 = new MemoryLine(0, 0);
    this.line9 = new MemoryLine(0, 0);
    this.line10 = new MemoryLine(0, 0);
    this.line11 = new MemoryLine(0, 0);
    this.line12 = new MemoryLine(0, 0);
    this.line13 = new MemoryLine(0, 0);
    this.line14 = new MemoryLine(0, 0);
    this.line15 = new MemoryLine(0, 0);
    
    }
    
    public void recordData(int direction, int data, int id_processor){
        //System.out.println("guardando en memoria dato: "+data+"direccion: "+direction+" ID: "+id_processor);
        switch(direction){
            case 0:
                line0.setData(data);
                line0.setIdProcessor(id_processor);
                break;
            case 1:
                line1.setData(data);
                line1.setIdProcessor(id_processor);
                break;
            case 2:
                line2.setData(data);
                line2.setIdProcessor(id_processor);
                break;
            case 3:
                line3.setData(data);
                line3.setIdProcessor(id_processor);
                break;
            case 4:
                line4.setData(data);
                line4.setIdProcessor(id_processor);
                break;
            case 5:
                line5.setData(data);
                line5.setIdProcessor(id_processor);
                break;
            case 6:
                line6.setData(data);
                line6.setIdProcessor(id_processor);
                break;
            case 7:
                line7.setData(data);
                line7.setIdProcessor(id_processor);
                break;
            case 8:
                line8.setData(data);
                line8.setIdProcessor(id_processor);
                break;
            case 9:
                line9.setData(data);
                line9.setIdProcessor(id_processor);
                break;
            case 10:
                line10.setData(data);
                line10.setIdProcessor(id_processor);
                break;
            case 11:
                line11.setData(data);
                line11.setIdProcessor(id_processor);
                break;
            case 12:
                line12.setData(data);
                line12.setIdProcessor(id_processor);
                break;
            case 13:
                line13.setData(data);
                line13.setIdProcessor(id_processor);
                break;
            case 14:
                line14.setData(data);
                line14.setIdProcessor(id_processor);
                break;
            case 15:
                line15.setData(data);
                line15.setIdProcessor(id_processor);
                break;
        }
        
    }
    public int readData(int direction){
        //System.out.println("leyendo en memoria direccion: "+direction);
        int data = 0;
        switch(direction){
            case 0:
                data = line0.getData();
                break;
            case 1:
                data = line1.getData();
                break;
            case 2:
                data = line2.getData();
                break;
            case 3:
                data = line3.getData();
                break;
            case 4:
                data = line4.getData();
                break;
            case 5:
                data = line5.getData();
                break;
            case 6:
                data = line6.getData();
                break;
            case 7:
                data = line7.getData();
                break;
            case 8:
                data = line8.getData();
                break;
            case 9:
                data = line9.getData();
                break;
            case 10:
                data = line10.getData();
                break;
            case 11:
                data = line11.getData();
                break;
            case 12:
                data = line12.getData();
                break;
            case 13:
                data = line13.getData();
                break;
            case 14:
                data = line14.getData();
                break;
            case 15:
                data = line15.getData();
                break;
        }
        return data;
        
    }
    
}
