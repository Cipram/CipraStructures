package TDABinaryTree;

public class BTNode<E> implements BTPosition<E> {
	
	private E element;
	private BTPosition<E> left, right, parent;
	
	public BTNode( E element, BTPosition<E> left, BTPosition<E> right, BTPosition<E> parent ) {
		this.element = element; this.left = left; 
		this.right = right; this.parent = parent;
	}
	// setters y getters para element, left, right y parent.

	@Override
	public E element() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BTPosition<E> getParent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BTPosition<E> left() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BTPosition<E> right() {
		// TODO Auto-generated method stub
		return null;
	}
}
