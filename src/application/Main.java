package application;

//Tuntud vead :
//	Memory leak kui liiga palju "make" klõpsida (vähendatud vb)
//	
//  
//  
import java.util.ArrayList;

import Data.DataReport;
import Data.Mine;
import Data.MineField;
import Data.MineRow;
import GUI.ActiveToggler;
import GUI.DrawArea;
import GUI.ScrollZoomHandler;
import Math.Point;
import Math.PropertyCalculator;
import Math.Utility;
import Output.PdfCreator;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class Main extends Application {
	private HBox mainPane;
	private DrawArea drawArea;
	private PdfCreator pdfCreator;
	private DataReport dataReport;
	private MineField globalMineField;
	private VBox infoBox;
	
	private StringProperty globalId = new SimpleStringProperty("0");
	private StringProperty globalDensity = new SimpleStringProperty("0");
	private StringProperty globalDepth = new SimpleStringProperty("60");
	private StringProperty globalWidth = new SimpleStringProperty("60");
	private StringProperty globalAzimuth = new SimpleStringProperty("1600");
	private StringProperty globalXAzimuth = new SimpleStringProperty("3200");
	private StringProperty globalMineCount = new SimpleStringProperty("25");
	private StringProperty globalRowSpace = new SimpleStringProperty("10");
	private StringProperty globalRowCount = new SimpleStringProperty("6");
	
	private StringProperty globalMilDensity = new SimpleStringProperty();
	
	ArrayList<RadioButton> triforceTg1;
	ArrayList<RadioButton> triforceTg2;
	
	private void run() {
		
		clearDrawArea();
		ArrayList<Boolean> selections = Utility.getSelectionArray(triforceTg1, triforceTg2);
		ArrayList<Double> data = Utility.buildList(globalWidth, globalMineCount, globalDepth, globalRowCount, globalRowSpace, globalMilDensity);
		PropertyCalculator propCalc = new PropertyCalculator(selections, data);
		
		//globalMineField = new MineField(2, 5, 5.7, 7, 0.2, 1600, 0);
		globalMineField = new MineField(2, propCalc.getRowCount(), propCalc.getRowSpace(),
										propCalc.getRowMines(), propCalc.getRowDensity(),
										Double.parseDouble(globalAzimuth.get()),
										Double.parseDouble(globalXAzimuth.get()));
										
		globalMineField.moveToPoint(drawArea.getCenterX(), drawArea.getCenterY());
		drawMineField(globalMineField);
		drawArea.getWhitespace().setOnScroll(new ScrollZoomHandler(globalMineField, drawArea));
		drawArea.drawDirGuide(60, 60, Double.parseDouble(globalXAzimuth.get()));
		
		Text count = (Text) infoBox.getChildren().get(0);
		count.setText("Miinide arv: "+globalMineField.getMineCount());
		
		dataReport = new DataReport();
		dataReport.setMineSummary(globalMineField.getFullSummary());
		pdfCreator = new PdfCreator(dataReport, "");
		pdfCreator.setDataClass(dataReport);
		
	}
	
	public VBox getOptionFieldBox() {
		
		VBox fieldVbox = new VBox(15);
		fieldVbox.setPadding(new Insets(10,10,10,10));
		
		TextField idField = new TextField();
		setBinding(idField, globalId);
		idField.setText("0");
		
		TextField densityField = new TextField();
		setBinding(densityField, globalDensity);
		densityField.setText("0.2");
		
		TextField lengthField = new TextField();
		setBinding(lengthField, globalDepth);
		lengthField.setText("100");
		
		TextField widthField = new TextField();
		setBinding(widthField, globalWidth);
		widthField.setText("85");
		
		TextField azimuthField = new TextField();
		setBinding(azimuthField, globalAzimuth);
		azimuthField.setText("1400");
		
		TextField crossAzimuthField = new TextField();
		setBinding(crossAzimuthField, globalXAzimuth);
		crossAzimuthField.setText("2700");
		
		fieldVbox.getChildren().addAll(idField, densityField, lengthField, widthField, azimuthField, crossAzimuthField);
		
		return fieldVbox;
		
	}
	
	public void clearDrawArea() {
		try {
			globalMineField.destroy();
		} catch (NullPointerException e) {}
		
		globalMineField = null;
		drawArea.clear();
	}
	
	private ComboBox<String> getMilDensitySelectBox() {
		
		ComboBox<String> milDensitySelect = new ComboBox<>();
		milDensitySelect.getItems().addAll("Lagundada", "Siduda", "Suunata", "Seisata");
		milDensitySelect.getSelectionModel().selectFirst();
		milDensitySelect.setPrefWidth(150);
		milDensitySelect.valueProperty().bindBidirectional(globalMilDensity);
		
		return milDensitySelect;
	}
	
	private GridPane getFirstTriforcePane() {
		GridPane gp = new GridPane();
		RadioButton widthRb = getSelectRB("Laius", triforceTg1);
		RadioButton countRb = getSelectRB("Miinide arv", triforceTg1);
		
		TextField widthField = getSelectTF(globalWidth);
		TextField countField = getSelectTF(globalMineCount);
		widthField.setDisable(true);
		countField.setDisable(true);
		
		widthRb.setOnAction(new ActiveToggler(widthField, widthRb));
		countRb.setOnAction(new ActiveToggler(countField, countRb));
		
		Text azimuthText = new Text("Ristasimuut");
		Text xAzimuthText = new Text("Välja suund");
		
		
		TextField xAzimuthField = getSelectTF(globalXAzimuth);
		xAzimuthField.textProperty().bindBidirectional(globalXAzimuth);
		TextField azimuthField = azimuthSelectField(xAzimuthField);
		
		Text densityText = new Text("Efekt");
		
		ComboBox<String> milDensitySelect = getMilDensitySelectBox();
		
		gp.add(widthRb, 0, 0);
		gp.add(countRb, 0, 1);
		gp.add(widthField, 1, 0);
		gp.add(countField, 1, 1);
		gp.add(densityText, 0, 2);
		gp.add(milDensitySelect, 1, 2);
		gp.add(azimuthText, 0, 3);
		gp.add(xAzimuthText, 0, 4);
		gp.add(azimuthField, 1, 3);
		gp.add(xAzimuthField, 1, 4);
		
		gp.setHgap(10);
		gp.setVgap(5);
		gp.setPadding(new Insets(15, 15, 15, 15));
		
		return gp;
	}
	private GridPane getSecondTriforcePane(Stage newMineFieldStage) {
		GridPane gp = new GridPane();
		RadioButton depthRb = getSelectRB("Sügavus", triforceTg2);
		RadioButton rowsRb = getSelectRB("Ridade arv", triforceTg2);
		RadioButton rowSpaceRb = getSelectRB("Reavahe", triforceTg2);
		
		TextField depthField = getSelectTF(globalDepth);
		TextField rowsField = getSelectTF(globalRowCount);
		TextField rowSpaceField = getSelectTF(globalRowSpace);
		
		depthField.setDisable(true);
		rowsField.setDisable(true);
		rowSpaceField.setDisable(true);
		
		depthRb.setOnAction(new ActiveToggler(depthField, depthRb));
		rowsRb.setOnAction(new ActiveToggler(rowsField, rowsRb));
		rowSpaceRb.setOnAction(new ActiveToggler(rowSpaceField, rowSpaceRb));
		
		Button okButton = getRunButton(newMineFieldStage);
		
		gp.add(depthRb, 0, 0);
		gp.add(rowsRb, 0, 1);
		gp.add(rowSpaceRb, 0, 2);
		
		gp.add(depthField, 1, 0);
		gp.add(rowsField, 1, 1);
		gp.add(rowSpaceField, 1, 2);
		
		gp.add(okButton, 1, 3);
		
		gp.setHgap(10);
		gp.setVgap(5);
		gp.setPadding(new Insets(15, 15, 15, 15));
		
		return gp;
	}
	public void showNewMineFieldWindow() {
		
		initToggleGroups();
		
		Stage newMineFieldStage = new Stage();
		
		GridPane mainOptionPane = new GridPane();
		Text optionExplain = new Text("Vali ja täida väljad teadaolevate andmetega!");
		optionExplain.setFont(Font.font("Courier", 15));
		
		GridPane triforce1 = getFirstTriforcePane();
		GridPane triforce2 = getSecondTriforcePane(newMineFieldStage);
		
		mainOptionPane.add(optionExplain, 0, 0);
		mainOptionPane.add(triforce1, 0, 1);
		mainOptionPane.add(triforce2, 1, 1);
		
		mainOptionPane.setVgap(25);
		mainOptionPane.setHgap(5);
		mainOptionPane.setPadding(new Insets(15,15,15,15));
		
		Scene scene = new Scene(mainOptionPane);
		scene.getStylesheets().addAll(Main.class.getResource("application.css").toExternalForm());
	
		newMineFieldStage.setScene(scene);
		newMineFieldStage.setTitle("Uus miiniväli");
		newMineFieldStage.setHeight(300);
		newMineFieldStage.setWidth(650);
		newMineFieldStage.show();
	}
	
	private RadioButton getSelectRB(String title, ArrayList<RadioButton> myTg) {
		RadioButton rb = new RadioButton();
		rb.setText(title);
		myTg.add(rb);
		
		return rb;
	}
	
	
	private TextField getSelectTF(StringProperty boundTo) {
		TextField tf = new TextField();
		tf.textProperty().bindBidirectional(boundTo);
		
		return tf;
	}
	
	private TextField azimuthSelectField(TextField xAzimuthField) {
		TextField tf1 = new TextField("0");
		tf1.textProperty().bindBidirectional(globalAzimuth);
		
		tf1.textProperty().addListener(new ChangeListener<String>() {
            @Override 
            public void changed(ObservableValue ov, String t, String t1) {
            	if (!t1.equals("")) {
            		int value = Integer.parseInt(t1) + 1600;
                    xAzimuthField.setText(String.valueOf(value));
            	} else {
            		 xAzimuthField.setText("0");
            	}
            	             
            }    
        });
		
		return tf1;
	}
	
	private void initToggleGroups() {
		triforceTg1 = new ArrayList<>();
		triforceTg2 = new ArrayList<>();
	}
	
	public void setBinding(TextField textField, StringProperty variable) {
		textField.textProperty().bindBidirectional(variable);
	}
	
	private void drawMineField(MineField mfield) {
		
		for(MineRow mrow : mfield.getMineRows()) {
			drawMineRow(mrow);
		}
	
		drawArea.addObjects(mfield.getDataLine1().getDrawAbleObjects());
		drawArea.addObjects(mfield.getDataLine2().getDrawAbleObjects());
		
		drawArea.checkBoundaries();
		
	}
	
	private void drawMineRow(MineRow mrow) {
		for(Mine mine : mrow.getMines()) {
			drawMine(mine);
		}
	}
	
	private void drawMine(Mine mine) {
		drawArea.addObject(mine.getMineImage());
		
	}
	
	public void addNewMine() {
		globalMineField.addExtraMine();
		
		Text count = (Text) infoBox.getChildren().get(0);
		count.setText("Miinide arv: "+(globalMineField.getMineCount()+globalMineField.getExtraMines().size()));
		
		drawArea.addObject(globalMineField.getExtraMines().get(globalMineField.getExtraMines().size()-1).getMineImage());
	}
	
	public Button getNewButton() {
		Button btn = new Button("Uus");
		btn.setPrefWidth(100);
		btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
		    public void handle(MouseEvent me) {	    	
		    	showNewMineFieldWindow();
		    }
		});
		
		return btn;
	}
	
	public Button getNewMineButton() {
		Button btn = new Button("Lisamiin");
		btn.setPrefWidth(100);
		btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
		    public void handle(MouseEvent me) {	    	
		    	addNewMine();
		    }
		});
		
		return btn;
	}
	
	public Button getClearButton() {
		Button btn = new Button("Puhasta");
		btn.setPrefWidth(100);
		btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
		    public void handle(MouseEvent me) {	    	
		    	clearDrawArea();
		    }
		});
		
		return btn;
	}
	public void drawDirGuide(double x, double y) {
		drawDirGuide(x, y);
	}
	
	private VBox getInfoBox() {
		VBox result = new VBox(10);
		
		Text count = new Text();
		Text suggestion = new Text();
		boolean success = true;
		
		try {
			count = new Text("Miinide arv: "+String.valueOf(globalMineField.getMineCount()));
			suggestion = new Text("Topeltlaenguid: 101%");
		} catch (NullPointerException e) {
			success = false;
		}
		
		if(!success) {
			count = new Text("Miinide arv: 0");
			suggestion = new Text("Topeltlaenguid: 101%");
		}
		
		result.getChildren().addAll(count, suggestion);
		result.getStyleClass().add("nodeBox");
		
		infoBox = result;
		return result;
	}
	
	public Button getPdfButton() {
		Button btn = new Button("Loo pass");
		btn.setPrefWidth(100);
		
		btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
		    public void handle(MouseEvent me) {	    	
		    	dataReport.setMineSummary(globalMineField.getFullSummary());
		    	pdfCreator.makePDF();
		    }
		});
		
		return btn;
	}
	
	public VBox getTopPane() {
		
		VBox vbox = new VBox(10);
		vbox.setPadding(new Insets(15,15,15,15));
		vbox.getChildren().addAll(getNewButton(), getNewMineButton(), getClearButton(), getPdfButton(), getInfoBox());
		
		return vbox;
	}
	
	private DrawArea getDrawArea() {
		DrawArea dArea = new DrawArea(900, 600);
		return dArea;
	}
	
	private Button getRunButton(Stage newMineFieldStage){
		
		Button runButton = new Button("OK");
		runButton.setPrefHeight(40);
		runButton.setPrefWidth(100);
		
		runButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
		    public void handle(MouseEvent me) {
		    	run();
		    	newMineFieldStage.hide();
		    	
		    }
		});
		
		return runButton;
	}
	
	public HBox getMainPane() {
		
		HBox bp = new HBox(10);
		drawArea = getDrawArea();
		bp.getChildren().add(getTopPane());
		bp.getChildren().add(drawArea.getObjectGroup());
		
		return bp;
	}
	@Override
	public void start(Stage primaryStage) {
		try {
			mainPane = getMainPane();
			Scene scene = new Scene(mainPane,1000,700);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Tõkkepass");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
		
		// (id, density, pikkus, laius, algnurk, lõppnurk, algpunkti x, algpunkti y)
	}
}
