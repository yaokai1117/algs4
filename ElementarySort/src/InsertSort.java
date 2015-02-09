
public class 	 {

	public static void sort(Comparable[] a) {
		for (int i = 0; i < a.length; i++) {
			for (int j = i; j > 0; j--) {
				if(a[j].compareTo(a[j-1]) < 0)
					swap(a, j, j-1);
				else 
					break;
			}
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
