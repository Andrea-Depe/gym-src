package gym_software.gym_software_third_project.view;

import gym_software_project.gym_software_project.MainView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This class is used to create the root user view.
 * It extends the Application class.
 * It overrides the start method of the Application class.
 * It is used to create the main user view.
 *
 * @author Matteo Franchini, Andrea De Pellegrin
 * @version 1.0
 * @since 2024
 */
public class RootUserView extends Application{

    /**
     * This method is used to create the main user view.
     * @param stage
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainView.class.getResource("root-create-user-interface.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 600);
        stage.setTitle("RootUserPage");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This method is used to launch the application.
     * @param args
     */
    public static void main(String[] args) {
        launch();
    }
}
