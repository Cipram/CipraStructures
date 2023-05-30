package TDABinaryTree;

import Exceptions.BoundaryViolationException;
import Exceptions.InvalidPositionException;
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
	
	public static <E> PositionList<E> alturacam(BTNode<E> n, BinaryTree<E> t){
		
	}
	
	private static <E> int preCam(BTNode<E> n, BinaryTree<E> t) {
		int mayor = 0;
		int aux = 1;
		try {
			if (t.isExternal(n))
				mayor = 1;
			else {
				for (Position<E> h : t.children(n)) {
					aux = preCam(h,t);
					if (aux > )
				}
			}
		} catch (InvalidPositionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mayor;
	}
	
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
