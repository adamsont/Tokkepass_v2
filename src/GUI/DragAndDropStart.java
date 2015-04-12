package GUI;

import java.awt.MouseInfo;

import Math.Utility;
import javafx.beans.property.DoubleProperty;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class DragAndDropStart implements EventHandler<MouseEvent> {
	
	private DoubleProperty mineX;
	private DoubleProperty mineY;
	private MineImage mineImage;
	
	public DragAndDropStart(DoubleProperty startX, DoubleProperty startY, MineImage mineImage) {
		this.mineX = startX;
		this.mineY = startY;
		this.mineImage = mineImage;
	}
	@Override
	public void handle(MouseEvent event) {
		if(event.isPrimaryButtonDown()) {
			System.out.println("Drag started");
			System.out.println("PointerX = "+MouseInfo.getPointerInfo().getLocation().getX());
			System.out.println("PointerY = "+MouseInfo.getPointerInfo().getLocation().getY());
			
			System.out.println("GivenX = "+mineX.get());
			System.out.println("GivenY = "+mineY.get());
			Utility.dragStartX = MouseInfo.getPointerInfo().getLocation().getX() - mineX.get();
			Utility.dragStartY = MouseInfo.getPointerInfo().getLocation().getY() - mineY.get();
		} else if(event.isSecondaryButtonDown()) {
			mineImage.setToggleCount(mineImage.getToggleCount()+1);
    		mineImage.getMineImage().setImage(mineImage.getImage(mineImage.getToggleType()));
		}
		
	}

}
