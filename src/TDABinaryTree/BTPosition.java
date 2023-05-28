package TDABinaryTree;

import TDALista.Position;

public interface BTPosition<E> extends Position<E> {
	// agrega getters y setters para element, parent, left y right.
	public BTPosition<E> getParent();
	
	public BTPosition<E> left();
	
	public BTPosition<E> right();
}