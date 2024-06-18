package gym_software.gym_software_third_project.controller;



import gym_software.gym_software_third_project.MainView;
import gym_software.gym_software_third_project.model.EserciziManager;
import gym_software.gym_software_third_project.model.EserciziModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.List;

public class UserExercizeController {
    public UserExercizeController () {}
    private static int currentAdminID;

    private EserciziManager eserciziManager = new EserciziManager();

    @FXML
    private TableView<EserciziModel> exercizeTalbe;

    @FXML
    private TableColumn<EserciziModel, String> exercize;

    @FXML
    private TableColumn<EserciziModel, String> serie;

    @FXML
    private TableColumn<EserciziModel, String> ripetizioni;

    @FXML
    private Button GoBackButton;

    @FXML
    private void handleBackButtonClick() {
        MainView.goBack();
    }

    public void set_current_ID (int userID) {
        currentAdminID = userID;
        System.out.println("USER EXERCIZE CONTROLLER: current_ID set to " + currentAdminID);
    }

    public void initialize() {
        exercize.setCellValueFactory(cellData -> cellData.getValue().esercizeProperty());
        serie.setCellValueFactory(cellData -> cellData.getValue().serieProperty());
        ripetizioni.setCellValueFactory(cellData -> cellData.getValue().ripetizioniProperty());

        exercizeTalbe.setItems(getData());
    }

    private ObservableList<EserciziModel>  getData() {
        List<EserciziModel> esercizi = eserciziManager.getEserciziByUser(currentAdminID);
        ObservableList<EserciziModel> data = FXCollections.observableArrayList(esercizi);

        return data;
    }



}
