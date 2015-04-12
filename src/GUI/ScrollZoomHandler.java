package GUI;

import java.awt.MouseInfo;

import Data.MineField;
import javafx.event.EventHandler;
import javafx.scene.input.ScrollEvent;
import javafx.scene.shape.Rectangle;

public class ScrollZoomHandler implements EventHandler<ScrollEvent>{
	
	MineField mfield;
	DrawArea drawArea;
	
	public ScrollZoomHandler(MineField mfield, DrawArea drawArea) {
		this.mfield = mfield;
		this.drawArea = drawArea;
	}
	@Override
	public void handle(ScrollEvent event) {
		if(event.getDeltaY() < 0) {
			mfield.zoom(mfield.getCenterX(), mfield.getCenterY(), 0.8);
		} else {
			mfield.zoom(mfield.getCenterX(), mfield.getCenterY(), 1.25);
		}
		drawArea.checkBoundaries();
		
	}
	
	public void setMineField(MineField mfield) {
		this.mfield = mfield;
	}

}
