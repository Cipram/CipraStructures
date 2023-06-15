package TDAArbolBB;

import java.util.Comparator;

import Exceptions.InvalidKeyException;
import Interfaces.Entry;
import Interfaces.Map;
import Interfaces.PositionList;
import TDALista.ListaDE;
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
		return size;
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
			size--;
		}
		return eliminado;
	}

	private void eliminar(NodoABB<Entry<K,V>> n) {
		if (n == root) {
			if (soloTieneHijoIzq(n)) {
				root = n.getLeft();
				root.setPadre(null);
			}
			else {
				if (soloTieneHijoIzq(n)) {
					root = n.getRight();
					root.setPadre(null);
				}
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
				if (soloTieneHijoIzq(n)) { // el nodo solo tiene un hijo izquierdo
					if (n.getPadre().getLeft() == n)
						n.getPadre().setLeft(n.getLeft());
					else
						n.getPadre().setRight(n.getLeft());
					n.getLeft().setPadre(n.getPadre());
				}
				else
					if (soloTieneHijoDer(n)) { //el nodo solo tiene un hijo derecho
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
		PositionList<K> l = new ListaDE<K>();
		if (!isEmpty())
			for (NodoABB<Entry<K,V>> h : nodos()) {
				l.addLast(h.getRotulo().getKey());
			}
		return l;
	}

	@Override
	public Iterable<V> values() {
		PositionList<V> l = new ListaDE<V>();
		if (!isEmpty())
			for (NodoABB<Entry<K,V>> h : nodos()) {
				l.addLast(h.getRotulo().getValue());
			}
		return l;
	}

	@Override
	public Iterable<Entry<K, V>> entries() {
		PositionList<Entry<K,V>> l = new ListaDE<Entry<K,V>>();
		if (!isEmpty())
			for (NodoABB<Entry<K,V>> h : nodos()) {
				l.addLast(h.getRotulo());
			}
		return l;
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
		if (key == null)
			throw new InvalidKeyException("key nula");
	}

	private PositionList<NodoABB<Entry<K,V>>> nodos(){
		PositionList<NodoABB<Entry<K,V>>> l = new ListaDE<NodoABB<Entry<K,V>>>();
		if (!isEmpty());
			inOrder(root,l);
		return l;
	}

	private void inOrder(NodoABB<Entry<K,V>> n, PositionList<NodoABB<Entry<K,V>>> l) {
		if (n.getLeft().getRotulo() != null)
			inOrder(n.getLeft(),l);
		l.addLast(n);
		if (n.getRight().getRotulo() != null)
			inOrder(n.getRight(),l);
	}
}