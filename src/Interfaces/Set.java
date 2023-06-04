package Interfaces;

import Exceptions.SetException;

public interface Set<E> extends Iterable<E>{
	
	public void insert(E x);
	
	public boolean member(E x);
	
	public void delete(E x) throws SetException;;
}
