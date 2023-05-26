package TDAArbol;

import Exceptions.InvalidOperationException;
import Exceptions.InvalidPositionException;
import TDALista.Position;
import TDALista.PositionList;

public class Tester {
	public static void main(String args[]) {
		ArbolGen<Integer> t1 = new ArbolGen<Integer>();
		try {
			t1.createRoot(0);
			for (int i = 1; i < 4; i++) {
				t1.addLastChild(t1.root(), i);
			}
			int a = 3;
			for (int i = 0; i < 2;i++) {
				for (Position<Integer> p : t1.children(t1.root())) {
					a += 1;
					t1.addLastChild(p, a);
				}
			}
		
		
			System.out.println();
			System.out.println("--------");
			System.out.println();
			
			//t1.mostrarPorNivelesInv();
			ArbolGen<Integer> t2 = t1.clone();
			t2.mostrarPorNiveles();	
			
			System.out.println();
			System.out.println("--------");
			System.out.println();
			
			PositionList<TNodo<Integer>> l = OpArbol.hijosIzq(t1);
			
			for (TNodo<Integer> p : l) {
				System.out.println(p.element());
			}
			
			System.out.println();
			System.out.println("--------");
			System.out.println();
			
			//OpArbol.removeHijosIzq(t1);
			
			//t1.mostrarPorNiveles();
			
			System.out.println();
			System.out.println("--------");
			System.out.println();
			
			t1.removeElement(2);
			
			t1.mostrarPorNiveles();
			
		} catch (InvalidPositionException | InvalidOperationException | EmptyTreeException e) {
			System.out.println(e.getMessage());
		}
	}
}
