package TDAArbolBB;

import java.util.Comparator;

public class ABB<E extends Comparable<E>> {
	protected NodoABB<E> raiz;
	protected Comparator<E> comp;
	
	public ABB(Comparator<E> comp) {
		raiz = new NodoABB<E>(null,null);
		this.comp = comp;
	}
	
	public NodoABB<E> buscar(E x){
		return buscarAux(x,raiz);
	}
	
	private NodoABB<E> buscarAux(E x, NodoABB<E> p){
		int c;
		NodoABB<E> ret = null;
		if (p.getRotulo() == null)
			return p;
		else {
			c = comp.compare(x, p.getRotulo());
			if (c == 0)
				return p;
			else 
				if (c < 0)
					ret = buscarAux(x,p.getLeft());
				else
					ret = buscarAux(x,p.getRight());
		}
		return ret;
	}
	
	public void expandir(NodoABB<E> p) {
		p.setLeft(new NodoABB<E>(null,p));
		p.setPadre(new NodoABB<E>(null,p));
	}
	
	public void eliminar( NodoABB<E> p ) {
		
		if (isExternal(p)) {
			// Caso 1: p es hoja => Convertir el nodo en un dummy y soltar sus hijos dummy.
			p.setRotulo(null);
			p.setLeft(null);
			p.setRight(null);
		}
		else { // p no es hoja
			if ( p == raiz ){
				//…. Completar …. Con casos 2, 3 y 4 …
				System.out.println();
			}
			else 
				if (soloTieneHijoIzq(p)) {
				// Caso 2: Enganchar al padre de p con el hijo izquierdo de p
					if(p.getPadre().getLeft() == p) // p es el hijo izquierdo de su padre
						p.getPadre().setRight(p.getLeft()); // el hijo izq del padre de p es ahora el hijo de p
					else // p es el hijo derecho de su padre
						p.getPadre().setRight(p.getLeft()); // el hijo derecho del padre de p es el hijo de p
				p.getLeft().setPadre(p.getPadre()); // Ahora el padre del hijo izq de p es su abuelo
				}
				else
					if (soloTieneHijoDer(p)){
						// Caso 3: Enganchar al padre de p con el hijo derecho de p
						if( p.getPadre().getLeft() == p ) // p es hijo izquierdo de su padre
							p.getPadre().setLeft( p.getRight() ); // el hijo izq del padre de p es el hijo de p
						else
							p.getPadre().setRight( p.getRight() ); // el hijo derecho del padre de p es el hijo de p
						p.getRight().setPadre( p.getPadre() ); // Ahora el padre del hijo der. de p es su abuelo
					}
					else { // Caso 4: p tiene dos hijos => seteo como rótulo de p al rótulo del sucesor inorder de p.
						p.setRotulo(eliminarMinimo( p.getRight()));
					}
		}
	}
	
	private E eliminarMinimo( NodoABB<E> p ) {
		if( p.getLeft().getRotulo() == null ) { // El hijo izquierdo de p es un dummy
			E aRetornar = p.getRotulo(); // salvo el rótulo a devolver
			if( p.getRight().getRotulo() == null ) { // p es hoja (pues sus hijos son dummy)
				p.setRotulo( null ); // Convierto a p en dummy haciendo nulo su rótulo
				p.setLeft( null ); // y desenganchando sus dos hijos dummy
				p.setRight( null );
			} 
			else { // p solo tiene hijo derecho (xq no tiene izquierdo)
				// Engancho al padre de p con el hijo derecho de p.
				// Seguro tiene que ser el hijo derecho de su padre.
				p.getPadre().setRight( p.getRight() );
				p.getRight().setPadre( p.getPadre() );
			}
			return aRetornar;
		}
		else { // Si p tiene hijo izquierdo, entonces p.getRotulo() no es el mínimo.
			// El mínimo tiene que estar en el subárbol izquierdo
			return eliminarMinimo( p.getLeft() );
		}
	}

	private boolean isExternal(NodoABB<E> p) {
		return p.getLeft().getRotulo() == null && p.getRight().getRotulo() == null;
	}
	
	private boolean soloTieneHijoIzq(NodoABB<E> p) {
		return p.getLeft() != null && p.getRight().getRotulo() == null;
	}
	
	private boolean soloTieneHijoDer(NodoABB<E> p) {
		return p.getRight() != null && p.getLeft().getRotulo() == null;
	}
	
	public NodoABB<E> getRaiz(){
		return raiz;
	}
}
