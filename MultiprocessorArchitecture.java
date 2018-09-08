/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cachecoherencesimulation;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class join the logic cache coherence system, including busses interfaces
 * processor unit and internal processor block implementation. It is implemented with thread because is analise at real 
 * time all the events that take place in to the system, between processor blocks
 * @author daniel
 */
public class MultiprocessorArchitecture extends Thread {

    BusInterface bus;//instance of BusInterface
    private Thread t;// thread instance
    ProcessorBlock block1;//instance of the 4 processor blocks
    ProcessorBlock block2;
    ProcessorBlock block3;
    ProcessorBlock block4;
    List<Long> listTime1, listTime2;//list to store times where each processor block request the bus usage
    List<Integer> listOrdered;//ordered list to execute each processor block
    boolean transition;
    int cycles;//counter of the cycles of the system

    /**
     * Constructor: create the instance of the system of cache coherence
     */
    public MultiprocessorArchitecture() {
        this.transition = true;//setting up initial values
        this.cycles = 0;
        
        bus = new BusInterface("bus");//create an instance of bus interface
        listTime1 = new ArrayList<>(4);
        listTime2 = new ArrayList<>(4);
        listOrdered = new ArrayList<>(4);
        
        block1 = new ProcessorBlock(1);//start 4 simultaneous processor's unit
        block1.start();
        
        block2 = new ProcessorBlock(2);
        block2.start();
        
        block3 = new ProcessorBlock(3);
        block3.start();
        
        block4 = new ProcessorBlock(4);
        block4.start();

    }

    /**
     * infinity loop of the system
     */
    @Override
    @SuppressWarnings({"SleepWhileInLoop", "CallToThreadYield"})
    public void run() {

        try {

            for (;;) {//infinity loop
               if(transition){
                    System.out.println("ciclos "+cycles);
                    this.cycles++;//increase cycles counter
                    Thread.sleep(300);//validate the bus each 0.1 second
                    readPetitions();//bus petitions are read
                    executePetitions();//execute those petitions in order to avoid incoherence
                    listTime1.clear();//clean the processors queue
                    listTime2.clear();//clean auxiliar processors queue
                    listOrdered.clear();}//ordered execution queue
                 
                

            }

        } catch (InterruptedException e) {

            System.out.println("sleep interrupted");
        }

    }

    /**
     * start the system thread.
     */
    @Override
    public void start() {
        if (t == null) {
            t = new Thread(this);
            t.start();
        }
    }
    /**
     * used to pause the system throught GUI
     */
    public void startMultiprocessor(){
        this.transition = true;
    }
    
    /**
     * resume the paused the system throught GUI
     */
    public void stopMultiprocessor(){
        this.transition = false;
    }

    /**
     * ordered execution of processors queue
     */
    private void executePetitions() {

        int idProcessor = 0;
        if (!listOrdered.isEmpty()) {//sort the processors in case the queue is not empty
            for (int i = 0; i < 4; i++) {//1 to 4 processor units
                idProcessor = listOrdered.get(i);
                if (idProcessor != 0) {
                    System.out.println("ejecutando-->"+idProcessor);
                    execAux(idProcessor);//execute a particular id
                    usingBus(idProcessor);//set the bus status as busy
                }
                try {
                    Thread.sleep(200);//waiting for cpu to read the bus
                } catch (InterruptedException ex) {
                    Logger.getLogger(MultiprocessorArchitecture.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        else{
            //System.out.println("lista de peticiones vacía");
        }
    }
    /**
     * set the status of the bus, used or not
     * @param id of the processor that requested the bus
     */
    private void usingBus(int id){
        switch(id){//choose id
            case 1:
                block1.setUsingBus(false);//turno off 
                break;
            case 2:
                block2.setUsingBus(false);
                break;
            case 3:
                block3.setUsingBus(false);
                break;
            case 4: 
                block4.setUsingBus(false);
                break;
        }
    
    }

    /**
     * Aux method to help the processing of each processor block
     * @param id
     * @return 
     */
    private int execAux(int id) {
        //System.out.println("ejecutando bus");
        int idProcessor = 0;//clean last id and others variables
        int direction = 0;
        int data = 0;
        String requestType = "";
        int resultado = 0;

        switch (id) {//choose id
            case 1:
                
                idProcessor = 1;//set the id of processor
                direction = block1.getBusDirectionOut();//get address requested of the processor block
                data = block1.getBusDataOut();//get data of the processor
                requestType = block1.getBusRequestType();//get the type of request BW or BR
                resultado = bus.busPetition(idProcessor, direction, data, requestType);//execute bus petition
                if(requestType.equals("BR")){block1.controller.recordOnBR(direction,resultado);}//returns a value in case of bus read
                generateRequest(1, requestType,direction);//generate alerts to others processor blocks BW or BR and address 

                break;
            case 2:
                
                idProcessor = 2;
                direction = block2.getBusDirectionOut();
                data = block2.getBusDataOut();
                requestType = block2.getBusRequestType();
                resultado = bus.busPetition(idProcessor, direction, data, requestType);
                if(requestType.equals("BR")){block2.controller.recordOnBR(direction,resultado);}
                generateRequest(2, requestType,direction);

                break;
            case 3:
                
                idProcessor = 3;
                direction = block3.getBusDirectionOut();
                data = block3.getBusDataOut();
                requestType = block3.getBusRequestType();
                resultado = bus.busPetition(idProcessor, direction, data, requestType);
                if(requestType.equals("BR")){block3.controller.recordOnBR(direction,resultado);}
                generateRequest(3, requestType,direction);

                break;
                
            case 4:
                
                idProcessor = 4;
                direction = block4.getBusDirectionOut();
                data = block4.getBusDataOut();
                requestType = block4.getBusRequestType();
                resultado = bus.busPetition(idProcessor, direction, data, requestType);
                if(requestType.equals("BR")){block4.controller.recordOnBR(direction,resultado);}
                generateRequest(4, requestType,direction);

                break;

        }

        return resultado;
    }

    /**
     * Generate an alert to others processor blocks, of BW, BR in an specific address 
     * @param id of the processor bloock that generate the alert
     * @param requestType the type of request BW or BR
     * @param direction the address
     */
    private void generateRequest(int id, String requestType, int direction) {
        switch (id) {//choose id of processor
            case 1://cleans the last flags
                block1.setBusDataIn(-1);
                block1.setBW(false);
                block1.setBR(false);
                if (requestType.equals("BW")) {//update in case of BW
                    block2.setBusDirectionIn(direction);//set direction  and BW , BR flags
                    block2.setBR(false);
                    block2.setBW(true);
                    block3.setBusDirectionIn(direction);
                    block3.setBR(false);
                    block3.setBW(true);
                    block4.setBusDirectionIn(direction);
                    block4.setBR(false);
                    block4.setBW(true);
                    
                } else {//update in case of BR
                    block2.setBusDirectionIn(direction);
                    block2.setBR(true);
                    block2.setBW(false);
                    block3.setBusDirectionIn(direction);
                    block3.setBR(true);
                    block3.setBW(false);
                    block4.setBusDirectionIn(direction);
                    block4.setBR(true);
                    block4.setBW(false);
                    
                }
                break;
            case 2:
                block2.setBusDataIn(-1);
                block2.setBW(false);
                block2.setBR(false);
                if (requestType.equals("BW")) {
                    block1.setBusDirectionIn(direction);
                    block1.setBR(false);
                    block1.setBW(true);
                    block3.setBusDirectionIn(direction);
                    block3.setBR(false);
                    block3.setBW(true);
                    block4.setBusDirectionIn(direction);
                    block4.setBR(false);
                    block4.setBW(true);
                    
                } else {
                    block1.setBusDirectionIn(direction);
                    block1.setBR(true);
                    block1.setBW(false);
                    block3.setBusDirectionIn(direction);
                    block3.setBR(true);
                    block3.setBW(false);
                    block4.setBusDirectionIn(direction);
                    block4.setBR(true);
                    block4.setBW(false);
                    
                }
                break;
            case 3:
                block3.setBusDataIn(-1);
                block3.setBW(false);
                block3.setBR(false);
                if (requestType.equals("BW")) {
                    block1.setBusDirectionIn(direction);
                    block1.setBR(false);
                    block1.setBW(true);
                    block2.setBusDirectionIn(direction);
                    block2.setBR(false);
                    block2.setBW(true);
                    block4.setBusDirectionIn(direction);
                    block4.setBR(false);
                    block4.setBW(true);
                    
                } else {
                    block1.setBusDirectionIn(direction);
                    block1.setBR(true);
                    block1.setBW(false);
                    block2.setBusDirectionIn(direction);
                    block2.setBR(true);
                    block2.setBW(false);
                    block4.setBusDirectionIn(direction);
                    block4.setBR(true);
                    block4.setBW(false);
                    
                }
                break;
            case 4:
                block4.setBusDataIn(-1);
                block4.setBW(false);
                block4.setBR(false);
                if (requestType.equals("BW")) {
                    block1.setBusDirectionIn(direction);
                    block1.setBR(false);
                    block1.setBW(true);
                    block2.setBusDirectionIn(direction);
                    block2.setBR(false);
                    block2.setBW(true);
                    block3.setBusDirectionIn(direction);
                    block3.setBR(false);
                    block3.setBW(true);
                    
                } else {
                    block1.setBusDirectionIn(direction);
                    block1.setBR(true);
                    block1.setBW(false);
                    block2.setBusDirectionIn(direction);
                    block2.setBR(true);
                    block2.setBW(false);
                    block3.setBusDirectionIn(direction);
                    block3.setBR(true);
                    block3.setBW(false);
                    
                }
                break;
        }
    }

    /**
     * Read all the processor block that are requesting for the bus
     */
    private void readPetitions() {
       
        boolean ref1 = block1.getUsingBus();//read state of need of the bus
        boolean ref2 = block2.getUsingBus();
        boolean ref3 = block3.getUsingBus();
        boolean ref4 = block4.getUsingBus();

        long time1 = block1.getTime();//get time when the processor request the bus
        long time2 = block2.getTime();// to maintain order execution
        long time3 = block3.getTime();
        long time4 = block4.getTime();
       
        long cero = 0;
        

        if (ref1 == true) {///add the times to queues
            listTime1.add(time1);
            listTime2.add(time1);
        } else {//add cero in case there is not a request
            listTime1.add(cero);
            listTime2.add(cero);
        }

        if (ref2 == true) {
            listTime1.add(time2);
            listTime2.add(time2);
        } else {
            listTime1.add(cero);
            listTime2.add(cero);
        }

        if (ref3 == true) {
            listTime1.add(time3);
            listTime2.add(time3);
        } else {
            listTime1.add(cero);
            listTime2.add(cero);
        }

        if (ref4 == true) {
            listTime1.add(time4);
            listTime2.add(time4);
        } else {
            listTime1.add(cero);
            listTime2.add(cero);
        }

        sort();//sort the order execution

    }

    /**
     * method to create order execution of each processor block
     */
    private void sort() {//
        int index = 0;
        long suma = listTime1.get(0) + listTime1.get(1) + listTime1.get(2) + listTime1.get(3);//check if the list in empty
        if (suma == 0) { //System.out.println("no petitions found");
        } else {
            for (int i = 0; i < 4; i++) {//go throught each CPU
                long MinorElement = getMinorElement();//algoritm implemented insertion sort
                if(MinorElement == 0){
                    index = 8;
                    listOrdered.add(index);
                }
                else{
                    index = getIndexOf(MinorElement);//look for the element of the CPU
                    listOrdered.add(index+1);//add the cpu id to the ordered list
                }
                
            }
        }
        
        

    }
    
    /**
     * Auxiliar method of the sorting algoritm that
     * get the minor element
     * @return the minor element 
     */
    private long getMinorElement() {
        long menor = listTime1.get(0);
        long menor_aux = 0;
        int menorIndex = 0;
        
        for (int i = 0; i < listTime1.size(); i++) {
            menor_aux = listTime1.get(i);
            if(menor_aux<menor) {
                menor = menor_aux;
                menorIndex = i;
            }
        }
        listTime1.remove(menorIndex);
        return menor;
    }

    /**
     * Getter of the index of specified data
     * @param data
     * @return 
     */
    private int getIndexOf(long data) {
        int index = 0;

        for (int i = 0; i < listTime2.size(); i++) {
            if (listTime2.get(i) == data) {
                index = i;
                break;
            }
        }
        return index;
    }
    /**
     * this method is used to stop all the system threads
     */
    public void pauseMultiprocessor() throws InterruptedException{
        
       
       
    
    }
    
    /**
     * this method is used to resume all the system threads
     */
    public void resumeMultiprocessor(){
        
        
        
    
    }

}
