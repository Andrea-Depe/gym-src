package gym_software.gym_software_third_project.view;


import gym_software_project.gym_software_project.MainView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This class is used to create the RootAdminView page.
 * This class extends Application class.
 * The RootAdminView page is used to create a new admin account.
 */
public class RootAdminView extends Application {

    /**
     * This method is used to start the RootAdminView page.
     * @param stage
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainView.class.getResource("root-create-admin-interface.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 600);
        stage.setTitle("RootAdminPage");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This method is used to launch the RootAdminView page.
     * @param args
     */
    public static void main(String[] args) {
        launch();
    }
}
