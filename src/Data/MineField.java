package Data;

import java.util.ArrayList;

import GUI.DataLine;
import Math.Point;
import Math.Utility;

public class MineField {
	
	int id;

	ArrayList<MineRow> mineRows;
	ArrayList<Mine> extraMines = new ArrayList<>();
	
	DataLine dataLine1;
	DataLine dataLine2;
	
	private double density, rowSpace;
	private int rowCount, rowMines;
	
	private double dir, crossDir;
	private double cornerX, cornerY;
	
	private int zoomed;
	String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXY";
	
	private ArrayList<Point> outCorners = new ArrayList<>();
	
	public MineField(int id, int rowCount, double rowSpace, int rowMines, double density, double dir, double crossDir) {
		
		this.id = id;
		this.rowCount = rowCount;
		this.rowMines = rowMines;
		this.rowSpace = rowSpace;
		this.density = density;
		this.dir = dir;
		this.crossDir = crossDir;
		this.zoomed = 0;
		this.cornerX = 0;
		this.cornerY = 0;
		
		mineRows = generateMineRows();
		genOutCorners(60);
		makeDataLines();
	}

	private ArrayList<MineRow> generateMineRows() {
		
		//Tuhandikud radiaanideks
		double radDir = (crossDir / 3200.0) * Math.PI;
		double tempX, tempY;
		boolean tempStag = false;
		
		char[] tempAlphabet = alphabet.toCharArray();
		char tempId;

		//Ridade vahekaugused
		double dX, dY;
		dX = rowSpace * Math.sin(radDir);
		dY = rowSpace * Math.cos(radDir);

		ArrayList<MineRow> outRows = new ArrayList<>(rowCount);
		MineRow tempRow;

		for (int i = 0; i<rowCount; i++) {
			
			tempX = 0 + dX*i;
			tempY = 0 + dY*i;
			tempId = tempAlphabet[i];
			
			tempRow = new MineRow(tempId, density, rowMines, dir, tempX, tempY, tempStag);
			outRows.add(tempRow);
			
			tempStag = !tempStag;
		}

		return outRows;
	}
	
	private void genOutCorners(double offset) {
		Point tpoint1 = new Point();
		Point tpoint2 = new Point();
		
		double radDir = (dir / 3200.0) * Math.PI;
		double radXDir = (crossDir / 3200.0) * Math.PI;
	
		double XsmallV = Math.sin(radDir) * offset;
		double YsmallV = Math.cos(radDir) * offset;
		
		double XsmallXV = Math.sin(radXDir) * offset;
		double YsmallXV = Math.cos(radXDir) * offset;
		
		double XbigV = Math.sin(radDir) * (getLocalWidth() * 5);
		double YbigV = Math.cos(radDir) * (getLocalWidth() * 5);
		
		double XbigXV = Math.sin(radXDir) * (getLocalDepth() * 5);
		double YbigXV = Math.cos(radXDir) * (getLocalDepth() * 5);
		
//		System.out.println(XsmallV);
//		System.out.println(YsmallV);
//		System.out.println(XsmallXV);
//		System.out.println(YsmallXV);
//		System.out.println("______________");
//		System.out.println(XbigV);
//		System.out.println(YbigV);
//		System.out.println(XbigXV);
//		System.out.println(YbigXV);
//		
//		----
//		|   |
//		 *---
		tpoint1.setX(-XsmallV);
		tpoint1.setY(YsmallV);
		
		tpoint2.setX(-XsmallXV);
		tpoint2.setY(-YsmallXV);
		
		outCorners.add(Utility.getMiddlePoint(tpoint1, tpoint2));
//		----
//		|   |
//		 ---*
		tpoint1.setX(XbigV + XsmallV);
		tpoint1.setY(YbigV + YsmallV);
		
		tpoint2.setX(XbigV - XsmallXV);
		tpoint2.setY(YbigV - YsmallXV);
		
		outCorners.add(Utility.getMiddlePoint(tpoint1, tpoint2));
//		*---
//		|   |
//		 ----
		tpoint1.setX(XbigXV + XsmallXV);
		tpoint1.setY(YbigXV + YsmallXV);
		
		tpoint2.setX(XbigXV - XsmallV);
		tpoint2.setY(YbigXV - YsmallV);
		
		outCorners.add(Utility.getMiddlePoint(tpoint1, tpoint2));
		
//		---*
//		|   |
//		 ----
		tpoint1.setX(XbigXV + XbigV + XsmallV);
		tpoint1.setY(YbigXV + YbigV + YsmallV);
		
		tpoint2.setX(XbigXV + XbigV + XsmallXV);
		tpoint2.setY(YbigXV + YbigV + YsmallXV);
	
		outCorners.add(Utility.getMiddlePoint(tpoint1, tpoint2));
		
	}
	
	private void makeDataLines() {
		dataLine1 = new DataLine(outCorners.get(2), outCorners.get(3), 
								String.valueOf(dir), String.valueOf(getLocalWidth()));
		dataLine2 = new DataLine(outCorners.get(3), outCorners.get(1), 
								String.valueOf(crossDir), String.valueOf(getLocalDepth()));
	}
	
	public double getCenterX() {
		
		MineRow lastRow = mineRows.get(mineRows.size() - 1);
		Mine lastMine = lastRow.getMines().get(lastRow.getMines().size() - 1);
		
		MineRow firstRow = mineRows.get(0);
		Mine firstMine = firstRow.getMines().get(0);
		
		double endX = lastMine.getX();
		double startX = firstMine.getX();
		
		return (endX - startX) / 2.0 + startX;
		
	}
	
	public double getCenterY() {
		
		MineRow lastRow = mineRows.get(mineRows.size() - 1);
		Mine lastMine = lastRow.getMines().get(lastRow.getMines().size() - 1);
		
		MineRow firstRow = mineRows.get(0);
		Mine firstMine = firstRow.getMines().get(0);
		
		double endY = lastMine.getY();
		double startY = firstMine.getY();
		
		return (endY - startY) / 2.0 + startY;
	}
	
	public void zoom(double xref, double yref, double multiplier) {
		if (multiplier > 1) {
			zoomed++;
		} else {
			zoomed--;
		}
		
		for (Point p : outCorners) {
			p.zoom(xref, yref, multiplier);
		}
		
		dataLine1.zoom(xref, yref, multiplier);
		dataLine2.zoom(xref, yref, multiplier);
		if(!extraMines.isEmpty()) {
			for(Mine mine : extraMines) {
				mine.zoom(xref, yref, multiplier);
			}
		}
		
		
		for(MineRow row : mineRows) {
			for(Mine mine : row.getMines()) {
				mine.zoom(xref, yref, multiplier);
			}
		}
		
	}
	
	public void addExtraMine() {
		Mine mine = new Mine(1, 10, 60);
		extraMines.add(mine);
	}
	
	public String toString() {
		String outString = "";
		
		for (MineRow mineRow : mineRows) {
			outString += mineRow.toString();
		}
		
		outString += "\n";
		return outString;
	}
	
	public void destroy() {
		for(MineRow mrow : mineRows) {
			mrow.destroy();
		}
		
		mineRows = null;
	}
	
	public void moveToPoint(double x, double y) {
		double vx = x - this.getCenterX();
		double vy = y - this.getCenterY();
		
		for(Point point : outCorners) {
			point.addVector(vx, vy);
		}
		
		dataLine1.addVector(vx, vy);
		dataLine2.addVector(vx, vy);
		
		if(!extraMines.isEmpty()) {
			for(Mine mine : extraMines) {
				mine.addVector(vx, vy);
			}
		}
		
		
		for(MineRow row : mineRows) {
			for(Mine mine : row.getMines()) {
				mine.addVector(vx, vy);
			}
		}
	}
	
	public ArrayList<Mine> getCornerMines() {
		ArrayList<Mine> returnMines = new ArrayList<>();
		
		ArrayList<Mine> firstRow = mineRows.get(0).getMines();
		Mine mine1 = firstRow.get(0);
		Mine mine2 = firstRow.get(firstRow.size() - 1);
		
		ArrayList<Mine> lastRow = mineRows.get(mineRows.size() - 1).getMines();
		Mine mine3 = lastRow.get(0);
		Mine mine4 = lastRow.get(lastRow.size() - 1);
		
		returnMines.add(mine1);
		returnMines.add(mine2);
		returnMines.add(mine3);
		returnMines.add(mine4);
		
		return returnMines;
	}
	
	public ArrayList<ArrayList<Integer>> getFullSummary() {
		
		ArrayList<ArrayList<Integer>> result = new ArrayList<>();
		
		for(MineRow mrow : mineRows) {
			result.add(mrow.getSummary());
		}
		
		return result;
	}
	
	public int getMineCount() {
		return rowCount * rowMines;
	}
	
	public ArrayList<MineRow> getMineRows() {
		return mineRows;
	}

	public void setMineRows(ArrayList<MineRow> mineRows) {
		this.mineRows = mineRows;
	}

	public double getDensity() {
		return density;
	}

	public void setDensity(double density) {
		this.density = density;
	}

	public double getDir() {
		return dir;
	}

	public void setDir(double dir) {
		this.dir = dir;
	}

	public double getCrossDir() {
		return crossDir;
	}

	public void setCrossDir(double crossDir) {
		this.crossDir = crossDir;
	}

	public DataLine getDataLine1() {
		return dataLine1;
	}

	public void setDataLine1(DataLine dataLine1) {
		this.dataLine1 = dataLine1;
	}

	public DataLine getDataLine2() {
		return dataLine2;
	}

	public void setDataLine2(DataLine dataLine2) {
		this.dataLine2 = dataLine2;
	}
	
	private double getLocalWidth() {
		return rowMines / density;
	}
	
	private double getLocalDepth() {
		return (rowCount -1) * rowSpace;
	}
	
	public ArrayList<Point> getOutCorners() {
		return outCorners;
	}

	public void setOutCorners(ArrayList<Point> outCorners) {
		this.outCorners = outCorners;
	}
	
	public ArrayList<Mine> getExtraMines() {
		return extraMines;
	}

	public void setExtraMines(ArrayList<Mine> extraMines) {
		this.extraMines = extraMines;
	}

	
	
	
	
}
