package TDAGrafo;

import java.util.Iterator;

import Exceptions.EmptyListException;
import Exceptions.InvalidEdgeException;
import Exceptions.InvalidPositionException;
import Exceptions.InvalidVertexException;
import Interfaces.*;
import TDALista.ListaDE;

public class DGrafoLAD<V,E> implements GraphD<V,E>{
	private PositionList<Vertice<V,E>> vertices;
	private PositionList<Arco<V,E>> arcos;
	
	public DGrafoLAD() {
		vertices = new ListaDE<Vertice<V,E>>();
		arcos = new ListaDE<Arco<V,E>>();
	}
	
	@Override
	public Iterable<Vertex<V>> vertices() {
		PositionList<Vertex<V>> l = new ListaDE<Vertex<V>>();
		for (Vertice<V,E> v : vertices) {
			l.addLast(v);
		}
		return l;
	}

	@Override
	public Iterable<Edge<E>> edges() {
		PositionList<Edge<E>> l = new ListaDE<Edge<E>>();
		for (Arco<V,E> v : arcos) {
			l.addLast(v);
		}
		return l;
	}

	@Override
	public Iterable<Edge<E>> incidentEdges(Vertex<V> v) throws InvalidVertexException {
		PositionList<Edge<E>> l = new ListaDE<Edge<E>>();
		Vertice<V,E> w = checkVertex(v);
		for (Arco<V,E> a : w.getAdyacentes()) {
			if (a.getV2() == w)
				l.addLast(a);
		}
		return l;
	}

	@Override
	public Iterable<Edge<E>> succesorEdges(Vertex<V> v) throws InvalidVertexException {
		PositionList<Edge<E>> l = new ListaDE<Edge<E>>();
		Vertice<V,E> w = checkVertex(v);
		for (Arco<V,E> a : w.getAdyacentes()) {
			if (a.getV1() == w)
				l.addLast(a);
		}
		return l;
	}

	@Override
	public Vertex<V> opposite(Vertex<V> v, Edge<E> e) throws InvalidVertexException, InvalidEdgeException {
		Vertice<V,E> vert = checkVertex(v);
		Arco<V,E> arco = checkEdge(e);
		Vertice<V,E> ret = null;
		if (vert == arco.getV1()) {
			ret = arco.getV2();
		}
		else {
			if (vert == arco.getV2()) {
				ret = arco.getV1();
			}
			else {
				throw new InvalidEdgeException("El arco no tiene le vertice dado");
			}
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
		Vertice<V,E> vert1 = checkVertex(v);
		Vertice<V,E> vert2 = checkVertex(w);
		Iterator<Arco<V,E>> it = vert1.getAdyacentes().iterator();
		boolean es = false;
		while (it.hasNext() && !es) {
			es = vert2 == it.next().getV2();
		}
		return es;
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

}
