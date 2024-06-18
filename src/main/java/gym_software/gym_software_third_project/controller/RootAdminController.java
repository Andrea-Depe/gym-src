package gym_software.gym_software_third_project.controller;



import gym_software.gym_software_third_project.MainView;
import gym_software.gym_software_third_project.model.InstrtuctorAnManager;
import gym_software.gym_software_third_project.model.InstructorAnagraficaModel;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RootAdminController {
    public static int id_istr = 0;
    private InstrtuctorAnManager instrtuctorAnManager = new InstrtuctorAnManager();

    @FXML
    private Node createAdminButton;

    @FXML
    private Button GoBackButton;

    @FXML
    private TextField CFTextField;

    @FXML
    private TextField NameTextField;

    @FXML
    private TextField SurnameTextField;
    @FXML
    private void handleBackButtonClick() {
        MainView.goBack();
    }
    @FXML
    private void createInstructor() {
        System.out.println("ROOT ADMIN CONTROLLER: create Instructor");

        String temp_CF = CFTextField.getText();
        String temp_name = NameTextField.getText();
        String temp_surname = SurnameTextField.getText();

        System.out.println("ROOT ADMIN CONTROLLER: " + temp_CF + " " + temp_name + " " + temp_surname + " " + id_istr);

        InstructorAnagraficaModel temp_istr = new InstructorAnagraficaModel(temp_CF, temp_name, temp_surname, id_istr);

        if (instrtuctorAnManager.containInstructor(temp_istr.getCF())) {
            System.out.println("ROOT ADMIN CONTROLLER: Instructor already exists");
            return;
        } else {
            System.out.println("ROOT ADMIN CONTROLLER: Instructor does not exist");
            instrtuctorAnManager.addInstructor(temp_istr);
            System.out.println("ROOT ADMIN CONTROLLER: Instructor added");
        }

        Stage stage = (Stage) createAdminButton.getScene().getWindow();
        stage.close();
    }
}
