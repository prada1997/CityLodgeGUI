package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Room;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class GridRoomViewController implements Initializable {

    @FXML
    Label roomID, roomStatus, roomType;

    @FXML
    Text bedrooms, features;

    private Room roomObj;
    private RecordObservableController object;

    public void setObject(RecordObservableController object) {
        this.object = object;
    }

    public void setRoomObj(Room obj) {
        this.roomObj = obj;
    }

    @FXML
    ImageView visual;

    @FXML
    Button viewDetails;

    // handler to go to detail view of room from the click of view
    public void displayRoomDetail(ActionEvent event) throws IOException {
//        String view = viewDetails.getId()+".fxml"; //event.getSource().getID();
        FXMLLoader loader = new FXMLLoader();
        Pane root = loader.load(getClass().getResource("/view/RoomView.fxml").openStream());
        RoomViewController controller = loader.getController();


        controller.setRoomObj(roomObj);
        controller.setObject(object);
        controller.viewRoom();

        Scene nextScene = new Scene(root);
        Stage window = (Stage) viewDetails.getScene().getWindow();
        window.setScene(nextScene);
        window.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


}
