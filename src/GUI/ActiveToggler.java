package GUI;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class ActiveToggler implements EventHandler<ActionEvent>{
	
	private TextField toggleField;
	private RadioButton selectButton;
	
	public ActiveToggler(TextField toggleField, RadioButton selectButton) {
		this.toggleField = toggleField;
		this.selectButton = selectButton;
	}
	
	@Override
	public void handle(ActionEvent arg0) {
		// TODO Auto-generated method stub
		//toggleField.setEditable(selectButton.isSelected());
		toggleField.setDisable(!selectButton.isSelected());
	}

}
