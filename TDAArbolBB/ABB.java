package TDAArbolBB;

import java.util.Comparator;

import Exceptions.EmptyQueueException;
import Exceptions.EmptyStackException;
import Exceptions.InvalidKeyException;
import TDACola.ColaEnlazada;
import TDAPila.PilaConEnlaces;

public class ABB<E extends Comparable<E>> {
	protected NodoABB<E> raiz;
	protected Comparator<E> comp;
	
	public ABB(Comparator<E> comp) {
		raiz = new NodoABB<E>(null,null);
		this.comp = comp;
	}
	
	public NodoABB<E> buscar(E x){
		return buscarAux(x,raiz);
	}
	
	private NodoABB<E> buscarAux(E x, NodoABB<E> p){
		int c;
		NodoABB<E> ret = null;
		if (p.getRotulo() == null)
			ret = p;
		else {
			c = comp.compare(x, p.getRotulo());
			if (c == 0)
				ret = p;
			else 
				if (c < 0) 
					ret = buscarAux(x,p.getLeft());
				else 
					ret = buscarAux(x,p.getRight());
		}
		return ret;
	}
	
	public void expandir(NodoABB<E> p) {
		p.setLeft(new NodoABB<E>(null,p));
		p.setRight(new NodoABB<E>(null,p));
	}
	
	public E remove(E rot) throws InvalidKeyException {
		//checkRot(rot);
		NodoABB<E> n = buscar(rot);
		E eliminado = null;
		if (n.getRotulo()!= null) {
			eliminado =  n.getRotulo();
			eliminar(n);
		}
		return eliminado;
	}

	private void eliminar(NodoABB<E> n) {
		if (n == raiz) {
			if (soloTieneHijoIzq(n)) {
				raiz = n.getLeft();
				raiz.setPadre(null);
			}
			else {
				if (soloTieneHijoIzq(n)) {
					raiz = n.getRight();
					raiz.setPadre(null);
				}
				else {
					NodoABB<E> min = encontrarMin(n.getRight());
					n.setRotulo(min.getRotulo());
					eliminar(min);
				}
			}
		}
		else
			if (isExternal(n)){
				n.setRotulo(null);
				n.setLeft(null);
				n.setRight(null);
			}
			else {
				if (soloTieneHijoIzq(n)) { // el nodo solo tiene un hijo izquierdo
					if (n.getPadre().getLeft() == n)
						n.getPadre().setLeft(n.getLeft());
					else
						n.getPadre().setRight(n.getLeft());
					n.getLeft().setPadre(n.getPadre());
				}
				else
					if (soloTieneHijoDer(n)) { //el nodo solo tiene un hijo derecho
						if (n.getPadre().getLeft()==n)
							n.getPadre().setLeft(n.getRight());
						else
							n.getPadre().setRight(n.getRight());
						n.getRight().setPadre(n.getPadre());
					}
					else { //el nodo tiene dos hijos
						NodoABB<E> min = encontrarMin(n.getRight());
						n.setRotulo(min.getRotulo());
						eliminar(min);
					}
			}
	}

	private NodoABB<E> encontrarMin(NodoABB<E> n){
		NodoABB<E> min = n;
		while (!isExternal(min) || !soloTieneHijoDer(min)) {
			min = n.getLeft();
		}
		return min;
	}
	private boolean isExternal(NodoABB<E> p) {
		return p.getLeft().getRotulo() == null && p.getRight().getRotulo() == null;
	}
	
	private boolean soloTieneHijoIzq(NodoABB<E> p) {
		return p.getLeft() != null && p.getRight().getRotulo() == null;
	}
	
	private boolean soloTieneHijoDer(NodoABB<E> p) {
		return p.getRight() != null && p.getLeft().getRotulo() == null;
	}
	
	public NodoABB<E> getRaiz(){
		return raiz;
	}
	
	public void mostrarPorNiveles() {
		ColaEnlazada<NodoABB<E>> cola = new ColaEnlazada<NodoABB<E>>();
		cola.enqueue((NodoABB<E>) raiz);
		
		NodoABB<E> aux;
		try {
			while (!cola.isEmpty()) {
				aux = cola.dequeue();
				if (aux.getRotulo() != null) {
					if (aux.getPadre() != null)
						System.out.print(" " + aux.getRotulo().toString() + "[" + aux.getPadre().getRotulo().toString() + "]" + " ");
					else
						System.out.print(" " + aux.getRotulo().toString() + "[-]" + " ");
				}
				if (aux.getLeft() != null) {
					cola.enqueue((NodoABB<E>) aux.getLeft());
				}
				if (aux.getRight() != null) {
					cola.enqueue((NodoABB<E>) aux.getRight());
				}
			}
			System.out.println();
		} catch (EmptyQueueException e) {
			e.printStackTrace();
		}
	}
	
	public ABB<E> interseccion(ABB<E> B){
		ABB<E> C = new ABB<E>(new MiComparator());
		preBuscar(raiz, B, C);
		return C;
	}
	
	private void preBuscar(NodoABB<E> n, ABB<E> t, ABB<E> c) {
		if (n != null && n.getRotulo() != null && t.buscar(n.getRotulo()).getRotulo() == null) {
			NodoABB<E> aux = c.buscar(n.getRotulo());
			aux.setRotulo(n.getRotulo());
			c.expandir(aux);
		}
		if (n.getLeft() != null) {
			preBuscar(n.getLeft(), t, c);
		}
		if (n.getRight() != null) {
			preBuscar(n.getRight(), t, c);
		}
	}
	
	public NodoABB<E> ejercicio14(E rot){
		NodoABB<E> n = buscar(rot);
		ColaEnlazada<NodoABB<E>> Cola = new ColaEnlazada<NodoABB<E>>();
		inBuscar(raiz, n, Cola);
		NodoABB<E> aux = null;
		try {
			while (!Cola.isEmpty() && n != Cola.front()) {
				aux = Cola.dequeue();
			}
		} catch(EmptyQueueException e) {
			
		}
		return aux;
	}

	private void inBuscar(NodoABB<E> n, NodoABB<E> n1, ColaEnlazada<NodoABB<E>> p){
		if (n.getRotulo() != null && n.getLeft().getRotulo() != null) {
			inBuscar(n.getLeft(), n1, p);
		}
		p.enqueue(n);
		if (n.getRotulo() != null && n.getRight().getRotulo() != null) {
			inBuscar(n.getRight(), n1, p);
		}
	}
	
	public void mostrarInOr() {
		mostrarInOrden(raiz);
	}
	
	private void mostrarInOrden(NodoABB<E> n){
		if (n.getRotulo() != null && n.getLeft().getRotulo() != null) {
			mostrarInOrden(n.getLeft());
		}
		System.out.print(n.getRotulo() + "-");
		if (n.getRotulo() != null && n.getRight().getRotulo() != null) {
			mostrarInOrden(n.getRight());
		}
	}
}
