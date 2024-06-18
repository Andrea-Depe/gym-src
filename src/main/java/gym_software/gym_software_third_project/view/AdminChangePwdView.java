package gym_software.gym_software_third_project.view;


import gym_software_project.gym_software_project.MainView;
import gym_software_project.gym_software_project.controller.AdminChangePwdController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminChangePwdView extends Application {
    private int currentUserID;

    public AdminChangePwdView(int currentUserID) {
        this.currentUserID = currentUserID;
    }

    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainView.class.getResource("admin-change-pwd.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 600);
        AdminChangePwdController adminChangePwdController = new AdminChangePwdController();
        adminChangePwdController.set_current_ID(currentUserID);
        stage.setTitle("Change Password");
        stage.setScene(scene);
        stage.show();
    }

    public static void main (String[] args) { launch(); }
}
