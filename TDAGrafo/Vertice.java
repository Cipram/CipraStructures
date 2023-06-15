package TDAGrafo;

import Interfaces.Position;
import Interfaces.PositionList;
import Interfaces.Vertex;
import TDALista.*;

public class Vertice<V,E> implements Vertex<V>{
	private V rotulo;
	private PositionList<Arco<V,E>> adyacentes;
	private Position<Vertice<V,E>> posicionEnVertices;
	
	public Vertice(V rotulo) {
		this.rotulo = rotulo;
		adyacentes = new ListaDE<Arco<V,E>>();
	}
	
	public void setRotulo(V rot) {
		rotulo = rot;
	}
	
	public PositionList<Arco<V,E>> getAdyacentes(){
		return adyacentes;
	}
	
	public void setPosicionEnVertices(Position<Vertice<V,E>> p) {
		posicionEnVertices = p;
	}
	
	public Position<Vertice<V,E>> getPosicionEnVertices(){
		return posicionEnVertices;
	}
	
	@Override
	public V element() {
		return rotulo;
	}

}
