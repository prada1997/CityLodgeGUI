package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.DateTime;
import model.Room;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;


public class RentRoomViewController implements Initializable {

    @FXML
    Button rentRoom;

    @FXML
    DatePicker rentDate;

    @FXML
    Spinner<Integer> numberOfDays;

    @FXML
    TextField customerId;

    private Room roomObj;
    private RecordObservableController object;

    public void setObject(RecordObservableController object) {
        this.object = object;
    }

    public void setRoomObj(Room roomObj) {
        this.roomObj = roomObj;
    }

    //to take the input from the user for renting the room
    public void rentRoom(ActionEvent event) throws IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = rentDate.getValue().format(formatter);
        DateTime aDate = new DateTime(formattedDate);

        roomObj.rent(customerId.getText(),aDate ,numberOfDays.getValue());

        Stage window = (Stage) rentRoom.getScene().getWindow();
        window.close();

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        numberOfDays.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(2,10));
    }
}
