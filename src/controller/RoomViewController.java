package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.DateTime;
import model.Room;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RoomViewController implements Initializable {

    private Room roomObj;

    public void setRoomObj(Room roomObj) {
        this.roomObj = roomObj;
    }

    private RecordObservableController object;

    public void setObject(RecordObservableController object) {
        this.object = object;
    }

    @FXML
    Button rentRoom, completeMaintenenace, performMaintenance, MainMenuView, returnRoom;

    @FXML
    VBox roomView,details;

    //method to go back in the previous menu
    public void previousMenu(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        PreviousMenuController obj  = new PreviousMenuController();
        String view = MainMenuView.getId()+".fxml";
        obj.previousMenuShow(event,view, object);
    }

    // to rent from room from available
    public void rentRoom() throws IOException {


        Stage newStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResource("/view/RentRoomView.fxml").openStream());
        RentRoomViewController controller = loader.getController();
        controller.setRoomObj(roomObj);

        newStage.setScene(new Scene(root,400,400));
        newStage.show();
    }


    //to perform room maintenance
    public void performMaintenance(ActionEvent event){
        new Alert(Alert.AlertType.CONFIRMATION, "The room is under maintenance now.",
                // alerting the user to select yes or no
                new ButtonType[] {ButtonType.OK }).showAndWait().ifPresent(type -> {
            //if the input from the user is yes system.exit(0)
            if (type == ButtonType.OK){
                roomObj.performMaintenance();
            }
        });

    }

    //to perform complete maintenance
    public void completeMaintenance(ActionEvent event){
        new Alert(Alert.AlertType.CONFIRMATION, "Maintenance is completed.\nThe room is available for Rent",
                // alerting the user to select yes or no
                new ButtonType[] {ButtonType.OK }).showAndWait().ifPresent(type -> {
            //if the input from the user is yes system.exit(0)
            if (type == ButtonType.OK){
                DateTime date = new DateTime();
                roomObj.completeMaintenance(date);
            }
        });
    }

    //to return room from the rent room
    public void returnRoom(ActionEvent event) throws IOException {

        Stage newStage = new Stage();

        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResource("/view/ReturnRoomView.fxml").openStream());
        ReturnRoomViewController controller = loader.getController();
        controller.setRoomObj(roomObj);

        newStage.setScene(new Scene(root,400,400));
        newStage.show();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            FXMLLoader loader = new FXMLLoader();

            Pane show = loader.load(getClass().getResource("/view/HiringRecordView.fxml").openStream());
            HiringRecordViewController controller = loader.getController();
            controller.lateFee.setText("900");

            details.getChildren().add(show);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    //showing the room data in the UI
    public void viewRoom(){
        try {

            FXMLLoader loader = new FXMLLoader();
            Pane viewRow = loader.load(getClass().getResource("/view/GridRoomView.fxml").openStream());
            GridRoomViewController controller = loader.getController();


            if(object.getObservableMap().get(roomObj.getRoomId()).getNumberOfBeds() == 2){
                controller.bedrooms.setText("2");
            } else if (object.getObservableMap().get(roomObj.getRoomId()).getNumberOfBeds() == 4) {
                controller.bedrooms.setText("4");
            } else if (object.getObservableMap().get(roomObj.getRoomId()).getNumberOfBeds() == 6) {
                controller.bedrooms.setText("6");
            }
            controller.viewDetails.setVisible(false);
            controller.features.setText(object.getObservableMap().get(roomObj.getRoomId()).getFeatures());
            controller.roomID.setText(object.getObservableMap().get(roomObj.getRoomId()).getRoomId());
            controller.roomStatus.setText(object.getObservableMap().get(roomObj.getRoomId()).getRoomStatus());
            controller.roomType.setText(object.getObservableMap().get(roomObj.getRoomId()).getRoomType());

            Image image = new Image(object.getObservableMap().get(roomObj.getRoomId()).getRoomImage(),350,185,false,false);
            controller.visual.setImage(image);

            viewRow.setStyle("-fx-border-color: black;");

            roomView.getChildren().add(viewRow);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


