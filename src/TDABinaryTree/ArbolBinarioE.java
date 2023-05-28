package TDABinaryTree;

import java.util.Iterator;

import Exceptions.*;

import TDALista.ListaDE;
import TDALista.Position;
import TDALista.PositionList;

public class ArbolBinarioE<E> implements BinaryTree<E> {

	protected BTPosition<E> raiz;
	protected int size;
	
	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public Iterator<E> iterator() {
		PositionList<E> l = new ListaDE<E>();
		for (Position<E> h : positions()) {
			l.addLast(h.element());
		}
		return l.iterator();
	}

	@Override
	public Iterable<Position<E>> positions() {
		PositionList<Position<E>> l = new ListaDE<Position<E>>();
		if (!isEmpty())
			preBinario((BTNode<E>) raiz,l);
		return l;
	}
	
	private void preBinario(BTNode<E> n, PositionList<Position<E>> l) {
		l.addLast(n);
		try {
			if (hasLeft(n))
				preBinario((BTNode<E>) n.left(),l);
			if (hasRight(n))
				preBinario((BTNode<E>) n.right(),l);
		} catch (InvalidPositionException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public E replace(Position<E> v, E e) throws InvalidPositionException {
		if (isEmpty())
			throw new InvalidPositionException("El arbol esta vacio");
		BTNode<E> nodo = checkPosition(v);
		E ret = nodo.element();
		nodo.setElement(e);
		return ret;
	}

	@Override
	public Position<E> root() throws EmptyTreeException {
		if (isEmpty())
			throw new EmptyTreeException("El arbol esta vacio");
		return raiz;
	}

	@Override
	public Position<E> parent(Position<E> v) throws InvalidPositionException, BoundaryViolationException {
		if (isEmpty())
			throw new InvalidPositionException("El arbol esta vacio");
		BTNode<E> nodo = checkPosition(v);
		if (isRoot(v))
			throw new BoundaryViolationException("El nodo raiz no tiene padre");
		return nodo.getParent();
	}

	@Override
	public Iterable<Position<E>> children(Position<E> v) throws InvalidPositionException {
		if (isEmpty())
			throw new InvalidPositionException("El arbol esta vacio");
		BTNode<E> nodo = checkPosition(v);
		PositionList<Position<E>> hijos = new ListaDE<Position<E>>();
		if (hasLeft(nodo))
			hijos.addLast((BTNode<E>) nodo.left());
		if (hasRight(nodo))
			hijos.addLast((BTNode<E>) nodo.right());
		return hijos;
	}

	@Override
	public boolean isInternal(Position<E> v) throws InvalidPositionException {
		if (isEmpty()) 
			throw new InvalidPositionException("El arbol esta vacio");
		BTNode<E> nodo = checkPosition(v);
		return hasRight(nodo) || hasLeft(nodo);
	}

	@Override
	public boolean isExternal(Position<E> v) throws InvalidPositionException {
		if (isEmpty()) 
			throw new InvalidPositionException("El arbol esta vacio");
		BTNode<E> nodo = checkPosition(v);
		return !hasRight(nodo) && !hasLeft(nodo);
	}

	@Override
	public boolean isRoot(Position<E> v) throws InvalidPositionException {
		if (isEmpty())
			throw new InvalidPositionException("El arbol esta vacio");
		BTNode<E> nodo = checkPosition(v);
		return nodo == raiz;
	}

	@Override
	public E remove(Position<E> v) throws InvalidOperationException, InvalidPositionException{
		if (isEmpty())
			throw new InvalidPositionException("El arbol esta vacio");
		BTNode<E> nodo = checkPosition(v);
		E ret;
		if (isRoot(nodo)) {
			if (hasLeft(nodo) && !hasRight(nodo)) {
				ret = raiz.element();
				raiz = null;
				raiz = nodo.left();
				size--;
			}
			else {
				if (hasRight(nodo) && !hasLeft(nodo)) {
					ret = raiz.element();
					raiz = null;
					raiz = nodo.right();
					size--;
				}
				else {
					if (!hasLeft(nodo) && !hasRight(nodo)) {
						ret = raiz.element();
						raiz = null;
						size--;
					}
					else
						throw new InvalidOperationException("No se puede eliminar la raiz si tiene mas de un hijo");
				}
					
			}
		}
		else {
			BTNode<E> padre = (BTNode<E>) nodo.getParent();
			if (hasLeft(nodo) && !hasRight(nodo)) {
				if (nodo == padre.left()) {
					ret = nodo.element();
					padre.setLeft(null);
					padre.setLeft(nodo.left());
					nodo.left().setPadre(padre);
					nodo = null;
					size--;
				}
				else {
					ret = nodo.element();
					padre.setRight(null);
					padre.setRight(nodo.left());
					nodo.left().setPadre(padre);
					nodo = null;
					size--;
				}
			}
			else {
				if (hasRight(nodo) && !hasLeft(nodo)) {
					if (nodo == padre.left()) {
						ret = nodo.element();
						padre.setLeft(null);
						padre.setLeft(nodo.right());
						nodo.right().setPadre(padre);
						nodo = null;
						size--;
					}
					else {
						ret = nodo.element();
						padre.setRight(null);
						padre.setRight(nodo.right());
						nodo.right().setPadre(padre);
						nodo = null;
						size--;
					}
				}
				else {
					if (!hasRight(nodo) && !hasLeft(nodo)) {
						if (nodo == padre.left()) {
							ret = nodo.element();
							padre.setLeft(null);
							nodo = null;
							size--;
						}
						else {
							ret = nodo.element();
							padre.setRight(null);
							nodo = null;
							size--;
						}
					}
					else
						throw new InvalidOperationException("El nodo tiene mas de un hijo y no puede eliminarse");
				}
			}
		}
		return ret;
	}

	@Override
	public Position<E> left(Position<E> v) throws InvalidPositionException, BoundaryViolationException {
		if (isEmpty())
			throw new InvalidPositionException("El arbol esta vacio");
		BTNode<E> nodo = checkPosition(v);
		if (!hasLeft(nodo))
			throw new BoundaryViolationException("El nodo no tiene hijo izquierdo");
		return nodo.left();
	}

	@Override
	public Position<E> right(Position<E> v) throws InvalidPositionException, BoundaryViolationException {
		if (isEmpty())
			throw new InvalidPositionException("El arbol esta vacio");
		BTNode<E> nodo = checkPosition(v);
		if (!hasRight(nodo))
			throw new BoundaryViolationException("El nodo no tiene hijo derecho");
		return nodo.right();
	}

	@Override
	public boolean hasLeft(Position<E> v) throws InvalidPositionException {
		BTNode<E> nodo = checkPosition(v);
		return nodo.left() != null;
	}

	@Override
	public boolean hasRight(Position<E> v) throws InvalidPositionException {
		BTNode<E> nodo = checkPosition(v);
		return nodo.right() != null;
	}

	@Override
	public Position<E> createRoot(E r) throws InvalidOperationException {
		if (raiz == null)
			raiz = new BTNode<E>(r,null,null,null);
		else
			throw new InvalidOperationException("El arbol ya tiene raiz");
		size++;
		return raiz;
	}

	@Override
	public Position<E> addLeft(Position<E> v, E r) throws InvalidOperationException, InvalidPositionException {
		if (isEmpty())
			throw new InvalidPositionException("El arbol esta vacio");
		BTNode<E> nodo = checkPosition(v);
		if (hasLeft(nodo))
			throw new InvalidOperationException("El nodo ya tiene un hijo izquierdo");
		BTNode<E> hijo = new BTNode<E> (r,null,null,nodo);
		nodo.setLeft(hijo );
		size++;
		return hijo;
	}

	@Override
	public Position<E> addRight(Position<E> v, E r) throws InvalidOperationException, InvalidPositionException {
		if (isEmpty())
			throw new InvalidPositionException("El arbol esta vacio");
		BTNode<E> nodo = checkPosition(v);
		if (hasRight(nodo))
			throw new InvalidOperationException("El nodo ya tiene un hijo izquierdo");
		BTNode<E> hijo = new BTNode<E> (r,null,null,nodo);
		nodo.setRight(hijo);
		size++;
		return hijo;
	}

	@Override
	public void attach(Position<E> r, BinaryTree<E> T1, BinaryTree<E> T2) throws InvalidPositionException {
		BTNode<E> nodo = checkPosition(r);
		try {
			if (isExternal(nodo)) {
				nodo.setLeft((BTPosition<E>) T1.root());
				nodo.setRight((BTPosition<E>) T2.root());
				size += T1.size() + T2.size();
			}
			else
				throw new InvalidPositionException("El nodo no es externo");
		} catch (EmptyTreeException e) {
			e.printStackTrace();
		}
	}
	
	private BTNode<E> checkPosition(Position<E> p) throws InvalidPositionException {
		if (p == null)
			throw new InvalidPositionException("la posicion es nula");
		if (p.element() == null)
			throw new InvalidPositionException("El elemento de la posicione es nula");
		return (BTNode<E>) p;
	}

}
