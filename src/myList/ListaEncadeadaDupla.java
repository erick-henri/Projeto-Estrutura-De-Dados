package myList;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class ListaEncadeadaDupla<O> implements MyInterfaceList<O> {

	class Node {
		int id;
		O valor;
		Node next;
		Node prev;

		public Node(O valor) {

			this.valor = valor;
			this.id = idNext;
			idNext++;
			this.next = null;
			this.prev = null;
		}

		public void setId(int id) {
			this.id = id;
		}

	}

	class MyIterator implements Iterator<O> {

		Node atual = null;

		@Override
		public boolean hasNext() {
			if (atual == null && head != null) {
				return true;
			} else if (atual != null) {
				return atual.next != null;
			}

			return false;
		}

		@Override
		public O next() {
			if (atual == null && head != null) {
				atual = head;
				return atual.valor;
			} else if (atual != null) {
				atual = atual.next;
				return atual.valor;
			}

			throw new NoSuchElementException();
		}

	}

	public Node head;
	public Node tail;
	public int idNext;
	public int size;

	public ListaEncadeadaDupla() {
		head = null;
		tail = null;
		idNext = 0;
		size = 0;
	}

	private Node searchById(int id) {
		Node aux = head;

		while (aux != null) {
			if (aux.id == id) {
				return aux;
			}
			aux = aux.next;
		}

		return null;
	}

	public int searchByValue(Object valor) {
		// producarando pelo valor
		Node aux = head;
		while (aux != null) {
			if (aux.valor.equals(valor)) {
				return aux.id;
			}
			aux = aux.next;
		}
		return -1;
	}

	@Override
	public boolean add(O valor) {
		// adicionar embaixo
		Node novo = new Node(valor);
		if (tail == null) {
			head = novo;
			tail = novo;
		} else {
			novo.prev = tail;
			tail.next = novo;
			tail = novo;
		}
		size++;
		return true;
	}

	@Override
	public boolean remove(Object valor) {
		int id = searchByValue(valor);
		Node removido = searchById(id);

		if (head == null) {
			System.out.println("Lista vazia!");
			return false;
		}

		if (removido == null) {
			System.out.println("Criterio invalido!");
			return false;
		}

		Node anterior = removido.prev;
		Node posterior = removido.next;

		if (head == tail) {
			head = null;
			tail = null;
			idNext = 0;
		} else {

			if (head == removido) {
				int aux = 0;
				head = head.next;
				head.prev = null;
				removido.next = null;
				Node x = head;
				while (aux != (size - 2)) {
					x.setId(aux);
					x = x.next;
					aux++;
				}
				idNext--;
			} else if (tail == removido) {
				tail.prev = null;
				tail = anterior;
				tail.next = null;
				idNext--;
			} else {
				int aux = removido.id;
				removido.next = null; // retirar o item removido da lista
				removido.prev = null;
				Node x = posterior;
				if (anterior != null) { // primeiro verifica se existe um item anterior
					anterior.next = posterior; // e adiciona o prev do removido nele
				}

				if (posterior != null) { // primeiro verifica se existe um item posterior
					posterior.prev = anterior; // e adiciona o next do removido nele
					while (aux != tail.id+1) {
						x.setId(aux);
						x = x.next;
						aux++;
					}
				}
				idNext--;
			}
		}

		size--;
		return false;
	}

	@Override
	public void add(int id, O valor) {
		// adicionar no meio
		Node anterior = searchById(id);

		if (anterior == tail) { // se o valor anterior for a tail, então irar apenas utilizar o metododo de
								// adicionar embaixo
			add(valor);
			return; // dar o retorno logo aqui para não ter que ir para o proximo if
		}

		if (anterior == null) {
			// System.out.println("Valor não pode ser adicionado, pelo criteiro estar
			// invalido!");
		} else {
			Node novo = new Node(valor);
			novo.next = anterior.next;
			novo.prev = anterior;
			anterior.next = novo;

			Node posterior = novo.next;

			if (posterior != null) {
				posterior.prev = novo;
			}

			size++;
		}
	}

	@Override
	public boolean contains(Object valor) {
		if (head != null) {
			Node aux = head;
			while (aux != null) {
				if (aux.valor.equals(valor)) {
					return true;
				}
				aux = aux.next;
			}
		}
		return false;
	}

	@Override
	public O get(int id) {
		Node aux = head;
		while (aux != null) {
			if (aux.id == id) {
				return aux.valor;
			}
			aux = aux.next;
		}
		return null;
	}

	@Override
	public int indexOf(Object valor) {
		Node aux = head;
		while (aux != null) {
			if (aux.valor.equals(valor)) {
				return aux.id;
			}
			aux = aux.next;
		}
		return 0;
	}

	@Override
	public O set(int id, O valor) {
		Node aux = head;
		while (aux != null) {
			if (aux.id == id) {
				aux.valor = valor;
				return aux.valor;
			}
			aux = aux.next;
		}
		return null;
	}

	@Override
	public Object[] toArray() {
		Node aux = head;
		Object[] array = new Object[size];
		int i = 0;
		while (aux != null) {
			array[i] = aux;
			aux = aux.next;
			i++;
		}

		return array;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public Iterator<O> iterator() {
		// TODO Auto-generated method stub
		return new MyIterator();
	}

	@Override
	public boolean isEmpty() {

		if (head == null) {
			return true;
		}
		return false;
	}

	// Metodos não usados

	@Override
	public int lastIndexOf(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ListIterator<O> listIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListIterator<O> listIterator(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public O remove(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<O> subList(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T[] toArray(T[] arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addAll(Collection<? extends O> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(int arg0, Collection<? extends O> arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}
}
