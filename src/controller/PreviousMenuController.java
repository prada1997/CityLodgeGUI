package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PreviousMenuController {

    //controller for moving fromm one menu to other menu
    public void previousMenuShow(ActionEvent event, String view, RecordObservableController object) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResource("/view/" + view).openStream());
        MainMenuViewController controller = loader.getController();
        controller.setObserve(object);
        Scene nextScene = new Scene(root,725,813);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(nextScene);

        window.show();
    }

}