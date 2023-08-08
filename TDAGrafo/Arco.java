package TDAGrafo;

import Exceptions.InvalidKeyException;
import Interfaces.Edge;
import Interfaces.Entry;
import Interfaces.Position;

public class Arco<V,E> implements Edge<E>{
	private E rotulo;
	private Vertice<V,E> v1;
	private Vertice<V,E> v2;
	private Position<Arco<V,E>> posicionEnArcos;
	private Position<Arco<V,E>> posicionV1;
	private Position<Arco<V,E>> posicionV2;
	
	public Arco(E rotulo, Vertice<V,E> v1, Vertice<V,E> v2) {
		this.rotulo = rotulo;
		this.v1 = v1;
		this.v2 = v2;
	}
	
	public void setRotulo(E rot) {
		rotulo = rot;
	}
	
	public void setPosicionEnArcos(Position<Arco<V,E>> p) {
		posicionEnArcos = p;
	}
	
	public void setPosicionV1(Position<Arco<V,E>> p) {
		posicionV1 = p;
	}
	
	public void setPosicionV2(Position<Arco<V,E>> p) {
		posicionV2 = p;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void setV1(Vertice v) {
		v1 = v;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void setV2(Vertice v) {
		v2 = v;
	}
	
	public Position<Arco<V,E>> getPosicionV1(){
		return posicionV1;
	}
	
	public Position<Arco<V,E>> getPosicionV2(){
		return posicionV2;
	}
	
	public Position<Arco<V,E>> getPosicionEnArcos() {
		return posicionEnArcos;
	}
	
	public Vertice<V,E> getV1(){
		return v1;
	}
	
	public Vertice<V,E> getV2(){
		return v2;
	}
	@Override
	public E element() {
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
