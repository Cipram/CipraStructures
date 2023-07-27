package TDAGrafo;

import java.util.Iterator;

import Exceptions.InvalidKeyException;
import Interfaces.Entry;
import Interfaces.Position;
import Interfaces.PositionList;
import Interfaces.Vertex;
import TDAMapeo.Entrada;
import TDALista.ListaDE;
import TDAMapeo.MapeoHashCerrado;

public class Vertice<V,E> extends MapeoHashCerrado<Object,Object> implements Vertex<V>{
	private V rotulo;
	private Entry<Object,Object> bucket; 
	private PositionList<Arco<V,E>> adyacentes;
	private Position<Vertice<V,E>> posicionEnVertices;
	
	public Vertice(V rotulo) {
		this.rotulo = rotulo;
		bucket = new Entrada<Object,Object>(null,null);
		adyacentes = new ListaDE<Arco<V,E>>();
	}

	public PositionList<Arco<V,E>> getAdyacentes() {
		return adyacentes;
	}

	public void setRotulo(V x) {
		rotulo = x;
	}

	public void setPosicionEnVertices(Position<Vertice<V, E>> last) {
		posicionEnVertices = last;
	}
	
	public Position<Vertice<V,E>> getPosicionEnVertices(){
		return posicionEnVertices;
	}	

	//----- METODOS DEL HASHmAP -----
	
	@Override
	public V element() {
		return rotulo;
	}

	@Override
	public int size() {
		return 1;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public Object get(Object key) throws InvalidKeyException {
		Object ret = null;
		if (bucket.getKey() == key && bucket.getValue() != null)
			ret = bucket.getValue();
		return ret;
	}

	@Override
	public Object put(Object key, Object value) throws InvalidKeyException {
		Object ret = null;
		if (bucket.getKey() == null)
			bucket = new Entrada<Object,Object>(key,value);
		else {
			if (bucket.getKey().equals(key)) {
				ret = bucket.getValue();
				Entrada<Object,Object> aux = (Entrada) bucket;
				aux.setValue(value);
			}
		}
		return ret;
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
	
	public Vertex<V> clone(){
		Vertice<V,E> vert = new Vertice<V,E>(rotulo);
		Iterator<Arco<V,E>> it = getAdyacentes().iterator();
		while (it.hasNext()) {
			vert.getAdyacentes().addLast(it.next());
		}
		return vert;
	}
}
