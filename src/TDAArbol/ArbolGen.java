package TDAArbol;

import java.util.Iterator;

import Exceptions.BoundaryViolationException;
import Exceptions.EmptyListException;
import Exceptions.InvalidOperationException;
import Exceptions.InvalidPositionException;
import TDALista.*;

public class ArbolGen<E> implements Tree<E> {

	protected TNodo<E> raiz;
	protected int size;
	
	public ArbolGen() {
		raiz = null;
		size = 0;
	}
	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public Iterator<E> iterator() {
		PositionList<E> l = new ListaDE<E>();
		for (Position<E> p : positions()) {
			l.addLast(p.element());
		}
		return l.iterator();
	}

	@Override
	public Iterable<Position<E>> positions() {
		PositionList<Position<E>> l = new ListaDE<Position<E>>();
		pre(raiz,l);
		return l;
	}
	
	private void pre(TNodo<E> n, PositionList<Position<E>> l) {
		l.addLast(n);
		for (TNodo<E> p : n.getHijos()) {
			pre(p,l);
		}
	}
	
	
	@Override
	public E replace(Position<E> v, E e) throws InvalidPositionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Position<E> root() throws EmptyTreeException {
		if (isEmpty())
			throw new EmptyTreeException("El arbol esta vacio");
		return null;
	}

	@Override
	public Position<E> parent(Position<E> v) throws InvalidPositionException, BoundaryViolationException {
		TNodo<E> ret = checkPosition(v);
		if (v == raiz)
			throw new BoundaryViolationException("");
		return ret.getPadre();
	}

	@Override
	public Iterable<Position<E>> children(Position<E> v) throws InvalidPositionException {
		TNodo<E> ret = checkPosition(v);
		PositionList<Position<E>> aux = new ListaDE<Position<E>>();
		for (Position<TNodo<E>> p : ret.getHijos().positions())
			aux.addLast(p.element());
		return aux;
	}

	@Override
	public boolean isInternal(Position<E> v) throws InvalidPositionException {
		TNodo<E> nodo = checkPosition( v );
		return !nodo.getHijos().isEmpty();
	}

	@Override
	public boolean isExternal(Position<E> v) throws InvalidPositionException {
		TNodo<E> nodo = checkPosition( v );
		return nodo.getHijos().isEmpty();
	}

	@Override
	public boolean isRoot(Position<E> v) throws InvalidPositionException {
		TNodo<E> ret = checkPosition(v);
		return ret == raiz;
	}

	@Override
	public void createRoot(E e) throws InvalidOperationException {
		if (raiz != null)
			throw new InvalidOperationException("El arbol ya tiene raiz");
		raiz = new TNodo<E>(e);
	}

	@Override
	public Position<E> addFirstChild(Position<E> p, E e) throws InvalidPositionException {
		TNodo<E> n = checkPosition(p);
		TNodo<E> ret = new TNodo<E>(e);
		n.getHijos().addFirst(ret);
		return ret;
	}

	@Override
	public Position<E> addLastChild(Position<E> p, E e) throws InvalidPositionException {
		TNodo<E> n = checkPosition(p);
		TNodo<E> ret = new TNodo<E>(e);
		n.getHijos().addLast(ret);
		return ret;
	}

	@Override
	public Position<E> addBefore(Position<E> p, Position<E> rb, E e) throws InvalidPositionException {
		TNodo<E> padre = checkPosition(p);
		TNodo<E> hermano = checkPosition(p);
		TNodo<E> ret = new TNodo<E>(e);
		padre.getHijos().addBefore(new TNodo<TNodo<E>>( hermano ), ret);
		return ret;
	}

	@Override
	public Position<E> addAfter(Position<E> p, Position<E> lb, E e) throws InvalidPositionException {
		TNodo<E> padre = checkPosition(p);
		TNodo<E> hermano = checkPosition(p);
		TNodo<E> ret = new TNodo<E>(e);
		padre.getHijos().addAfter(new TNodo<TNodo<E>>( hermano ), ret);
		return ret;
	}

	@Override
	public void removeExternalNode(Position<E> p) throws InvalidPositionException {
		TNodo<E> nodo = checkPosition(p);
		if (isInternal(nodo))
			throw new InvalidPositionException("La posicion no es una hoja");
		removeNode(nodo);
	}

	@Override
	public void removeInternalNode(Position<E> p) throws InvalidPositionException {
		TNodo<E> nodo = checkPosition(p);
		if (isExternal(nodo))
			throw new InvalidPositionException("La posicion es una hoja");
		removeNode(nodo);
	}

	@Override
	public void removeNode(Position<E> p) throws InvalidPositionException {
		if ( size == 0)
			throw new InvalidPositionException("no se ... ");
		try{
			TNodo<E> n = checkPosition(p);
			if ( n == raiz ){
				if ( n.getHijos().size() == 1 ){
					Position<TNodo<E>> nuevaRaiz = n.getHijos().first();
					raiz = nuevaRaiz.element();
					raiz.setPadre(null);
					size--;
				}
				else{
					if  ( size == 1 ){
						raiz = null;
						size--;
					}
					else{
						throw new InvalidPositionException("No se puede eliminar ...");
					}
				}
			}
			else{
				TNodo<E> padre = n.getPadre();
				PositionList<TNodo<E>> hijosN = n.getHijos();
				PositionList<TNodo<E>> hijosPadre = padre.getHijos();
				Position<TNodo<E>> primero = hijosPadre.first(); 
				
				while( primero.element() != n ) //se busca la posicion de 
					primero = hijosPadre.next(primero);
				
				while ( !hijosN.isEmpty() ){
					Position<TNodo<E>> hijoInsertar = hijosN.first();
					hijosPadre.addBefore(primero, hijoInsertar.element()); 	//se mete los hijos de n como hijos de padre
					hijoInsertar.element().setPadre(padre);					//se cambia el padre de los hijos de n 
					hijosN.remove(hijoInsertar);  							// se sca los hijos que quedaron como hijos de n
				}
				hijosPadre.remove(primero); 								//  se eliminar definitivamente el n
				size--;
			}
		} catch (EmptyListException | BoundaryViolationException e){ //
			throw new InvalidPositionException("");
		}		
	}
	
	private TNodo<E> checkPosition(Position<E> v) throws InvalidPositionException {
		if (v == null)
			throw new InvalidPositionException("La posicion se nula");
		return (TNodo<E>) v;
	}

}
