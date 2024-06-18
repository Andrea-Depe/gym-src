package gym_software.gym_software_third_project.view;

import gym_software_project.gym_software_project.MainView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This class is used to create the RootHomePage page.
 * This class extends Application class.
 * The RootHomePage page is the main page for the root user,
 * allowing the creation of new users, new instructors,
 * new clients and the deletion of users.
 */
public class RootHomePage extends Application{

    /**
     * This method is used to start the RootHomePage page.
     * @param stage
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainView.class.getResource("root-home.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 600);
        stage.setTitle("RootHomePage");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This method is used to launch the RootHomePage page.
     * @param args
     */
    public static void main(String[] args) {
        launch();
    }
}
