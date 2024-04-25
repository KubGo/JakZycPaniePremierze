package com.kubago.jakzycpaniepremierze;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class StartMenuController implements Initializable {
    @FXML
    private ChoiceBox<Integer> wybierzLiczbeGraczy;
    private List<Integer> liczbyGraczy = new ArrayList<>(List.of(2, 3, 4, 5, 6, 7, 8 ));

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        wybierzLiczbeGraczy.getItems().addAll(liczbyGraczy);
    }

}
