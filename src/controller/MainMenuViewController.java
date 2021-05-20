package controller;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import model.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Room;

import java.io.*;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class MainMenuViewController implements Initializable {

    private RecordObservableController observe;

    @FXML
    MenuBar menuBar;
    @FXML
    MenuItem AddRoomView, exportFile, importFile, exit;
    @FXML
    TilePane centerBox;
    @FXML
    Button add;


    public RecordObservableController getObserve() {
        return observe;
    }

    public void setObserve(RecordObservableController observe) {
        this.observe = observe;
        reloadUI();
    }

    //method to add room from the UI
    public void addRoomClick(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResource("/view/AddRoomView.fxml").openStream());
        AddRoomViewController controller = loader.getController();
        controller.setObject(observe);
        Scene nextScene = new Scene(root,725,818);

        Stage window = (Stage) add.getScene().getWindow();
        window.setScene(nextScene);
        window.show();
    }

    //to input the data from the file to map
    public void importFile(ActionEvent event) {


        try {
            File fileFetch = new File("file/exportFile.txt");
            FileReader fileObj = new FileReader(fileFetch);
            BufferedReader readObj = new BufferedReader(fileObj);
            HashMap<String, Room> allRooms = new HashMap<>();
            String[] row;

            int r;

            while((row = readObj.readLine().split(":")) != null) {

                System.out.println(row.toString());
                //check for order in the file
                //for the standard room
                System.out.println(row[0]);
                if (row[0].substring(0, 1).toLowerCase().equals("r")) {
                    StandardRoom obj = new StandardRoom(row[0], Integer.parseInt(row[1]), row[2], row[3], row[4], row[5]);

                    allRooms.put(obj.getRoomId(), obj);
                }
                //for suite
                else{
                    SuiteRoom obj = new SuiteRoom(row[0], Integer.parseInt(row[1]), row[2], row[3], new DateTime(row[5]),row[4],row [6]);

                    allRooms.put(obj.getRoomId(), obj);

                }

            }
            readObj.close();

            FileReader fileReadObj = new FileReader(fileFetch);
            BufferedReader readHiringObj = new BufferedReader(fileReadObj);

            int entries = 1;
            String checkRoomId = "";
            String[] hiringRow = readObj.readLine().split(":");

            while(readHiringObj.readLine() != null){

                if (hiringRow[0].indexOf("_") > 0) {
                    checkRoomId = hiringRow[0].split("_")[0];
                    break;
                }
                hiringRow = readObj.readLine().split(":");
            }
            do {

//                String[] hiringRow = readObj.readLine().split(":");
                if (hiringRow[0].indexOf("_") > 0) {

                    HiringRecord standardHiringRecord = new HiringRecord(hiringRow[0].split("_")[1],
                            hiringRow[0].split("_")[0], new DateTime(hiringRow[1]),
                            new DateTime(hiringRow[2]));
                    if (allRooms.containsKey(hiringRow[0].split("_")[0]))
                    {
                        allRooms.get(hiringRow[0].split("_")[0]).getRecord().put(entries, standardHiringRecord);
                        if(checkRoomId.equals(hiringRow[0].split("_")[0])){
                            entries++;
                        }
                        else {
                            entries = 0;
                        }
                    }

                }
                hiringRow = readObj.readLine().split(":");
            }while (readObj.readLine() != null) ;
    }
        catch(Exception e)

        {
            e.printStackTrace();
        }

}
    //to export the data from map to file
    public void exportFile(ActionEvent event){

        try{
            File fileFetch = new File("file/exportFile.txt");
            FileWriter fileObj = new FileWriter(fileFetch);
            BufferedWriter writeObj = new BufferedWriter(fileObj);

            for(String key : observe.getObservableMap().keySet()) {

                writeObj.write(observe.getObservableMap().get(key).toString());

                for (int entry : observe.getObservableMap().get(key).getRecord().keySet()) {
                    writeObj.newLine();
                    writeObj.write(observe.getObservableMap().get(key).getRecord().get(entry).toString());
//                    writeObj.newLine();
                }
                writeObj.newLine();
            }


            writeObj.flush(); writeObj.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    //to exit from the menu
    public void exit(ActionEvent event){
        new Alert(Alert.AlertType.CONFIRMATION, "Do you want to quit ?",
                // alerting the user to select yes or no
                new ButtonType[] {ButtonType.YES, ButtonType.NO }).showAndWait().ifPresent(type -> {
            //if the input from the user is yes system.exit(0)
            if (type == ButtonType.YES) System.exit(0);
        });

    }

    //for showing the room on the main menu
    public void reloadUI (){
        try {

            for( String key: observe.getObservableMap().keySet()){

                FXMLLoader loader = new FXMLLoader();
                Pane viewRow = loader.load(getClass().getResource("/view/GridRoomView.fxml").openStream());
                GridRoomViewController controller = loader.getController();


                if(observe.getObservableMap().get(key).getNumberOfBeds() == 2){
                    controller.bedrooms.setText("2");
                } else if (observe.getObservableMap().get(key).getNumberOfBeds() == 4) {
                    controller.bedrooms.setText("4");
                } else if (observe.getObservableMap().get(key).getNumberOfBeds() == 6) {
                    controller.bedrooms.setText("6");
                }

                controller.features.setText(observe.getObservableMap().get(key).getFeatures());
                controller.roomID.setText(observe.getObservableMap().get(key).getRoomId());
                controller.roomStatus.setText(observe.getObservableMap().get(key).getRoomStatus());
                controller.roomType.setText(observe.getObservableMap().get(key).getRoomType());

                Image image = new Image(observe.getObservableMap().get(key).getRoomImage(),350,185,false,false);
                controller.visual.setImage(image);

                controller.setRoomObj(observe.getObservableMap().get(key));
                controller.setObject(observe);

                viewRow.setStyle("-fx-border-color: black;");

                centerBox.getChildren().add(viewRow);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }

}
