
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

	private int size;							//the number of items on the deque
	private DequeNode head, tail;				//the head node's next is pointed to the first item
												//while tail point to the last item
	
	/**
	 * helper DequeNode class
	 * @author yaokai
	 *
	 */
	private class DequeNode {
		private Item item;
		private DequeNode next, pre;
	}
	
	/**
	 * construct an empty deque
	 */
	public Deque() {
		head = new DequeNode();
		head.next = tail;
		tail = null;
		size = 0;
	}
	
	/**
	 * is the deque empty?
	 * @return
	 */
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
	 * return the number of items on the deque
	 * @return
	 */
	public int size() {
		return size;
	}
	
	/**
	 * insert the item at the front
	 * @param item
	 */
	public void addFirst(Item item) {
		if(item == null)
			throw new NullPointerException("trying to add a null pointer");
		DequeNode tmpNode = new DequeNode();
		tmpNode.item = item;
		tmpNode.next = head.next;
		tmpNode.pre = head;
		if(!isEmpty()){
			head.next.pre = tmpNode;
		}
		else {
			tail = tmpNode;
		}
		head.next = tmpNode;
		size++;
	}
	
	/**
	 * insert the item at the end
	 * @param item
	 */
	public void addLast(Item item) {
		if(item == null)
			throw new NullPointerException("trying to add a null pointer");
		DequeNode tmpNode = new DequeNode();
		tmpNode.next = null;
		tmpNode.item = item;
		if(!isEmpty()){
			tmpNode.pre = tail;
			tail.next = tmpNode;
		}
		else {
			tmpNode.pre = head;
			head.next = tmpNode;
		}
		tail = tmpNode;
		size++;
	}
	
	/**
	 * delete and return the item at the front
	 * @return
	 */
	public Item removeFirst() {
		if(isEmpty())
			throw new NoSuchElementException("the deque is empty");
		DequeNode tmpNode = head.next;
		if(tmpNode != tail){
			tmpNode.next.pre = head;
		}
		else {
			tail = null;
		}
		head.next = tmpNode.next;
		size--;
		return tmpNode.item;
	}
	
	/**
	 * delete and return the item at the end
	 * @return
	 */
	public Item removeLast() {
		if(isEmpty())
			throw new NoSuchElementException("the deque is empty");
		DequeNode tmpNode = tail;
		tmpNode.pre.next = null;
		if(tmpNode.pre != head){
			tail = tmpNode.pre;
		}
		else{
			tail = null;
		}
		size--;
		return tmpNode.item;
	}
	
	/*
	 * an iterator, doesn't implement remove()
	 */
	private class DequeIterator implements Iterator<Item> {
		private DequeNode current = head.next;
		
		public boolean hasNext() {
			return current != null;
		}
		
		public void remove() {
			throw new UnsupportedOperationException("remove() is not implemented");
		}
		
		public Item next() {
			if(!hasNext())
				throw new NoSuchElementException("doesn't have next");
			Item item = current.item;
			current = current.next;
			return item;
		}
	}
	
	/**
	 * return an iterator to this deque in  order from front to end
	 */
	public Iterator<Item> iterator() {
		return new DequeIterator(); 
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Deque<Integer> testDeque = new Deque<Integer>();
		Deque<String> testDeque2 = new Deque<String>();
		Deque<Integer> testDeque3 = new Deque<Integer>();
		Deque<Integer> testDeque4 = new Deque<Integer>();
		testDeque.addFirst(2);
		testDeque.addLast(1);
		testDeque.removeFirst();
		testDeque.removeLast();
	}

}
