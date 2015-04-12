package GUI;

import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

public class DrawArea {
	
	Group objectGroup;
	Rectangle whitespace;
	
	public DrawArea(double width, double height) {
		objectGroup = new Group();
		whitespace = new Rectangle(width, height, Color.WHITE);
		whitespace.setStroke(Color.BLACK);
		objectGroup.getChildren().add(whitespace);
		
	}
	
	public void drawCircle(double x, double y, double radius) {
		Circle circle = new Circle(x, y, radius);
		circle.setFill(Color.TRANSPARENT);
		circle.setStroke(Color.GRAY);
		circle.setStrokeWidth(4);
		
		addObject(circle);
	}
	
	public void drawDirGuide(double x, double y, double dir) {
		drawCircle(x, y, 50);
		drawDirArrow(0, Color.RED, x, y, 0, 8);
		drawDirArrow(3200, Color.BLUE, x, y, 0, 8);
		drawDirArrow(dir, Color.ORANGE, x, y, 30, 7);
	}
	
	public void drawDirArrow(double direction, Color color, double sx, double sy, double dist, double width) {
		Polygon dirArrow = new Polygon();
		
		double centerDist = dist;
		double centerWidth = width;
		double height = 50 - centerDist;
		
		double radDir = -((direction -3200) / 3200.0) * Math.PI;
		
		double topX, topY;
		double leftX, leftY;
		double rightX, rightY;
		double centerVectorX, centerVectorY;
		
		centerVectorX = Math.sin(radDir) * centerDist;
		centerVectorY = Math.cos(radDir) * centerDist;
		
		topX = Math.sin(radDir) * height + centerVectorX + sx;
		topY = Math.cos(radDir) * height + centerVectorY + sy;
		
		leftX = Math.sin(radDir-Math.PI / 2.0) * centerWidth + centerVectorX + sx;
		leftY = Math.cos(radDir-Math.PI / 2.0) * centerWidth + centerVectorY + sy;
		
		rightX = Math.sin(radDir+Math.PI / 2.0) * centerWidth + centerVectorX + sx;
		rightY = Math.cos(radDir+Math.PI / 2.0) * centerWidth + centerVectorY + sy;
		
		Double[] pointArray = new Double[]{topX, topY, leftX, leftY, rightX, rightY};
		
		dirArrow.getPoints().addAll(pointArray);
		dirArrow.setFill(color);
		
		addObject(dirArrow);
		
	}
	
	public void checkBoundaries() {
		for(Node node : objectGroup.getChildren()) {
			double nx = node.getLayoutBounds().getMinX() + node.getLayoutX();
			double ny = node.getLayoutBounds().getMinY() + node.getLayoutY();
			
			if(whitespace.contains(nx, ny)) {
				node.setVisible(true);
			} else {
				node.setVisible(false);
			}
			
		}
	}
	public void clear() {
		objectGroup.getChildren().clear();
		objectGroup.getChildren().add(whitespace);
		//drawDirGuide(60, 60);
	
	}
	public void addObjects(ArrayList<Node> nodes) {
		for(Node node : nodes) {
			objectGroup.getChildren().add(node);
		}
	}
	
	public void addObject(Node node) {
		objectGroup.getChildren().add(node);
	}
	
	public double getCenterX() {
		return whitespace.getWidth() / 2;
	}
	
	public double getCenterY() {
		return whitespace.getHeight() / 2;
	}
		
	public Group getObjectGroup() {
		return objectGroup;
	}

	public void setObjectGroup(Group objectGroup) {
		this.objectGroup = objectGroup;
	}

	public Rectangle getWhitespace() {
		return whitespace;
	}

	public void setWhitespace(Rectangle whitespace) {
		this.whitespace = whitespace;
	}
	
}
