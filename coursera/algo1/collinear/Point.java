/******************************************************************************
  *  Compilation:  javac Point.java
  *  Execution:    java Point
  *  Dependencies: none
  *  
  *  An immutable data type for points in the plane.
  *  For use on Coursera, Algorithms Part I programming assignment.
  *
  ******************************************************************************/

import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class Point implements Comparable<Point> {
    
    private final int x;     // x-coordinate of this point
    private final int y;     // y-coordinate of this point
    
    /**
     * Initializes a new point.
     *
     * @param  x the <em>x</em>-coordinate of the point
     * @param  y the <em>y</em>-coordinate of the point
     */
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }
    
    /**
     * Draws this point to standard draw.
     */
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }
    
    /**
     * Draws the line segment between this point and the specified point
     * to standard draw.
     *
     * @param that the other point
     */
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }
    
    /**
     * Returns the slope between this point and the specified point.
     * Formally, if the two points are (x0, y0) and (x1, y1), then the slope
     * is (y1 - y0) / (x1 - x0). For completeness, the slope is defined to be
     * +0.0 if the line segment connecting the two points is horizontal;
     * Double.POSITIVE_INFINITY if the line segment is vertical;
     * and Double.NEGATIVE_INFINITY if (x0, y0) and (x1, y1) are equal.
     *
     * @param  that the other point
     * @return the slope between this point and the specified point
     */
    public double slopeTo(Point that) {
        if (this.x == that.x) { // vertical
            if (this.y == that.y) {
                return Double.NEGATIVE_INFINITY;
            }
            else {
                return Double.POSITIVE_INFINITY;
            }
        }
        else {
            if (this.y == that.y) {
                return 0.0;          
            }
            else {
                return (double) (that.y - this.y) / (double) (that.x - this.x);
            }
            
        }
    }
    
    /**
     * Compares two points by y-coordinate, breaking ties by x-coordinate.
     * Formally, the invoking point (x0, y0) is less than the argument point
     * (x1, y1) if and only if either y0 < y1 or if y0 = y1 and x0 < x1.
     *
     * @param  that the other point
     * @return the value <tt>0</tt> if this point is equal to the argument
     *         point (x0 = x1 and y0 = y1);
     *         a negative integer if this point is less than the argument
     *         point; and a positive integer if this point is greater than the
     *         argument point
     */
    public int compareTo(Point that) {
        int yDiff = this.y - that.y;
        if (yDiff == 0) {
            return this.x - that.x;
        }
        else {
            return yDiff;
        }
    }
    
    /**
     * Compares two points by the slope they make with this point.
     * The slope is defined as in the slopeTo() method.
     *
     * @return the Comparator that defines this ordering on points
     */
    public Comparator<Point> slopeOrder() {
        return new BySlope();
    }
    private class BySlope implements Comparator<Point> {
        public int compare(Point a, Point b) {
            double aSlope = Point.this.slopeTo(a);
            double bSlope = Point.this.slopeTo(b);
            if (aSlope < bSlope) {
                return -1;
            }
            else if (bSlope < aSlope) {
                return 1;
            }
            else {
                return 0;
            }
        }
    }
    
    /**
     * Returns a string representation of this point.
     * This method is provide for debugging;
     * your program should not rely on the format of the string representation.
     *
     * @return a string representation of this point
     */
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }
    
    /**
     * Unit tests the Point data type.
     */
    
    private static void shortAssert(String testID, double a, double b) {
        if (a != b) {
            StdOut.println("ERROR in " + testID + ":" + a + " != " + b);
        }
        else {
            StdOut.println("PASS " + testID);
        }
    }
    private static void shortAssertLessThan(String testID, double a, double b) {
        if (a >= b) {
            StdOut.println("ERROR in " + testID + ":" + a + " not less than " + b);
        }
        else {
            StdOut.println("PASS " + testID);
        }
    }
    public static void main(String[] args) {
        Point p0 = new Point(0, 0);
        Point p1 = new Point(3, 4); 
        Point p2 = new Point(6, 8);
        Point p3 = new Point(3, 10);
        Point p4 = new Point(-4, -5);
        
        // slope tests
        shortAssert("p1p2 slope", p1.slopeTo(p2), 4.0/3.0);
        shortAssert("p1p3 slope", p1.slopeTo(p3), Double.POSITIVE_INFINITY);
        shortAssert("same point slope", p3.slopeTo(p3), Double.NEGATIVE_INFINITY);          
        shortAssert("reverse", p3.slopeTo(p2), p2.slopeTo(p3));
        
        // comparator
        shortAssertLessThan("compare1", p4.compareTo(p1), 0);
        shortAssertLessThan("compare2", p1.compareTo(p3), 0);
        shortAssertLessThan("compare3", 0, p3.compareTo(p1));        
        shortAssert("compare4", 0 , p4.compareTo(p4));
        
        // slope order
        shortAssertLessThan("slope order 1", p1.slopeOrder().compare(p1, p2), 0);
        shortAssert("slope order 2", p0.slopeOrder().compare(p1, p2), 0);
        shortAssert("slope order 3", p0.slopeOrder().compare(p1, p2), 0);
        shortAssert("slope order 4", p1.slopeOrder().compare(p0, p2), 0);
        shortAssertLessThan("slope order 5", p0.slopeOrder().compare(p4, p1), 0);
    }
}
