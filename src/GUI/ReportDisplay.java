package GUI;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import Data.DataReport;

public class ReportDisplay {

	private DataReport dataReport;
	
	public ReportDisplay(DataReport dataReport) {
		this.dataReport = dataReport;
	}
	
	public void display() {
		try {
			Stage stage = new Stage();
			VBox mainPane = getMainPane();
			Scene scene = new Scene(mainPane,1000,700);
			stage.setScene(scene);
			stage.setTitle("Tõkkepass");
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private VBox getMainPane() {
		VBox mainVbox = new VBox();
		mainVbox.getChildren().add(getTopPane());
		mainVbox.getChildren().add(getBottomPane());
		return mainVbox;
	}
	
	private HBox getTopPane() {
		HBox topPane = new HBox();
		topPane.getChildren().addAll(getBox1wTitle(), getBox2_17(), getBox3_4_5());
		return topPane;
	}
	
	private GridPane getBottomPane() {
		GridPane bottomPane = new GridPane();
		return bottomPane;
	}
	
	private VBox getBox1wTitle() {
		VBox resultBox = new VBox();
		resultBox.getChildren().addAll(getTitleBox(), getBox1());
		return resultBox;
	}
	
	private Group getTitleBox() {
		Group result = new Group();
		return result;
	}
	
	private HBox getBox1() {
		HBox resultBox = new HBox();
		return resultBox;
	}
	
	private VBox getBox2_17() {
		VBox resultBox = new VBox();
		resultBox.getChildren().addAll(getBox2(), getBox17());
		return resultBox;
	}
	
	private HBox getBox2() {
		HBox resultBox = new HBox();
		return resultBox;
	}
	
	private HBox getBox17() {
		HBox resultBox = new HBox();
		return resultBox;
	}
	
	private VBox getBox3_4_5() {
		VBox resultBox = new VBox();
		resultBox.getChildren().addAll(getBox3(), getBox4(), getBox5());
		return resultBox;
	}
	
	private HBox getBox3() {
		HBox resultBox = new HBox();
		return resultBox;
	}
	
	private HBox getBox4() {
		HBox resultBox = new HBox();
		return resultBox;
	}
	
	private HBox getBox5() {
		HBox resultBox = new HBox();
		return resultBox;
	}
	
	private HBox getTextBox(String data) {
		HBox hbox = new HBox();
        Text text = new Text(data);
        
        hbox.getChildren().add(text);
        hbox.setAlignment(Pos.CENTER);
        hbox.setStyle("-fx-border-color: black;");
        return hbox ;
	}
	
}
