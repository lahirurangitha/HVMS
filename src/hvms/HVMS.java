package hvms;

import java.time.LocalDate;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author lahiru
 */
public class HVMS extends Application {

    Stage window;
    Scene welcomeScene, firstPgScene;
    static int windowWidth = 1000;
    static int windowHeight = 600;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {

        // Use a border pane as the root for scene
        BorderPane border = new BorderPane();

        HBox hbox = title();
        border.setTop(hbox);
     
        border.setCenter(addGridPane());

        Scene scene = new Scene(border, windowWidth, windowHeight);
        stage.setScene(scene);
        stage.setTitle("High Voltage Insulator Surface Condition Monitoring System");
        stage.show();
    }

    private GridPane addGridPane() {

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));

        Label lblInsType = new Label("Insulator Type");
        lblInsType.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
        grid.add(lblInsType, 0, 0);
        ObservableList<String> insOptions
                = FXCollections.observableArrayList(
                        "Option 1",
                        "Option 2",
                        "Option 3"
                );
        ComboBox insulatorType = new ComboBox(insOptions);
        insulatorType.setStyle("-fx-padding:0 0 0 100");
        grid.add(insulatorType, 1, 0);

        //date
        Label lbldatePicker = new Label("Date");
        lbldatePicker.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
        grid.add(lbldatePicker, 3, 0);
        DatePicker datePicker = new DatePicker();
//        datePicker.setOnAction(new EventHandler() {
//            public void handle(Event t) {
//                LocalDate date = datePicker.getValue();
//                System.err.println("Selected date: " + date);
//            }
//        });
        grid.add(datePicker, 4, 0);

        //col3 row1 - reord data btn
        Button btnRecordData = new Button("Record Data");
        btnRecordData.setStyle("-fx-padding:0 50 0 50;-fx-font: 15 Vardana");
        grid.add(btnRecordData, 5, 0);

        //leakage=======================
        VBox leakageWave = new VBox();
        Label lblLeakage = new Label("Leakage Current Wave Form");
        lblLeakage.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
        ImageView imgWave = new ImageView(
                new Image(HVMS.class.getResourceAsStream("graphics/wave.png"), 300, 200, false, false));
        leakageWave.getChildren().addAll(lblLeakage, imgWave);

        grid.add(leakageWave, 0, 1, 2, 2);

        //max peak supply====================
        VBox lblMaxPeakSup = new VBox();
        lblMaxPeakSup.setAlignment(Pos.CENTER);
        lblMaxPeakSup.setSpacing(10);
        Label lblmax = new Label("Max Allowed Leakage Current");
        lblmax.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
        Label lblsupply = new Label("Supply Voltage");
        lblsupply.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
        Label lblpeak = new Label("Peak Leakage Current");
        lblpeak.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
        lblMaxPeakSup.getChildren().addAll(lblmax, lblpeak, lblsupply);
        grid.add(lblMaxPeakSup, 3, 1, 2, 2);

        VBox maxPeakSup = new VBox();
        maxPeakSup.setAlignment(Pos.CENTER);
        maxPeakSup.setSpacing(10);
        TextField txtMax = new TextField();
        TextField txtPeak = new TextField();
        TextField txtsupply = new TextField();
        maxPeakSup.getChildren().addAll(txtMax, txtPeak, txtsupply);
        grid.add(maxPeakSup, 5, 1, 2, 2);

        //leakage===========================
        VBox classification = new VBox();
        classification.setSpacing(10);
        Label lblclassif = new Label("Leackage Current Classification");
        lblclassif.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
        TextArea classif = new TextArea();
//        classif.setDisable(true);
        classif.setMaxSize(300, 100);
        classification.getChildren().addAll(lblclassif, classif);
        grid.add(classification, 0, 3, 3, 1);

        HBox loadStop = new HBox();
        loadStop.setSpacing(50);
        loadStop.setAlignment(Pos.CENTER_RIGHT);
        Button btnLoadData = new Button("Load Input Data");
        btnLoadData.setStyle("-fx-padding:0 50 0 50;-fx-font: 15 Vardana");
        
        ImageView imgStart = new ImageView(
                new Image(HVMS.class.getResourceAsStream("graphics/start.png")));;

        ImageView imgStop = new ImageView(
                new Image(HVMS.class.getResourceAsStream("graphics/stop.png")));
        loadStop.getChildren().addAll(btnLoadData,imgStart, imgStop);

        grid.add(loadStop, 3, 3,3,1);

        return grid;
    }

    private HBox title() {

        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);   // Gap between nodes
        hbox.setAlignment(Pos.CENTER);
        //start image
//        ImageView imgStart = new ImageView(
//                new Image(HVMS.class.getResourceAsStream("graphics/start.png")));;

        // col 2 row 1 - title
        Text title = new Text("HIGH VOLTAGE INSULATOR SURFACE CONDITION\n\t\tMONITORING SYSTEM");
        title.setFont(Font.font("Verdana", FontWeight.BOLD, 30));

        hbox.getChildren().addAll(title);

        return hbox;
    }

}
