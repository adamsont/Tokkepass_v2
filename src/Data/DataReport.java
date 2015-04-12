package Data;

import java.util.ArrayList;

import Math.Utility;

public class DataReport {
	
	// Arvutatud:
	
	//1.
	private String orderOrigin; //K�suandja: lipnik Ants Antson
	private String placingUnit; //Paigaldav �ksus: 32.jvp/PiR/3.jagu
	private String unitXO; //paigaldava �ksuse juht:Peeter Pakiraam
	private String XORank; //paigaldava �ksuse juhi auaste:  Seersant 
	
	//2.
	private String sDate; //Alguse kuup�ev: 15 MAR 15
	private String sTime; //Alguse kellaaeg: 0800 (algul oli �hes hunnikus sDate-iga, kuid viimases lahtris on tarvis kuup�eva eraldi) (Tegelikult peaks olema kuup�ev ja kellaaeg j�rjest)
	private String eDate; //L�petamise kuup�ev: 15 1400 MAR 15
	private String eTime;
	private String composer; //Passi valmistaja: seersant Peeter Pakiraam
	
	//4.
	private String mfNumber; //Miiniv�lja number: 3210/1
	
	//8. 
	private String fenceDesc; //Tarastuse kirjeldus: standardne
	
	//9.
	private int rowCount; //Asetatud miiniridade/-v��ndite arv: 6
	private String rowDesc; //V��ndite/ridade t�histuse kirjeldus: Ei ole
	
	//10.
	private String passWidth; //L�bip��su laius
	private String passMarkings; //L�bip��su m�rgistus
	private String passCloseMethod; //Sulgemismeetod
	
	//11.
	private String minefieldType; //Miiniv�lja t��p: siduv
	private int mineCount; //Miinide koguarv
	private int minesInRow; //Miinide arv reas
	private String minePosition; //Miinid maapinnal/pinnases
	private String mineDescription; // Tabel, milles miinide arv ja t��p ridades
	
	
	//12 M�RKUSTE TABEL, milles on mitmed read vabatekstina j�rjekorras SISULISELT POLE VAJA MUUD PEALE �HE KIRJAKASTI
	private double mineSpacing; //Miinide vahe reas: 5(m)
	private String antiInfantryPlacement; //Komistustraadiga jalav�evastaste miinide asukoht NAGU ARU SAIN SIIS NATOS POLE LUBATUD JALAV�EVASTASED MIINID.
	private String splintLoc; //Ohutussplintide/ -vahendite asukoht (s�tikusplindid ja muu nodi)
	private String passCloseMinesLoc; //L�bip��su sulgemiseks etten�htud miinide asukoht
	private String deminersGuide; //Informatsioon demineerijatele
	
	//14 millimeeterpaberi k�ljel
	
	private double enemyDir; //Vastase l�henemise suund (noolega, oletan, et teil selleks oma muutuja)
	//double north; //P�hjasuund (j�llegi noolega)
	
	//17
	private String classification; //Salastusaste: �ppus/salajane (siduda kuidagi dropdown men��sse)
	private ArrayList<ArrayList<Integer>> mineSummary;
	
	public DataReport() {
		
	}
	
	public ArrayList<Integer> getFullSumArray() {
		int size = mineSummary.get(0).size();
		
		ArrayList<Integer> result = new ArrayList<>();
		
		for(int i = 0; i<size; i++) {
			result.add(Utility.sumMatrixColumn(mineSummary, i));
		}
		
		return result;
	}
	
	public String getOrderOrigin() {
		return orderOrigin;
	}

	public void setOrderOrigin(String orderOrigin) {
		this.orderOrigin = orderOrigin;
	}

	public String getPlacingUnit() {
		return placingUnit;
	}

	public void setPlacingUnit(String placingUnit) {
		this.placingUnit = placingUnit;
	}

	public String getUnitXO() {
		return unitXO;
	}

	public void setUnitXO(String unitXO) {
		this.unitXO = unitXO;
	}

	public String getXORank() {
		return XORank;
	}

	public void setXORank(String xORank) {
		XORank = xORank;
	}

	public String getsDate() {
		return sDate;
	}

	public void setsDate(String sDate) {
		this.sDate = sDate;
	}

	public String getsTime() {
		return sTime;
	}

	public void setsTime(String sTime) {
		this.sTime = sTime;
	}

	public String geteDate() {
		return eDate;
	}

	public void seteDate(String eDate) {
		this.eDate = eDate;
	}

	public String geteTime() {
		return eTime;
	}

	public void seteTime(String eTime) {
		this.eTime = eTime;
	}

	public String getComposer() {
		return composer;
	}

	public void setComposer(String composer) {
		this.composer = composer;
	}

	public String getMfNumber() {
		return mfNumber;
	}

	public void setMfNumber(String mfNumber) {
		this.mfNumber = mfNumber;
	}

	public String getFenceDesc() {
		return fenceDesc;
	}

	public void setFenceDesc(String fenceDesc) {
		this.fenceDesc = fenceDesc;
	}

	public int getRowCount() {
		return rowCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

	public String getRowDesc() {
		return rowDesc;
	}

	public void setRowDesc(String rowDesc) {
		this.rowDesc = rowDesc;
	}

	public String getPassWidth() {
		return passWidth;
	}

	public void setPassWidth(String passWidth) {
		this.passWidth = passWidth;
	}

	public String getPassMarkings() {
		return passMarkings;
	}

	public void setPassMarkings(String passMarkings) {
		this.passMarkings = passMarkings;
	}

	public String getPassCloseMethod() {
		return passCloseMethod;
	}

	public void setPassCloseMethod(String passCloseMethod) {
		this.passCloseMethod = passCloseMethod;
	}

	public String getMinefieldType() {
		return minefieldType;
	}

	public void setMinefieldType(String minefieldType) {
		this.minefieldType = minefieldType;
	}

	public int getMineCount() {
		return mineCount;
	}

	public void setMineCount(int mineCount) {
		this.mineCount = mineCount;
	}

	public int getMinesInRow() {
		return minesInRow;
	}

	public void setMinesInRow(int minesInRow) {
		this.minesInRow = minesInRow;
	}

	public String getMinePosition() {
		return minePosition;
	}

	public void setMinePosition(String minePosition) {
		this.minePosition = minePosition;
	}

	public String getMineDescription() {
		return mineDescription;
	}

	public void setMineDescription(String mineDescription) {
		this.mineDescription = mineDescription;
	}

	public double getMineSpacing() {
		return mineSpacing;
	}

	public void setMineSpacing(double mineSpacing) {
		this.mineSpacing = mineSpacing;
	}

	public String getAntiInfantryPlacement() {
		return antiInfantryPlacement;
	}

	public void setAntiInfantryPlacement(String antiInfantryPlacement) {
		this.antiInfantryPlacement = antiInfantryPlacement;
	}

	public String getSplintLoc() {
		return splintLoc;
	}

	public void setSplintLoc(String splintLoc) {
		this.splintLoc = splintLoc;
	}

	public String getPassCloseMinesLoc() {
		return passCloseMinesLoc;
	}

	public void setPassCloseMinesLoc(String passCloseMinesLoc) {
		this.passCloseMinesLoc = passCloseMinesLoc;
	}

	public String getDeminersGuide() {
		return deminersGuide;
	}

	public void setDeminersGuide(String deminersGuide) {
		this.deminersGuide = deminersGuide;
	}

	public double getEnemyDir() {
		return enemyDir;
	}

	public void setEnemyDir(double enemyDir) {
		this.enemyDir = enemyDir;
	}

	public String getClassification() {
		return classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

	public ArrayList<ArrayList<Integer>> getMineSummary() {
		return mineSummary;
	}

	public void setMineSummary(ArrayList<ArrayList<Integer>> mineSummary) {
		this.mineSummary = mineSummary;
	}
	
	
	
	//18 J�RJESTUS MILLES ON: (Andy failis on siia nimetatud 1 String box)
	/*AUASTE(xoRank)
	NIMI (unitXO)
	KUUP�EV (sDate)
	(allkiri)-NOPE */
	
	
}
