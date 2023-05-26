package TDAArbol;

import java.util.Iterator;

import Exceptions.BoundaryViolationException;
import Exceptions.EmptyListException;
import Exceptions.EmptyQueueException;
import Exceptions.EmptyStackException;
import Exceptions.InvalidOperationException;
import Exceptions.InvalidPositionException;
import TDACola.ColaEnlazada;
import TDALista.*;
import TDAPila.PilaConEnlaces;

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
		if (!isEmpty())
			pre(raiz,l);
		return l;
	}
	
	private void pre(TNodo<E> n, PositionList<Position<E>> l) {
		l.addLast(n);
		if (!n.getHijos().isEmpty())
			for (TNodo<E> p : n.getHijos()) {
				if (p != null)
					pre(p,l);
			}
	}
	
	
	@Override
	public E replace(Position<E> v, E e) throws InvalidPositionException {
		if (isEmpty())
			throw new InvalidPositionException("el arbol esta vacio");
		TNodo<E> nodo = checkPosition(v);
		TNodo<E> nuevo = new TNodo<E>(e);
		return null;
	}
	

	@Override
	public Position<E> root() throws EmptyTreeException {
		if (isEmpty())
			throw new EmptyTreeException("El arbol esta vacio");
		return raiz;
	}

	@Override
	public Position<E> parent(Position<E> v) throws InvalidPositionException, BoundaryViolationException {
		TNodo<E> ret = checkPosition(v);
		if (isRoot(ret))
			throw new BoundaryViolationException("El nodo raiz no tiene padres");
		return ret.getPadre();
	}

	@Override
	public Iterable<Position<E>> children(Position<E> v) throws InvalidPositionException {
		if (isEmpty())
			throw new InvalidPositionException("El arbol esta vacio");
		TNodo<E> ret = checkPosition(v);
		PositionList<Position<E>> hijos = new ListaDE<Position<E>>();
		for (Position<TNodo<E>> p : ret.getHijos().positions())
			hijos.addLast(p.element());
		return hijos;
	}

	@Override
	public boolean isInternal(Position<E> v) throws InvalidPositionException {
		TNodo<E> nodo = checkPosition(v);
		boolean es = false;
			if (nodo.getPadre() != null) {
				checkFather(nodo);
				es = !nodo.getHijos().isEmpty();
			}
			else {
				if (isRoot(nodo))
					es = !nodo.getHijos().isEmpty();
			}
		return es;
	}

	@Override
	public boolean isExternal(Position<E> v) throws InvalidPositionException {
		TNodo<E> nodo = checkPosition(v);
		boolean es = false;
		if (isRoot(nodo)) {
			es = nodo.getHijos().isEmpty();
		}
		else{
			if (nodo.getHijos().isEmpty()) {
				checkFather(nodo);
				es = true;
			}
		}
		return es;
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
		raiz.setPadre(null);
		size++;
	}

	@Override
	public Position<E> addFirstChild(Position<E> p, E e) throws InvalidPositionException {
		if (isEmpty())
			throw new InvalidPositionException("El arbol esta vacio");
		TNodo<E> n = checkPosition(p);
		TNodo<E> ret = new TNodo<E>(e);
		ret.setPadre(n);
		n.getHijos().addFirst(ret);
		size++;
		return ret;
	}

	@Override
	public Position<E> addLastChild(Position<E> p, E e) throws InvalidPositionException {
		if (isEmpty())
			throw new InvalidPositionException("El arbol esta vacio");
		TNodo<E> n = checkPosition(p);
		TNodo<E> ret = new TNodo<E>(e);
		ret.setPadre(n);
		n.getHijos().addLast(ret);
		size++;
		return ret;
	}

	@Override
	public Position<E> addBefore(Position<E> p, Position<E> rb, E e) throws InvalidPositionException {
		TNodo<E> padre = checkPosition(p);
		TNodo<E> hermano = checkPosition(rb);
		TNodo<E> ret = new TNodo<E>(e);
		if (isEmpty())
			throw new InvalidPositionException("El arbol esta vacio");
		if (hermano.getPadre() != padre)
			throw new InvalidPositionException("Los nodos no son parientes");
		try {
			PositionList<TNodo<E>> hijos = padre.getHijos();
			Position<TNodo<E>> cursor = hijos.first();
			boolean esta = false;
			while (!esta  && cursor != null) {
				if (cursor.element() == hermano) {
					esta = true;
				}
				else {
					if (cursor == hijos.last()) {
						cursor = null;
					}
					else {
						cursor = hijos.next(cursor);
					}
				}
						
			}
			if (!esta)
				throw new InvalidPositionException("La posicion no se encuentra en el arbol");
			ret.setPadre(padre);
			hijos.addBefore(cursor, ret);
		} catch (EmptyListException | BoundaryViolationException e1) {
			System.out.println(e1.getMessage());
		}
		size++;
		return ret;
	}

	@Override
	public Position<E> addAfter(Position<E> p, Position<E> lb, E e) throws InvalidPositionException {
		TNodo<E> padre = checkPosition(p);
		TNodo<E> hermano = checkPosition(lb);
		TNodo<E> ret = new TNodo<E>(e);
		if (isEmpty())
			throw new InvalidPositionException("El arbol esta vacio");
		if (hermano.getPadre() != padre)
			throw new InvalidPositionException("Los nodos no son parientes");
		try {
			PositionList<TNodo<E>> hijos = padre.getHijos();
			Position<TNodo<E>> cursor = hijos.first();
			boolean esta = false;
			while (!esta  && cursor != null) {
				if (cursor.element() == hermano) {
					esta = true;
				}
				else {
					if (cursor == hijos.last()) {
						cursor = null;
					}
					else {
						cursor = hijos.next(cursor);
					}
				}
						
			}
			if (!esta)
				throw new InvalidPositionException("La posicion no se encuentra en el arbol");
			ret.setPadre(padre);
			hijos.addAfter(cursor, ret);
		} catch (EmptyListException | BoundaryViolationException e1) {
			System.out.println(e1.getMessage());
		}
		size++;
		return ret;
	}

	@Override
	public void removeExternalNode(Position<E> p) throws InvalidPositionException {
		if (isEmpty())
			throw new InvalidPositionException("El arbol esta vacio");
		TNodo<E> nodo = checkPosition(p);
		if (!isExternal(nodo))
			throw new InvalidPositionException("La posicion no es una hoja");
		removeNode(nodo);
	}

	@Override
	public void removeInternalNode(Position<E> p) throws InvalidPositionException {
		if (isEmpty())
			throw new InvalidPositionException("El arbol esta vacio");
		TNodo<E> nodo = checkPosition(p);
		if (!isInternal(nodo))
			throw new InvalidPositionException("La posicion es una hoja");
		removeNode(nodo);
	}

	@Override
	public void removeNode(Position<E> p) throws InvalidPositionException {
		if (isEmpty())
			throw new InvalidPositionException("no se ... ");
		try{
			TNodo<E> n = checkPosition(p);
			if (isRoot(n)){
				if ( n.getHijos().size() == 1 ){
					Position<TNodo<E>> nuevaRaiz = n.getHijos().first();
					raiz = nuevaRaiz.element();
					raiz.setPadre(null);
					size--;
				}
				else{
					if  (size == 1 ){
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
			throw new InvalidPositionException("Posicion invalida");
		}		
	}
	
	private boolean checkFather(Position<E> h) throws InvalidPositionException{
		TNodo<E> nodo = checkPosition(h);
		TNodo<E> padre = nodo.getPadre();
		TNodo<E> cursor;
		PositionList<TNodo<E>> hijos = padre.getHijos();
		boolean esta = false;
		Iterator<TNodo<E>> it = hijos.iterator();
		while (it.hasNext() && !esta) {
			cursor = it.next();
			if (cursor == nodo) {
				esta = true;
			}
		}
		if (!esta)
			throw new InvalidPositionException("El nodo no es hijo de su padre");
		return esta;
	}
	
	private TNodo<E> checkPosition(Position<E> v) throws InvalidPositionException {
		try {
			if(v == null)
				throw new InvalidPositionException("La posición es nula.");
			if(v.element() == null)
				throw new InvalidPositionException("La posición fue eliminada previamente.");
			return (TNodo<E>) v;
		} catch(ClassCastException e) {
			throw new InvalidPositionException("La posición es inválida, no es de tipo Nodo E.");
		}
	}
	
	//------- EJERCICIOS TP 6 ------
	
	// - ej4 
	// b
	
	public PositionList<TNodo<E>> elementosPos(){
		PositionList<TNodo<E>> l = new ListaDE<TNodo<E>>();
		pos(raiz,l);
		return l;
	}
	
	private void pos(TNodo<E> n, PositionList<TNodo<E>> l) {
		if (!n.getHijos().isEmpty()) {
			for (Position<TNodo<E>> p : n.getHijos().positions()) {
				pos(p.element(),l);
			}
		}
		l.addLast(n);
	}
	
	//d
	
	public ArbolGen<E> clone(){
		ArbolGen<E> t = new ArbolGen<E>();
		try {
			t.createRoot(raiz.element());
			preC(raiz, t.root(), t);
		} catch (InvalidOperationException | EmptyTreeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return t;
	}
	
	private void preC(TNodo<E> nodoOrigin, Position<E> padreClone, ArbolGen<E> TClone) {
		TNodo<E> aux;
		try {
			for (Position<TNodo<E>> hijo : nodoOrigin.getHijos().positions()) {
				aux = (TNodo<E>) TClone.addLastChild(padreClone, hijo.element().element());
				preC(hijo.element(),aux,TClone);
			}
		} catch (InvalidPositionException e) {
			e.printStackTrace();
		}
	}
	
	public ArbolGen<E> cloneC() {
		ColaEnlazada<TNodo<E>> cola = new ColaEnlazada<TNodo<E>>();
		ArbolGen<E> t = new ArbolGen<E>();
		cola.enqueue(raiz);
		TNodo<E> aux;
		try {
			while (!cola.isEmpty()) {
				aux = cola.dequeue();
				if (t.isEmpty())
					t.createRoot(aux.element());
				else {
					if (t.size() == 1)
						t.addLastChild(t.root(), aux.element());
					else
						t.addLastChild(aux.getPadre(), aux.element());
				}
				if (!aux.getHijos().isEmpty()) {
					for (Position<TNodo<E>> p : aux.getHijos().positions()) {
						cola.enqueue(p.element());
					}
				}
			}
		} catch (EmptyQueueException | InvalidOperationException | InvalidPositionException | EmptyTreeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return t;
	}
	
	// - ej5
	//a
	
	public void mostrarPorNiveles() {
		ColaEnlazada<TNodo<E>> cola = new ColaEnlazada<TNodo<E>>();
		cola.enqueue(raiz);
		TNodo<E> padre = raiz;
		TNodo<E> hijo;
		TNodo<E> aux;
		int prof = 0;
		try {
			while (!cola.isEmpty()) {
				aux = cola.dequeue();
				hijo = aux;
				/*if (hijo.getPadre() == padre) {
					padre = hijo;
					System.out.println();
				}*/
				if (profundidad(aux) != prof) {
					prof = profundidad(aux);
					System.out.println();
				}
				System.out.print(aux.element().toString());
				if (!aux.getHijos().isEmpty()) {
					for (Position<TNodo<E>> p : aux.getHijos().positions()) {
						cola.enqueue(p.element());
					}
				}
			}
		} catch (EmptyQueueException e) {
			e.printStackTrace();
		}
	}
	
	// b
	
	public void mostrarPorNivelesInv() {
		PilaConEnlaces<TNodo<E>> pila = new PilaConEnlaces<TNodo<E>>();
		ColaEnlazada<TNodo<E>> cola = new ColaEnlazada<TNodo<E>>();
		cola.enqueue(raiz);
		int prof = 0;
		TNodo<E> aux;
		try {
			while (!cola.isEmpty()) {
				aux = cola.dequeue();
				pila.push(aux);
				if (!aux.getHijos().isEmpty()) {
					for (Position<TNodo<E>> p : aux.getHijos().positions()) {
						cola.enqueue(p.element());
					}
				}
			}
			while (!pila.isEmpty()) {
				System.out.println(pila.pop().element());
			}
		} catch (EmptyQueueException | EmptyStackException e) {
			e.printStackTrace();
		}
	}
	
	public int profundidad(Position<E> v ) {
		int ret = 0;
		try {
			if (isRoot(v) )
				ret = 0;
			else
				ret = 1 + profundidad(parent( v ) );
		} catch (InvalidPositionException | BoundaryViolationException e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	// ej 6
	
	public void removeElement(E e) {
		preR(raiz, e);
	}
	
	private void preR(TNodo<E> nodo, E e) {
		try {
			if (nodo.element().equals(e)) {
				removeNode(nodo);
			}
			for (TNodo<E> hijo : nodo.getHijos()) {
				preR(hijo,e);
			}
		} catch (InvalidPositionException e1) {
			e1.printStackTrace();
		}
		
	}
	
}
