package pl.jft;

public class MyFirstProgram {
	
	public static void main(String[] args) {
		hello("world");
		hello("user");
		hello("Maciek");

		Square s = new Square(7);
		System.out.println("Area of square with side " + s.l + " = " + s.area());

		Rectangle r = new Rectangle(3, 4);
		System.out.println("Area of rectangle with sides " + r.a + " and " + r.b + " = " + r.area());
	}

	public static void hello(String somebody) {
		System.out.println("Hello " + somebody + "!");
	}
	
}