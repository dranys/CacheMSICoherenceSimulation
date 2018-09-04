/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cachecoherencesimulation;

import java.util.concurrent.Semaphore;

/**
 *
 * @author daniel
 */
public class ProcessorBlock extends Thread {

    private static Semaphore semaphore;
    Cpu cpu;
    CacheController controller;
    private boolean usingBus, BW, BR;
    private int busDirectionIn, busDirectionOut, busDataOut, busDataIn, busIdentifier;
    private String busRequestType;
    private String pbName;
    private long clockTimeBus;

    private Thread t;

    public ProcessorBlock(int id) {
        
        this.pbName = "ProcessorBlock" + id;
        this.controller = new CacheController("Controlador" + id);
        this.cpu = new Cpu(id);
        this.usingBus = false;
        this.BW = false;
        this.BR = false;
        this.busDirectionIn = -1;
        this.busDirectionOut = -1;
        semaphore = new Semaphore(1);
        this.busRequestType = "";
        this.clockTimeBus = 0;
        this.busDataIn = 0;
        this.cpu.start();

    }

    private void CPUconnection() {
        //System.out.println("readRequest pb = "+cpu.getReadRequest());
        if (!cpu.getReadRequest()) {
            //System.out.println("dentro");
            String operation = cpu.getRequest();
            int direction = cpu.getDirection();
            int data = cpu.getData();

            busDataOut = data;
            busDirectionOut = direction;
            busIdentifier = cpu.getIdentifier();
       
            int busSignalOut = controller.connectCacheCpu(operation, direction, data);
            if (busSignalOut == 1) {
                cpu.pauseCpu();
                //System.out.println("solicitud de BW");
                busRequestType = "BW";
                this.clockTimeBus = System.nanoTime();
                usingBus = true;

            } else if (busSignalOut == 2) {
                cpu.pauseCpu();
                //System.out.println("solicitud de BR");
                busRequestType = "BR";
                this.clockTimeBus = System.nanoTime();
                usingBus = true;

            }

            cpu.setReadRequest(true);
        }
    }

    @Override
    public void run() {

        try {

            for (;;) {
                busCheck();
                CPUconnection();
                Thread.sleep(50);

                while (usingBus) {//this block pause cpu for waiting to connect and comunucate
                    
                    busCheck();
                    cpu.pauseCpu();
                    Thread.sleep(50);
                    //yield();
                }
                cpu.resumeCpu();

            }

        } catch (InterruptedException e) {

            System.out.println("sleep interrupted");
        }
    }

    private void busCheck() {
        
        //if(busRequestType.equals("BR")){//waiting for data to read from memory
            //controller.recordOnBR(busDirectionOut, busDataIn);//save the data 
        //}
        
        if (busDirectionIn != busDirectionOut) {
            System.out.println("bus checking BW values BW: "+BW+" BR: "+BR+" direction in: "+busDirectionIn+" direction out: "+busDirectionOut);
            controller.busRead(BW, BR, busDirectionIn);
        }
    }

    @Override
    public void start() {
        if (t == null) {
            t = new Thread(this);
            t.start();
        }
    }
    
    public long getTime(){
        return clockTimeBus;
    }

    public void setBW(boolean BW) {
        this.BW = BW;
    }

    public void setBR(boolean BR) {
        this.BR = BR;
    }
    public void setBusDataIn(int dataIn){
        this.busDataIn = dataIn;
    }
   
    
    public void setBusDirectionIn(int busDirection) {
        this.busDirectionIn = busDirection;
    }

    public void setBusDataOut(int busDataOut) {
        this.busDataOut = busDataOut;
    }

    public int getBusDirectionOut() {
        return busDirectionOut;
    }

    public int getBusDataOut() {
        return busDataOut;
    }

    public int getBusIdentifier() {
        return busIdentifier;
    }
    
    public boolean getUsingBus(){
        return this.usingBus;
    }
   
    
    public void setUsingBus(boolean used){
        this.usingBus = used;
    }

    public String getBusRequestType() {
        return busRequestType;
    }

    
}
