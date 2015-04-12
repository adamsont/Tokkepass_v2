package Math;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Point {
	
	private DoubleProperty x;
	private DoubleProperty y;
	
	public Point() {
		this.x = new SimpleDoubleProperty(0);
		this.y = new SimpleDoubleProperty(0);
	}
	
	public Point(double x, double y) {
		this.x = new SimpleDoubleProperty(x);
		this.y = new SimpleDoubleProperty(y);
	}
	
	public Point(Point p) {
		this.x = p.x;
		this.y = p.y;
	}
	
	public Point getMiddlePoint(Point p) {
		double rx = (p.getX() + x.get()) / 2.0;
		double ry = (p.getY() + y.get()) / 2.0;
		
		return new Point(rx, ry);
	}
	
	public void addVector(double vx, double vy) {
		x.set(x.get() + vx);
		y.set(y.get() + vy);
	}
	
	public void zoom(double xref, double yref, double multiplier) {
		double vx = xref - this.x.get();
		double vy = yref - this.y.get();
		
		vx *= multiplier;
		vy *= multiplier;
		
		this.x.set(xref-vx);
		this.y.set(yref-vy);
	}

	public double getX() {
		return x.get();
	}

	public void setX(double x) {
		this.x.set(x);
	}

	public double getY() {
		return y.get();
	}

	public void setY(double y) {
		this.y.set(y);
	}

	public DoubleProperty getXProperty() {
		return x;
	}

	public void setXProperty(DoubleProperty x) {
		this.x = x;
	}

	public DoubleProperty getYProperty() {
		return y;
	}

	public void setYProperty(DoubleProperty y) {
		this.y = y;
	}
	
	
}
