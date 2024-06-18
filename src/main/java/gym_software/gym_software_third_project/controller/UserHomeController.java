package gym_software.gym_software_third_project.controller;


import gym_software.gym_software_third_project.MainView;
import gym_software.gym_software_third_project.view.AdminChangePwdView;
import gym_software.gym_software_third_project.view.UserBookingView;
import gym_software.gym_software_third_project.view.UserTableView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class UserHomeController {
    private static int currentUserID;

    @FXML
    private Button GoBackButton;

    @FXML
    private void handleBackButtonClick() {
        MainView.goBack();
    }

    public void currentLogged (int userID) {
        currentUserID = userID;
        System.out.println("USER HOME CONTROLLER: current_ID set to " + currentUserID);
    }

    @FXML
    private Button btnLogout;

    @FXML
    private void handlePrenotazione() {
        System.out.println("USER HOME CONTROLLER: Prenotazione button pressed");

        try {
            UserBookingView userBookingView = new UserBookingView(currentUserID);
            userBookingView.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handlePwdChange() {
        System.out.println("USER HOME CONTROLLER: Password change button pressed");

        try {
            AdminChangePwdView adminChangePwdView = new AdminChangePwdView(currentUserID);
            adminChangePwdView.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleEserciziTable() {
        System.out.println("USER HOME CONTROLLER: Exercize button pressed");

        try {
            UserTableView userTableView = new UserTableView(currentUserID);
            userTableView.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void handleLogout() {
        System.out.println("USER HOME CONTROLLER: Logout button pressed");
        Stage stage = (Stage) btnLogout.getScene().getWindow();
        stage.close();
    }


}
