import java.util.Arrays;



public class Brute {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		In in = new In(args[0]);
		int N = in.readInt();
		Point[] points = new Point[N];
		
		StdDraw.setXscale(0, 32768);
		StdDraw.setYscale(0, 32768);
		for (int i = 0; i < N; i++) {
			int x = in.readInt();
			int y = in.readInt();
			points[i] = new Point(x, y);
			points[i].draw();
		}
		
		//very important for drawing lines
		Arrays.sort(points);
		
		for (int i = 0; i < N; i++) 
			for (int j = i + 1; j < N; j++)
				for (int k = j + 1; k < N; k++){
					if (points[i].slopeTo(points[j]) != points[i].slopeTo(points[k]))
						continue;
					for (int l = k + 1; l < N; l++)
						if (points[i].slopeTo(points[k]) == points[i].slopeTo(points[l])) {
							points[i].drawTo(points[l]);				//sort before is needed
							System.out.println(points[i] + " -> " + points[j] + " -> " + points[k] + " -> " + points[l]);
						}
				}
	}

}
