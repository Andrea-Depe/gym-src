package gym_software.gym_software_third_project.view;


import gym_software_project.gym_software_project.MainView;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class RegistrationView extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        MainView.loadScene("registration-view.fxml", "Registration");
    }

    public static void main(String[] args) {
        launch();
    }
}
