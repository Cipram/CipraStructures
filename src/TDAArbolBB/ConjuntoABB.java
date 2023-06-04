package TDAArbolBB;

import java.util.Comparator;
import java.util.Iterator;

import Exceptions.InvalidKeyException;
import Exceptions.SetException;
import Interfaces.Set;

public class ConjuntoABB<E extends Comparable<E>> implements Set<E> {

	protected ABB<E> elems;
	protected int size;
	
	public ConjuntoABB(Comparator<E> comp) {
		elems = new ABB<E>(comp);
		size = 0;
	}
	
	@Override
	public void insert(E x) {
		NodoABB<E> p = elems.buscar(x);
		if (p.getRotulo() == null) {
			elems.expandir(p);
			p.setRotulo(x);
			size++;
		}
	}

	@Override
	public boolean member(E x) {
		return elems.buscar(x).getRotulo() != null;
	}

	@Override
	public void delete(E x) throws SetException {
		NodoABB<E> p = elems.buscar(x);
		if (p.getRotulo() != null) {
			try {
				elems.remove(x);
			} catch (InvalidKeyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			size--;
		}
		else
			throw new SetException("Elminar: no puede eliminar");
	}
	
	
	public String toString() {
		return inorder(elems.getRaiz());
	}
	
	private String inorder(NodoABB<E> p) {
		if (p.getRotulo() != null) {
			return "(" + inorder(p.getLeft()) + p.getRotulo() + inorder(p.getRight()) + ")";
		}
		else
			return "";
	}

	@Override
	public Iterator<E> iterator() {
		
		return null;
	}

}
