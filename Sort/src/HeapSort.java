import java.util.Comparator;

public class HeapSort {
	
	public static void sort(Object[] a, Comparator c) {
		int N = a.length - 1;
		for (int i = N/2; i >= 1; i--)
			sink(a, i, N, c);
		while (N > 1){
			swap(a, 1, N);
			sink(a, 1, --N, c);
		}
	}
	
	private static void sink(Object[] a, int k, int N, Comparator c) {
		while(2*k <= N) {
			int j = 2*k;
			if (j+1 <= N && c.compare(a[j+1], a[j]) > 0)
				j++;
			if (c.compare(a[j], a[k]) < 0)
				break;
			swap(a, j, k);
			k = j;
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
		Student[] studentList = new Student[11];
		for (int i = 1; i < studentList.length; i++) {
			studentList[i] = new Student((int)(10*Math.random()+1), i + "th  ");
		}
		
		sort(studentList, Student.BY_ID);
		for (int i = 1; i < studentList.length; i++) {
			System.out.print(studentList[i]);
		}
		
		System.out.println();
		
		sort(studentList, Student.BY_NAME);
		for (int i = 1; i < studentList.length; i++) {
			System.out.print(studentList[i]);
		}
	}

}
