package view;
import controller.MainMenuViewController;
import controller.RecordObservableController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sqlQuery.ConnectionTest;
import sqlQuery.FetchRecords;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CityLodgeGUIMain extends Application {
    public static void main(final String[] args) {
        Application.launch();
    }

    //main method to start the GUI
    public void start(Stage primaryStage) throws Exception {
        try {
            //created the connection of the database
            Connection startDatabase = new ConnectionTest().Connection();
            //saving the record from the database to the map
            RecordObservableController roomRecord = new RecordObservableController();
            //reading record from the database
            new FetchRecords(startDatabase, roomRecord);

            FXMLLoader loader = new FXMLLoader();
            Parent root = loader.load(getClass().getResource("/view/MainMenuView.fxml").openStream());
            MainMenuViewController controller = loader.getController();
            controller.setObserve(roomRecord);

            primaryStage.setTitle("City Lodge Application");
            primaryStage.setScene(new Scene(root, 725, 813));
            primaryStage.show();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}