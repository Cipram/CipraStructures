package TDABinaryTree;

import Exceptions.EmptyTreeException;
import Exceptions.InvalidPositionException;

public class OpArbolB {

	public static <E> void espejo(ArbolBinarioE<E> t) {
		try {
			preReflejo((BTNode<E>) t.root(), t);
		} catch (EmptyTreeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static <E> void preReflejo(BTNode<E> n, ArbolBinarioE<E> t) {
		BTNode<E> left = null;
		BTNode<E> right = null;
		try {
			if (t.hasLeft(n)) {
				left = (BTNode<E>) n.left();
				preReflejo((BTNode<E>)n.left(), t);
			}
			if (t.hasRight(n)) {
				right = (BTNode<E>) n.right();
				preReflejo((BTNode<E>)n.right(), t);
			}
			n.setLeft(right);
			n.setRight(left);
		} catch (InvalidPositionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
