package gym_software.gym_software_third_project.view;


import gym_software_project.gym_software_project.MainView;
import gym_software_project.gym_software_project.controller.UserBookingController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class UserBookingView extends Application {
    private int currentUserID;

    public UserBookingView(int currentUserID) {
        this.currentUserID = currentUserID;
        System.out.println("USER BOOKING VIEW: current_ID set to " + currentUserID);
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainView.class.getResource("user-booking.fxml"));
        UserBookingController userBookingController = new UserBookingController();
        userBookingController.setCurrentUserID(currentUserID);
        Scene scene = new Scene(fxmlLoader.load(), 1200, 600);
        stage.setTitle("UserBookingView");
        stage.setScene(scene);
        stage.show();
    }

    public static void main (String[] args) { launch(); }
}

