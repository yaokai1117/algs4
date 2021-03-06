import java.util.Comparator;


public class InsertSort	 {

	public static void sort(Comparable[] a) {			//using Comparable
		for (int i = 0; i < a.length; i++) {
			for (int j = i; j > 0; j--) {
				if(a[j].compareTo(a[j-1]) < 0)
					swap(a, j, j-1);
				else 
					break;
			}
		}
	}

	public static void sort(Object[] a, int low, int high, Comparator c) {	//using Comparator in order to be used in MergeSort and QuickSort
		for (int i = low; i <= high; i++){
			for (int j = i; j > 0; j--)
				if (c.compare(a[j], a[j-1]) < 0)
					swap(a, j-1, j);
				else
					break;
		}
	}
	
	private static void swap(Object[] a, int i, int j) {
		Object temp = a[i];
		a[i] = a[j];
		a[j] = temp; 
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Integer[] a = new Integer[10];
		for (int i = 0; i < a.length; i++) {
			a[i] = 10 - i;
		}
		sort(a);
		for (Integer integer : a) {
			System.out.println(integer);
		}
	}

}
