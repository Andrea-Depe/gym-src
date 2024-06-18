package gym_software.gym_software_third_project.view;


import gym_software_project.gym_software_project.MainView;
import gym_software_project.gym_software_project.controller.AdminUserTableController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminUserTableView extends Application {
    private static int currentUserID;

    public AdminUserTableView(int userID) {
        currentUserID = userID;
        System.out.println("ADMIN USER TABLE VIEW: current_ID set to " + currentUserID);

    }
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainView.class.getResource("clienti-view.fxml"));
        AdminUserTableController adminUserTableController = new AdminUserTableController();
        adminUserTableController.set_current_ID(currentUserID);
        Scene scene = new Scene(fxmlLoader.load(), 1200, 600);
        stage.setTitle("UserTableView");
        stage.setScene(scene);
        stage.show();
    }

    public static void main (String[] args) { launch(); }
}
