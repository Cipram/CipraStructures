package TDALista;
 
import Exceptions.*;
import Interfaces.Position;
import Interfaces.PositionList;

import java.util.Iterator;

public class ListaSE<E> implements PositionList<E>{
	protected Nodo<E> head;
	protected int size;
	
	public ListaSE() {
		head = null;
		size = 0;
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public Position<E> first() throws EmptyListException {
		if(isEmpty())
			throw new EmptyListException("La lista está vacía.");
		return head;
	}
	
	public Position<E> last() throws EmptyListException {
		Nodo<E> last; 
		if(isEmpty())
			throw new EmptyListException("La lista está vacía.");
		last = head; // Es como arrancar con i=0, si size == 1 entonces no entra al while.
		while(last.getNext() != null) {
			last = last.getNext();
		}
		return last;
	}
	
	public Position<E> next(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		Nodo<E> aux = checkPosition(p);
		if(aux.getNext() == null)
			throw new BoundaryViolationException("La posición recibida es la última.");
		return aux.getNext();
	}
	
	public Position<E> prev(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		checkPosition(p);
		try {
			if(p == first()) 
				throw new BoundaryViolationException("La posición recibida es la primera.");
		} catch(EmptyListException e) {
			e.printStackTrace();
		}
		Nodo<E> aux = head;
		while(aux.getNext() != p && aux.getNext() != null) {
			aux = aux.getNext();
		}
		if(aux.getNext() == null)	
			throw new InvalidPositionException("La posición no pertenece a la lista.");
		return aux;
	}

	public void addFirst(E element) {
		head = new Nodo<E>(element, head);
		size++;
	}
	
	public void addLast(E element) {
		if(isEmpty())
			addFirst(element); 
		else {
			Nodo<E> p = head;
			while(p.getNext() != null) {
				p = p.getNext();
			}
			p.setNext(new Nodo<E>(element));
			size++; // addFirst ya aumenta el tamaño.
		}
	}
	
	public void addAfter(Position<E> p, E element) throws InvalidPositionException {
		Nodo<E> n = checkPosition(p);
		Nodo<E> nuevo = new Nodo<E>(element);
		nuevo.setNext(n.getNext());
		n.setNext(nuevo);
		size++;
	}
	
	public void addBefore(Position<E> p, E element) throws InvalidPositionException{
		checkPosition(p);
		try {
			if(p == first())
				addFirst(element);
			else
				addAfter(prev(p),element);
		} catch (EmptyListException | BoundaryViolationException e) {
			e.printStackTrace();
		}
	}
	
	public E remove(Position<E> p) throws InvalidPositionException {
		Nodo<E> n = checkPosition(p);
		E aux = null;
		try {
			if(p == first()) 
				head = n.getNext();
			else
				checkPosition(prev(p)).setNext(n.getNext());
			size--;
			// Pongo los campos del nodo n en null, para detectar en
			// el futuro que p es una posición inválida x como se implemento checkPosition 
			aux = p.element();
			n.setElement(null);
			n.setNext(null);
			
		} catch (EmptyListException | BoundaryViolationException e) {
			e.printStackTrace(); }
		return aux;
	}
	
	public E set(Position<E> p, E element) throws InvalidPositionException {
		Nodo<E> nodo = checkPosition(p); // Ya lanza la excepción
		E removed = nodo.element();
		nodo.setElement(element);
		return removed;
	}
	
	@Override
	public Iterator<E> iterator() {
		return new ElementIterator<E>(this);
	}

	@Override
	public Iterable<Position<E>> positions() { // Devuelve un iterable de posiciones
		PositionList<Position<E>> toReturn = new ListaSE<Position<E>>();
		Nodo<E> actual = head;
		while(actual != null) {
			toReturn.addLast(actual);
			actual = actual.getNext();
		}
		
		return toReturn;
	}
	
	public String toString() {
		Iterator<E> it = iterator(); // Le pido el iterador a la lista this.
		String s = "[";
		while(it.hasNext()) {
			s += it.next();          // Hay un cast implícito de E a String, equivale a: s+=it.next().toString();
			if(it.hasNext())         // Hago append de una coma si quedan elementos.
				s += ", ";
		}
		s += "]";
		return s;
	}

	private Nodo<E> checkPosition(Position<E> p) throws InvalidPositionException {
		try {
			if(p == null)
				throw new InvalidPositionException("La posición es nula.");
			if(p.element() == null)
				throw new InvalidPositionException("La posición fue eliminada previamente.");
			return (Nodo<E>) p;
		} catch(ClassCastException e) {
			throw new InvalidPositionException("La posición es inválida, no es de tipo Nodo E.");
		}
	}
	

	// -------------  Clase privada Nodo<E> -------------
	/**
	 * Clase Nodo anidada, permite evitar modificar la lista mediante casting
	 * explicito desde la clase cliente.
	 * Cliente no debe conocer como funciona la estructura, solamente debe saber
	 * que hace.
	 * @param <E> generico.
	 */
	
	@SuppressWarnings("hiding")
	private class Nodo<E> implements Position<E> { // Lo unico distinto a la otra es que implementa Position y en vez de getElement() es element()
		private E element;
		private Nodo<E> next;

		public Nodo(E element, Nodo<E> next) {
			this.element = element;
			this.next = next;
		}

		public Nodo(E element) {
			this(element, null);
		}

		public E element() {
			return element;
		}

		public void setElement(E element) {
			this.element = element;
		}

		public Nodo<E> getNext() {
			return next;
		}

		public void setNext(Nodo<E> next) {
			this.next = next;
		}
	}
}
