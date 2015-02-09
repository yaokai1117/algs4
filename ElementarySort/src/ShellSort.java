
public class ShellSort {

	public static void sort(Comparable[] a) {
		int N = a.length;
		int h = 1;
		while(h < N/3)
			h = 3*h + 1;
		while (h > 0) {
			for (int i = h; i < N; i++)
				for(int j = i; j >= h && a[j].compareTo(a[j-h]) < 0; j -= h)
					swap(a, j, j-h);
			h = h/3;
		}
	}
	
	private static void swap(Comparable[] a, int i, int j) {
		Comparable temp = a[i];
		a[i] = a[j];
		a[j] = temp; 
	}
	
	private static class Item implements Comparable<Item> {
		
		private int i;
		private String str;
		
		public Item(int i, String str) {
			this.i = i;
			this.str = str;
		}
		
		@Override
		public int compareTo(Item o) {
			// TODO Auto-generated method stub
			if(this.i > o.i)
				return 1;
			else if(this.i < o.i)
				return -1;
			return 0;
		}
		
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return i + " : " + str;
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Item[] items = new Item[10];
		for (int i = 0; i < items.length; i++) {
			items[i] = new Item(10 - i, Integer.toString(10 - i));
		}
		sort(items);
		for (int i = 0; i < items.length; i++) {
			System.out.println(items[i]);
		}
	}

}
