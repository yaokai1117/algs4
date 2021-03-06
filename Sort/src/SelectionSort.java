
public class SelectionSort {

	public static void sort(Comparable[] a){
		for (int i = 0; i < a.length; i++) {
			int min = i;
			for (int j = i+1; j < a.length; j++) {
				if(a[j].compareTo(a[min]) < 0)
					min = j;
			}
			swap(a, i, min);
		}
				
	}
	
	private static void swap(Comparable[] a, int i, int j) {
		Comparable temp = a[i];
		a[i] = a[j];
		a[j] = temp; 
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Integer[] a  = new Integer[10];
		for (int i = 0; i < a.length; i++) {
			a[i] = 10 - i;
		}
		sort(a);
		for (int i = 0; i < a.length; i++) {
			System.out.println(a[i]);
		}
	}

}
