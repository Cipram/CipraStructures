package Interfaces;

public interface BTPosition<E> extends Position<E> {
	
	public BTPosition<E> getParent();
	
	public BTPosition<E> left();
	
	public BTPosition<E> right();
	
	public void setLeft(BTPosition<E> l);
	
	public void setRight(BTPosition<E> r);
	
	public void setElement(E e);
	
	public void setPadre(BTPosition<E> p);
	
	public int childrenNum();
}