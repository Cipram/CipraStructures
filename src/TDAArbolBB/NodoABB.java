package TDAArbolBB;

public class NodoABB<E> {
	private E rotulo;
	private NodoABB<E> padre;
	private NodoABB<E> left;
	private NodoABB<E> right;
	
	public NodoABB(E rotulo, NodoABB<E> padre) {
		this.rotulo = rotulo;
		this.padre = padre;
		left = null;
		right = null;
	}
	
	public E getRotulo() {
		return rotulo;
	}
	
	public NodoABB<E> getPadre(){
		return padre;
	}
	
	public NodoABB<E> getLeft() {
		return left;
	}
	
	public NodoABB<E> getRight() {
		return right;
	}
	
	public void setRotulo(E r) {
		rotulo = r;
	}
	
	public void setLeft(NodoABB<E> l) {
		left = l;
	}
	
	public void setRight(NodoABB<E> r) {
		right = r;
	}
	
	public void setPadre(NodoABB<E> p) {
		padre = p;
	}
	
}
