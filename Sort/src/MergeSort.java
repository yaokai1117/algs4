import java.util.Comparator;

public class MergeSort {
	
	private static final int CUTOFF = 7;

	private static void merge(Object[] a, Object[] aux, int low, int mid, int high, Comparator c) {
		for (int k = low; k <= high; k++)
			aux[k] = a[k];
		
		int i = low, j = mid + 1;
		for (int k = low; k <= high; k++){
			if (i > mid)
				a[k] = aux[j++];
			else if (j > high)
				a[k] = aux[i++];
			else if (c.compare(aux[j], aux[i]) < 0)
				a[k] = aux[j++];
			else 
				a[k] = aux[i++];
		}
	}
	
	private static int mergeCR(Object[] a, Object[] aux, int low, int mid, int high, Comparator c) {
		for (int i = low; i <= high; i++)
			aux[i] = a[i];
		int i = low, j = mid + 1;
		int ret = 0;
		for (int k = low; k <= high; k++){
			if (i > mid)
				a[k] = aux[j++];
			else if (j > high)
				a[k] = aux[i++];
			else if (c.compare(aux[j], aux[i]) < 0){
				a[k] = aux[j++];
				ret += mid - i + 1;
			}
			else 
				a[k] = aux[i++];
		}
		return ret;
	}
	
	public static void sort(Object[] a, Object[] aux, int low, int high, Comparator c) {
		
//		if(low >= high)
//			return;
		
		if (high <= low + CUTOFF - 1){			// First step of optimizing, using insertion sort for tiny subarrays
			InsertSort.sort(a, low, high, c);
			return;
		}
		
		int mid = (low + high) / 2;
		sort(a, aux, low, mid, c);
		sort(a, aux, mid+1, high, c);
		if (c.compare(a[mid], a[mid+1]) >= 0)	// Second step of optimizing, stopped
			return;
		merge(a, aux, low, mid, high, c);
	}
	
	
	public static void sortBU(Object[] a, Comparator c) {
		int N = a.length;
		Object[] aux = new Object[N]; 
		for (int size = 1; size < N; size = 2*size)
			for (int low = 0; low < N-size; low += 2*size)
				merge(a, aux, low, low+size-1, Math.min(low+2*size-1, N-1), c);
	}
	
	
	public static int countReverse(Object[] a, Object[] aux, int low, int high, Comparator c) {
		if(low >= high)
			return 0;
		int mid = low + (high - low) / 2;
		int ret = 0;
		ret += countReverse(a, aux, low, mid, c);
		ret += countReverse(a, aux, mid + 1, high, c);
		ret += mergeCR(a, aux, low, mid, high, c);
		return ret;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Student[] studentList = new Student[10];
		Student[] aux = new Student[10];
		for (int i = 0; i < studentList.length; i++) {
//			studentList[i] = new Student((int)(10*Math.random()+1), i + "th");
			studentList[i] = new Student(10 - i, i + "th");
		}
		
		//sort(studentList, aux, 0, studentList.length-1, Student.BY_ID);
		sortBU(studentList, Student.BY_ID);
		for (Student student : studentList) {
			System.out.println(student);
		}
		
		//sort(studentList, aux, 0, studentList.length-1, Student.BY_NAME);
		sortBU(studentList, Student.BY_NAME);
		for (Student student : studentList) {
			System.out.println(student);
		}
		
		System.out.println(countReverse(studentList, aux, 0, studentList.length - 1, Student.BY_ID));
	}

}
