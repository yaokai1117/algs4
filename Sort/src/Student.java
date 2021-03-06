import java.util.Comparator;


public class Student {
		
		public static final Comparator<Student> BY_ID = new ById();
		public static final Comparator<Student> BY_NAME = new ByName();
		
		private int id;
		private String name;
		
		public Student (int id, String name) {
			this.id = id;
			this.name = name;
		}
		
		private static class ById implements Comparator<Student>{
			@Override
			public int compare(Student o1, Student o2) {
				return o1.id - o2.id;
			}
		}
		
		private static class ByName implements Comparator<Student> {
			@Override
			public int compare(Student o1, Student o2) {
				return o1.name.compareTo(o2.name);
			}
		}
		
		@Override
		public String toString() {
			return id +" " + "name: " +   name;
		}
		
	}