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
/**
 * Clase que define los elementos que debe tener un Nodo de la lista.
 * @author xavier
 */
public class Node{
    // Variable en la cual se va a guardar el data.
    private int data;
    // Variable para enlazar los nodos.
    private Node nodeNext;
    /**
     * Constructor que inicializamos el data de las variables.
     */
    public void Node(){
        this.data = 0;
        this.nodeNext = null;
    }
    
    // Métodos get y set para los atributos.
    
    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public Node getNext() {
        return nodeNext;
    }

    public void setNext(Node next) {
        this.nodeNext = next;
    }   
}