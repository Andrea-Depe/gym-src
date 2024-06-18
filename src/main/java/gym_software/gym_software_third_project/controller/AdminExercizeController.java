package gym_software.gym_software_third_project.controller;




import gym_software.gym_software_third_project.MainView;
import gym_software.gym_software_third_project.model.EserciziManager;
import gym_software.gym_software_third_project.model.EserciziModel;
import gym_software.gym_software_third_project.model.UserAnManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AdminExercizeController {
    private EserciziManager eserciziManager = new EserciziManager();
    private UserAnManager userAnManager = new UserAnManager();
    private static int currentAdminID;

    @FXML
    private Label addLabel;

    @FXML
    private ComboBox<String> clientiComboBox;

    @FXML
    private TextField ExercizeTextField;

    @FXML
    private TextField SerieTextField;

    @FXML
    private TextField RipTextField;

    @FXML
    private Button GoBackButton;


    @FXML
    private void initialize() {
        System.out.println("ADMIN EXERCIZE CONTROLLER: initialize combo box");
        initializeClientiComboBox();
    }

    @FXML
    private void handleBackButtonClick() {
        MainView.goBack();
    }

    private void initializeClientiComboBox() {
        System.out.println("ADMIN EXERCIZE CONTROLLER: currentAdminID = " + currentAdminID);

        if (currentAdminID == 0) {
            System.out.println("ADMIN EXERCIZE CONTROLLER: currentAdminID non è stato impostato");
            return;
        }

        ObservableList<String> clienti = FXCollections.observableArrayList();
        String[] clientArray = userAnManager.getAllClientiByInstructor(currentAdminID);
        System.out.println("ADMIN EXERCIZE CONTROLLER: clientArray.length = " + clientArray.length);
        for (String client_temp : clientArray) {
            if (client_temp != null) {
                System.out.println("ADMIN EXERCIZE CONTROLLER: cliente_cf = " + client_temp);
                clienti.add(client_temp);
            }
        }
        clientiComboBox.setItems(clienti);
    }


    public void set_current_ID (int userID) {
        currentAdminID = userID;
        System.out.println("ADMIN USER TABLE CONTROLLER: current_ID set to " + currentAdminID);
    }

    @FXML
    private void handleCreateUserButton() {
        System.out.println("ADMIN EXERCIZE CONTROLLER: create User button pressed");

        String temp_CF = clientiComboBox.getValue();

        UserAnManager userAnManager = new UserAnManager();
        int temp_ID = userAnManager.getIDByCF(temp_CF);

        String temp_exercize = ExercizeTextField.getText();
        int temp_serie = Integer.parseInt(SerieTextField.getText());
        int temp_rip = Integer.parseInt(RipTextField.getText());

        System.out.println("ADMIN EXERCIZE CONTROLLER: " + temp_CF + " " + temp_exercize + " " + temp_serie + " " + temp_rip);

        EserciziModel eserciziModel = new EserciziModel(temp_ID, currentAdminID, temp_exercize, temp_serie, temp_rip);
        eserciziManager.addEsercizio(eserciziModel);

        addLabel.setVisible(true);
    }
}
