package Math;

import java.io.File;
import java.util.ArrayList;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;

public class Utility {
	
	public static Image normalMine = new Image("file:///"+new File("resources/miin.png").getAbsolutePath());
	public static Image demMine = new Image("file:///"+new File("resources/demv.png").getAbsolutePath());
	public static Image magnetMine = new Image("file:///"+new File("resources/magnet.png").getAbsolutePath());
	public static Image dblMine = new Image("file:///"+new File("resources/dbl_miin.png").getAbsolutePath());
	public static Image rodMine = new Image("file:///"+new File("resources/rod.png").getAbsolutePath());
	public static Image noMine = new Image("file:///"+new File("resources/Planeeritav.png").getAbsolutePath());
	
	private double minDist = 5;
	
	public static double dragStartX;
	public static double dragStartY;
	
	static public Point getMiddlePoint(Point p1, Point p2) {
		double rx = (p1.getX() + p2.getX()) / 2.0;
		double ry = (p1.getY() + p2.getY()) / 2.0;
		
		return new Point(rx, ry);
	}
	
	static double calcMissingCount(double milDensity, double width, double depth, double rowSpace) {
		double result = 0;
		double rows = depth / rowSpace;
		
		//TODO: Miinimumiga arvestada
		double density = milDensity / rows;
		
		result = width*rows*density;
		return result;
	}
	
	public static ArrayList<Boolean> getSelectionArray (ArrayList<RadioButton> t1, ArrayList<RadioButton> t2) {
		ArrayList<Boolean> returnList = new ArrayList<>();
		returnList.add(t1.get(0).isSelected());
		returnList.add(t1.get(1).isSelected());
		
		returnList.add(t2.get(0).isSelected());
		returnList.add(t2.get(1).isSelected());
		returnList.add(t2.get(2).isSelected());
		
		return returnList;
	}
	public static double parseDensityValue(StringProperty milDensity) {
		if(milDensity.get().equals("Lagundada")) {
			return 0.4;
		} else if(milDensity.get().equals("Siduda")) {
			return 0.6;
		} else if(milDensity.get().equals("Suunata")) {
			return 1;
		} else if(milDensity.get().equals("Seisata")) {
			return 1.3;
		} else {
			return 0.4;
		}
	}
	
	public static ArrayList<Double> buildList(StringProperty width, StringProperty mineCount, 
												StringProperty depth, StringProperty rowCount, 
											StringProperty rowSpace, StringProperty milDensity) {
		
		ArrayList<Double> returnList = new ArrayList<>();
		
		returnList.add(Double.parseDouble(width.get()));
		returnList.add(Double.parseDouble(mineCount.get()));
		returnList.add(Double.parseDouble(depth.get()));
		returnList.add(Double.parseDouble(rowCount.get()));
		returnList.add(Double.parseDouble(rowSpace.get()));
		returnList.add(parseDensityValue(milDensity));
		
		return returnList;
		
	}
	
	static double calcWidth(double milDensity, double count) {
		return count / milDensity;
	}
	
	static double calcCount(double milDensity, double width) {
		return width*milDensity;
	}
	
	static double calcDepth(double rowSpace, double rows) {
		return rowSpace*rows;
	}
	
	static double calcRowSpace(double rows, double depth) {
		return depth / rows;
	}
	
	public static int sumArray(ArrayList<Integer> data) {
		int sum = 0;
		
		for(Integer element : data) {
			sum += element;
		}
		
		return sum;
	}
	
	public static int sumMatrixColumn(ArrayList<ArrayList<Integer>> matrix, int index) {
		
		int sum = 0;
		
		for(ArrayList<Integer> row : matrix) {
			sum += row.get(index);
		}
		
		return sum;
	}
	
}
