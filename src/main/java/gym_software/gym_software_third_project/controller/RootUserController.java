package gym_software.gym_software_third_project.controller;


import gym_software.gym_software_third_project.MainView;
import gym_software.gym_software_third_project.model.InstrtuctorAnManager;
import gym_software.gym_software_third_project.model.InstructorAnagraficaModel;
import gym_software.gym_software_third_project.model.UserAnManager;
import gym_software.gym_software_third_project.model.UserAnagraficaModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class RootUserController {
    public static int id_user = 0;
    private UserAnManager userAnManager = new UserAnManager();
    private InstrtuctorAnManager instrtuctorAnManager = new InstrtuctorAnManager();

    @FXML
    private TextField CFTextField;

    @FXML
    private Button GoBackButton;

    @FXML
    private TextField NameTextField;

    @FXML
    private TextField SurnameTextField;

    @FXML
    private TextField PesoTextField;

    @FXML
    private TextField AltezzaTextField;

    @FXML
    private TextField AgeTextField;

    @FXML
    private ComboBox<String> InstructorComboBox;

    @FXML
    private Node createUserButton;

    @FXML
    private void handleBackButtonClick() {
        MainView.goBack();
    }


    @FXML
    private void createUser() {
        System.out.println("ROOT USER CONTROLLER: create User");

        String temp_CF = CFTextField.getText();
        String temp_name = NameTextField.getText();
        String temp_surname = SurnameTextField.getText();
        int temp_peso = Integer.parseInt(PesoTextField.getText());
        int temp_altezza = Integer.parseInt(AltezzaTextField.getText());
        int temp_age = Integer.parseInt(AgeTextField.getText());
        String temp_cognome_istruttore = InstructorComboBox.getValue();

        System.out.println("ROOT USER CONTROLLER: " + temp_CF + " " + temp_name + " " + temp_surname +
                " " + temp_peso + " " + temp_altezza + " " + temp_age + " " + id_user + " " +
                temp_cognome_istruttore);

        InstructorAnagraficaModel instructor = instrtuctorAnManager.getInstructorBySurname(temp_cognome_istruttore);

        System.out.println("ROOT USER CONTROLLER: " + instructor.getCF() + " " +
                instructor.getName() + " " + instructor.getSurname() + " " + instructor.getID());

        if (userAnManager.ContainUser(temp_CF)) {
            System.out.println("ROOT USER CONTROLLER: User already exists");
            return;
        } else {
            System.out.println("ROOT USER CONTROLLER: User doesn't exist");
            int temp_ID_istruttore = instructor.getID();

            UserAnagraficaModel user = new UserAnagraficaModel(temp_CF, temp_name, temp_surname,
                    temp_peso, temp_altezza, temp_age, id_user, temp_ID_istruttore);
            userAnManager.addUser(user);
            System.out.println("ROOT USER CONTROLLER: User added");
        }
        Stage stage = (Stage) createUserButton.getScene().getWindow();
        stage.close();
    }

    private void initializeInstructorComboBox() {
        System.out.println("ROOT USER CONTROLLER: initializeInstructorComboBox");
        ObservableList<String> instructorList = FXCollections.observableArrayList();
        String[] instructors = instrtuctorAnManager.getAllInstructorsSurname();
        for (String instructor : instructors) {
            if (instructor != null) {
                instructorList.add(instructor);
            }
        }
        InstructorComboBox.setItems(instructorList);
    }

    @FXML
    private void initialize() {
        System.out.println("ROOT USER CONTROLLER: initialize combo box...");
        initializeInstructorComboBox();
    }
}
