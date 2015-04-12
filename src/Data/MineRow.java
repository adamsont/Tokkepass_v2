package Data;

import java.util.ArrayList;

import Math.Utility;

public class MineRow {
	
	private int id;
	private ArrayList<Mine> mines;
	private int count;
	private double density;
	private double direction;
	private boolean staggered;
	
	public MineRow(int id, double density, int count, double direction, double startX, double startY, boolean staggered) {
		
		this.id = id;
		this.count = count;
		this.density = density;
		this.direction = direction;
		this.staggered = staggered;
		
		this.mines = generateRow(startX, startY);
	}
	
	private ArrayList<Mine> generateRow(double startX, double startY) {
		
		//Tuhandikud radiaanideks
		double radDir = (direction / 3200.0) * Math.PI;
		double tempX, tempY;
		int tempId;
		
		//Miinide vahekaugused
		double dX, dY;
		dX = (1 / density) * Math.sin(radDir);
		dY = (1 / density) * Math.cos(radDir);
		
		if (staggered) {
			startX = startX + dX/2.0;
			startY = startY + dY/2.0;
		}
		
		ArrayList<Mine> outMines = new ArrayList<>(count);
		Mine tempMine;
		
		for (int i = 0; i<count; i++) {
			
			tempX = startX + dX*i;
			tempY = startY + dY*i;
			tempId = this.id * 1000 + i + 1;
			
			tempMine = new Mine(tempId, tempX, tempY);
			outMines.add(tempMine);
		}
		
		return outMines;
	}
	
	public void destroy() {
		for(Mine mine : mines) {
			mine.destroy();
		}
		
		mines = null;
	}
	
	public ArrayList<Integer> getSummary() {
		ArrayList<Integer> result = new ArrayList<>();
		
		int normals = 0, demvs = 0, doubles = 0, magnets = 0, rods = 0, nones = 0;
		
		for(Mine mine : mines) {
			if(mine.getFuze().equals("demv")) {
				demvs++;
			} else if (mine.getFuze().equals("normal")) {
				normals++;
			} else if (mine.getFuze().equals("rod")) {
				rods++;
			} else if (mine.getFuze().equals("double")) {
				doubles++;
			} else if (mine.getFuze().equals("magnet")) {
				magnets++;
			} else if (mine.getFuze().equals("none")) {
				nones++;
			}
		}
		
		result.add(demvs);
		result.add(nones);
		result.add(normals);
		result.add(rods);
		result.add(doubles);
		result.add(magnets);
		result.add(Utility.sumArray(result));
		
		return result;
	}
	
	public String toString() {
		String outString = "";
		
		for (Mine mine : mines) {
			outString += mine.toString();
		}
		
		outString += "\n";
		return outString;
	}

	public ArrayList<Mine> getMines() {
		return mines;
	}

	public void setMines(ArrayList<Mine> mines) {
		this.mines = mines;
	}

	public double getDensity() {
		return density;
	}

	public void setDensity(double density) {
		this.density = density;
	}

	public double getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}
	
	
	
	
	
}
