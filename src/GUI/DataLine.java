package GUI;

import java.util.ArrayList;

import Math.Point;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class DataLine {
	
	Line line;
	Text dataTop;
	Text dataBottom;
	
	double centerX;
	double centerY;
	double dir;
	
	public DataLine(double sX, double sY, double eX, double eY, String dataTop, String dataBottom) {
		line = new Line(sX, sY, eX, eY);
		this.dataTop = new Text(dataTop);
		this.dataBottom = new Text(dataBottom);
		
		centerX = (sX-eX) / 2;
		centerY = (sY-eY) / 2;
		
		placeTexts();
		
	}
	
	public DataLine(Point startPoint, Point endPoint, String dataTop, String dataBottom) {
		
		line = new Line(startPoint.getX(), startPoint.getY(), endPoint.getX(), endPoint.getY());
		
		line.startXProperty().bindBidirectional(startPoint.getXProperty());
		line.startYProperty().bindBidirectional(startPoint.getYProperty());
		line.endXProperty().bindBidirectional(endPoint.getXProperty());
		line.endYProperty().bindBidirectional(endPoint.getYProperty());
		
		this.dataTop = new Text(dataTop);
		this.dataBottom = new Text(dataBottom);
		
		centerX = (startPoint.getX()+endPoint.getX()) / 2;
		centerY = (startPoint.getY()+endPoint.getY()) / 2;
		
		this.dir = (Double.parseDouble(dataTop) / 3200.0) * Math.PI;
		placeTexts();
	}
	
	private void placeTexts() {
		
		dataTop.xProperty().set(centerX-10);
		dataTop.yProperty().set(centerY-35);
		
		dataBottom.xProperty().set(centerX);
		dataBottom.yProperty().set(centerY-15);
		
		//dataTop.setRotate(90-dir*180.0 / Math.PI);
		//dataBottom.setRotate(90-dir*180.0 / Math.PI);
	}
	
	public void zoom(double xref, double yref, double multiplier) {
		double vxTop = xref - dataTop.xProperty().get();
		double vyTop = yref - dataTop.yProperty().get();
		
		double vxBottom = xref - dataBottom.xProperty().get();
		double vyBottom = yref - dataBottom.yProperty().get();
		
		vxTop *= multiplier;
		vyTop *= multiplier;
		
		vxBottom *= multiplier;
		vyBottom *= multiplier;
		
		dataTop.xProperty().set(xref-vxTop);
		dataTop.yProperty().set(yref-vyTop);
		
		dataBottom.xProperty().set(xref-vxBottom);
		dataBottom.yProperty().set(yref-vyBottom);
	}
	
	public void addVector(double vx, double vy) {
		
		//line.setStartX(line.getStartX()+vx);
		//line.setStartY(line.getStartY()+vy);
		
		//line.setEndX(line.getEndX()+vx);
		//line.setEndY(line.getEndY()+vy);
		
		System.out.println("Data added vector X: "+vx);
		System.out.println("Data added vector Y: "+vy);
		
		dataTop.xProperty().set(dataTop.xProperty().get() + vx);
		dataTop.yProperty().set(dataTop.yProperty().get() + vy + 30);
		dataBottom.xProperty().set(dataBottom.xProperty().get() + vx);
		dataBottom.yProperty().set(dataBottom.yProperty().get() + vy + 30);
		
	}
	public ArrayList<Node> getDrawAbleObjects() {
		
		ArrayList<Node> nodes = new ArrayList<>();
		nodes.add(line);
		nodes.add(dataBottom);
		nodes.add(dataTop);
		
		return nodes;
	}

	public Line getLine() {
		return line;
	}

	public void setLine(Line line) {
		this.line = line;
	}

	public double getCenterX() {
		return centerX;
	}

	public void setCenterX(double centerX) {
		this.centerX = centerX;
	}

	public double getCenterY() {
		return centerY;
	}

	public void setCenterY(double centerY) {
		this.centerY = centerY;
	}

	public Text getDataTop() {
		return dataTop;
	}

	public void setDataTop(Text dataTop) {
		this.dataTop = dataTop;
	}

	public Text getDataBottom() {
		return dataBottom;
	}

	public void setDataBottom(Text dataBottom) {
		this.dataBottom = dataBottom;
	}
	
	
	
	
}
