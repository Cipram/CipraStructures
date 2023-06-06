package TDAArbol;

import java.security.InvalidKeyException;

import Exceptions.EmptyTreeException;
import Exceptions.InvalidOperationException;
import Exceptions.InvalidPositionException;
import TDALista.Position;
import TDAMapeo.Map;
import TDAMapeo.MapeoConLista;

public class tester2 {
	public static void main(String arg[]) {
		ArbolGen<Integer> t = new ArbolGen<Integer>();
		try {
			t.createRoot(0);
			TNodo<Integer> n1 = (TNodo<Integer>) t.addLastChild(t.root(), 1);
			TNodo<Integer> n2 = (TNodo<Integer>) t.addLastChild(t.root(), 2);
			TNodo<Integer> n3 = (TNodo<Integer>) t.addLastChild(t.root(), 3);
			TNodo<Integer> n4 = (TNodo<Integer>) t.addLastChild(t.root(), 4);
		} catch (InvalidOperationException | InvalidPositionException | EmptyTreeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			t.removeSpecial(t.root());
		} catch (InvalidPositionException | EmptyTreeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static <E> Map<Character,Integer> crearMapeo(ArbolGen<Character> A){
		Map<Character,Integer> mapeo = new MapeoConLista<Character,Integer>();
		try {
			preMapeo((TNodo<Character>)A.root(),mapeo,A);
		} catch (EmptyTreeException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static void preMapeo(Position<Character> n, Map<Character,Integer> m, ArbolGen<Character> A) {
		try {
			int i = 0;
			for (Position<Character> h : A.children(n)) {
				preMapeo(h,m,A);
				i++;
			}
			m.put(n.element(), i);
		} catch (Exceptions.InvalidKeyException | InvalidPositionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
