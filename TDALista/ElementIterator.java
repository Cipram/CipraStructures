package TDALista;

import java.util.*;

import Exceptions.*;

/**
 * @class Clase ElementIterator
 * @author Thiago Trotta
 * @author Cipriano Martin
 * @param <E> Tipo de dato de los elementos.
 */
public class ElementIterator<E> implements Iterator<E> {
	protected PositionList<E> list; 
	protected Position<E> cursor; 
	
	/**
	 * Crea un objeto de tipo ElementIterator.
	 * @param l Lista que guarda el ElementIterator.
	 */
	public ElementIterator(PositionList<E> l) {
		list = l; 
		if(list.isEmpty()) 
			cursor = null; 
		else
			try {
				cursor = list.first(); 
			} catch (EmptyListException e) { 
				e.printStackTrace(); 
			}
	}
	
	public boolean hasNext() {
		return cursor != null; 
	}

	public E next() throws NoSuchElementException {
		if (cursor == null) 
			throw new NoSuchElementException("No hay un valor siguiente.");
		E elem = cursor.element(); 
		try {
			cursor = (cursor == list.last()) ? null : list.next(cursor);
		} catch (EmptyListException e) {
			e.printStackTrace();
		} catch (InvalidPositionException e) {
			e.printStackTrace();
		} catch (BoundaryViolationException e) {
			e.printStackTrace();
		}
		return elem; 
	}
}
