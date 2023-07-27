package TDAArbol;

import Exceptions.*;
import Interfaces.PositionList;

public class Tester {
	public static void main(String args[]) {
		ArbolGen<Integer> t1 = new ArbolGen<Integer>();
		try {
			t1.createRoot(0);
			
			TNodo<Integer> n1 = (TNodo<Integer>) t1.addLastChild(t1.root(), 1);
			
			TNodo<Integer> n2 = (TNodo<Integer>) t1.addLastChild(t1.root(), 2);
			
			TNodo<Integer> n3 = (TNodo<Integer>) t1.addLastChild(t1.root(), 3);
			
			TNodo<Integer> n4 = (TNodo<Integer>) t1.addLastChild(n1, 4);
			TNodo<Integer> n5 = (TNodo<Integer>) t1.addLastChild(n1, 5);
			
			TNodo<Integer> n6 = (TNodo<Integer>) t1.addLastChild(n2, 6);
			TNodo<Integer> n7 = (TNodo<Integer>) t1.addLastChild(n2, 7);
			
			TNodo<Integer> n8 = (TNodo<Integer>) t1.addLastChild(n3, 8);
			TNodo<Integer> n9 = (TNodo<Integer>) t1.addLastChild(n3, 9);
			
			TNodo<Integer> n10 = (TNodo<Integer>) t1.addLastChild(n4, 10);
			
			TNodo<Integer> n11 = (TNodo<Integer>) t1.addLastChild(n4, 11);
			
			//TNodo<Integer> n12 = (TNodo<Integer>) t1.addLastChild(n8, 12);
			
			//TNodo<Integer> n13 = (TNodo<Integer>) t1.addLastChild(n11, 13);
			
			System.out.println();
			System.out.println("----[ej4 - d]----");
			System.out.println();
			
			//t1.mostrarPorNivelesInv();
			ArbolGen<Integer> t2 = t1.clone();
			t2.mostrarPorNiveles();	
			
			System.out.println();
			System.out.println("---[ej 6 - a]-----");
			System.out.println();
			
			PositionList<TNodo<Integer>> l = OpArbol.hijosIzq(t1);
			
			for (TNodo<Integer> p : l) {
				System.out.println(p.element());
			}
			
			System.out.println();
			System.out.println("---[ej 6 - b]-----");
			System.out.println();
			
			//OpArbol.removeHijosIzq(t1);
			
			//t1.mostrarPorNiveles();
			
			System.out.println();
			System.out.println("---[ej 6 - d]-----");
			System.out.println();
			
			//t1.removeElement(2);
			
			//t1.mostrarPorNiveles();
			
			System.out.println();
			System.out.println("---[ej 6 - e]-----");
			System.out.println();
			
			System.out.println(OpArbol.sonParientes((TNodo<Integer>)t1.root(), n8, t1));
			
			System.out.println();
			System.out.println("---[ej 6 - f]-----");
			System.out.println();
			
			PositionList<Integer> l2 = OpArbol.caminoEntre((TNodo<Integer>)t1.root(), n8, t1);
			
			for (Integer p : l2) {
				System.out.println(p);
			}
			
			System.out.println();
			System.out.println("---[ej 6 - g]-----");
			System.out.println();
			
			PositionList<Integer> l3 = OpArbol.altura(0, t1);
			
			for (Integer e : l3) {
				System.out.println(e);
			}
			
			System.out.println();
			System.out.println("---[ej 7]-----");
			System.out.println();
			
			//t1.cambioRotulo(15, 1);
			
			//t1.mostrarPorNiveles();
			
			System.out.println();
			System.out.println("---[ej 8]-----");
			System.out.println();
			
			t1.rotarhijos(0);
			
			t1.mostrarPorNiveles();
			
			System.out.println();
			System.out.println("---[ej 9]-----");
			System.out.println();
			
			System.out.println(OpArbol.ancestroComun(n8, n9, t1).element());
			
		} catch (InvalidPositionException | InvalidOperationException | EmptyTreeException e) {
			System.out.println(e.getMessage());
		}
	}
}
