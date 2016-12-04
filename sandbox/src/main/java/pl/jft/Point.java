package pl.jft;

/**
 * Created by nishi on 2016-12-04.
 */
public class Point {

  public double p1;
  public double p2;

  public Point(double p1, double p2) {
    this.p1 = p1;
    this.p2 = p2;
  }

  public double distance(Point point) {
    return Math.sqrt(Math.pow(point.p1 - this.p1, 2) + Math.pow(point.p2 - this.p2, 2));
  }
}
