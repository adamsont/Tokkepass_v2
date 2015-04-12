package Math;

import java.util.ArrayList;

public class PropertyCalculator {
	
	boolean widthGiven;
	boolean countGiven;
	boolean depthGiven;
	boolean rowCountGiven;
	boolean rowSpaceGiven;
	
	double width;
	int mineCount;
	double depth;
	int rowCount;
	double rowSpace;
	double milDensity;
	
	double rowDensity;
	int rowMines;
	
	
	public PropertyCalculator(ArrayList<Boolean> selections, ArrayList<Double> data) {
		widthGiven = selections.get(0);
		countGiven = selections.get(1);
		
		depthGiven = selections.get(2);
		rowCountGiven = selections.get(3);
		rowSpaceGiven = selections.get(4);
		
		width = data.get(0);
		mineCount = data.get(1).intValue();
		
		depth = data.get(2);
		rowCount = data.get(3).intValue();
		rowSpace = data.get(4);
		
		milDensity = data.get(5);
		
		calculate();
		//debug();
	}
	
	private void debug() {
		System.out.println("------------");
		System.out.println("width - "+width);
		System.out.println("mineCount : "+mineCount);
		System.out.println("depth : "+depth);
		System.out.println("rowCount : "+rowCount);
		System.out.println("rowSpace : "+rowSpace);
		System.out.println("density : "+rowDensity);
		System.out.println("------------");
	}
	
	private void calculate() {
		
		// Triforce 1
		if(!countGiven && widthGiven) {
			mineCount = (int) (width*milDensity);
			
		} else if(!widthGiven && countGiven) {
			width = mineCount / milDensity;
			
		} else if(!widthGiven && !countGiven){
			System.out.println("triforce1 calculate error");
		}
		
		//Triforce 2
		if(rowCountGiven && rowSpaceGiven && depthGiven) {
			depth = rowCount * rowSpace;
			
		} else if(rowCountGiven && rowSpaceGiven && !depthGiven) {
			depth = rowCount * rowSpace;
			
		} else if(rowCountGiven && !rowSpaceGiven && depthGiven) {
			rowSpace = depth / rowCount;
			
		} else if(!rowCountGiven && rowSpaceGiven && depthGiven) {
			rowCount = (int) (depth / rowSpace);
			
		} else {
			System.out.println("triforce2 calculate error");
		}
		
		rowMines = mineCount / rowCount;
		
		rowDensity = mineCount / (rowCount*width);
		
		if(rowDensity > 0.2) {
			rowDensity = 0.2;
		}
		
	}
	
	
	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public int getMineCount() {
		return mineCount;
	}

	public void setMineCount(int mineCount) {
		this.mineCount = mineCount;
	}

	public double getDepth() {
		return depth;
	}

	public void setDepth(double depth) {
		this.depth = depth;
	}

	public int getRowCount() {
		return rowCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

	public double getRowSpace() {
		return rowSpace;
	}

	public void setRowSpace(double rowSpace) {
		this.rowSpace = rowSpace;
	}

	public double getMilDensity() {
		return milDensity;
	}
	
	public int getRowMines() {
		return rowMines;
	}

	public void setRowMines(int rowMines) {
		this.rowMines = rowMines;
	}

	public void setMilDensity(double milDensity) {
		this.milDensity = milDensity;
	}

	public double getRowDensity() {
		return rowDensity;
	}

	public void setRowDensity(double rowDensity) {
		this.rowDensity = rowDensity;
	}

}
