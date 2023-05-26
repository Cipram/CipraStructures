package TDAArbol;

import Exceptions.EmptyListException;
import Exceptions.InvalidPositionException;
import TDALista.ListaDE;
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
	
	// c
	
	
}
