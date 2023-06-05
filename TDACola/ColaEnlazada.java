package TDACola;

import Exceptions.EmptyQueueException;

public class ColaEnlazada<E> implements Queue<E> {
    protected NodoP<E> head, tail;
    protected int size;

    public ColaEnlazada() {
        head = null;
        tail = null;
        size = 0;
    }

    public int size() {
        return size; 
    }

    public boolean isEmpty() {
        return size == 0; // ó head == null
    }

    public E front() throws EmptyQueueException{
        if(isEmpty())
            throw new EmptyQueueException("La cola está vacía.");
        return head.getElemento();
    }

    public void enqueue(E element) {
        NodoP<E> nodo = new NodoP<E>(element);
        nodo.setSiguiente(null);
        if(isEmpty())
            head = nodo;
        else
            tail.setSiguiente(nodo);
        tail = nodo;
        size++;
    }

    public E dequeue() throws EmptyQueueException {
        if(isEmpty())
            throw new EmptyQueueException("La cola está vacía.");
        E tmp = head.getElemento();
        head = head.getSiguiente();
        size--;
        if(size == 0)
            tail = null;
        return tmp;
    }

}