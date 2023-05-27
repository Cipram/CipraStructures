package TDAArbol;

import Exceptions.BoundaryViolationException;
import Exceptions.EmptyListException;
import Exceptions.InvalidPositionException;
import TDALista.ListaDE;
import TDALista.Position;
import TDALista.PositionList;

public class OpArbol {

	
	// Ej 6
	
	// a
	
	public static <E> PositionList<TNodo<E>> hijosIzq(ArbolGen<E> t){
		PositionList<TNodo<E>> l = new ListaDE<TNodo<E>>();
		try {
			preIzq((TNodo<E>) t.root(),l, t);
		} catch (EmptyTreeException e) {
			e.printStackTrace();
		}
		return l;
	}
	
	private static <E> void preIzq(TNodo<E> n, PositionList<TNodo<E>> l, ArbolGen<E> t) {
		try {
			if (t.isInternal(n) && !t.isRoot(n)) {
				if (n.getPadre().getHijos().first().element() == n)
					l.addLast(n);
			}
			for (TNodo<E> hijo : n.getHijos()) {
				preIzq(hijo,l,t);
			}
		} catch (InvalidPositionException | EmptyListException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	// b
	
	public static <E> void removeHijosIzq(ArbolGen<E> t) {
		try {
			preDelIzq((TNodo<E>) t.root(), t);
		} catch (EmptyTreeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static <E> void preDelIzq(TNodo<E> n,ArbolGen<E> t) {
		try {
			if (t.isInternal(n) && !t.isRoot(n)) {
				if (n.getPadre().getHijos().first().element() == n)
					t.removeInternalNode(n);
			}
			for (TNodo<E> hijo : n.getHijos()) {
				preDelIzq(hijo,t);
			}
		} catch (InvalidPositionException | EmptyListException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	// e
	
	public static <E> boolean sonParientes(TNodo<E> n1, TNodo<E> n2, ArbolGen<E> t) {
		int profN1 = Profundidad(n1, t);
		int profN2 = Profundidad(n2, t);
		boolean hayCamino = false;
		if (profN1 < profN2)
			hayCamino = preParientes(n1,n2,t);
		else
			if (profN1 > profN2)
				hayCamino = preParientes(n2,n1,t);
		return hayCamino;
	}
	
	private static <E> boolean preParientes(TNodo<E> pariente, TNodo<E> buscado, ArbolGen<E> t){
		boolean encontre = false;
		if (pariente == buscado) {
			encontre = true;
		}
		else {
			for (TNodo<E> hijo : pariente.getHijos()) {
				if (!encontre)
					encontre = preParientes(hijo,buscado,t);
			}
		}
		return encontre;
	}
	
	// f
	
	public static <E> PositionList<E> caminoEntre(TNodo<E> n1, TNodo<E> n2, ArbolGen<E> t){
		PositionList<E> l = new ListaDE<E>();
		int profN1 = Profundidad(n1, t);
		int profN2 = Profundidad(n2, t);
		if (profN1 < profN2)
			preParientesCamino(n1,n2,t,l);
		else
			if (profN1 > profN2)
				preParientesCamino(n2,n1,t,l);
		return l;
	}
	
	private static <E> boolean preParientesCamino(TNodo<E> pariente, TNodo<E> buscado, ArbolGen<E> t, PositionList<E> l){
		boolean encontre = false;
		if (pariente == buscado) {
			encontre = true;
		}	
		else {
			for (TNodo<E> hijo : pariente.getHijos()) {
				if (!encontre) {
					encontre = preParientesCamino(hijo,buscado,t,l);
				}
			}
		}
		if(encontre)
			l.addFirst(pariente.element());
		return encontre;
	}
	
	// g
	
	// h
	
	public static <E> PositionList<E> altura(E r, ArbolGen<E> t) {
		PositionList<E> camino = new ListaDE<E>();
		TNodo<E> hojaN;
		try {
			hojaN = preB((TNodo<E>) t.root(), r, t);
			if (hojaN == null)
				System.out.println("el nodo no esta en el arbol");
			else
				preR(hojaN,t,camino);
		} catch (EmptyTreeException e) {
			e.printStackTrace();
		}
		return camino;
	}
	
	public static <E> TNodo<E> preB(TNodo<E> n, E r, ArbolGen<E> t){
		TNodo<E> ret = null;
		if (n.element().equals(r)) {
			ret = n;
		}
		else {
			for (TNodo<E> hijo : n.getHijos()) {
				if (ret == null)
					ret = preB(hijo,r,t);
			}
		}
		return ret;
	}
	
	
	private static <E> int preR(TNodo<E> R, ArbolGen<E> t, PositionList<E> l){
		int mayor = 0;
		int aux = 1;
		try {
			for (TNodo<E> hijos : R.getHijos()) {
				aux += preR(hijos,t,l);
				if (aux > mayor) {
					for (int i = 0; i < mayor; i++) {
						l.remove(l.first());
					}
					mayor = aux;
				}
				else {
					for (int i = 0; i < aux; i++) {
						l.remove(l.last());
					}
				}
				aux = 1;
			}
			l.addLast(R.element());
		} catch (InvalidPositionException | EmptyListException e) {
			e.printStackTrace();
		}
		return mayor;
	}
	
	private static <E> int Profundidad(Position<E> v, ArbolGen<E> t) {
		int ret = 0;
		try {
			if (t.isRoot(v))
				ret = 0;
			else
				ret = 1 + Profundidad(t.parent(v), t );
		} catch (InvalidPositionException | BoundaryViolationException e) {
			e.printStackTrace();
		}
		return ret;
	}
}
