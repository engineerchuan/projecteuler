import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class BruteCollinearPoints {
    
    private final List<LineSegment> finalsegments;
    
    public BruteCollinearPoints(Point[] points) {
        finalsegments = new ArrayList<LineSegment>();
        if (points.length < 4) {
            throw new IllegalArgumentException(" # points must be greater than 4");
        }
        for (int i = 0; i < points.length; ++i) {
            for (int j = i+1; j < points.length; ++j) {
                for (int k = j+1; k < points.length; ++k) {
                    for (int l = k+1; l < points.length; ++l) {
                        if (points[i].slopeTo(points[j]) == points[i].slopeTo(points[k]) &&
                            points[i].slopeTo(points[j]) == points[i].slopeTo(points[l])) {
                            
                            // we have a match!
                            Point[] segmentPoints = new Point[4];
                            segmentPoints[0] = points[i];
                            segmentPoints[1] = points[j];
                            segmentPoints[2] = points[k];
                            segmentPoints[3] = points[l];
                            
                            Arrays.sort(segmentPoints);
                            LineSegment ls = new LineSegment(segmentPoints[0], segmentPoints[3]);
                            finalsegments.add(ls);
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