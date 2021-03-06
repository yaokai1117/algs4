
public class Event implements Comparable<Event> {
	private final double time;
	private Particle a, b;
	private int countA, countB;
	
	public Event(double time, Particle a, Particle b) {
		// TODO Auto-generated constructor stub
		this.time = time;
		this.a = a;
		this.b = b;
		if (a != null)
			countA = a.getCount();
		if (b != null)
			countB = b.getCount();
	}
	
	public Particle getA(){
		return a;
	}
	
	public Particle getB() {
		return b;
	}
	
	public double getTime() {
		return time;
	}
	
	@Override
	public int compareTo(Event o) {
		// TODO Auto-generated method stub
		return (int)(10000000*(this.time - o.time));
	}
	
	public boolean isValid(double time) {
		if (a == null && b == null)
			return false;
		else if (a != null && b == null)
			return a.getCount() == countA;
		else if (a == null && b != null)
			return b.getCount() == countB;
		else 
			return a.getCount() == countA && b.getCount() == countB;
	}
	
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
