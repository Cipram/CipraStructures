package TDABinaryTree;

import Exceptions.InvalidOperationException;
import Exceptions.InvalidPositionException;
import Interfaces.PositionList;

public class TesterAB {

	public static void main(String[] args) {
		
		ArbolBinarioE<Integer> t = new ArbolBinarioE<Integer>();
		
		BTNode<Integer> n0;
		try {
			
			//Arbol 1
			n0 = (BTNode<Integer>) t.createRoot(0);
		
			BTNode<Integer> n1 = (BTNode<Integer>) t.addLeft(n0,1);
			BTNode<Integer> n2 = (BTNode<Integer>) t.addRight(n0,2);
			
			BTNode<Integer> n3 = (BTNode<Integer>) t.addLeft(n1,3);
			BTNode<Integer> n4 = (BTNode<Integer>) t.addRight(n1,4);
			
			BTNode<Integer> n5 = (BTNode<Integer>) t.addLeft(n2,5);
			
			BTNode<Integer> n6 = (BTNode<Integer>) t.addLeft(n3,6);
			
			BTNode<Integer> n7 = (BTNode<Integer>) t.addLeft(n5,7);
			BTNode<Integer> n8 = (BTNode<Integer>) t.addRight(n5,8);	
			
			//Arbol2
			
			ArbolBinarioE<Integer> t1 = new ArbolBinarioE<Integer>();
			
			BTNode<Integer> nn0 = (BTNode<Integer>) t1.createRoot(2);
			
			BTNode<Integer> nn1 = (BTNode<Integer>) t1.addLeft(nn0,5);
			BTNode<Integer> nn2 = (BTNode<Integer>) t1.addLeft(nn1,7);
			//BTNode<Integer> nn3 = (BTNode<Integer>) t1.addRight(nn1,8);
			
			System.out.println();
			System.out.println("............ ej 2 / c .............");
			System.out.println();
			
			System.out.println(OpAB.parientes(n0, n3, t));
			
			System.out.println();
			System.out.println("............ ej 2 / b .............");
			System.out.println();
			/*
			PositionList<BTNode<Integer>> l = OpAB.parientesCamino(n0, n6, t);
			
			for (BTNode<Integer> h : l) {
				System.out.println(h.element());
			}
			*/
			System.out.println();
			System.out.println("............ ej 2 / e .............");
			System.out.println();
			/*
			PositionList<BTNode<Integer>> l1 = OpAB.profundiadCam(n8,t);
			
			for (BTNode<Integer> h : l1) {
				System.out.println(h.element());
			}
			*/
			System.out.println();
			System.out.println("............ ej 2 / f .............");
			System.out.println();
			
			t.mostrarPorNiveles();
			
			PositionList<BTNode<Integer>> l2 = OpAB.alturacam(n0, t);
			
			for (BTNode<Integer> h : l2) {
				System.out.println(h.element());
			}
			
			System.out.println();
			System.out.println("............ ej 3 / a .............");
			System.out.println();
			
			System.out.println(OpAB.esPropio(t));
			
			System.out.println();
			System.out.println("............ ej 3 / c .............");
			System.out.println();
			
			System.out.println(OpAB.esSub(t1, t));
			
			System.out.println();
			System.out.println("............ ej 3 / d .............");
			System.out.println();
			
			OpAB.mostrarExpresion(t);
			
		} catch (InvalidOperationException | InvalidPositionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
