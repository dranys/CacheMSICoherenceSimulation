package cachecoherencesimulation;

import java.util.concurrent.Semaphore;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Random;

/**
 *
 * @author daniel
 */
public class Cpu extends Thread {

    private Thread t;
    private final int identifier;//ID of the processor
    private String request;//String of requested activity, (read, write, execute)
    private int direction;//Direction of the requested activity
    private int data;
    private boolean readRequest;
    private boolean pausedCpu;
    

    /**
     * Constructor
     *
     * @param identifier : identificador del CPU creado
     */
    public Cpu(int identifier) {
        this.identifier = identifier;
        generateRequest();
        System.out.println("CPU" + identifier+" request: " + request + " direction: " + direction + " data: " + data);
        this.direction = 0;
        this.data = 0;
        this.readRequest = false;
        this.pausedCpu = false;
        
    }

    @Override
    @SuppressWarnings({"SleepWhileInLoop", "CallToThreadYield"})
    public void run() {

        try {

            for (;;) {
                Thread.sleep(1000);
                while (pausedCpu) {
                    
                    //System.out.println("Pausado CPU: " + identifier + ":"+pausedCpu);
                    Thread.sleep(1000);
                }
                generateRequest();
                System.out.println("CPU" + identifier+" request: " + request + " direction: " + direction + " data: " + data);
                readRequest = false;
            }

        } catch (InterruptedException e) {

            System.out.println("sleep interrupted");
        }

    }

    @Override
    public void start() {
        if (t == null) {
            t = new Thread(this);
            t.start();
        }
    }

    public void generateRequest() {

        Random rand = new Random();//here is generated a random number to select activity
        int n = rand.nextInt(3) + 1;//1 write, 2 read, 3 execute
        switch (n) {
            case 1:
                this.request = "PW";
                break;
            case 2:
                this.request = "PR";
                break;
            case 3:
                this.request = "PE";
                break;
        }
        this.direction = rand.nextInt(16) + 0;// set a new direction between 0 and 16
        this.data = rand.nextInt(10) + 1;// set a new data between 0 and 16
    }

    /**
     * getter of Indentifier
     *
     * @return Indentifier
     */
    public int getIdentifier() {
        return identifier;
    }

    /**
     * getter of requested activity (read, write, execute)
     *
     * @return request
     */
    public String getRequest() {
        return request;
    }

    /**
     * getter of memory direction
     *
     * @return memory direction
     */
    public int getDirection() {
        return direction;
    }

    public int getData() {
        return data;
    }

    public void setReadRequest(boolean readRequest) {

        this.readRequest = readRequest;
    }

    public boolean getReadRequest() {
        return readRequest;
    }

    public void pauseCpu() {
        //System.out.println("pausando cpu");
        this.pausedCpu = true;

    }

    public void resumeCpu() {
        this.pausedCpu = false;
    }

}
