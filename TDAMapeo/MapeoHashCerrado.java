package TDAMapeo;

import Exceptions.*;
import Interfaces.*;
import TDALista.*;

public class MapeoHashCerrado<K,V> implements Map<K,V>{
	protected static final float factorDeCarga = 0.5f; // n/N
	protected Entry<K,V> [] bucket;
	protected Entry<K,V> disp = new Entrada<K,V>(null,null);
	protected int N = 13;
	protected int n;
	
	@SuppressWarnings("unchecked")
	public MapeoHashCerrado() {
		bucket = (Entry<K,V>[]) new Entrada[N]; 
	}
	
	@Override
	public int size() {
		return n;
	}

	@Override
	public boolean isEmpty() {
		return n == 0;
	}

	@Override
	public V get(K key) throws InvalidKeyException {
		checkKey(key);
		int i = h(key);
		int aux = 1;
		V ret = null;
		while (aux < N && bucket[i] != null && ret == null) {
			if (bucket[i] != disp && bucket[i].getKey().equals(key))
				ret = bucket[i].getValue();
			i = (i+1) % N;
			aux++;
		}
		return ret;
	}

	private void checkKey(K key) throws InvalidKeyException {
		if (key == null)
			throw new InvalidKeyException("La key no es valida");
	}

	@Override
	public V put(K key, V value) throws InvalidKeyException {
		checkKey(key);
		int i = h(key);
		boolean fin = false;
		V ret = null;
		int aux = 1;
		while (aux <= N && !fin) {
			if (bucket[i] == null || bucket[i] == disp) {
				bucket[i] = new Entrada<K,V>(key,value);
				n++;
				fin = true;
			}
			else
				if (bucket[i].getKey().equals(key)) {
					ret = bucket[i].getValue();
					Entrada<K,V> n = (Entrada<K,V>) bucket[i];
					n.setValue(value);
					fin = true;
				}
			i = (i+1) % N;
			aux++;
		}
		
		if (size()/N >= factorDeCarga)
			reHash();
		
		return ret;
	}

	@Override
	public V remove(K key) throws InvalidKeyException {
		checkKey(key);
		V ret = null;
		int i = h(key);
		int aux = 1;
		while(aux <= N && bucket[i] != null && ret == null) {
			if (bucket[i] != disp && bucket[i].getKey().equals(key)) {
				ret = bucket[i].getValue();
				bucket[i] = disp;
				n--;
			} 
			else {
				i = (i+1) % N;
				aux++;
			}
					
		}
		return ret;
	}

	@Override
	public Iterable<K> keys() {
		PositionList<K> l = new ListaDE<K>();
		for (int i = 0; i < N;i++) {
			if (bucket[i] != null && bucket[i] != disp) {
				l.addLast(bucket[i].getKey());
			}
		}
		return l;
	}

	@Override
	public Iterable<V> values() {
		PositionList<V> l = new ListaDE<V>();
		for (int i = 0; i < N;i++) {
			if (bucket[i] != null && bucket[i] != disp) {
				l.addLast(bucket[i].getValue());
			}
		}
		return l;
	}

	@Override
	public Iterable<Entry<K, V>> entries() {
		PositionList<Entry<K,V>> it = new ListaDE<Entry<K,V>>(); 
		for (int i = 0; i < N; i++) {
			if(bucket[i] != null && bucket[i] != disp)
				it.addLast(bucket[i]);
		}
		return it;
	}

	@SuppressWarnings("unchecked")
	private void reHash() {
		Iterable<Entry<K, V>> l = entries();
		N = primoSiguiente(N*2);
		bucket = (Entry<K,V>[]) new Entrada[N];
		n = 0;
		try {
			for (Entry<K,V> e : l) {
				
					put(e.getKey(),e.getValue());
				
			}
		} catch (InvalidKeyException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	private int primoSiguiente(int n) {
		int i = n + 1;
		boolean es = false;
		while (!es) {
			es = true;
			for (int j = 2; j < i && es; j++) {
	            if (i % j == 0) {
	                es =  false;
	                i++;
	            }
	        }
		}
		return i;
	}

	private int h(K i) {
		return Math.abs(i.hashCode() % N);
	}
}