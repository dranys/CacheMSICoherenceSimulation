package cachecoherencesimulation;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Random;

/**
 * This class creates a new thread of type CPU to create events of write, read and execute.
 * @author daniel
 */
public class Cpu extends Thread {

    private Thread t;
    private final int identifier;//ID of the processor
    private String request;//String of requested activity, (read, write, execute)
    private int direction;//Direction of the requested activity
    private int data;// data created by CPU
    private boolean readRequest;//the type of request generated by CPU, BW or BR
    private boolean pausedCpu;//a flag to pause when is not need to generate request to cache block
    

    /**
     * Constructor of CPU.
     * @param identifier : CPU identifier
     */
    public Cpu(int identifier) {
        this.identifier = identifier;//setting initial values
        generateRequest();//generate the first request
        this.direction = 0;//set inital values of direction and data
        this.data = 0;
        this.readRequest = false;
        this.pausedCpu = false;
        
    }

    /**
     * the infinity loop of the cpu
     */
    @Override
    @SuppressWarnings({"SleepWhileInLoop", "CallToThreadYield"})
    public void run() {

        try {

            for (;;) {//infinity loop
                Thread.sleep(1000);//the processor generates requests each second 
                while (pausedCpu) {
                    Thread.sleep(1000);//wait for one second when the bus is used
                }
                generateRequest();//creates the new values of request, direction, and data
                readRequest = false;//flag to show when the CPU is not waiting
            }

        } catch (InterruptedException e) {

            System.out.println("sleep interrupted");
        }

    }

    /**
     * method to initiate the thread.
     */
    @Override
    public void start() {
        if (t == null) {
            t = new Thread(this);
            t.start();
        }
    }

    /**
     * method to create a new request, setting a new random value for request type (bus write and bus read),
     * direction and data.
     */
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
        this.direction = rand.nextInt(16) + 0;// set a new direction between 0 and 15
        this.data = rand.nextInt(10) + 1;// set a new data between 0 and 15
    }

    /**
     * getter of Indentifier of the CPU
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
     * getter of memory direction.
     * @return memory direction.
     */
    public int getDirection() {
        return direction;
    }
    
    /**
     * getter of the CPU data.
     * @return the data
     */

    public int getData() {
        return data;
    }
    
    /**
     * setter of the read request flag
     * @param readRequest 
     */

    public void setReadRequest(boolean readRequest) {

        this.readRequest = readRequest;
    }

    /**
     * getter of the flag (readRequest).
     * @return tha flag value
     */
    public boolean getReadRequest() {
        return readRequest;
    }

    /**
     * Set a flag to pause the CPU when is waiting for bus usage.
     */
    public void pauseCpu() {
        this.pausedCpu = true;

    }

    /**
     * resume the CPU after finishing of use of the bus.
     */
    public void resumeCpu() {
        this.pausedCpu = false;
    }

}
