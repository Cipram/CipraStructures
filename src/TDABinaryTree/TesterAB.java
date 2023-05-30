package TDABinaryTree;

import Exceptions.InvalidOperationException;
import Exceptions.InvalidPositionException;
import TDALista.PositionList;

public class TesterAB {

	public static void main(String[] args) {
		
		ArbolBinarioE<Integer> t = new ArbolBinarioE<Integer>();
		
		BTNode<Integer> n0;
		try {
			n0 = (BTNode<Integer>) t.createRoot(0);
		
			BTNode<Integer> n1 = (BTNode<Integer>) t.addLeft(n0,1);
			BTNode<Integer> n2 = (BTNode<Integer>) t.addRight(n0,2);
			
			BTNode<Integer> n3 = (BTNode<Integer>) t.addLeft(n1,3);
			BTNode<Integer> n4 = (BTNode<Integer>) t.addRight(n1,4);
			
			BTNode<Integer> n5 = (BTNode<Integer>) t.addLeft(n2,5);
			
			BTNode<Integer> n6 = (BTNode<Integer>) t.addLeft(n3,6);
			
			BTNode<Integer> n7 = (BTNode<Integer>) t.addLeft(n5,7);
			BTNode<Integer> n8 = (BTNode<Integer>) t.addRight(n5,8);		
		
			System.out.println();
			System.out.println("............ ej 2 / c .............");
			System.out.println();
			
			System.out.println(OpAB.parientes(n0, n3, t));
			
			System.out.println();
			System.out.println("............ ej 2 / b .............");
			System.out.println();
			
			PositionList<BTNode<Integer>> l = OpAB.parientesCamino(n0, n6, t);
			
			for (BTNode<Integer> h : l) {
				System.out.println(h.element());
			}
			
			System.out.println();
			System.out.println("............ ej 2 / e .............");
			System.out.println();
			
			PositionList<BTNode<Integer>> l1 = OpAB.profundiadCam(n8,t);
			
			for (BTNode<Integer> h : l1) {
				System.out.println(h.element());
			}
			
			System.out.println();
			System.out.println("............ ej 2 / f .............");
			System.out.println();
			
			
			
		} catch (InvalidOperationException | InvalidPositionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
