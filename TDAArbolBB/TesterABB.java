package TDAArbolBB;

public class TesterABB {
	public static void main (String []args) {
		ABB<Integer> t = new ABB<Integer>(new MiComparator<Integer>());
		agregar(14,t);
		agregar(12,t);
		agregar(50,t);
		agregar(19,t);
		agregar(3,t);
		agregar(8,t);
		
		ABB<Integer> t1 = new ABB<Integer>(new MiComparator<Integer>());
		agregar(40,t1);
		agregar(18,t1);
		agregar(3,t1);
		agregar(14,t1);
		
		t.mostrarPorNiveles();
		
		System.out.println();
		System.out.println("***************| ej 7 |**************");
		System.out.println();
		
		ABB<Integer> t3 = t.interseccion(t1);
		
		t3.mostrarPorNiveles();
		
		System.out.println();
		System.out.println("***************| ej 9 |**************");
		System.out.println();
		
		t.mostrarInOr();
		System.out.println();
		System.out.println(t.ejercicio14(14).getRotulo());
		
	}
	
	private static void agregar(Integer a, ABB<Integer> t){
		NodoABB<Integer> n = t.buscar(a);
		n.setRotulo(a);
		t.expandir(n);
	}
}
