import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
// import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {
    
    private final Point[] points;
    private final List<LineSegment> finalsegments;
    
    private void checkArguments1(Point[] points) {
        if (points == null) {
          throw new IllegalArgumentException("points is null");
        }
        for (int i = 0; i < points.length; ++i) {
            if (points[i] == null) {
              throw new IllegalArgumentException("a point in here is null");
            }
        }
    
    }
    private void checkArguments2(Point[] points) {
        Arrays.sort(points);
        for (int i = 0; i < points.length-1 ; ++i) {
            if (points[i].compareTo(points[i+1]) == 0) {
              throw new IllegalArgumentException("repeated point");
            }
        }
    }
    public FastCollinearPoints(Point[] points2) {
        
        checkArguments1(points2);
        
        // copy over
        points = new Point[points2.length];
        for (int i = 0; i < points2.length; ++i) {
            points[i] = points2[i];
        }
        checkArguments2(points);
        Arrays.sort(points);
        
        finalsegments = new ArrayList<LineSegment>();
        
        if (points.length < 4) {
            // pass, cannot possibly have a match
        }
        else {
            // StdOut.println("points length" + points.length);
            
            for (int i = 0; i < points.length; ++i) {
                Arrays.sort(points); // sort back to original order
                Point pt = points[i];
                // StdOut.println("point i = " + pt);
                
                Arrays.sort(points, pt.slopeOrder());
                
                List<Integer> breaks = new ArrayList<Integer>(); // start / end
                int lastStart = 0;
                for (int j = 0; j < points.length-1; ++j) {
                    if ((pt.slopeTo(points[j]) != pt.slopeTo(points[j+1]))) {
                        if ((j + 1 - lastStart) >= 3) {
                            breaks.add(lastStart);
                            breaks.add(j+1);
                            
                            // StdOut.println("pushing a break " + lastStart + ", " + (j+1));
                            lastStart = j+1;
                        }
                        else {
                            lastStart = j+1;
                        }
                    }
                }
                if ((points.length - lastStart) >= 3) {
                    breaks.add(lastStart);
                    breaks.add(points.length);
                    // StdOut.println("pushing a break at end " + lastStart + ", " + points.length);
                }
                
                // do the breaks
                for (int k = 0; k < breaks.size(); k += 2) {
                    int start = breaks.get(k);
                    int end = breaks.get(k+1);
                    Arrays.sort(points, start, end);
                    LineSegment ls;
                    
                    if (pt.compareTo(points[start]) < 0) {
                        ls = new LineSegment(pt, points[end-1]);
                        
                        finalsegments.add(ls);
                        // StdOut.println("push " + ls);
                        
                    }
                    
                    // else if (pt.compareTo(points[end-1]) > 0) {
                    //  ls = new LineSegment(points[start], pt);
                    // } else {
                    //  ls = new LineSegment(points[start], points[end-1]);
                    // }
                    
                }
                
            }
        }
    }
    public int numberOfSegments() {
        return finalsegments.size();
    }
    public LineSegment[] segments() {
        LineSegment[] ls = new LineSegment[finalsegments.size()];
        for (int i = 0; i < finalsegments.size(); ++i) {
            ls[i] = finalsegments.get(i);
        }
        return ls;
    }
    
}