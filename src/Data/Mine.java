package Data;

import java.awt.MouseInfo;
import java.io.File;

import GUI.MineImage;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.shape.Circle;

public class Mine {
	private int id;
	
	private DoubleProperty x;
	private DoubleProperty y;
	
	private boolean active;
	private boolean doubleCharge;
	
	private String fuze;
	
	private MineImage mineImage;
	private ImageView mineImg;

	public Mine(int id, double x, double y) {
		
		this.id = id;
		this.x = new SimpleDoubleProperty(5*x);
		this.y = new SimpleDoubleProperty(5*y);
		this.fuze = "normal";
		
		mineImage = new MineImage("normal", 17, this.x, this.y, this);
		mineImage.bindXY(this.x, this.y);
		
	}
	
	public String toString() {
		return "[ "+x.getValue().intValue()+","+ y.getValue().intValue()+" ] ";
	}
	
	public void zoom(double xref, double yref, double multiplier) {
		double vx = xref - x.get();
		double vy = yref - y.get();
		
		vx *= multiplier;
		vy *= multiplier;
		
		this.setX(xref-vx);
		this.setY(yref-vy);
	}
	
	public void zoomImage(double multiplier) {
		mineImage.zoom(multiplier);
	}
	
	public void addVector(double vx, double vy) {
		this.x.set(x.get()+vx);
		this.y.set(y.get()+vy);
	}
	
	public void destroy() {
		mineImg.xProperty().unbindBidirectional(this.x);
		mineImg.yProperty().unbindBidirectional(this.y);
		mineImg.setOnMouseDragged(null);
		mineImg = null;
	}
	
	
	
	public ImageView getMineImg() {
		return mineImg;
	}

	public void setMineImg(ImageView mineImg) {
		this.mineImg = mineImg;
	}
	
	
	public ImageView getMineImage() {
		return mineImage.getMineImage();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getX() {
		return x.getValue();
	}

	public void setX(double x) {
		this.x.set(x);
	}

	public double getY() {
		return y.getValue();
	}

	public void setY(double y) {
		this.y.set(y);
	}

	public boolean isDoubleCharge() {
		return doubleCharge;
	}

	public void setDoubleCharge(boolean doubleCharge) {
		this.doubleCharge = doubleCharge;
	}

	public String getFuze() {
		return fuze;
	}

	public void setFuze(String fuze) {
		this.fuze = fuze;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
}
