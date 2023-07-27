package TDAGrafo;

import Exceptions.EmptyQueueException;
import Exceptions.InvalidVertexException;
import Interfaces.*;
import TDAArbolBB.OPArbolBB;
import TDACola.ColaEnlazada;
import TDALista.ListaDE;

public class Tester {
	public static void main (String arg[]) {
		DGrafoLAD<Character,Integer> grafo = new DGrafoLAD<Character,Integer>();
		DGrafoLAD<Character,Integer> grafo2 = new DGrafoLAD<Character,Integer>();
		GrafoLAD<Integer,Integer> grafo3 = new GrafoLAD<Integer,Integer>();
		DGrafoMatriz<Integer,Integer> grafo4 = new DGrafoMatriz<Integer,Integer>(6);
		DGrafoLAD<Integer,Integer> GrafoLista = new DGrafoLAD<Integer,Integer>();
		DGrafoLAD<Integer,Integer> GrafoArbol = new DGrafoLAD<Integer,Integer>();
		
		Vertex<Character> va = grafo.insertVertex('a');
		Vertex<Character> vb = grafo.insertVertex('b');
		Vertex<Character> vc = grafo.insertVertex('c');
		Vertex<Character> vd = grafo.insertVertex('d');
		Vertex<Character> ve = grafo.insertVertex('e');
		Vertex<Character> vf = grafo.insertVertex('f');
		Vertex<Character> vg = grafo.insertVertex('g');
		Vertex<Character> vh = grafo.insertVertex('h');
		
		Vertex<Character> va1 = grafo2.insertVertex('a');
		Vertex<Character> vb1 = grafo2.insertVertex('b');
		Vertex<Character> vc1 = grafo2.insertVertex('a');
		Vertex<Character> vd1 = grafo2.insertVertex('d');
		Vertex<Character> ve1 = grafo2.insertVertex('e');
		Vertex<Character> vf1 = grafo2.insertVertex('f');
		Vertex<Character> vg1 = grafo2.insertVertex('a');
		Vertex<Character> vh1 = grafo2.insertVertex('h');
		Vertex<Character> vi1 = grafo2.insertVertex('i');
		
		Vertex<Integer> v1 = GrafoLista.insertVertex(1);
		Vertex<Integer> v2 = GrafoLista.insertVertex(2);
		Vertex<Integer> v3 = GrafoLista.insertVertex(3);
		Vertex<Integer> v4 = GrafoLista.insertVertex(4);
		
		Vertex<Integer> A1 = GrafoArbol.insertVertex(0);
		Vertex<Integer> A2 = GrafoArbol.insertVertex(1);
		Vertex<Integer> A3 = GrafoArbol.insertVertex(1);
		Vertex<Integer> A4 = GrafoArbol.insertVertex(2);
		Vertex<Integer> A5 = GrafoArbol.insertVertex(2);
		Vertex<Integer> A6 = GrafoArbol.insertVertex(2);
		Vertex<Integer> A7 = GrafoArbol.insertVertex(3);
		
		Vertex<Integer> V1 = grafo3.insertVertex(1);
		Vertex<Integer> V2 = grafo3.insertVertex(2);
		Vertex<Integer> V3 = grafo3.insertVertex(3);
		Vertex<Integer> V4 = grafo3.insertVertex(4);
		Vertex<Integer> V5 = grafo3.insertVertex(5);
		Vertex<Integer> V6 = grafo3.insertVertex(6);
		Vertex<Integer> V7 = grafo3.insertVertex(7);
		Vertex<Integer> V8 = grafo3.insertVertex(8);
		Vertex<Integer> V9 = grafo3.insertVertex(9);
		Vertex<Integer> V10 = grafo3.insertVertex(10);
		
		Vertex<Integer> m1 = grafo4.insertVertex(1);
		Vertex<Integer> m2 = grafo4.insertVertex(2);
		Vertex<Integer> m3 = grafo4.insertVertex(3);
		Vertex<Integer> m4 = grafo4.insertVertex(4);
		Vertex<Integer> m5 = grafo4.insertVertex(5);
		Vertex<Integer> m6 = grafo4.insertVertex(6);
		
		try {
			
			grafo.insertEdge(va, vb, 0);
			grafo.insertEdge(vb, vc, 1);
			grafo.insertEdge(vd, vg, 2);
			grafo.insertEdge(ve, vh, 3);
			grafo.insertEdge(vc, vd, 4);
			grafo.insertEdge(vd, ve, 5);
			grafo.insertEdge(ve, vf, 6);
			grafo.insertEdge(vf, vg, 7);
			grafo.insertEdge(vg, vh, 8);
			
			grafo2.insertEdge(va1, vb1, 1);
			grafo2.insertEdge(va1, vd1, 2);
			grafo2.insertEdge(va1, vc1, 5);
			grafo2.insertEdge(vb1, vh1, 1);
			grafo2.insertEdge(vh1, vc1, 2);
			grafo2.insertEdge(vc1, vi1, 3);
			grafo2.insertEdge(vb1, ve1, 3);
			grafo2.insertEdge(ve1, vf1, 4);
			grafo2.insertEdge(vf1, va1, 7);
			grafo2.insertEdge(vd1, vf1, 3);
			grafo2.insertEdge(vf1, vg1, 6);
			
			GrafoLista.insertEdge(v1, v2, 0);
			GrafoLista.insertEdge(v2, v3, 0);
			GrafoLista.insertEdge(v3, v4, 0);
			
			GrafoArbol.insertEdge(A1,A2,0);
			GrafoArbol.insertEdge(A1,A3,0);
			GrafoArbol.insertEdge(A2,A4,0);
			GrafoArbol.insertEdge(A2,A5,0);
			GrafoArbol.insertEdge(A2,A6,0);
			GrafoArbol.insertEdge(A3,A7,0);
			
			grafo3.insertEdge(V1, V2, 0);
			grafo3.insertEdge(V3, V4, 1);
			grafo3.insertEdge(V4, V5, 2);
			grafo3.insertEdge(V6, V1, 3);
			grafo3.insertEdge(V1, V6, 4);
			grafo3.insertEdge(V6, V7, 5);
			grafo3.insertEdge(V6, V8, 6);
			grafo3.insertEdge(V1, V3, 7);
			grafo3.insertEdge(V5, V1, 8);
			grafo3.insertEdge(V7, V10, 10);
			grafo3.insertEdge(V9, V10, 9);
			
			grafo4.insertEdge(m1,m2,1);
			grafo4.insertEdge(m2,m3,2);
			grafo4.insertEdge(m1,m3,4);
			grafo4.insertEdge(m1,m4,3);
			grafo4.insertEdge(m3,m1,2);
			grafo4.insertEdge(m3,m4,1);
			grafo4.insertEdge(m3,m5,1);
			grafo4.insertEdge(m4,m5,3);
			grafo4.insertEdge(m5,m6,2);
			grafo4.insertEdge(m6,m3,1);
			grafo4.insertEdge(m5,m1,4);
			
		}
		catch (InvalidVertexException e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println("###### ej 3 ######");
		
		//OpGrafo.dfsShell(grafo);
		
		System.out.println();
		
		//grafo.shellDfs();
		
		System.out.println("###### ej 4 ######");
		
		//grafo.shellBfs();
		
		System.out.println();
		
		//OpGrafo.bfsShell(grafo);
		System.out.println();
		System.out.println("###### ej 5 a) ######");
		System.out.println();
		
		//System.out.println("Costo minimo de D a H: " + OpGrafo.menorCamino(grafo,vd,vh));
		
		System.out.println("Costo minimo de A a E: " + OpGrafo.menorCamino(grafo,va,ve));
		System.out.println("Costo minimo de A a H: " + OpGrafo.menorCamino(grafo,va,vh));
		System.out.println("Costo minimo de D a H: " + OpGrafo.menorCamino(grafo,vd,vh));
		System.out.println("Grafo2:");
		System.out.println("Costo minimo de A a C: " + OpGrafo.menorCamino(grafo2, va1, vc1));
		System.out.println("Costo minimo de A a F: " + OpGrafo.menorCamino(grafo2, va1, vf1));
		System.out.println("Costo minimo de D a I: " + OpGrafo.menorCamino(grafo2, vd1, vi1));
		
		System.out.println();
		System.out.println("###### ej 5 b) ######");
		System.out.println();
		
		ListaDE<Vertex<Character>> l = new ListaDE<Vertex<Character>>();
		l = OpGrafo.menorCaminoLista(grafo, vd, vh);
		for (Vertex<Character> v : l) {
			System.out.print(v.element() + " ");
		}
		
		System.out.println();
		System.out.println("###### ej 6 b) ######");
		System.out.println();
		
		System.out.println(OpGrafo.encontrarR(grafo, 'f').element());
		
		OpGrafo.borrarR(grafo2,'a');
		
		System.out.println();
		System.out.println("###### ej 6 e) ######");
		System.out.println();
		
		System.out.println(OpGrafo.EsListaConHeadA(GrafoLista, v1));
		System.out.println(OpGrafo.EsListaConHeadA(grafo2, va1));
		System.out.println(OpGrafo.EsListaConHeadA(GrafoLista, v3));
		System.out.println(OpGrafo.EsListaConHeadA(grafo, va));
		
		System.out.println();
		System.out.println("###### ej 6 f) ######");
		System.out.println();
		
		System.out.println(OpGrafo.esArbolHeadA(GrafoArbol, A3));
		
		System.out.println();
		System.out.println("###### ej 6 g) ######");
		System.out.println();
		
		OpGrafo.borrarMayorIncidente(grafo);
		
		System.out.println();
		System.out.println("###### ej 7 a) ######");
		System.out.println();
		
		System.out.println(OpGrafo.esConexo(grafo3));
		
		System.out.println();
		System.out.println("###### ej 7 b) ######");
		System.out.println();
		
		System.out.println("el grafo tiene: " + OpGrafo.cantComponentes(grafo3) + " componentes.");
		
		System.out.println();
		System.out.println("###### ej 7 c) ######");
		System.out.println();
		
		System.out.println(OpGrafo.variaComponentes(grafo3, V7));
		
		System.out.println();
		System.out.println("###### ej 8 b) ######");
		System.out.println();
		
		int [][] mat1 = new int [10][10];
		int [][] mat2 = new int [10][10];
		
		mat1[1][1] = 1;
		mat1[1][2] = 1;
		mat1[1][7] = 1;
		mat1[2][3] = 1;
		mat1[3][4] = 1;
		mat1[4][5] = 1;
		mat1[4][7] = 1;
		mat1[5][6] = 1;
		mat1[8][7] = 1;
		mat1[4][9] = 1;
		
		mostrarMatriz(mat1);
		OpGrafo.warshall(mat1, mat2);
		System.out.println();
		mostrarMatriz(mat2);
		
		System.out.println();
		System.out.println("###### ej 9 a) ######");
		System.out.println();
		
		Integer [][] mat = OpGrafo.floyd(grafo4);
		ColaEnlazada<Integer> cola = new ColaEnlazada<Integer>();
		
		OpGrafo.floydCamino(mat, 0, 2, cola);
		try {
			for(int i = 0; i < cola.size();i++) {
				System.out.print(cola.dequeue());
			}
		} catch (EmptyQueueException e) {
			System.out.println(e.getMessage());
		}
	}
	
	private static void mostrarMatriz(int [][] mat) {
		int n = mat.length-1;
		for(int i = 0; i<n; i++) {
			for (int j = 0; j<n; j++) {
				System.out.print(mat[i][j]);
			}
			System.out.println();
		}
	}
}
