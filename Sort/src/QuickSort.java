import java.util.Comparator;


public class QuickSort {
	
	private static final int CUTOFF = 10;

	private static void swap(Object[] a, int i, int j) {
		Object temp = a[i];
		a[i] = a[j];
		a[j] = temp; 
	}
	
	
	private static int partition(Object[] a, int low, int high, Comparator c) {
		int i = low, j = high + 1;
		while (true){
			while (c.compare(a[++i], a[low]) < 0)
				if (i == high) break;
			while (c.compare(a[--j], a[low]) > 0)
				if (j == low) break;
			
			if (j <= i)
				break;
			swap(a, i, j);
		}
		swap(a, low, j);
		return j;
	}
	
	public static void sort(Object[] a, int low, int high, Comparator c) {
		
//		if (low >= high)
//			return;
		
		if (high <= low + CUTOFF - 1){								//using insertion sort for tiny subarrays
			InsertSort.sort(a, low, high, c);
			return;
		}
		
		int pivot = (int)((high-low + 1)*Math.random()) + low;		//random pivot, avoid "the worst condition"
		swap(a, low, pivot);
		
		pivot = partition(a, low, high, c);
		sort(a, low, pivot-1, c);
		sort(a, pivot+1, high, c);
	}
	
	/*
	 * shuffle  id needed before this 3-way quick sort
	 */
	public static void sort3Way(Object[] a, int low, int high, Comparator c) {
		
		if (low >= high)
			return;
		
		int i = low + 1;
		int lt = low, gt = high;
		Object v = a[low];
		while (i <= gt){
			int cmp = c.compare(a[i], v);
			if (cmp < 0)
				swap(a, i++, lt++);
			else if (cmp > 0)
				swap(a, i, gt--);
			else 
				i++;
		}
		
		sort3Way(a, low, lt - 1, c);
		sort3Way(a, gt + 1, high, c);
	}
	/*
	 * select the number n elem in array
	 * shuffle is needed before selection
	 */
	public static Object select(Object[] a, int n, Comparator c) {
		int low = 0, high = a.length - 1;
		while (low < high){
			int j = partition(a, low, high, c);
			if (j < n)
				low = j + 1;
			else if (j > n)
				high = j - 1;
			else 
				break;
		}
		return a[n];
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Student[] studentList = new Student[10];
		for (int i = 0; i < studentList.length; i++) {
			studentList[i] = new Student((int)(10*Math.random()+1), i + "th");
		}
		
//		Student selectTest = (Student)select(studentList, 2, Student.BY_ID);
//		System.out.println(selectTest + " ");
		
//		sort(studentList, 0, studentList.length - 1, Student.BY_ID);
		sort3Way(studentList, 0, studentList.length - 1, Student.BY_ID);
		for (Student student : studentList) {
			System.out.print(student + " ");
		}
		
		System.out.println();
		
//		sort(studentList, 0, studentList.length - 1, Student.BY_NAME);
		sort3Way(studentList, 0, studentList.length - 1, Student.BY_NAME);
		for (Student student : studentList) {
			System.out.print(student + " ");
		}
	}

}
