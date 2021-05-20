package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.DateTime;
import model.Room;

import java.time.format.DateTimeFormatter;


public class ReturnRoomViewController {
    @FXML
    Button submit;

    @FXML
    DatePicker dateInput;

    @FXML
    Label setLabel, function;

    private Room roomObj;

    //showing ui of for taking input from the user.
    public void setRoomObj(Room roomObj) {
        this.roomObj = roomObj;
    }
    public void onClickSubmit(ActionEvent event){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = dateInput.getValue().format(formatter);
        DateTime aDate = new DateTime(formattedDate);

        roomObj.returnRoom(aDate);

        Stage window = (Stage) submit.getScene().getWindow();
        window.close();
    }
}
