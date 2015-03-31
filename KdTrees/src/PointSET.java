import java.util.TreeSet;
import java.util.Stack;

public class PointSET {

	private TreeSet<Point2D> pSet;
	
	public PointSET() {
		pSet = new TreeSet<Point2D>();
	}
	
	public boolean isEmpty() {
		return pSet.isEmpty();
	}
	
	public int size() {
		return pSet.size();
	}
	
	public void insert(Point2D p) {
		if (p == null)
			throw new NullPointerException();
		pSet.add(p);
	}
	
	public boolean contains(Point2D p) {
		if (p == null)
			throw new NullPointerException();
		return pSet.contains(p);
	}
	
	public void draw() {
		for (Point2D point2d : pSet) {
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.setPenRadius(.01);
			point2d.draw();
		}
	}
	
	public Iterable<Point2D> range(RectHV rect) {
		if (rect == null)
			throw new NullPointerException();
		Stack<Point2D> point2ds = new Stack<Point2D>();
		for (Point2D point2d : pSet) {
			if (rect.contains(point2d))
				point2ds.push(point2d);
		}
		return point2ds;
	}
	
	public Point2D nearest(Point2D p) {
		if (p == null)
			throw new NullPointerException();
		if (pSet.isEmpty())
			return null;
		Point2D retPoint2d = pSet.first();
		for (Point2D point2d : pSet) {
			if (nearer(p, point2d, retPoint2d))
				retPoint2d = point2d;
		}
		return retPoint2d;
	}
	private boolean nearer(Point2D dest, Point2D p1, Point2D p2) {
		double x0 = dest.x(), y0 = dest.y(), x1 = p1.x(), y1 = p1.y(), x2 = p2.x(), y2 = p2.y();
		return ((x1-x0)*(x1-x0) + (y1-y0)*(y1-y0)) < ((x2-x0)*(x2-x0) + (y2-y0)*(y2-y0));
	}
	
	/**
	 * @param args
 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		In in = new In(args[0]);
		StdDraw.show(0);
		
		PointSET brute = new PointSET();
		while (!in.isEmpty()) {
				double x = in.readDouble();
				double y = in.readDouble();
				Point2D p = new Point2D(x, y);
				brute.insert(p);
		}
		
		for (Point2D point2d : brute.pSet) {
			System.out.println(point2d);
		}
		
		while (true) {

            // the location (x, y) of the mouse
            double x = StdDraw.mouseX();
            double y = StdDraw.mouseY();
            Point2D query = new Point2D(x, y);

            // draw all of the points
            StdDraw.clear();
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.setPenRadius(.01);
            brute.draw();

            // draw in red the nearest neighbor (using brute-force algorithm)
            StdDraw.setPenRadius(.03);
            StdDraw.setPenColor(StdDraw.RED);
            brute.nearest(query).draw();
            StdDraw.setPenRadius(.02);

//            // draw in blue the nearest neighbor (using kd-tree algorithm)
//            StdDraw.setPenColor(StdDraw.BLUE);
//            kdtree.nearest(query).draw();
            StdDraw.show(0);
            StdDraw.show(40);
        }
	}

}
