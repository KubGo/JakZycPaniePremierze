package com.kubago.jakzycpaniepremierze;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
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
        ToggleGroup urzednik = new ToggleGroup();

        for (int i = 1; i <= liczbaGraczy; i++){
            GridPane gracz = new GridPane();
            gracz.setHgap(10);
            gracz.getChildren().clear();
            gracz.setId("gracz_" + i);
            Label playerData = new Label("Gracz nr: " + i + ":");
            gracz.add(playerData, 0, 0);
            TextField name = new TextField();
            name.setId("name_" + i);
            gracz.add(name, 1, 0);
            RadioButton isUrzednik = new RadioButton();
            isUrzednik.setId("urzednik_" + i);
            isUrzednik.setToggleGroup(urzednik);
            gracz.add(isUrzednik, 2,0);
            gracze.getChildren().add(gracz);

        }
        Label baseUrzednikLabel = new Label("Wybierz urzędnika zaznaczając gracza. " +
                "Jeżeli brak znaznacz ten przycisk:");
        baseUrzednikLabel.setWrapText(true);
        RadioButton noUrzednik = new RadioButton();
        noUrzednik.setToggleGroup(urzednik);
        noUrzednik.setId("noneUrzednik");
        noUrzednik.setSelected(true);
        GridPane noneUrzednik = new GridPane();
        noneUrzednik.add(baseUrzednikLabel, 0,0,2,2);
        noneUrzednik.add(noUrzednik, 2, 0, 1, 2);
        noneUrzednik.setHgap(10);
        gracze.getChildren().add(noneUrzednik);
        Button start = new Button("Start");
        start.setOnAction(this::zacznijGre);
        gracze.getChildren().add(start);
    }

    private void zacznijGre(ActionEvent event) {
        System.out.println("Gra zaczęta");
    }

}
