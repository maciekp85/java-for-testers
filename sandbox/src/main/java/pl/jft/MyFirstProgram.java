package pl.jft;

public class MyFirstProgram {
	
	public static void main(String[] args) {

		// My homework
		// Lesson 1. Basics of programming
		// Exercise No. 2 Training with using functions, classes, objects and methods

		// 2.3
		Point a = new Point(2,5);
		Point b = new Point(5,9);

		Point c = new Point(-4, 1);
		Point d = new Point(4, 7);

		System.out.println("Distance beetwen points a=(" + a.p1 + ", " + a.p2 + ") " + "and b=" + "(" + b.p1 + ", " + b.p2 + ") = " + distance(a, b) );
		System.out.println("Distance beetwen points a=(" + c.p1 + ", " + c.p2 + ") " + "and b=" + "(" + d.p1 + ", " + d.p2 + ") = " + distance(c, d) );

		// 2.4
		System.out.println("Distance beetwen points a=(" + a.p1 + ", " + a.p2 + ") " + "and b=" + "(" + b.p1 + ", " + b.p2 + ") = " + a.distance(b) );
		System.out.println("Distance beetwen points a=(" + c.p1 + ", " + c.p2 + ") " + "and b=" + "(" + d.p1 + ", " + d.p2 + ") = " + c.distance(d) );

	}

	public static double distance(Point p1, Point p2) {
		return Math.sqrt(Math.pow(p2.p1 - p1.p1, 2) + Math.pow(p2.p2 - p1.p2, 2));
	}

}