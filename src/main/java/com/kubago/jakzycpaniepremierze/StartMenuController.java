package com.kubago.jakzycpaniepremierze;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class StartMenuController implements Initializable {
    @FXML
    private ChoiceBox<Integer> wybierzLiczbeGraczy;
    @FXML
    private VBox gracze;
    private List<Integer> liczbyGraczy = new ArrayList<>(List.of(2, 3, 4, 5, 6, 7, 8 ));

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        wybierzLiczbeGraczy.getItems().addAll(liczbyGraczy);
        wybierzLiczbeGraczy.setOnAction(this::ustawGraczy);
    }

    public void ustawGraczy(ActionEvent event){
        Integer liczbaGraczy = wybierzLiczbeGraczy.getValue();
        gracze.getChildren().clear();

        for (int i = 1; i <= liczbaGraczy; i++){
            HBox gracz = new HBox();
            gracz.getChildren().clear();
            gracz.setId("gracz_" + i);
            Label playerData = new Label("Gracz nr: " + i + ":");
            gracz.getChildren().add(playerData);
            TextField name = new TextField();
            name.setId("name_" + i);
            gracz.getChildren().add(name);

            gracze.getChildren().add(gracz);

        }
    }

}
