package TDAGrafo;

import Exceptions.EmptyListException;
import Exceptions.InvalidEdgeException;
import Exceptions.InvalidPositionException;
import Exceptions.InvalidVertexException;
import Interfaces.Edge;
import Interfaces.Graph;
import Interfaces.PositionList;
import Interfaces.Vertex;
import TDALista.*;

public class GrafoMatriz<E,V> implements Graph<V,E>{
	private PositionList<Vertex<V>> vertices;
	private PositionList<Edge<E>> arcos;
	private Edge<E> [][] matriz;
	private int cantVertices;
	
	@SuppressWarnings("unchecked")
	public GrafoMatriz(int n) {
		vertices = new ListaDE<Vertex<V>>();
		arcos = new ListaDE<Edge<E>>();
		matriz = (Edge<E> [][]) new ArcoMat[n][n]; 
		cantVertices = 0;
	}
	
	@Override
	public Iterable<Vertex<V>> vertices() {
		PositionList<Vertex<V>> lista = new ListaDE<Vertex<V>>();
		for (Vertex<V> v : vertices) {
			lista.addLast(v);
		}
		return lista;
	}

	@Override
	public Iterable<Edge<E>> edges() {
		PositionList<Edge<E>> lista = new ListaDE<Edge<E>>();
		for (Edge<E> e : arcos) {
			lista.addLast(e);
		}
		return lista;
	}

	@Override
	public Iterable<Edge<E>> incidentEdges(Vertex<V> v) throws InvalidVertexException {
		VerticeMat<V> v1 = checkVertex(v);
		PositionList<Edge<E>> lista = new ListaDE<Edge<E>>();
		int fila = v1.getIndice();
		for (int i = 0; i < cantVertices;i++) {
			if (matriz[fila][i] != null)
				lista.addLast(matriz[fila][i]);
		}
		return lista;
	}

	@Override
	public Vertex<V> opposite(Vertex<V> v, Edge<E> e) throws InvalidVertexException, InvalidEdgeException {
		VerticeMat<V> v1 = checkVertex(v);
		VerticeMat<V> ret;
		ArcoMat<V,E> arco = checkEdge(e);
		if (arco.getV1() == v1)
			ret = arco.getV2();
		else {
			if (arco.getV2() == v1)
				ret = arco.getV1();
			else
				throw new InvalidEdgeException("El vertice no corresponde al arco");
		}
		return ret;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Vertex<V>[] endvertices(Edge<E> e) throws InvalidEdgeException {
		ArcoMat<V,E> arco = checkEdge(e);
		VerticeMat<V> [] arreglo = new VerticeMat[2];
		arreglo[0] = arco.getV1();
		arreglo[1] = arco.getV2();
		return arreglo;
	}

	@Override
	public boolean areAdjacent(Vertex<V> v, Vertex<V> w) throws InvalidVertexException {
		VerticeMat<V> vv = checkVertex(v);
		VerticeMat<V> ww = checkVertex(w);
		int i = vv.getIndice();
		int j = ww.getIndice();
		return matriz[i][j] != null;
	}

	@Override
	public V replace(Vertex<V> v, V x) throws InvalidVertexException {
		VerticeMat<V> vert1 = checkVertex(v);
		V ret = vert1.element();
		vert1.setRotulo(x);
		return ret;
	}

	@Override
	public Vertex<V> insertVertex(V x) {
		VerticeMat<V> v = new VerticeMat<V>(x, cantVertices++);
		if (cantVertices > matriz.length)
			reMatrix();
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
		VerticeMat<V> v1 = checkVertex(v);
		VerticeMat<V> v2 = checkVertex(w);
		ArcoMat<V,E> arco = new ArcoMat<V,E>(v1,v2,e);
		int fila = v1.getIndice();
		int col = v2.getIndice();
		matriz[fila][col] = arco;
		matriz[col][fila] = arco;
		try {
			arcos.addLast(arco);
			arco.setPosicionEnArcos(arcos.last());
		} catch (EmptyListException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return arco;
	}

	@Override
	public V removeVertex(Vertex<V> v) throws InvalidVertexException {
		VerticeMat<V> vert1 = checkVertex(v);
		V ret = null;
		int i = vert1.getIndice();
		try {
			for (int j = 0; j < cantVertices; j++) {
				if (matriz[i][j] != null)	
					removeEdge(matriz[i][j]);
			}
			ret = vertices.remove(vert1.getPosicionEnVertices()).element();
		} catch (InvalidEdgeException | InvalidPositionException e) {
			System.out.println(e.getMessage());
		}
		return ret;
	}

	@Override
	public E removeEdge(Edge<E> e) throws InvalidEdgeException {
		ArcoMat<V,E> arco = checkEdge(e);
		E ret = null;
		int fila = arco.getV1().getIndice();
		int col = arco.getV2().getIndice();
		try {
			matriz[fila][col] = matriz[col][fila] = null;
			ret = arcos.remove(arco.getPosicionEnArcos()).element();
		} catch(InvalidPositionException exc ) {
			System.out.println(exc.getMessage());
		}
		return ret;
	}
	
	private void reMatrix() {
		int size = cantVertices * 2;
		Edge<E> [][] arreglo = new ArcoMat[size][size];
		for (int i = 0; i < cantVertices-1; i++) {
			for (int j = 0; j < cantVertices-1; j++) {
				if (matriz[i][j] != null)
					arreglo[i][j] = matriz[i][j];
			}
		}
		matriz = arreglo;
	}
	
	private VerticeMat<V> checkVertex(Vertex<V> v) throws InvalidVertexException {
		VerticeMat<V> toRet;
		if(v == null)
			throw new InvalidVertexException("El vértice no es válido.");
		try {
			toRet = (VerticeMat<V>) v;
		} catch(ClassCastException e) {
			throw new InvalidVertexException("No es de tipo Vértice.");
		}
		return toRet;
	}
	
	@SuppressWarnings("unchecked")
	private ArcoMat<V,E> checkEdge(Edge<E> e) throws InvalidEdgeException {
		ArcoMat<V,E> toRet = null;
		if(e == null)
			throw new InvalidEdgeException("El arco no es válido.");
		try {
			toRet = (ArcoMat<V,E>) e;
		} catch(ClassCastException e1) {
			throw new InvalidEdgeException("No es de tipo Arco.");
		}
		return toRet;
	}
}