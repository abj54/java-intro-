// GreatCircle.java: Takes four doubles x1, y1, x2, and y2 representing the
// latitude and longitude in degrees of two points on earth as command-line
// arguments and writes the great-circle distance d (in km) between them,
// calculated as d=111arccos(sin(x1)sin(x2)+cos(x1)cos(x2)cos(y1-y2)).

import edu.princeton.cs.algs4.StdOut;

public class GreatCircle {
    public static void main(String[] args) {
        double x1 = Double.parseDouble(args[0]);
        double y1 = Double.parseDouble(args[1]);
        double x2 = Double.parseDouble(args[2]);
        double y2 = Double.parseDouble(args[3]);
        double X1 = Math.toRadians(x1);
        double X2 = Math.toRadians(x2);
        double Y1 = Math.toRadians(y1);
        double Y2 = Math.toRadians(y2);
        double f = Math.acos(Math.sin(X1)*Math.sin(X2)+Math.cos(X1)
        *Math.cos(X2)*Math.cos(Y1-Y2)); 
        double d = 111*Math.toDegrees(f);
        StdOut.println(d);
    }
}
