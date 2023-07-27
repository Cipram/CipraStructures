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
import Interfaces.GraphD;
import Interfaces.Position;
import Interfaces.Vertex;
import TDACola.ColaEnlazada;
import TDALista.ListaDE;

public class OpGrafo {
	
	private final static Object VISITADO = new Object();
	private final static Object NOVISITADO = new Object();
	private final static Object ESTADO = new Object();
	
	//Recorrido grafo ej 3 a)i)
	public static <V,E> void dfsShell(Graph<V,E> g) {
		try {
			for (Vertex<V> v : g.vertices())
				v.put(ESTADO, NOVISITADO);
			for (Vertex<V> v : g.vertices()) {
				if (v.get(ESTADO) == NOVISITADO)
					dfs(g,v);
			}
		} catch (InvalidKeyException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static <V,E> void dfs(Graph<V,E> g, Vertex<V> v) {
		System.out.println(v.element());
		try {
			v.put(ESTADO, VISITADO);
			Iterable<Edge<E>> adyacentes = g.incidentEdges(v);
			for (Edge<E> e : adyacentes) {
				Vertex<V> w = g.opposite(v, e);
				if (w.get(ESTADO) == NOVISITADO) {
					dfs(g,w);
				}
			}
		} catch (InvalidKeyException | InvalidVertexException | InvalidEdgeException e) {
			System.out.println(e.getMessage());
		}
	}
	
	//Recorrido grafo ej 4 a)ii)
	
	public static <V,E> void bfsShell(Graph<V,E> g) {
		try {
			for (Vertex<V> v : g.vertices()) {
				v.put(ESTADO, NOVISITADO);
			}
			for (Vertex<V> v : g.vertices()) {
				if (v.get(ESTADO) == NOVISITADO)
					bfs(g,v);
			}
		} catch (InvalidKeyException e) {
			System.out.println(e.getMessage());
		}
	}
	
	private static <V,E> void bfs(Graph<V,E> g, Vertex<V> v) {
		ColaEnlazada<Vertex<V>> cola = new ColaEnlazada<Vertex<V>>();
		cola.enqueue(v);
		Vertex<V> u;
		try {
			v.put(ESTADO, VISITADO);
			while(!cola.isEmpty()) {
				u = cola.dequeue();
				System.out.println(u.element());
				for (Vertex<V> w : g.vertices()) {
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
	
	//##### ej 5 a)
	
	@SuppressWarnings("hiding")
	public static <V,Integer> float menorCamino(GraphD<V,Integer> g, Vertex<V> a, Vertex<V> b) {
		Valor min = new Valor(90);
		Valor aux = new Valor();
		try {
			for (Vertex<V> v : g.vertices())
				v.put(ESTADO, NOVISITADO);
			CamMin(g,a,b,min,aux);
		} catch (InvalidKeyException e) {
			System.out.println(e.getMessage());
		}
		return min.getValor();
	}
	
	@SuppressWarnings("hiding")
	private static <V,Integer> boolean CamMin(GraphD<V,Integer> g, Vertex<V> a, Vertex<V> b, Valor costoMin, Valor costoAct) {
		boolean es = false;
		int aux = costoAct.getValor();
		try {
			a.put(ESTADO, VISITADO);
			Iterable<Edge<Integer>> adyacentes = g.succesorEdges(a);
			for (Edge<Integer> e : adyacentes) {
				costoAct.setValor(aux); //una vez que recorrio el camino de un vertice, costoAct vuelve a su valor original
				es = false;
				Vertex<V> w = g.opposite(a, e);
				if(w.get(ESTADO) == NOVISITADO) {
					if (w == b) { //Si el vector que tiene en frente es el destino, 
						es = true; //marca el camino como positivo para el backtracking
						costoAct.setValor(costoAct.getValor()+((int) e.element()));
					}
					else {
						costoAct.setValor(costoAct.getValor()+((int) e.element())); //si no es el destino
						es = CamMin(g,w,b,costoMin,costoAct);	//sigue buscando con el peso del arco
					}
					if (es) {	// si camino fue positivo en algun momento, se comparan los costos de caminos
						if (costoAct.getValor() < costoMin.getValor()) {
							costoMin.setValor(costoAct.getValor());
						}
					}
					else { //si el destino no se encontro nunca en el camino de w
						costoAct.setValor(costoAct.getValor() - ((int) e.element())); //le sacamos el costo de su camino
					}
				}
			}
			a.put(ESTADO, NOVISITADO);
		} catch (InvalidVertexException | InvalidEdgeException | InvalidKeyException e1) {
			System.out.println(e1.getMessage());
		}
		return es;
	}
	
	//###### ej 5 b)
	
	@SuppressWarnings("hiding")
	public static <V,Integer> ListaDE<Vertex<V>> menorCaminoLista(GraphD<V,Integer> g, Vertex<V> a, Vertex<V> b) {
		ListaDE<Vertex<V>> listaMin = new ListaDE<Vertex<V>>();
		ListaDE<Vertex<V>> listaAct = new ListaDE<Vertex<V>>();
		try {
			for (Vertex<V> v : g.vertices())
				v.put(ESTADO, NOVISITADO);
			CaminoMin(g,a,b,listaMin,listaAct);
		} catch (InvalidKeyException e) {
			System.out.println(e.getMessage());
		}
		return listaAct;
	}
	
	@SuppressWarnings({ "hiding", "unused" })
	private static <V,Integer> void CaminoMin(GraphD<V,Integer> g, Vertex<V> a, Vertex<V> b, ListaDE<Vertex<V>> camMin, ListaDE<Vertex<V>> camAct) {
//		boolean encontre = false;
		try {
			a.put(ESTADO, VISITADO);
			camAct.addLast(a);
			if (a == b) {
//				encontre = true;
				if (camMin.isEmpty() || camAct.size() <= camMin.size()) {
					camMin = OpGrafo.cloneLista(camAct);
				}
			}
			else {
				ListaDE<Vertex<V>> aux = OpGrafo.cloneLista(camAct);
				Iterable<Edge<Integer>> adyacentes = g.succesorEdges(a);
				for (Edge<Integer> e : adyacentes) {
					//camAct = OpGrafo.cloneLista(aux); //una vez que recorrio el camino de un vertice, costoAct vuelve a su valor original
//					encontre = false;
					Vertex<V> w = g.opposite(a, e);
					if(w.get(ESTADO) == NOVISITADO) {
						CaminoMin(g,w,b,camMin,camAct);
					}
				}
			}
			camAct.remove(camAct.last());
			a.put(ESTADO, NOVISITADO);
		} catch (InvalidVertexException | InvalidEdgeException | InvalidKeyException | InvalidPositionException | EmptyListException e1) {
			System.out.println(e1.getMessage());
		}
	}
	
	@SuppressWarnings("unchecked")
	public static <V, E> ListaDE<Vertex<V>> cloneLista(ListaDE<Vertex<V>> lista){
		ListaDE<Vertex<V>> ret = new ListaDE<Vertex<V>>();
		Vertice<V,E> aux;
		for (Position<Vertex<V>> pos  : lista.positions()) {
			aux = (Vertice<V,E>) pos.element();
			ret.addLast(aux.clone());
		}
		return ret;
	}
	
	//###### ej 6 b)
	
	public static <V, E> Vertex<V> encontrarR(GraphD<V,E> g, V elem){
		Vertex<V> ret = null;
		Vertex<V> aux;
		try {
			for(Vertex<V> v : g.vertices()) {
				v.put(ESTADO, NOVISITADO);
			}
			Iterator<Vertex<V>> it = g.vertices().iterator();
			while (it.hasNext() && ret == null) {
				aux = it.next();
				if (aux.get(ESTADO) == NOVISITADO) {
					ret = vertR(g,elem,aux);
				}
			}
		} catch (InvalidKeyException e) {
			System.out.println(e.getMessage());
		}
		return ret;
	}
	
	private static <V,E> Vertex<V> vertR(GraphD<V, E> g, V elem, Vertex<V> a){
		Vertex<V> ret = null;
		try {
			a.put(ESTADO, VISITADO);
			if (a.element() == elem) {
				ret = a;
			}
			else {
				Iterator<Edge<E>> adyacentes = g.succesorEdges(a).iterator();
				while (adyacentes.hasNext() && ret == null) {
					Edge<E> e = adyacentes.next();
					Vertex<V> w = g.opposite(a, e);
					if (w.get(ESTADO) == NOVISITADO) {
						ret = vertR(g,elem,w);
					}
				}
			}
		} catch (InvalidKeyException | InvalidVertexException | InvalidEdgeException e1) {
			System.out.println(e1.getMessage());
		}
		return ret;
	}
	
	//##### ej 6 d)
	
	public static <V,E> void borrarR(GraphD<V,E> g, V R){
		try {
			for (Vertex<V> v : g.vertices()) {
				v.put(ESTADO, NOVISITADO);
			}
			for (Vertex<V> v : g.vertices()) {
				if (v.get(ESTADO) == NOVISITADO) {
					if (v.element() == R) {
						g.removeVertex(v);
					}
					else {
						v.put(ESTADO, VISITADO);
					}
				}
			}
		} catch (InvalidKeyException | InvalidVertexException e) {
			System.out.println(e.getMessage());
		}
	}
	/*
	private static <V,E> void dfsDelR(GraphD<V,E> g, Vertex<V> v, V elem) {
		try {
			if (v.element() == elem) {
				g.removeVertex(v);
			}
			else {
				Iterable<Edge<E>> adyacentes = g.succesorEdges(v);
				for (Edge<E> e : adyacentes) {
					Vertex<V> w = g.opposite(v, e);
					if (w.get(ESTADO) == NOVISITADO) {
						dfsDelR(g,w,elem);
					}
				}
			}
		} catch(InvalidKeyException | InvalidVertexException | InvalidEdgeException e1) {
			System.out.println(e1.getMessage());
		}
	}
	*/
	//#### ej 6 e)
	
	public static <V,E> boolean EsListaConHeadA(GraphD<V,E> g, Vertex<V> a) {
		Vertex<V> head = null;
		boolean es = true;
		Iterator<Vertex<V>> verts = g.vertices().iterator();
		while (verts.hasNext() && es) {
			Vertex<V> v = verts.next();
			if (head == null) {
				head = v;
				es = head == a;
			}
		}
		if (es) {
			es = esLista(g,a);
		}
		return es;
	}
	
	private static <V,E> boolean esLista(GraphD<V,E> g, Vertex<V> a) {
		boolean es = true;
		try {
			Iterator<Edge<E>> it = g.succesorEdges(a).iterator();
			int i = 0;
			while(it.hasNext() && es) {
				i++;
				Edge<E> e = it.next();
				Vertex<V> w;
				w = g.opposite(a, e);
				if (i < 2 )
					es = esLista(g,w);
				else
					es = false;
			}
		} catch (InvalidVertexException | InvalidEdgeException e1) {
			System.out.println(e1.getMessage());
		}
		return es;
	}
	
	//#### ej 6 f)
	
	public static <V,E> boolean esArbolHeadA(GraphD<V,E> g, Vertex<V> a) {
		Vertex<V> head = null;
		Vertex<V> v = null;
		boolean es = true;
		try {
			Iterator<Vertex<V>> verts = g.vertices().iterator();
			while (verts.hasNext() && es) {
				v = verts.next();
				if (head == null) {
					head = v;
					es = head == a;
				}
				v.put(ESTADO,NOVISITADO);
			}
			if (es)
				es = esArbol(g,head);
		} catch (InvalidKeyException e) {
			System.out.println(e.getMessage());
		}
		return es;
	}
	
	private static <V,E> boolean esArbol(GraphD<V,E> g, Vertex<V> v){
		boolean es = true;
		Iterator<Edge<E>> it;
		try {
			v.put(ESTADO, VISITADO);
			it = g.succesorEdges(v).iterator();
			while (it.hasNext() && es) {
				Edge<E> e = it.next();
				Vertex<V> w = g.opposite(v, e);
				if (w.get(ESTADO) == VISITADO)
					es = false;
				else
					es = esArbol(g,w);
			}
		} catch (InvalidVertexException | InvalidEdgeException | InvalidKeyException e) {
			System.out.println(e.getMessage());
		}
		return es;
	}
	
	//#### ej 6 g)
	
	@SuppressWarnings("unused")
	public static <V,E> void borrarMayorIncidente(GraphD<V,E> g) {
		int mayor = 0;
		int aux = 0;
		Vertex<V> mayorV = null;
		try {	
			for (Vertex<V> v : g.vertices()) {
				aux = 0;
				Iterable<Edge<E>> ad = g.incidentEdges(v);
				for (Edge<E> ed : ad) {
					aux++;
				}
				if (aux > mayor) {
					mayor = aux;
					mayorV = v;
				}
					
			}
			g.removeVertex(mayorV);
		} catch (InvalidVertexException e) {
			System.out.println(e.getMessage());
		}
	}
	
	//##### ej 7 a)
	
	public static <V,E> boolean esConexo(Graph<V,E> g) {
		Vertex<V> head = null;
		boolean es = true;
		try {	
			for (Vertex<V> v : g.vertices()) {
				if (head == null)
					head = v;
				v.put(ESTADO, NOVISITADO);
			}
			Iterator<Vertex<V>> verts = g.vertices().iterator();
			while (verts.hasNext() && es){ 		
				Vertex<V> v = verts.next();
				es = caminoEntreHead(g,v,head);
			}
		} catch (InvalidKeyException e) {
			System.out.println(e.getMessage());
		}
		return es;
	}
	
	//##### ej 7 b)
	
	public static <V,E> int cantComponentes(Graph<V,E> g) {
		boolean visitado = false;
		ListaDE<Vertex<V>> heads = new ListaDE<Vertex<V>>(); //Aqui se guarda un vertice para cada componente
		try {	
			for (Vertex<V> v : g.vertices()) {
				if (heads.isEmpty())
					heads.addLast(v);
				v.put(ESTADO, NOVISITADO);
			}
			for (Vertex<V> v : g.vertices()) { 		//Recorremos todos los vertices del grafo
				Iterator<Vertex<V>> itHeads = heads.iterator();
				visitado = false;
				while(itHeads.hasNext() && !visitado) { //Mientras v no pertenesca algun componente iteramos
					Vertex<V> x = itHeads.next(); 
					visitado = caminoEntreHead(g,v,x);
				}
				if (!visitado) //Si nunca se visito ningun componente, encontramos uno nuevo y guardamos el vertice actual
					heads.addLast(v);
			}
		} catch (InvalidKeyException e) {
			System.out.println(e.getMessage());
		}
		return heads.size();
	}
	
	private static <V,E> boolean caminoEntreHead(Graph<V,E> g, Vertex<V> A, Vertex<V> B) {
		boolean hay = false;
		try {
			A.put(ESTADO, VISITADO);
			if (A == B) {
				hay = true;
			}
			else {
				Iterator<Edge<E>> it = g.incidentEdges(A).iterator();
				while (it.hasNext() && !hay) {
					Edge<E> ed = it.next();
					Vertex<V> w = g.opposite(A, ed);
					if (w.get(ESTADO) == NOVISITADO)
						hay = caminoEntreHead(g,w,B);
				}
			}
			A.put(ESTADO, NOVISITADO);
		} catch (InvalidVertexException | InvalidEdgeException | InvalidKeyException e) {
			System.out.println(e.getMessage());
		}
		return hay;
	}
	
	//#### ej 7 c)
	
	public static <V,E> boolean variaComponentes(Graph<V,E> g, Vertex<V> v) {
		int cant1 = 0;
		int cant2 = 0;
		try {
			cant1 = cantComponentes(g);
			g.removeVertex(v);
			cant2 = cantComponentes(g);
		} catch (InvalidVertexException e) {
			System.out.println(e.getMessage());
		}
		return cant1 != cant2;
	}
	
	//#### ej 8 a)
	
	public static void warshall(int [][] A, int [][] B){
		int n = A.length - 1;
		
		copiarMatriz(A,B);
		for (int k = 0; k <n; k++) {
			for (int i = 0; i<n; i++) {
				for (int j = 0; j<n; j++) {
					B[i][j] = Math.max(B[i][j], Math.min(B[i][k], B[k][j]));
				}
			}
		}
	}
	
	private static void copiarMatriz(int[][] A, int [][] B) {
		int n = A.length - 1;
		for (int i = 0; i<n; i++) {
			for (int j = 0; j<n; j++) {
				B[i][j] = A[i][j];
			}
		}
	}
	
	//#### ej 9 a)
	
	public static <V,E> Integer[][] floyd(DGrafoMatriz<V,E> g){
		Integer[][] peso = g.getMatriz();
		int n = peso.length;
		Integer[][] mat = new Integer[n][n];
		Integer[][] mat2 = new Integer[n][n];
		for (int i = 0; i<n ; i++) {
			for (int j = 0; j<n ; j++) {
				if (peso[i][j] != 0) {
					mat[i][j] = peso[i][j];
				}
				else
					mat[i][j] = 100;
				mat2[i][j] = 0;
			}
		}
		
		for (int i = 0; i<n ; i++) {
			mat[i][i] = 0;
		}
		
		for (int k = 0; k<n ; k++) {
			for (int i = 0; i<n ; i++) {
				for (int j = 0; j<n ; j++) {
					if (mat[i][k] + mat[k][j] < mat[i][j]) {
						mat[i][j] = mat[i][k] + mat[k][j];
						mat2[i][j] = k+1;
					}
				}
			}
		}
		return mat2;
	}
	
	public static <V,E> void floydCamino(Integer[][] mat, int i, int j, ColaEnlazada<Integer> cola) {
		int k = (int) mat[i][j];
		if (k != 0) {
			floydCamino(mat,i,k,cola);
			cola.enqueue(k);
			floydCamino(mat,k,j,cola);
		}
	}
}
