package TDAPila;

public class NodoP<E> {
	protected E elemento;
	protected NodoP<E> siguiente;
	
	//-------------Constructor-------------
	/**
	 * Crea un objeto Nodo con Elemento elemt
	 * @param elemt 
	 * @param NodoP s
	 * **/
	public NodoP(E elemt, NodoP<E> s) {
		elemento = elemt;
		siguiente = s;
	}
	/**
	 * Crea un objeto Nodo con Elemento elemt
	 * @param elemt
	 * **/
	public NodoP(E item) {
		this(item,null);
	}
	//-------------Comandos-------------
	/**
	 * Setea el elemento del nodo que recibe el mensaje
	 * @param elemt elemento a setear
	 * **/
	public void setElemento(E elemt) {
		elemento = elemt;
	}
	/**
	 * Setea el nodo siguiente del nodo que recibe el
	 * mensaje
	 * @param  s nodo a insertar 
	 * **/
	public void setSiguiente(NodoP<E> s) {
		siguiente = s;
	}
	//-------------Consultas-------------
	/**
	 * Retorna el elemento del nodo que recibe el mensaje
	 * @return elemento del nodo
	 * **/
	public E getElemento() {
		return elemento;
	}
	/**
	 * Retorna el nodo siguiente al que recibe el mensaje
	 * @Returns nodo siguiente del nodo
	 * **/
	public NodoP<E> getSiguiente() {
		return siguiente;
	}
	public NodoP<E> getNext() {
		// TODO Auto-generated method stub
		return null;
	}
	public void setNext(Object object) {
		// TODO Auto-generated method stub
		
	}
}
