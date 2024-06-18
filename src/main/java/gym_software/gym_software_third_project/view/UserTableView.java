package gym_software.gym_software_third_project.view;


import gym_software_project.gym_software_project.MainView;
import gym_software_project.gym_software_project.controller.UserExercizeController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class UserTableView extends Application {
    private int currentUserID;

    public UserTableView(int currentUserID) {
        this.currentUserID = currentUserID;
        System.out.println("USER TABLE VIEW: current_ID set to " + currentUserID);
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainView.class.getResource("user-table.fxml"));
        UserExercizeController userTableController = new UserExercizeController();
        userTableController.set_current_ID(currentUserID);
        Scene scene = new Scene(fxmlLoader.load(), 1200, 600);
        stage.setTitle("UserTableView");
        stage.setScene(scene);
        stage.show();
    }

    public static void main (String[] args) { launch(); }

}
