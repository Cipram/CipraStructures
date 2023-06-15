package TDABinaryTree;

import Exceptions.EmptyTreeException;
import Exceptions.InvalidOperationException;
import Exceptions.InvalidPositionException;
import Interfaces.Position;

public class Tester {
	
	public static void main (String arg[]) {
		ArbolBinarioE<Integer> t = new ArbolBinarioE<Integer>();
		try {
			t.createRoot(0);
			BTNode<Integer> n1 = (BTNode<Integer>) t.addLeft(t.root(), 1);
			BTNode<Integer> n2 = (BTNode<Integer>) t.addRight(t.root(), 2);
			
			BTNode<Integer> n3 = (BTNode<Integer>) t.addLeft(n1, 3);
			BTNode<Integer> n4 = (BTNode<Integer>) t.addRight(n1, 4);
			
			BTNode<Integer> n5 = (BTNode<Integer>) t.addLeft(n2, 5);
			BTNode<Integer> n6 = (BTNode<Integer>) t.addRight(n2, 6);
			
			BTNode<Integer> n7 = (BTNode<Integer>) t.addLeft(n3, 7);
			BTNode<Integer> n8 = (BTNode<Integer>) t.addRight(n6, 8);
			
		} catch (InvalidOperationException | InvalidPositionException | EmptyTreeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("------------ °/------/° ------------");
		
		System.out.println();
		t.mostrarPorNiveles();
		System.out.println();
		
		System.out.println("------------ ej 2 b ------------");
		
		OpArbolB.espejo(t);
		
		System.out.println();
		t.mostrarPorNiveles();
		System.out.println();
	}
}
