package TDADiccionario;

import Exceptions.InvalidEntryException;
import Exceptions.InvalidKeyException;
import TDALista.ListaDE;
import TDALista.PositionList;

public class DiccionarioHashCerrado<K,V> implements Dictionary<K,V>{
	protected static final float factorDeCarga = 0.5f;
	protected int size;
	protected int capacidad;
	protected Entry<K,V> disponible;
	protected Entry<K,V> [] bucket;
	
	public DiccionarioHashCerrado() {	
		capacidad = 13;
		size = 0;
		disponible = new Entrada<K,V>(null,null);
		bucket = new Entrada[capacidad];
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
	public Entry<K, V> find(K key) throws InvalidKeyException {
		checkKey(key);
		int i = hashCode(key);
		int aux = 0;
		Entry<K,V> ret = null;
		while (ret == null && aux < capacidad && bucket[i] != null ) {
			if (bucket[i] != disponible && bucket[i].getKey().equals(key)) {
				ret = bucket[i];
			}
			i = (i+1) % capacidad;
			aux++;
		}
		return ret;
	}

	@Override
	public Iterable<Entry<K, V>> findAll(K key) throws InvalidKeyException {
		PositionList<Entry<K,V>> l = new ListaDE<Entry<K,V>>();
		checkKey(key);
		int i = hashCode(key);
		int aux = 0;
		while (aux < capacidad && bucket[i] != null) {
			if (bucket[i] != disponible && bucket[i].getKey().equals(key)) {
				l.addLast(bucket[i]);
			}
			i = (i+1) % capacidad;
			aux++;
		}
		return l;
	}

	@Override
	public Entry<K, V> insert(K key, V value) throws InvalidKeyException {
		checkKey(key);
		int i = hashCode(key);
		int aux = 0;
		Entry<K,V> ret = null;
		while (aux < capacidad && ret == null) {
			if (bucket[i] == null || bucket[i] == disponible) {
				ret = new Entrada<K,V>(key,value);
				bucket[i] = ret;
				size++;
			}
			else {
				i = (i+1) % capacidad;
				aux++;
			}
		}
		if (size() / capacidad >= factorDeCarga) {
			reHash(capacidad*2);
		}
		return ret;
	}

	@Override
	public Entry<K, V> remove(Entry<K, V> e) throws InvalidEntryException {
		if (e == null || e.getKey() == null) 
			throw new InvalidEntryException("La entrada no es valida");
		int i = hashCode(e.getKey());
		int aux = 0;
		Entry<K,V> ret = null;
		while(bucket[i] != null && ret == null && aux < capacidad) {
			if (bucket[i] != disponible && bucket[i].getKey().equals(e.getKey())) {
				ret = bucket[i];
				bucket[i] = disponible;
				size--;
			}
			else {
				i = (i+1) % capacidad;
				aux++;
			}
		}
		if (ret == null)
			throw new InvalidEntryException("La entrada no esta en diccionario");
		return ret;
	}

	@Override
	public Iterable<Entry<K, V>> entries() {
		PositionList<Entry<K,V>> l = new ListaDE<Entry<K,V>>();
		for (int i = 0; i < bucket.length; i++) {
			if (bucket[i] != null && bucket[i] != disponible)
				l.addLast(bucket[i]);
		}
		return l;
	}
	
	@SuppressWarnings("unchecked")
	private void reHash(int n) {
		Iterable<Entry<K,V>> cont = entries();
		capacidad = nextPrimo(n);
		bucket = new Entrada[capacidad];
		size = 0;
		try {
			for (Entry<K,V> e : cont) {
				insert(e.getKey(),e.getValue());
			}
		} catch (InvalidKeyException e1) {
			e1.printStackTrace();
		}
	}
	
	private static int nextPrimo(int n) {
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
	
	private int hashCode(K key) {
		return Math.abs(key.hashCode() % capacidad);
	}
	
	private void checkKey(K key) throws InvalidKeyException {
		if (key == null)
			throw new InvalidKeyException("La key es invalida");
		
	}
}
