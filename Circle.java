/**
*12203645 
*Carl Jasper DUMO
*/
package week5;

public class Circle {
	private double radius;	
	public Circle(double r){
		radius = r;
	}

	public double getArea(){
		return Math.PI * radius * radius;
	}

	public double getRadius(){
		return radius;
	}

	public String toString(){
		String str;
		str = "Radius: " + radius +
		"Area: " + getArea();
		return str;
	}

	public boolean equals(Circle c){
		boolean status;
		if(c.getRadius() == radius)
		status = true;
		else
		status = false;
		return status;
	}

	public boolean greaterThan(Circle c){
		boolean status;
		if(c.getArea() > getArea())
		status = true;
		else
		status = false;
		return status;
	}
}

public static void main(String[] args) {
    Circle myObj1 = new Circle();  
    Circle myObj2 = new Circle();  
  }