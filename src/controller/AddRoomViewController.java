package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import model.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddRoomViewController implements Initializable {

    @FXML
    Button MainMenuView, imagePick;

    @FXML
    TextField inputRoomID;
    @FXML
    RadioButton twoBeds, fourBeds, sixBeds, oneBed;
    @FXML
    TextField inputSummary, filePath;
    @FXML
    RadioButton standard, suite;
    @FXML
    VBox fileChooseContainer;

    private RecordObservableController object;

    public void setObject(RecordObservableController object) {
        this.object = object;
    }

    private ToggleGroup numberOfBeds;
    private ToggleGroup roomType;


    public void previousMenu(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        PreviousMenuController obj = new PreviousMenuController();
        String view = MainMenuView.getId() + ".fxml";
        obj.previousMenuShow(event, view, object);
    }

    public void addRoom(ActionEvent actionEvent) throws IOException {
        String roomId = inputRoomID.getText();

        String numberOfBeds = ((RadioButton) this.numberOfBeds.getSelectedToggle()).getId();
        String summary = inputSummary.getText();
        String roomType = ((RadioButton) this.roomType.getSelectedToggle()).getId();

        if (numberOfBeds.equals("twoBeds")) {
            numberOfBeds = "2";
        } else if (numberOfBeds.equals("fourBeds")) {
            numberOfBeds = "4";
        } else if (numberOfBeds.equals("oneBed")) {
            numberOfBeds = "1";
        } else
            numberOfBeds = "6";

        if (roomType.equals("standard")) {
            object.getObservableMap().put(roomId, new StandardRoom(roomId,Integer.parseInt(numberOfBeds), roomType,"Available",summary,"file:///"+ filePath.getText()));
        } else if (roomType.equals("suite")) {
            //here should be last maintenance Date
            DateTime todayDate = new DateTime();
            object.getObservableMap().put(roomId, new SuiteRoom(roomId,6,roomType,"Available",todayDate,summary, "file:///"+ filePath.getText()));
        }

        PreviousMenuController obj = new PreviousMenuController();
        String view = MainMenuView.getId() + ".fxml";
        obj.previousMenuShow(actionEvent, view, object);

    }

    public void fileChoose(ActionEvent event) throws IOException {
        FileChooser filechoose = new FileChooser();

        File fileSelected = filechoose.showOpenDialog(null);
        if(fileSelected != null){
            filePath.setText(fileSelected.getName());
        }

    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        numberOfBeds = new ToggleGroup();
        this.twoBeds.setToggleGroup(numberOfBeds);
        this.fourBeds.setToggleGroup(numberOfBeds);
        this.sixBeds.setToggleGroup(numberOfBeds);
        this.oneBed.setToggleGroup(numberOfBeds);

        roomType = new ToggleGroup();
        this.standard.setToggleGroup(roomType);
        this.suite.setToggleGroup(roomType);


    }
}
