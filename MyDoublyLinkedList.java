/* 	Besirat Melaku
 	CSC 364
 	This class named MyDoublyLinkedList is public and concrete. It extends
	MyAbstractSequentialList and implements Cloneable. It also overrides the clone and 
	equals methods that are inherited from the Object class. 
 */
 import java.util.*;
public class MyDoublyLinkedList<E> extends MyAbstractSequentialList<E> implements Cloneable {
	private Node<E> head = new Node<E>(null);

	public MyDoublyLinkedList() {
		head.next = head;
		head.previous = head;
	}

	private static class Node<E> {
		E element;
		Node<E> previous;
		Node<E> next;

		public Node(E element) {
			this.element = element;
		}
	}

	public String toString() {
		StringBuilder result = new StringBuilder("[");
		Node<E> current = head.next;
		for (int i = 0; i < size; i++) {
			result.append(current.element);
			current = current.next;
			if (current != head) {
				result.append(", ");
			}
		}
		result.append("]");

		return result.toString();
	}

	private Node<E> getNode(int index) {
		Node<E> current = head;
		if (index < size / 2)
			for (int i = -1; i < index; i++)
				current = current.next;
		else
			for (int i = size; i > index; i--)
				current = current.previous;
		return current;
	}

	@Override
	public void add(int index, E e) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
		}
		Node<E> prev = getNode(index - 1);
		Node<E> next = prev.next;
		Node<E> newNode = new Node<E>(e);
		prev.next = newNode;
		next.previous = newNode;
		newNode.previous = prev;
		newNode.next = next;
		size++;
	}

	@Override
	public void clear() {
		head.next = head;
		head.previous = head;
		size = 0;
	}

	@Override
	public boolean contains(E object) {
		for (Node<E> current = head.next; current != head; current = current.next) {
			E e = current.element;
			if (object == null ? e == null : object.equals(e))
				return true;
		}
		return false;
	}

	@Override
	public E get(int index) {
		if (index < 0 || index >= this.size) {
			throw new IndexOutOfBoundsException("index: " + index + ", " + "size: " + size);
		}
		Node<E> elementNode = getNode(index);
		return elementNode.element;
	}

	@Override
	public int indexOf(E e) {
		Node<E> current = head.next;
		if (size > 0) {
			for (int i = 0; i < size; i++) {
				if (e == null) {
					if (current.element == null)
						return i;
				} else if (current.element.equals(e))
					return i;
				current = current.next;

			}
		} else {
			throw new java.util.NoSuchElementException();
		}
		return -2;
	}

	@Override
	public int lastIndexOf(E e) {
		Node<E> current = head.previous;
		if (size > 0) {
			for (int i = size - 1; i >= 0; i--) {
				if (e == null) {
					if (current.element == null)
						return i;
				} else if (current.element.equals(e))
					return i;
				current = current.previous;
			}
		} else {
			throw new java.util.NoSuchElementException();
		}
		return -2;
	}

	@Override
	public E remove(int index) {
		if (index < 0 || index >= size)
			throw new IndexOutOfBoundsException();
		if (size == 0)
			throw new NoSuchElementException();
		Node<E> current = getNode(index);
		E temp = current.element;
		Node<E> before = current.previous;
		Node<E> after = current.next;
		before.next = after;
		after.previous = before;
		current = null;
		size--;
		return temp;
	}

	@Override
	public Object set(int index, E e) {
		if (index < 0 || index >= size)
			throw new IndexOutOfBoundsException();
		Node<E> node = getNode(index);
		E old = node.element;
		node.element = e;
		return old;
	}

	@Override
	public E getFirst() {
		if (size < 0)
			throw new java.util.NoSuchElementException();
		return head.next.element;
	}

	@Override
	public E getLast() {
		if (size < 0)
			throw new java.util.NoSuchElementException();
		return head.previous.element;
	}

	@Override
	public void addFirst(E e) {
		add(0, e);
	}

	@Override
	public void addLast(E e) {
		add(size, e);
	}

	@Override
	public E removeFirst() {
		if (size <= 0)
			throw new java.util.NoSuchElementException(" No elements in the list");
		Node<E> node = getNode(0);
		E element = node.element;
		remove(0);
		return element;
	}

	@Override
	public E removeLast() {
		if (size <= 0)
			throw new java.util.NoSuchElementException("No elements in the list");
		Node<E> node = getNode(size - 1);
		E element = node.element;
		remove(size - 1);
		return element;
	}

	@Override
	public Object clone() {
		try {
			MyDoublyLinkedList<E> clone = (MyDoublyLinkedList)super.clone();
			clone.head = new Node<E>(null);
			clone.head.next = clone.head;
			clone.head.previous = clone.head;
			clone.size = 0;
			ListIterator <E> iterate = this.listIterator();
			while (iterate.hasNext()) {
				clone.add(iterate.next());
			}
			return clone;
		} catch (CloneNotSupportedException ex) {
			return null;
		}
	}

	@Override
	public boolean equals(Object other) {
		if (this==other)
		return true;
		else if (!(other instanceof MyDoublyLinkedList))
		return false;
		MyDoublyLinkedList<E> otherList= (MyDoublyLinkedList)other;
		if (otherList.size!=this.size)
			return false;
		else {
			Iterator<E> iter = this.listIterator();
			Iterator<E> otherIter = otherList.listIterator();
			
			while(iter.hasNext()) {
				E nextIter = iter.next();
				E otherNext = otherIter.next();
				if (nextIter==null) {
					if(otherNext!= nextIter)
						return false;
				}
				else {
					if(!((nextIter).equals(otherNext)))
						return false;
				}
			}
			
		 return true;
		}
		
	}
	
	private static enum ITERATOR_STATE {
		CANNOT_REMOVE, CAN_REMOVE_PREV, CAN_REMOVE_CURRENT
	};

	@Override
	public ListIterator<E> listIterator(int index) {
		return new MyDoublyLinkedListIterator(index);
	}

	private class MyDoublyLinkedListIterator implements ListIterator<E> {
		private Node<E> current;
		private int nextIndex;
		ITERATOR_STATE state = ITERATOR_STATE.CANNOT_REMOVE;

		private MyDoublyLinkedListIterator(int index) {
			if (index < 0 || index > size)
				throw new IndexOutOfBoundsException("iterator index out of bounds");
			current = getNode(index);
			nextIndex = index;
		}

		@Override
		public void add(E e) {
			Node<E> node = new Node<>(e);
			if (state == ITERATOR_STATE.CAN_REMOVE_PREV) {
				current.previous.next = node;
				node.previous = current.previous;
				current.previous = node;
				node.next = current;
				size++;
				nextIndex++;
			} else if (state == ITERATOR_STATE.CAN_REMOVE_CURRENT) {
				current.next.previous = node;
				node.next = current.next;
				current.next = node;
				node.previous = current;
				size++;
			} else if (state == ITERATOR_STATE.CANNOT_REMOVE) {
				current.previous.next = node;
				node.previous = current.previous;
				current.previous = node;
				node.next = current;
				nextIndex++;
				size++;
			}
			state = ITERATOR_STATE.CANNOT_REMOVE;
		}

		@Override
		public boolean hasNext() {
			return nextIndex < size;
		}

		@Override
		public boolean hasPrevious() {
			return nextIndex > 0;
		}

		@Override
		public E next() {
			if (nextIndex >= size)
				throw new NoSuchElementException();
			E toBeThrown = current.element;
			current = current.next;
			nextIndex++;
			state = ITERATOR_STATE.CAN_REMOVE_PREV;
			return toBeThrown;
		}

		@Override
		public int nextIndex() {
			return nextIndex;
		}

		@Override
		public E previous() {
			if (nextIndex <= 0)
				throw new NoSuchElementException();
			current = current.previous;
			E toThrow = current.element;
			nextIndex--;
			state = ITERATOR_STATE.CAN_REMOVE_CURRENT;
			return toThrow;
		}

		@Override
		public int previousIndex() {
			return (nextIndex - 1);
		}

		@Override
		public void remove() {
			switch (state) {
			case CANNOT_REMOVE:
				throw new IllegalStateException();
			case CAN_REMOVE_PREV:
				current.previous.previous.next = current;
				current.previous = current.previous.previous;
				nextIndex--;
				size--;
				state = ITERATOR_STATE.CANNOT_REMOVE;
				break;
			case CAN_REMOVE_CURRENT:
				current.previous.next = current.next;
				current.next.previous = current.previous;
				current = current.next;
				nextIndex++;
				size--;
				state = ITERATOR_STATE.CANNOT_REMOVE;
			}

		}

		@Override
		public void set(E e) {
			if (state == ITERATOR_STATE.CANNOT_REMOVE)
				throw new IllegalStateException();
			else if (state == ITERATOR_STATE.CAN_REMOVE_CURRENT)
				current.element = e;
			else
				current.previous.element = e;
		}
	}
}
