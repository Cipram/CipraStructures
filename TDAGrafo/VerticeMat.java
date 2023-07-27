package TDAGrafo;

import Exceptions.InvalidKeyException;
import Interfaces.Entry;
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
	
	//----- METODOS DEL HASHmAP -----
	
	@Override
	public V element() {
		return rotulo;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object get(Object key) throws InvalidKeyException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object put(Object key, Object value) throws InvalidKeyException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object remove(Object key) throws InvalidKeyException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Object> keys() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Object> values() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Entry<Object, Object>> entries() {
		// TODO Auto-generated method stub
		return null;
	}

}
