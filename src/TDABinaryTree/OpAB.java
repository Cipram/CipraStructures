package TDABinaryTree;

import TDACola.*;
import Exceptions.*;
import TDALista.*;

public class OpAB {
	
	//EJ 2 C
	public static <E> boolean parientes(BTNode<E> n1, BTNode<E> n2, BinaryTree<E> t) {
		boolean encontre = false;
		int p1 = profundidad(n1,t);
		int p2 = profundidad(n2,t);
		if (p1 > p2)
			encontre = prePariente(n2,n1,t);
		else
			encontre = prePariente(n1,n2,t);
		return encontre;
	}
	
	private static <E> boolean prePariente(BTNode<E> n, BTNode<E> buscado, BinaryTree<E> t) {
		boolean encontre = false;
		try {
			if (n == buscado)
				encontre = true;
			else {
				if (t.hasLeft(n) && !encontre)
					encontre = prePariente((BTNode<E>) t.left(n),buscado,t);
				if (t.hasRight(n) && !encontre)
					encontre = prePariente((BTNode<E>) t.right(n),buscado,t);
			}
		} catch (InvalidPositionException | BoundaryViolationException e) {
			e.printStackTrace();
		}
		return encontre;
	}
	
	//EJ 2 D
	
	public static <E> PositionList<BTNode<E>> parientesCamino(BTNode<E> n1, BTNode<E> n2, ArbolBinarioE<E> t){
		PositionList<BTNode<E>> l = new ListaDE<BTNode<E>>();
		int p1 = profundidad(n1,t);
		int p2 = profundidad(n2,t);
		if (p1 > p2)
			preParientesCam(n2,n1,t,l);
		else
			preParientesCam(n1,n2,t,l);
		return l;
	}
	
	private static <E> boolean preParientesCam(BTNode<E> n, BTNode<E> n1, ArbolBinarioE<E> t,PositionList<BTNode<E>> l) {
		boolean encontre = false;
		try {
			if (n == n1) {
				encontre = true;
			}
			else {
				if (t.hasLeft(n) && !encontre)
					encontre = preParientesCam((BTNode<E>) t.left(n),n1,t,l);
				if (t.hasRight(n) && !encontre)
					encontre = preParientesCam((BTNode<E>) t.right(n),n1,t,l);
			}
			if(encontre)
				l.addLast(n);
		} catch (InvalidPositionException | BoundaryViolationException e) {
			e.printStackTrace();
		}
		return encontre;
	}
	
	//EJ 2 e
	
	public static <E> PositionList<BTNode<E>> profundiadCam(BTNode<E> n, BinaryTree<E> t){
		PositionList<BTNode<E>> l = new ListaDE<BTNode<E>>();
		preProf(n,t,l);
		return l;
	}
	
	private static <E> void preProf(BTNode<E> n, BinaryTree<E> t, PositionList<BTNode<E>> l) {
		try {
			if (!t.isRoot(n)) {
				l.addLast(n);
				preProf((BTNode<E>) t.parent(n), t, l);
			}
		} catch (InvalidPositionException | BoundaryViolationException e) {
			e.printStackTrace();
		} 
	}
	
	// EJ 2 F
	
	public static <E> PositionList<BTNode<E>> alturacam(BTNode<E> n, BinaryTree<E> t){
		PositionList<BTNode<E>> l = new ListaDE<BTNode<E>>();
		preCam(n,t,l);
		return l;
	}
	
	private static <E> int preCam(BTNode<E> n, BinaryTree<E> t, PositionList<BTNode<E>> l) {
		int mayor = 0;
		int aux = 1;
		try {
			if (t.isExternal(n))
				mayor = 1;
			l.addLast(n);
				for (Position<E> h : t.children(n)) {
					aux = preCam((BTNode<E>) h,t,l);
					if (aux > mayor) {
						for (int i = 0; i < mayor;i++) {
							l.remove(l.first());
						}
						mayor = aux;
					}
				}
		} catch (InvalidPositionException | EmptyListException e) {
			e.printStackTrace();
		}
		return mayor;
	}
	
	// EJ 3 a
	
	public static <E> boolean esPropio(BinaryTree<E> t) {
		boolean es = false;
		try {
			es = preProp((BTNode<E>) t.root(),t);
		} catch (EmptyTreeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return es;
	}
	
	private static <E> boolean preProp(BTNode<E> n, BinaryTree<E> t){
		boolean es = true;
		try {
			if (t.hasLeft(n) && t.hasRight(n)) {
				for (Position<E> h : t.children(n)) {
					if (es)
						es = preProp((BTNode<E>) h, t);
				}
			}
			else {
				if (!t.hasLeft(n) && !t.hasRight(n))
					es = true;
				else
					es = false;
			}
		} catch (InvalidPositionException e) {
			e.printStackTrace();
		}
		return es;
	}
	
	// EJ 3 b
	
	public static <E> boolean esSub(BinaryTree<E> A1, BinaryTree<E> A) {
		boolean es = false;
		try {
			es = preBusqueda((BTNode<E>) A1.root(), (BTNode<E>) A.root(), A, A1);
		}
		catch (EmptyTreeException e) {
			
		}
		return es;
	}
	
	private static<E> boolean preBusqueda(BTNode<E> n1, BTNode<E> n, BinaryTree<E> t, BinaryTree<E> t1) {
		boolean es = false;
		try {
			if (n1.element().equals(n.element())) {
				es = preSubArbol(n,n1,t,t1);
			}
			else {
				for (Position<E> h : t.children(n)) {
					if (es == false)
						es = preBusqueda(n1, (BTNode<E>) h, t, t1);
				}
			}
		} catch (InvalidPositionException e) {
			e.printStackTrace();
		}
		return es;
	}
	
	private static<E> boolean preSubArbol(BTNode<E> n, BTNode<E> n1, BinaryTree<E> t, BinaryTree<E> t1) {
		boolean esSub = true;
		try {
			if (!n.element().equals(n1.element())) {
				esSub = false;
			}
			else {
				//Controlando hijos izquierdos de n y n1
				if (t.hasLeft(n) && t1.hasLeft(n1)){
					esSub = preSubArbol((BTNode<E>) t.left(n), (BTNode<E>) t1.left(n1), t, t1);
				}
				else {
					if (!t.hasLeft(n) && !t1.hasLeft(n1))
						esSub = true;
					else
						esSub = false;
				}
				//Controlando hijos derechos de n y n1
				if (esSub) {
					if (t.hasRight(n) && t1.hasRight(n1)){
						esSub = preSubArbol((BTNode<E>) t.right(n), (BTNode<E>) t1.right(n1), t, t1);
					}
					else {
						if (!t.hasRight(n) && !t1.hasRight(n1))
							esSub = true;
						else
							esSub = false;
					}
				}
			}
		}
		catch (InvalidPositionException | BoundaryViolationException e) {
			
		}
		return esSub;
	}
	
	public static <E> boolean EsSubArbol(BinaryTree<E> A1, BinaryTree<E> A){
		boolean es = false;
		PositionList<E> l = new ListaDE<E>();
		PositionList<E> l1 = new ListaDE<E>();
		
		for (Position<E> h : A.positions()) {
			l.addLast(h.element());
		}
		for (Position<E> h : A1.positions()) {
			l1.addLast(h.element());
		}
		
		try {
			Position<E> cursorA = l.first();
			Position<E> cursorA1 = l1.first();
			while (cursorA != l.last() || cursorA1 != l1.last()) {
				if (cursorA.element().equals(cursorA1.element())) {
					es = true;
					if (cursorA1 != l1.last())
						cursorA1 = l1.next(cursorA1);
				}
				else {
					es = false;
					cursorA1 = l1.first();
				}
				if (cursorA != l.last())
					cursorA = l.next(cursorA);
			}
		} catch (EmptyListException | InvalidPositionException | BoundaryViolationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return es;
	}
	
	//EJ 4
	
	public static <E> void mostrarExpresion(BinaryTree<E> t) {
		try {
			System.out.println("Expresion prefija: ");
			for (Position<E> h : t.positions()) {
				System.out.print(h.element());
			}
			System.out.println();
			System.out.println("Expresion sufija: ");
		
			sufija((BTNode<E>) t.root(), t);
			
			System.out.println();
			System.out.println("Expresion infija: ");
			
			infija((BTNode<E> )t.root(),t);
		} catch (EmptyTreeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static <E> void sufija(BTNode<E> n, BinaryTree<E> t) {
		try {
			for (Position<E> h : t.children(n)) {
				sufija((BTNode<E>) h, t);
			}
			System.out.print(n.element());
		} catch (InvalidPositionException e) {
			e.printStackTrace();
		}
	}
	
	private static <E> void infija(BTNode<E> n, BinaryTree<E> t) {
		ColaEnlazada<BTNode<E>> cola = new ColaEnlazada<BTNode<E>>();
		BTNode<E> aux;
		try {
			cola.enqueue((BTNode<E>) t.root());
		} catch (EmptyTreeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while (!cola.isEmpty()) {
				aux = cola.dequeue();
				System.out.print(aux.element());
				for (Position<E> h : t.children(aux)) {
					cola.enqueue((BTNode<E>) h);
				}
			}
		} catch (EmptyQueueException | InvalidPositionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//------------- aux ------------
	private static <E> int profundidad(BTNode<E> n, BinaryTree<E> t){
		int prof = 0;
		try {
			if (t.isRoot(n))
				prof = 0;
			else {
				prof = 1 + profundidad((BTNode<E>) t.parent(n),t);
			}
		} catch (InvalidPositionException | BoundaryViolationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prof;
	}
}
