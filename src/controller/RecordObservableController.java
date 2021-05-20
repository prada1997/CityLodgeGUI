package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import model.Room;

import java.util.HashMap;
import java.util.Map;

public class RecordObservableController {

    private Map<String, Room> map = new HashMap<>();

    private ObservableMap<String,Room> observableMap;

    public void setMap(Map<String, Room> map) {
        this.map = map;
        observableMap = FXCollections.observableMap(map);
    }

    public void setObservableMap(ObservableMap<String, Room> observableMap) {
        this.observableMap = observableMap;

    }

    public ObservableMap<String, Room> getObservableMap() {
        return observableMap;
    }

    public Map<String, Room> getMap() {
        return map;
    }
}
