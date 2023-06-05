package TDACola;

import Exceptions.*;

public class ArrayQueue <E> implements Queue<E>{
	protected E [] arreglo;
	protected int frente;
	protected int rabo;
	protected int tamano;
	
	//--------Constrcutor---------
	
	@SuppressWarnings("unchecked")
	public ArrayQueue(int max) {
		arreglo = (E[]) new Object [max];
		tamano = 0;
		frente = 0;
		rabo = 0;
	}
	
	//--------Comandos--------
	public void enqueue(E e) {
		if (tamano == arreglo.length - 1)
			resize();
		arreglo[rabo] = e;
		rabo = (rabo+1) % (arreglo.length);
		tamano++;
	}
	
	public E dequeue() throws EmptyQueueException{
		E temp;
		if (isEmpty())
			throw new EmptyQueueException("la cola esta vacia");
		temp = arreglo[frente];
		arreglo[frente] = null;
		frente = (frente+1) % arreglo.length;
		tamano--;
		return temp;
	}
	
	//--------Consultas--------
	public E front() throws EmptyQueueException{
		if (isEmpty())
			throw new EmptyQueueException("La cola esta vacia");
		return arreglo[frente];
	}
	
	public boolean isEmpty() {
		return frente == rabo;
	}
	
	public int size() {
		return tamano;
	}
	
	@SuppressWarnings("unchecked")
    private void resize() {
        int size = size(); 
        E[] nuevaCola = (E[]) new Object[size*2];
        for (int i = 0; i < size; i++) {
            nuevaCola[i] = arreglo[frente];
            frente = (frente + 1) % arreglo.length;
        }
        arreglo = nuevaCola;
        rabo = size;
        frente = 0;
    }
	
	public String toString() {
		String ou = "";
		for (int  i = 0; i < arreglo.length;i++) {
			if (arreglo[i] != null)
				ou += arreglo[i].toString();
		}
		return ou;
	}
}
