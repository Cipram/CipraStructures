package TDAGrafo;

import Interfaces.Position;
import Interfaces.Vertex;

public class VerticeMat<V> implements Vertex<V> {
	private Position<Vertex<V>> posicionEnVertices;
	private V rotulo;
	private int indice;
	
	public VerticeMat(V rot, int in) {
		rotulo = rot;
		indice = in;
	}
	
	public void setRotulo(V r) {
		rotulo = r;
	}
	
	public void setIndice(int indi) {
		indice = indi;
	}
	
	public void setPosicionEnVertices(Position<Vertex<V>> v) {
		posicionEnVertices = v;
	}
	
	public int getIndice() {
		return indice;
	}
	
	public Position<Vertex<V>> getPosicionEnVertices(){
		return posicionEnVertices;
	}
	
	@Override
	public V element() {
		return rotulo;
	}

}
