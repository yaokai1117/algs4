import java.util.Arrays;
import java.util.Comparator;


public class Fast {
	
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
		
		Point[] auxPoints = new Point[N];
		for (int i = 0; i < N; i++)
			auxPoints[i] =points[i];
		
		Arrays.sort(points);
		
		for (int i = 0; i < N; i++) {
			Arrays.sort(auxPoints);
			Comparator<Point> c = points[i].SLOPE_ORDER;
			Arrays.sort(auxPoints, c);
			int k = 0;
			for (int j = 0; j < N; j = k) {
				k = j + 1;
				while (k < N && c.compare(auxPoints[j], auxPoints[k]) == 0)
					k++;
				if (k-j >= 3 && auxPoints[j].compareTo(points[i]) > 0){
					points[i].drawTo(auxPoints[k-1]);
					System.out.print(points[i]);
					for (int l = j; l < k; l++)
						System.out.print(" -> " + auxPoints[l]);
					System.out.println();
				}
			}
		}
		
	}

}
