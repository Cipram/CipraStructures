package TDAPila;

import Exceptions.*;

public class PilaConEnlaces<E> implements Stack<E>{
	protected NodoP<E> Head;
	protected int indice;
	
	//-------------Constructor-------------
	public PilaConEnlaces() {
		Head = null;
		indice = 0;
	}
	//-------------Comandos-------------
	@Override
	public void push(E e) {
		Head = new NodoP<E>(e,Head);
		indice++;
	}
	@Override
	public E pop() throws EmptyStackException{
		E temp;
		if (isEmpty()) {
			throw new EmptyStackException("La pila esta vacia");
		}
		else {
			temp = Head.getElemento();
			Head = Head.getSiguiente();
			indice--;
		}
		return temp;
	}
	//-------------Comsultas-------------
	@Override
	public boolean isEmpty() {
		return indice == 0;
	}

	@Override
	public E top() throws EmptyStackException{
		if (indice == 0)
			throw new EmptyStackException("La pila no tiene nodos");
		return Head.getElemento();
	}

	@Override
	public int size() {
		return indice;
	}
	
}
