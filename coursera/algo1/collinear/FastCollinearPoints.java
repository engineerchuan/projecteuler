import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class FastCollinearPoints {
    
    private final List<LineSegment> finalsegments;
    public FastCollinearPoints(Point[] points) {
        
        finalsegments = new ArrayList<LineSegment>();
        if (points.length < 4) {
            throw new IllegalArgumentException(" # points must be greater than 4");
        }
        for (int i = 0; i < points.length - 3 + 1; ++i) {
            Arrays.sort(points); // sort back to original order
            Arrays.sort(points, i+1, points.length, points[i].slopeOrder());
            
            int nMatch = 0;
            for (int j = i + 1; j < points.length; ++j) {
                if (points[i].slopeTo(points[j]) == points[i].slopeTo(points[j+1])) {
                    nMatch += 1;
                }
                else {
                    if (nMatch >= 2) {
                        // push a set of lines onto
                        int nPoints = nMatch + 1;
                        Point[] segmentPoints = new Point[nPoints];
                        for (int k = 0; k < nPoints; ++k) {
                            segmentPoints[k] = points[j - nMatch + k];
                        }
                        Arrays.sort(segmentPoints);
                        LineSegment ls = new LineSegment(points[i], segmentPoints[nPoints-1]);
                        finalsegments.add(ls);
                        
                    }
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