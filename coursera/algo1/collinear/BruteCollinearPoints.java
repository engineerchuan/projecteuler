import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
//import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints {
    
    private final List<LineSegment> finalsegments;
    private final Point[] points;
    
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
    public BruteCollinearPoints(Point[] points2) {
        
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
            // there will be no matches
        }
        else {
            for (int i = 0; i < points.length; ++i) {
                for (int j = i+1; j < points.length; ++j) {
                    for (int k = j+1; k < points.length; ++k) {
                        for (int l = k+1; l < points.length; ++l) {
                            if (points[i].slopeTo(points[j]) == points[i].slopeTo(points[k]) &&
                                points[i].slopeTo(points[j]) == points[i].slopeTo(points[l])) {
                                
                                // assumes points are already sorted
                                LineSegment ls = new LineSegment(points[i], points[l]);
                                //StdOut.println("pushing " + points[i] + points[l]);
                                finalsegments.add(ls);
                            }
                        }
                    }
                }
            }
        }        
    }    // finds all line segments containing 4 points
    public int numberOfSegments() {
        return finalsegments.size();
    }        // the number of line segments
    public LineSegment[] segments() {
        LineSegment[] ls = new LineSegment[finalsegments.size()];
        for (int i = 0; i < finalsegments.size(); ++i) {
            ls[i] = finalsegments.get(i);
        }
        return ls;
        
    }
    
}