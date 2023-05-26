package TDAArbol;

import TDALista.*;

public class TNodo<E> implements Position<E>{
	protected E elem;
	protected TNodo<E> padre;
	protected PositionList<TNodo<E>> hijos;
	
	/**
	 * Crea una instancia de tipo TNodo con el elemento pasado por parametro.
	 * @param elemento elemento elemento del nodo.
	 */
	public TNodo(E elemento){
		elem = elemento;
		hijos = new ListaDE<TNodo<E>>();
	}
	
	/**
	 * Devuelve el TNodo padre.
	 * @return TNodo padre del nodo.
	 */
	public TNodo<E> getPadre(){
		return padre;
	}
	
	/**
	 * Devuelve una lista doblemente enlazada conteniendo los hijos del nodo.
	 * @return PositionList<TNodo<E>> lista a devolver.
	 */
	public PositionList<TNodo<E>> getHijos(){
		return hijos;
	}
	
	public void setPadre(TNodo<E> p) {
		padre = p;
	}
	
	public void setElemento(E elemento) {
		elem = elemento;
	}
	
	@Override
	public E element() {
		return elem;
	}
	
	
}
