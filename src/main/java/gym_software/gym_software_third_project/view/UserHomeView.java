package gym_software.gym_software_third_project.view;


import gym_software_project.gym_software_project.MainView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class UserHomeView extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainView.class.getResource("user-home.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 600);
        stage.setTitle("UserHomePage");
        stage.setScene(scene);
        stage.show();
    }

    public static void main (String[] args) { launch(); }

}
