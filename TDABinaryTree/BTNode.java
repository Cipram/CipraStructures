package TDABinaryTree;

import Interfaces.BTPosition;

public class BTNode<E> implements BTPosition<E> {
	
	private E element;
	private BTPosition<E> left;
	private BTPosition<E> right;
	private BTPosition<E> parent;
	
	public BTNode( E element, BTPosition<E> left, BTPosition<E> right, BTPosition<E> parent ) {
		this.element = element;
		this.left = left; 
		this.right = right; 
		this.parent = parent;
	}
	// setters y getters para element, left, right y parent.

	@Override
	public E element() {
		return element;
	}

	@Override
	public BTPosition<E> getParent() {
		return parent;
	}

	@Override
	public BTPosition<E> left() {
		return left;
	}

	@Override
	public BTPosition<E> right() {
		return right;
	}

	@Override
	public void setLeft(BTPosition<E> l) {
		left = l;
	}

	@Override
	public void setRight(BTPosition<E> r) {
		right = r;
	}

	@Override
	public void setElement(E e) {
		element = e;
	}

	@Override
	public void setPadre(BTPosition<E> p) {
		parent = p;
	}
	@Override
	public int childrenNum() {
		int cant = 0;
		if (left != null)
			cant++;
		if (right != null)
			cant++;
		return cant;
	}
	
	
}
