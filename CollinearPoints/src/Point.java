/*************************************************************************
 * Name: yaokai
 * Email: yaokai1117@gmail.com
 *
 * Compilation:  javac Point.java
 * Execution:
 * Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/

import java.util.Comparator;

public class Point implements Comparable<Point> {

    // compare points by slope
	public final Comparator<Point> SLOPE_ORDER = new BySlope();       // YOUR DEFINITION HERE

    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    // create the point (x, y)
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    // plot this point to standard drawing
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo(Point that) {
        if (this.x != that.x && this.y != that.y)
        	return ((double)this.y - that.y) / (this.x - that.x);
        else if (this.x != that.x)
        	return 0.0;
        else if (this.y != that.y)
        	return Double.POSITIVE_INFINITY;
        else 
        	return Double.NEGATIVE_INFINITY;
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) {
        if (this.y != that.y)
        	return this.y - that.y;
        else 
        	return this.x - that.x;
    }

    // return string representation of this point
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }
    
    private class BySlope implements Comparator<Point> {
    	@Override
    	public int compare(Point arg0, Point arg1) {
    		if (Point.this.slopeTo(arg0) < Point.this.slopeTo(arg1))
    			return -1;
    		else if (Point.this.slopeTo(arg0) == Point.this.slopeTo(arg1))
    			return 0;
    		else 
    			return 1;
    	}
    }

    // unit test
    public static void main(String[] args) {
        Point[] points = new Point[5];
        points[0] = new Point(1, 2);
        points[1] = new Point(2, 2);
        points[2] = new Point(1, 1);
        points[3] = new Point(0, 0);
        points[4] = new	Point(1, 2);
        
        StdDraw.setXscale(0, 5);
        StdDraw.setYscale(0, 5);
        for (Point point : points) {
			point.draw();
			System.out.println(points[3].slopeTo(point));
		}
    }
}