package GUI;

import java.io.File;

import Data.Mine;
import Math.Utility;
import javafx.beans.property.DoubleProperty;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class MineImage {
	
	ImageView mineImage;
	Mine ownerMine;
	int toggleCount = 0;
	
	public MineImage(String type, int size, DoubleProperty x, DoubleProperty y, Mine ownerMine) {
		mineImage = getImgView(type, size);
		addDragAndDrop(x, y);
		this.ownerMine = ownerMine;
	}
	
	public void bindXY(DoubleProperty x, DoubleProperty y) {
		//mineImage.xProperty().addListener(new OffsetListener(x, 30));
		//mineImage.yProperty().addListener(new OffsetListener(y, 30));
		
		//x.addListener(new OffsetListener(mineImage.xProperty(), -30));
		//y.addListener(new OffsetListener(mineImage.yProperty(), -30));
		
		this.mineImage.xProperty().bind(x.add(mineImage.getFitWidth() / 2.0));
		this.mineImage.yProperty().bind(y.add(mineImage.getFitHeight() / 2.0));
		
		//x.bind(mineImage.xProperty().add(10));
		//y.bind(mineImage.yProperty().add(10));
	}
	
	private void addDragAndDrop(DoubleProperty x, DoubleProperty y) {
		mineImage.setOnMousePressed(new DragAndDropStart(x, y, this));
		mineImage.setOnMouseDragged(new DragAndDropGo(x, y));
		
	}
	
	private ImageView getImgView(String type, int width){
		
		Image image = getImage(type);
			
		ImageView iv = new ImageView();
		iv.setImage(image);
		
		if(width != -1){
			iv.setFitWidth(width);
		} else {
			iv.setFitWidth(3);
		}
		
		iv.setPreserveRatio(true);
		
		return iv;
	}
	public String getToggleType() {
		String imageString;
		
		if(toggleCount == 1) {
			imageString = "demv";
			ownerMine.setFuze("demv");
		}  else if (toggleCount == 2) {
			imageString = "magnet";
			ownerMine.setFuze("magnet");
		} else if (toggleCount == 3) {
			imageString = "double";
			ownerMine.setFuze("double");
		} else if (toggleCount == 4) {
			imageString = "rod";
			ownerMine.setFuze("rod");
		} else if (toggleCount == 5) {
			imageString = "none";
			ownerMine.setFuze("none");
		} else {
			toggleCount = 0;
			imageString = "normal";
			ownerMine.setFuze("normal");
		}
		
		return imageString;
	}
	
	public Image getImage(String type) {
		Image image;
		
		if(type.equals("demv")) {
			image = Utility.demMine;
		}  else if (type.equals("magnet")) {
			image = Utility.magnetMine;
		} else if (type.equals("double")) {
			image = Utility.dblMine;
		} else if (type.equals("rod")) {
			image = Utility.rodMine;
		} else if (type.equals("none")) {
			image = Utility.noMine;
		} else {
			image = Utility.normalMine;
		}
		
		return image;
	}
	public void zoom(double multiplier) {
		
		mineImage.setScaleX(mineImage.getScaleX()*multiplier);
		mineImage.setScaleY(mineImage.getScaleY()*multiplier);
		
		mineImage.scaleXProperty();
		mineImage.scaleYProperty();
	}

	public ImageView getMineImage() {
		return mineImage;
	}

	public void setMineImage(ImageView mineImage) {
		this.mineImage = mineImage;
	}

	public int getToggleCount() {
		return toggleCount;
	}

	public void setToggleCount(int toggleCount) {
		this.toggleCount = toggleCount;
	}
	
}
