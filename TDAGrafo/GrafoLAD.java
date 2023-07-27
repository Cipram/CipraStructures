package TDAGrafo;

import java.util.Iterator;

import Exceptions.EmptyListException;
import Exceptions.EmptyQueueException;
import Exceptions.InvalidEdgeException;
import Exceptions.InvalidKeyException;
import Exceptions.InvalidPositionException;
import Exceptions.InvalidVertexException;
import Interfaces.Edge;
import Interfaces.Graph;
import Interfaces.Position;
import Interfaces.PositionList;
import Interfaces.Vertex;
import TDACola.ColaEnlazada;
import TDALista.ListaDE;

public class GrafoLAD<V,E> implements Graph<V,E>{
	private final static Object VISITADO = new Object();
	private final static Object NOVISITADO = new Object();
	private final static Object ESTADO = new Object();
	
	private PositionList<Vertice<V,E>> vertices;
	private PositionList<Arco<V,E>> arcos;
	
	public GrafoLAD() {
		vertices = new ListaDE<Vertice<V,E>>();
		arcos = new ListaDE<Arco<V,E>>();
	}
	@Override
	public Iterable<Vertex<V>> vertices() {
		PositionList<Vertex<V>> lista = new ListaDE<Vertex<V>>();
		for(Vertex<V> v : vertices) 
			lista.addLast(v);
		return lista;
	}

	@Override
	public Iterable<Edge<E>> edges() {
		PositionList<Edge<E>> lista = new ListaDE<Edge<E>>();
		for(Edge<E> e : arcos) 
			lista.addLast(e);
		return lista;
	}

	@Override
	public Iterable<Edge<E>> incidentEdges(Vertex<V> v) throws InvalidVertexException {
		PositionList<Edge<E>> l = new ListaDE<Edge<E>>();
		Vertice<V,E> w = checkVertex(v);
		for (Arco<V,E> a : w.getAdyacentes()) {
			if (a.getV1() == w || a.getV2() == w)
				l.addLast(a);
		}
		return l;
	}

	@Override
	public Vertex<V> opposite(Vertex<V> v, Edge<E> e) throws InvalidVertexException, InvalidEdgeException {
		Vertice<V,E> w = checkVertex(v);
		Arco<V,E> u = checkEdge(e);
		Vertice<V,E> ret = null;
		if (u.getV1() == w)
			ret = u.getV2();
		else {
			if (u.getV2() == w)
				ret = u.getV1();
			else
				throw new InvalidEdgeException("El vector no esta presente en este arco");
		}
		return ret;
	}

	@Override
	public Vertex<V>[] endvertices(Edge<E> e) throws InvalidEdgeException {
		Arco<V,E> a = checkEdge(e);
		Vertex<V> [] arreglo = new Vertex[2];
		arreglo[0] = a.getV1();
		arreglo[1] = a.getV2();
		return arreglo;
	}

	@Override
	public boolean areAdjacent(Vertex<V> v, Vertex<V> w) throws InvalidVertexException {
		Vertice<V,E> vert = checkVertex(v);
		Vertice<V,E> vert2 = checkVertex(w);
		Arco<V,E> aux;
		boolean encontre = false;
		Iterator<Arco<V,E>> it = vert.getAdyacentes().iterator();
		while (it.hasNext() && !encontre) {
			aux = it.next();
			if (aux.getV2() == vert2 || aux.getV1() == vert2)
				encontre = true;
		}
		return encontre;
	}

	@Override
	public V replace(Vertex<V> v, V x) throws InvalidVertexException {
		Vertice<V,E> vert = checkVertex(v);
		V ret = vert.element();
		vert.setRotulo(x);
		return ret;
	}

	@Override
	public Vertex<V> insertVertex(V x) {
		Vertice<V,E> v = new Vertice<V,E>(x);
		vertices.addLast(v);
		try {
			v.setPosicionEnVertices(vertices.last());
		} catch (EmptyListException e) {
			System.out.println(e.getMessage());
		}
		return v;
	}

	@Override
	public Edge<E> insertEdge(Vertex<V> v, Vertex<V> w, E e) throws InvalidVertexException {
		Vertice<V,E> vet1 = checkVertex(v);
		Vertice<V,E> vet2 = checkVertex(w);
		Arco<V,E> arco = new Arco<V,E>(e,vet1,vet2);
		try {
			vet1.getAdyacentes().addLast(arco);
			arco.setPosicionV1(vet1.getAdyacentes().last());
			vet2.getAdyacentes().addLast(arco);
			arco.setPosicionV2(vet2.getAdyacentes().last());
			arcos.addLast(arco);
			arco.setPosicionEnArcos(arcos.last());
		} catch (EmptyListException e1) {
			System.out.println(e1.getMessage());
		}
		return arco;
	}

	@Override
	public V removeVertex(Vertex<V> v) throws InvalidVertexException {
		Vertice<V,E> vert = checkVertex(v);
		V ret = vert.element();
		try {
			for (Arco<V,E> a : vert.getAdyacentes()) {
				removeEdge(a);
			}
			ret = vertices.remove(vert.getPosicionEnVertices()).element();
		} catch(InvalidPositionException | InvalidEdgeException e) {
			System.out.println(e.getMessage());
		}
		return ret;
	}

	@Override
	public E removeEdge(Edge<E> e) throws InvalidEdgeException {
		Arco<V,E> arco = checkEdge(e);
		E ret = null;
		Vertice<V,E> v1 = arco.getV1();
		Vertice<V,E> v2 = arco.getV2();
		try {
			v1.getAdyacentes().remove(arco.getPosicionV1());
			v2.getAdyacentes().remove(arco.getPosicionV2());
			Position<Arco<V,E>> aux = arco.getPosicionEnArcos();
			ret = arcos.remove(aux).element();
		} catch (InvalidPositionException e1) {
			System.out.println(e1.getMessage());
		}
		return ret;
	}
	
	@SuppressWarnings("unchecked")
	private Vertice<V,E> checkVertex(Vertex<V> v) throws InvalidVertexException {
		Vertice<V,E> toRet = null;
		if(v == null)
			throw new InvalidVertexException("El vértice no es válido.");
		try {
			toRet = (Vertice<V,E>) v;
		} catch(ClassCastException e) {
			throw new InvalidVertexException("No es de tipo Vértice.");
		}
		return toRet;
	}
	
	@SuppressWarnings("unchecked")
	private Arco<V,E> checkEdge(Edge<E> e) throws InvalidEdgeException {
		Arco<V,E> toRet = null;
		if(e == null)
			throw new InvalidEdgeException("El arco no es válido.");
		try {
			toRet = (Arco<V,E>) e;
		} catch(ClassCastException e1) {
			throw new InvalidEdgeException("No es de tipo Arco.");
		}
		return toRet;
	}

	//----- ej 3 a)ii) -----
	
	public void shellDfs() {
		try {
			for (Vertex<V> v : vertices()) {
				v.put(ESTADO, NOVISITADO);
			}
			for (Vertex<V> v : vertices()) {
				if (v.get(ESTADO) == NOVISITADO)
					dfs(v);
			}
		} catch (InvalidKeyException e) {
			System.out.println(e.getMessage());
		}
	}
	
	private void dfs(Vertex<V> v) {
		System.out.println(v.element());
		try {
			v.put(ESTADO, VISITADO);
			Iterable<Edge<E>> ad = incidentEdges(v);
			for (Edge<E> e : ad) {
				Vertex<V> w = opposite(v, e);
				if (w.get(ESTADO) == NOVISITADO) {
					dfs(w);
				}
			}
		} catch (InvalidKeyException | InvalidVertexException | InvalidEdgeException e) {
			System.out.println(e.getMessage());
		}
	}
	
	//----- ej 4 a)i) -----
	
	public void shellBfs() {
		try {
			for (Vertex<V> v : vertices()) {
				v.put(ESTADO, NOVISITADO);
			}
			for (Vertex<V> v : vertices()) {
				if (v.get(ESTADO) == NOVISITADO)
					bfs(v);
			}
		} catch (InvalidKeyException e) {
			System.out.println(e.getMessage());
		}
	}
	
	private void bfs(Vertex<V> v) {
		ColaEnlazada<Vertex<V>> cola = new ColaEnlazada<Vertex<V>>();
		cola.enqueue(v);
		Vertex<V> u;
		try {
			v.put(ESTADO, VISITADO);
			while(!cola.isEmpty()) {
				u = cola.dequeue();
				System.out.println(u.element());
				for (Vertex<V> w : vertices()) {
					if (w.get(ESTADO) == NOVISITADO) {
						w.put(ESTADO, VISITADO);
						cola.enqueue(w);
					}
				}
			}
		} catch (InvalidKeyException | EmptyQueueException e) {
			System.out.println(e.getMessage());
		}
	}
}
