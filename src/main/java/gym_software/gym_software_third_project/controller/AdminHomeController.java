package gym_software.gym_software_third_project.controller;



import gym_software.gym_software_third_project.MainView;
import gym_software.gym_software_third_project.view.AdminAgendaView;
import gym_software.gym_software_third_project.view.AdminChangePwdView;
import gym_software.gym_software_third_project.view.AdminExercizeView;
import gym_software.gym_software_third_project.view.AdminUserTableView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminHomeController {
    private static int currentUserID;

    public void currentAdminLogged (int userID) {
        currentUserID = userID;
        System.out.println("ADMIN HOME CONTROLLER: current_ID set to " + currentUserID);
    }


    @FXML
    private Button GoBackButton;

    @FXML
    private void handleBackButtonClick() {
        MainView.goBack();
    }



    @FXML
    private Button btnLogout;

    @FXML
    private void handleAgenda() {
        System.out.println("ADMIN HOME CONTROLLER: Agenda button pressed");

        try {
            AdminAgendaView adminAgendaView = new AdminAgendaView(currentUserID);
            adminAgendaView.start(new Stage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleClientiTable() {
        System.out.println("ADMIN HOME CONTROLLER: Clienti button pressed");

        try {
            AdminUserTableView adminUserTableView = new AdminUserTableView(currentUserID);
            adminUserTableView.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    @FXML
    private void handlePwdChange() {
        System.out.println("ADMIN HOME CONTROLLER: Password change button pressed");

        try {
            AdminChangePwdView adminChangePwdView = new AdminChangePwdView(currentUserID);
            adminChangePwdView.start(new Stage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleLogout() {
        System.out.println("ADMIN HOME CONTROLLER: Logout button pressed");
        //chiudi la finestra corrente
        Stage stage = (Stage) btnLogout.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleEserciziTable() {
        System.out.println("ADMIN HOME CONTROLLER: Esercizi button pressed");
        try {
            AdminExercizeView adminExercizeView = new AdminExercizeView(currentUserID);
            adminExercizeView.start(new Stage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
