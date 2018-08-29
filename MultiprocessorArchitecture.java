/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cachecoherencesimulation;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author daniel
 */
public class MultiprocessorArchitecture extends Thread {

    BusInterface bus;
    private Thread t;
    ProcessorBlock block1;
    ProcessorBlock block2;
    ProcessorBlock block3;
    ProcessorBlock block4;
    List<Long> listTime1, listTime2;
    List<Integer> listOrdered;

    public MultiprocessorArchitecture() {
        bus = new BusInterface("bus");
        listTime1 = new ArrayList<>(4);
        listTime2 = new ArrayList<>(4);
        listOrdered = new ArrayList<>(4);
        
        block1 = new ProcessorBlock(1);
        block1.start();
        
        block2 = new ProcessorBlock(2);
        block2.start();
        
        block3 = new ProcessorBlock(3);
        block3.start();
        
        block4 = new ProcessorBlock(4);
        block4.start();

    }

    @Override
    @SuppressWarnings({"SleepWhileInLoop", "CallToThreadYield"})
    public void run() {

        try {

            for (;;) {

                Thread.sleep(500);
                readPetitions();
                executePetitions();
                listTime1.clear();
                listTime2.clear();
                listOrdered.clear();

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

    private void executePetitions() {

        int idProcessor = 0;
        if (!listOrdered.isEmpty()) {
            for (int i = 0; i < 4; i++) {
                idProcessor = listOrdered.get(i);
                //System.out.println("id-->"+idProcessor);
                if (idProcessor != 0) {
                    //System.out.println("ejecutando__"+idProcessor);
                    execAux(idProcessor);
                }
            }
        }
        else{
            System.out.println("lista vacía");
        }
    }

    private int execAux(int id) {
        //System.out.println("ejecutando bus");
        int idProcessor = 0;
        int direction = 0;
        int data = 0;
        String requestType = "";
        int resultado = 0;

        switch (id) {
            case 1:
                
                idProcessor = 1;
                direction = block1.getBusDirectionOut();
                data = block1.getBusDataOut();
                requestType = block1.getBusRequestType();
                resultado = bus.busPetition(idProcessor, direction, data, requestType);
                block1.setBusDataIn(resultado);
                generateRequest(1, requestType);
                block1.setUsingBus(false);
                break;
            case 2:
                
                idProcessor = 2;
                direction = block2.getBusDirectionOut();
                data = block2.getBusDataOut();
                requestType = block2.getBusRequestType();
                resultado = bus.busPetition(idProcessor, direction, data, requestType);
                block2.setBusDataIn(resultado);
                generateRequest(2, requestType);
                block2.setUsingBus(false);
                break;
            case 3:
                
                idProcessor = 3;
                direction = block3.getBusDirectionOut();
                data = block3.getBusDataOut();
                requestType = block3.getBusRequestType();
                resultado = bus.busPetition(idProcessor, direction, data, requestType);
                block3.setBusDataIn(resultado);
                generateRequest(3, requestType);
                block3.setUsingBus(false);
                break;
                
            case 4:
                
                idProcessor = 4;
                direction = block4.getBusDirectionOut();
                data = block4.getBusDataOut();
                requestType = block4.getBusRequestType();
                resultado = bus.busPetition(idProcessor, direction, data, requestType);
                block4.setBusDataIn(resultado);
                generateRequest(4, requestType);
                block4.setUsingBus(false);
                break;

        }

        return resultado;
    }

    private void generateRequest(int id, String requestType) {
        switch (id) {
            case 1:
                if (requestType.equals("BW")) {
                    block2.setBR(false);
                    block2.setBW(true);
                    block3.setBR(false);
                    block3.setBW(true);
                    block4.setBR(false);
                    block4.setBW(true);
                } else {
                    block2.setBR(true);
                    block2.setBW(false);
                    block3.setBR(true);
                    block3.setBW(false);
                    block4.setBR(true);
                    block4.setBW(false);
                }
            case 2:
                if (requestType.equals("BW")) {
                    block1.setBR(false);
                    block1.setBW(true);
                    block3.setBR(false);
                    block3.setBW(true);
                    block4.setBR(false);
                    block4.setBW(true);
                } else {
                    block1.setBR(true);
                    block1.setBW(false);
                    block3.setBR(true);
                    block3.setBW(false);
                    block4.setBR(true);
                    block4.setBW(false);
                }
            case 3:
                if (requestType.equals("BW")) {
                    block1.setBR(false);
                    block1.setBW(true);
                    block2.setBR(false);
                    block2.setBW(true);
                    block4.setBR(false);
                    block4.setBW(true);
                } else {
                    block1.setBR(true);
                    block1.setBW(false);
                    block2.setBR(true);
                    block2.setBW(false);
                    block4.setBR(true);
                    block4.setBW(false);
                }
            case 4:
                if (requestType.equals("BW")) {
                    block1.setBR(false);
                    block1.setBW(true);
                    block2.setBR(false);
                    block2.setBW(true);
                    block3.setBR(false);
                    block3.setBW(true);
                } else {
                    block1.setBR(true);
                    block1.setBW(false);
                    block2.setBR(true);
                    block2.setBW(false);
                    block3.setBR(true);
                    block3.setBW(false);
                }
        }
    }

    private void readPetitions() {
       
        boolean ref1 = block1.getUsingBus();
        boolean ref2 = block2.getUsingBus();
        boolean ref3 = block3.getUsingBus();
        boolean ref4 = block4.getUsingBus();

        long time1 = block1.getTime();
        long time2 = block2.getTime();
        long time3 = block3.getTime();
        long time4 = block4.getTime();
       
        long cero = 0;
        

        if (ref1 == true) {
            listTime1.add(time1);
            listTime2.add(time1);
        } else {
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
        System.out.println("tiempo1="+listTime2.get(0));
        System.out.println("tiempo2="+listTime2.get(1));
        System.out.println("tiempo3="+listTime2.get(2));
        System.out.println("tiempo4="+listTime2.get(3));

        sort();

    }

    private void sort() {//
        int index = 0;
        long suma = listTime1.get(0) + listTime1.get(1) + listTime1.get(2) + listTime1.get(3);
        if (suma == 0) { System.out.println("no petitions found");
        } else {
            for (int i = 0; i < 4; i++) {
                long MinorElement = getMinorElement();
                if(MinorElement == 0){
                    index = 8;
                    listOrdered.add(index);
                }
                else{
                    index = getIndexOf(MinorElement);
                    listOrdered.add(index+1);
                }
                System.out.println("minorElement-->"+MinorElement+" index-->"+index);
                
            }
            System.out.println("ordenado--> 0:"+listOrdered.get(0)+" 1:"+listOrdered.get(1)+" 2:"+listOrdered.get(2)+" 3:"+listOrdered.get(3));
        }
        
        

    }

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

}
