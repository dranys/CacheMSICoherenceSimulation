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
 * Clase que define las operaciones básicas que debe tener una lista simple.
 * @author xavier
 */
public class List {
    // Puntero que indica el init de la lista o conocida tambien
    // como cabeza de la lista.
    private Node init;
    // Variable para registrar el tamaño de la lista.
    private int lenght;
    /**
     * Constructor por defecto.
     */
    public void List(){
        init = null;
        lenght = 0;
    }
    /**
     * Consulta si la lista esta vacia.
     * @return true si el primer nodo (init), no apunta a otro nodo.
     */
    public boolean isEmpty(){
        return init == null;
    }
    /**
     * Consulta cuantos elementos (nodos) tiene la lista.
     * @return numero entero entre [0,n] donde n es el numero de elementos
     * que contenga la lista.
     */
    public int getLenght(){
        return lenght;
    }
    /**
     * Agrega un newNode nodo al final de la lista.
     * @param valor a agregar.
     */
    public void addFinal(int valor){
        // Define un newNode nodo.
        Node newNode = new Node();
        // Agrega al valor al nodo.
        newNode.setData(valor);
        // Consulta si la lista esta vacia.
        if (isEmpty()) {
            // Inicializa la lista agregando como init al newNode nodo.
            init = newNode;
        // Caso contrario recorre la lista hasta llegar al ultimo nodo
        //y agrega el newNode.
        } else{
            // Crea ua copia de la lista.
            Node aux = init;
            // Recorre la lista hasta llegar al ultimo nodo.
            while(aux.getNext() != null){
                aux = aux.getNext();
            }
            // Agrega el newNode nodo al final de la lista.
            aux.setNext(newNode);
        }
        // Incrementa el contador de tamaño de la lista
        lenght++;
    }
    
    /**
     * Obtiene el valor de un nodo en una determinada posición.
     * @param posicion del nodo que se desea obtener su valor.
     * @return un numero entero entre [0,n-1] n = numero de nodos de la lista.
     * @throws Exception
     */
    public int getData(int posicion) throws Exception{
        // Verifica si la posición ingresada se encuentre en el rango
        // >= 0 y < que el numero de elementos del la lista.
        if(posicion>=0 && posicion<lenght){
            // Consulta si la posicion es el init de la lista.
            if (posicion == 0) {
                // Retorna el valor del init de la lista.
                return init.getData();
            }else{
                // Crea una copia de la lista.
                Node aux = init;
                // Recorre la lista hasta la posición ingresada.
                for (int i = 0; i < posicion; i++) {
                    aux = aux.getNext();
                }
                // Retorna el valor del nodo.
                return aux.getData();
            }
        // Crea una excepción de Posicion inexistente en la lista.
        } else {
            throw new Exception("Posicion inexistente en la lista.");
        }
    }
    
    /**
     * Elimina un nodo que se encuentre en la lista ubicado
     * por su posición.
     * @param posicion en la cual se encuentra el nodo a eliminar.
     */
    public void removeData(int posicion){
        // Verifica si la posición ingresada se encuentre en el rango
        // >= 0 y < que el numero de elementos del la lista.
        if(posicion>=0 && posicion<lenght){
            // Consulta si el nodo a eliminar es el primero
            if(posicion == 0){
                // Elimina el primer nodo apuntando al siguinte.
                init = init.getNext();
            }
            // En caso que el nodo a eliminar este por el medio 
            // o sea el ultimo
            else{
                // Crea una copia de la lista.
                Node aux = init;
                // Recorre la lista hasta lleger al nodo anterior al eliminar.
                for (int i = 0; i < posicion-1; i++) {
                    aux = aux.getNext();
                }
                // Guarda el nodo siguiente al nodo a eliminar.
                Node siguiente = aux.getNext();
                // Elimina la referencia del nodo apuntando al nodo siguiente.
                aux.setNext(siguiente.getNext());
            }
            // Disminuye el contador de tamaño de la lista.
            lenght--;
        }
    }
    
    
    
    /**
     * Mustra en pantalla los elementos de la lista.
     */
    public void list(){
        // Verifica si la lista contiene elementoa.
        if (!isEmpty()) {
            // Crea una copia de la lista.
            Node aux = init;
            // Posicion de los elementos de la lista.
            int i = 0;
            // Recorre la lista hasta el final.
            while(aux != null){
                // Imprime en pantalla el valor del nodo.
                System.out.print(i + ".[ " + aux.getData() + " ]" + " ->  ");
                // Avanza al siguiente nodo.
                aux = aux.getNext();
                // Incrementa el contador de la posión.
                i++;
            }
        }
    }
    
    public void sortList(){
        if(!isEmpty()){
            Node aux = init;
        
        }
    }
    
}