package Interfaces;

import Exceptions.EmptyQueueException;

public interface Queue<E> {
	/**Inserta elementos en el rabo de la cola
	 * @param e Elemento a insertar*/
	public void enqueue(E e);
	
	/**Elimina elementos en el frente de la cola*/
	public E dequeue() throws EmptyQueueException;
	
	/**Devuelve el proximo elemento a eliminar
	 * @return E elemento a eliminar*/
	public E front() throws EmptyQueueException;
	
	/**Dice si la cola esta vacia
	 * @return boolean*/
	public boolean isEmpty();
	
	/**Retorna la cantidad de elemetnos dentro de la cola
	 * @return int Elementos de la cola*/
	public int size();
}
