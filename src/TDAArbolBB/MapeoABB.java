package TDAArbolBB;

import java.util.Comparator;

import Exceptions.InvalidKeyException;
import TDAMapeo.*;

public class MapeoABB<K,V> implements Map<K,V> {

	private NodoABB<Entry<K,V>> root;
	private int size;
	private Comparator<K> comp;
	
	public MapeoABB(Comparator<K> c) {
		comp = c;
		size = 0;
		root = new NodoABB<Entry<K,V>>(null,null);
	}
	
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public V get(K key) throws InvalidKeyException {
		checkKey(key);
		NodoABB<Entry<K,V>> n = buscar(key);
		V ret = null;
		if (n.getRotulo() != null)
			ret = n.getRotulo().getValue();
		else
			ret = null;
		return ret;
	}

	@Override
	public V put(K key, V value) throws InvalidKeyException {
		checkKey(key);
		NodoABB<Entry<K,V>> n=buscar(key);
		Entry<K,V> en = new Entrada<K, V>(key,value);
		if (n.getRotulo()!=null){
			V viejo=n.getRotulo().getValue();
			n.setRotulo(en);
			return viejo; }
		else{
			n.setRotulo(en);
			n.setLeft(new NodoABB<Entry<K, V>>(null, n));
			n.setRight(new NodoABB<Entry<K, V>>(null, n));
			size++;
			return null; 
			} 
		}

	@Override
	public V remove(K key) throws InvalidKeyException {
		checkKey(key);
		NodoABB<Entry<K,V>> n = buscar(key);
		V eliminado = null;
		if (n.getRotulo()!= null) {
			eliminado =  n.getRotulo().getValue();
			eliminar(n);
		}
		return eliminado;
	}

	private void eliminar(NodoABB<Entry<K,V>> n) {
		if (n == root) {
			if (soloTieneHijoIzq(n)) {
				root = n.getLeft();
			}
			else {
				if (soloTieneHijoIzq(n))
					root = n.getRight();
				else {
					NodoABB<Entry<K,V>> min = encontrarMin(n.getRight());
					n.setRotulo(min.getRotulo());
					eliminar(min);
				}
			}
		}
		else
			if (isExternal(n)){
				n.setRotulo(null);
				n.setLeft(null);
				n.setRight(null);
			}
			else {
				if (soloTieneHijoIzq(n)){ // el nodo solo tiene un hijo izquierdo
					if (n.getPadre().getLeft() == n)
						n.getPadre().setLeft(n.getLeft());
					else
						n.getPadre().setRight(n.getLeft());
					n.getLeft().setPadre(n.getPadre());
				}
				else
					if (soloTieneHijoDer(n)){ //
						if (n.getPadre().getLeft()==n)
							n.getPadre().setLeft(n.getRight());
						else
							n.getPadre().setRight(n.getRight());
						n.getRight().setPadre(n.getPadre());
					}
					else { //el nodo tiene dos hijos
						NodoABB<Entry<K,V>> min = encontrarMin(n.getRight());
						n.setRotulo(min.getRotulo());
						eliminar(min);
					}
			size--;
			}
	}
	
	private NodoABB<Entry<K,V>> encontrarMin(NodoABB<Entry<K,V>> n){
		NodoABB<Entry<K,V>> min = n;
		while (!isExternal(min) || !soloTieneHijoDer(min)) {
			min = n.getLeft();
		}
		return min;
	}
	
	@Override
	public Iterable<K> keys() {

		return null;
	}

	@Override
	public Iterable<V> values() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Entry<K, V>> entries() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private NodoABB<Entry<K,V>> buscar(K key){
		return buscarAux(key,root);
	}

	private NodoABB<Entry<K,V>> buscarAux(K key, NodoABB<Entry<K,V>> n){
		NodoABB<Entry<K,V>> ret;
		int c;
		if (n.getRotulo() == null)
			return n; 
		else{
			c = comp.compare(key, n.getRotulo().getKey());
		if (c == 0) 
			ret =  n;
		else
			if (c<0)
				ret = buscarAux(key, n.getLeft());
			else
				ret = buscarAux(key, n.getRight()); 
		}
		return ret;
	}
	
	private boolean isExternal(NodoABB<Entry<K,V>> p) {
		return p.getLeft().getRotulo() == null && p.getRight().getRotulo() == null;
	}
	
	private boolean soloTieneHijoIzq(NodoABB<Entry<K,V>> p) {
		return p.getLeft() != null && p.getRight().getRotulo() == null;
	}
	
	private boolean soloTieneHijoDer(NodoABB<Entry<K,V>> p) {
		return p.getRight() != null && p.getLeft().getRotulo() == null;
	}
	
	public NodoABB<Entry<K,V>> getRaiz(){
		return root;
	}
	
	private void checkKey(K key) throws InvalidKeyException{
		if (key != null)
			throw new InvalidKeyException("key nula");
	}
}
