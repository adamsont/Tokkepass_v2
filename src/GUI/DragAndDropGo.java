package GUI;

import java.awt.MouseInfo;
import java.awt.Point;

import Math.Utility;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class DragAndDropGo implements EventHandler<MouseEvent>{
	
	private DoubleProperty x = new SimpleDoubleProperty();
	private DoubleProperty y = new SimpleDoubleProperty();
	
	public DragAndDropGo(DoubleProperty x, DoubleProperty y) {
		this.x.bindBidirectional(x);
		this.y.bindBidirectional(y);
		
	}
	@Override
	public void handle(MouseEvent event) {
		
		if(event.isPrimaryButtonDown()) {
			Point p = MouseInfo.getPointerInfo().getLocation();
			
			this.x.set(p.x - Utility.dragStartX);
			this.y.set(p.y - Utility.dragStartY);
			
		}
		
		
	}

}
