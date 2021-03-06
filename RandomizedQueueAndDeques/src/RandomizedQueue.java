
import java.util.Iterator;
import java.util.NoSuchElementException;

import javax.print.attribute.Size2DSyntax;

public class RandomizedQueue<Item> implements Iterable<Item> {
	
	private Item[] rqItems;							//array of items
	private int size;								//size of queue, not the number of items
	private int vacancy;							//number of remove item, size-vacancy is the number of items
	
	/**
	 * construct a new RandomizeQueue
	 */
	public RandomizedQueue() {
		rqItems = (Item[]) new Object[2];
		size = vacancy = 0;
	}
	
	/**
	 * is the queue empty?
	 * @return
	 */
	public boolean isEmpty() {
		return size - vacancy == 0;
	}
	
	/**
	 * return the number of elements on th queue
	 * @return
	 */
	public int size() {
		return size - vacancy;
	}
	
	/**
	 * resize the array to a length of capacity
	 * @param capacity
	 */
	private void resize(int capacity) {
		Item[] tmpItems = (Item[]) new Object[capacity];
		int rmNum = 0;
		for(int i = 0; i < size; i++){
			if(rqItems[i] == null){
				rmNum++;
				continue;
			}
			tmpItems[i - rmNum] = rqItems[i];
		}
		size = size - vacancy;
		vacancy = 0;
		rqItems = tmpItems;
	}
	
	/**
	 * add a item
	 * @param item
	 */
	public void enqueue(Item item) {
		if(item == null)
			throw new NullPointerException("trying to add a null");
		if(size == rqItems.length)
			resize(2 * size() + 1);										//size is different from size(), size() = size - vacancy
		rqItems[size++] = item;
	}
	
	/**
	 * delete and return a random item
	 * @return
	 */
	public Item dequeue() {
		if(isEmpty())
			throw new NoSuchElementException("the queue is empty");
		if(size() <= rqItems.length / 4)
			resize(2 * size() + 1);
		int n = StdRandom.uniform(size);
		while(rqItems[n] == null)
			n = StdRandom.uniform(size);
		Item tmpItem = rqItems[n];
		rqItems[n] = null;
		vacancy++;
		return tmpItem;
	}
	
	/**
	 * return(but not delete) a random order
	 * @return
	 */
	public Item sample() {
		if(isEmpty())
			throw new NoSuchElementException("the queue is empty");
		int n = StdRandom.uniform(size);
		while(rqItems[n] == null)
			n = StdRandom.uniform(size);
		return rqItems[n];
	}
	
	private class RandomQueueIterator implements Iterator<Item> {
		private int[] order;
		private int i;
		
		public RandomQueueIterator() {
			resize(size());
			order = new int[size];
			for (int i = 0; i < order.length; i++) {
				order[i] = i;
			}
			StdRandom.shuffle(order);
			i = size;
		}
		
		public boolean hasNext() {
			return i > 0;
		}
		
		public void remove() {
			throw new UnsupportedOperationException("doesn't support remove");
		}
		
		public Item next() {
			if(!hasNext())
				throw new NoSuchElementException("doesn't has next");
			i--;
			return rqItems[order[i]];
		}
	}
	
	
	public Iterator<Item> iterator() {
		return new RandomQueueIterator();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RandomizedQueue<String> testQueue = new RandomizedQueue<String>();
		testQueue.enqueue("a");
		testQueue.enqueue("b");
	}

}
