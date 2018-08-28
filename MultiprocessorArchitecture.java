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
    List<Long> listTime;
    List<Integer> listOrdered;

    public MultiprocessorArchitecture() {
        bus = new BusInterface("bus");
        listTime = new ArrayList<>(4);
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

                Thread.sleep(200);
                readPetitions();
                executePetitions();
                listTime.clear();
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
                if (idProcessor != 0) {
                    execAux(idProcessor);
                }
            }
        }
    }

    private int execAux(int id) {
        System.out.println("ejecutando bus");
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
                block1.setBusDirectionIn(resultado);
                generateRequest(1, requestType);
                block1.setUsingBus(false);
                break;
            case 2:
                idProcessor = 2;
                direction = block2.getBusDirectionOut();
                data = block2.getBusDataOut();
                requestType = block2.getBusRequestType();
                resultado = bus.busPetition(idProcessor, direction, data, requestType);
                block2.setBusDirectionIn(resultado);
                generateRequest(2, requestType);
                block2.setUsingBus(false);
                break;
            case 3:
                idProcessor = 3;
                direction = block3.getBusDirectionOut();
                data = block3.getBusDataOut();
                requestType = block3.getBusRequestType();
                resultado = bus.busPetition(idProcessor, direction, data, requestType);
                block3.setBusDirectionIn(resultado);
                generateRequest(3, requestType);
                block3.setUsingBus(false);
                break;
            case 4:
                idProcessor = 4;
                direction = block4.getBusDirectionOut();
                data = block4.getBusDataOut();
                requestType = block4.getBusRequestType();
                resultado = bus.busPetition(idProcessor, direction, data, requestType);
                block4.setBusDirectionIn(resultado);
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
        long time2 = block1.getTime();
        long time3 = block1.getTime();
        long time4 = block1.getTime();

        long cero = 0;

        if (ref1 == true) {
            listTime.add(time1);
        } else {
            listTime.add(cero);
        }

        if (ref2 == true) {
            listTime.add(time1);
        } else {
            listTime.add(cero);
        }

        if (ref3 == true) {
            listTime.add(time1);
        } else {
            listTime.add(cero);
        }

        if (ref4 == true) {
            listTime.add(time1);
        } else {
            listTime.add(cero);
        }
        sort();

    }

    private void sort() {
        long menor = 0;
        int index = 0;
        long suma = listTime.get(0) + listTime.get(1) + listTime.get(2) + listTime.get(3);
        if (suma == 0) { System.out.println("no petitions found");
        } else {
            for (int i = 1; i <= 4; i++) {
                index = getIndexOf(getMinorElement());
                listOrdered.add(index);
            }
        }

    }

    private long getMinorElement() {
        long menor = 0;
        long menor_aux = 0;
        for (int i = 0; i < 4; i++) {
            menor_aux = listTime.get(i);
            if (menor_aux == 0) {
                continue;
            } else {
                if (menor == 0 || menor > menor_aux) {
                    menor = menor_aux;
                }
            }
        }
        return menor;
    }

    private int getIndexOf(long data) {
        int index = 0;

        for (int i = 0; i < 4; i++) {
            if (listTime.get(i) == data) {
                index = i;
                break;
            }
        }
        return index;
    }

}
