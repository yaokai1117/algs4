import java.util.Comparator;

public class MergeSort {

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
	
	public static void sort(Object[] a, Object[] aux, int low, int high, Comparator c) {
		if(low >= high)
			return;
		int mid = (low + high) / 2;
		sort(a, aux, low, mid, c);
		sort(a, aux, mid+1, high, c);
		merge(a, aux, low, mid, high, c);
	}
	
	
	public static void sortBU(Object[] a, Comparator c) {
		int N = a.length;
		Object[] aux = new Object[N]; 
		for (int size = 1; size < N; size = 2*size)
			for (int low = 0; low < N-size; low += 2*size)
				merge(a, aux, low, low+size-1, Math.min(low+2*size-1, N-1), c);
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Student[] studentList = new Student[10];
		Student[] aux = new Student[10];
		for (int i = 0; i < studentList.length; i++) {
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
	}

}
