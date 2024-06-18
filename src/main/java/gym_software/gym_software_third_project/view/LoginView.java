package gym_software.gym_software_third_project.view;


import gym_software_project.gym_software_project.MainView;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginView extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        MainView.loadScene("login-view.fxml", "Login");
    }

    public static void main(String[] args) {
        launch();
    }
}
