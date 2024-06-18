package gym_software.gym_software_third_project.view;



import gym_software_project.gym_software_project.MainView;
import gym_software_project.gym_software_project.controller.AdminAgendaController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminAgendaView extends Application {
    private int current_ID;

    public AdminAgendaView(int current_ID) {
        this.current_ID = current_ID;
        System.out.println("ADMIN AGENDA VIEW: current_ID set to " + this.current_ID);
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainView.class.getResource("admin-agenda.fxml"));
        AdminAgendaController adminAgendaController = new AdminAgendaController();
        adminAgendaController.set_current_ID(current_ID);
        Scene scene = new Scene(fxmlLoader.load(), 1200, 600);
        stage.setTitle("Admin Agenda");
        stage.setScene(scene);
        stage.show();
    }

    public static void main (String[] args) { launch(); }
}
