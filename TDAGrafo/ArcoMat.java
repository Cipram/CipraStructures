package TDAGrafo;

import Interfaces.Edge;
import Interfaces.Position;
import Interfaces.Vertex;

public class ArcoMat<V,E> implements Edge<E> {
	private Position<Edge<E>> posicionEnArcos;
	private VerticeMat<V> v1;
	private VerticeMat<V> v2;
	private E rotulo;
	
	public ArcoMat(VerticeMat<V> vert1, VerticeMat<V> vert2, E e) {
		v1 = vert1;
		v2 = vert2;
		rotulo = e;
	}
	
	public void setElement(E r) {
		rotulo = r;
	}
	
	public void setV2(VerticeMat<V> v) {
		v2 = v;
	}
	
	public void setV1(VerticeMat<V> v) {
		v1 = v;
	}
	
	public void setPosicionEnArcos(Position<Edge<E>> p) {
		posicionEnArcos = p;
	}
	
	public VerticeMat<V> getV2(){
		return v2;
	}
	
	public VerticeMat<V> getV1(){
		return v1;
	}
	
	public Position<Edge<E>> getPosicionEnArcos(){
		return posicionEnArcos;
	}
	
	@Override
	public E element() {
		return rotulo;
	}
}
