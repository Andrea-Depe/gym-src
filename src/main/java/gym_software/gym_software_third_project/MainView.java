package gym_software.gym_software_third_project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Stack;

public class MainView extends Application {

    private static Stage primaryStage;
    private static Stack<Scene> sceneHistory = new Stack<>();

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        primaryStage.setResizable(false);
        loadScene("login-view.fxml", "Login");
    }

    public static void main(String[] args) {
        launch();
    }

    public static void loadScene(String fxmlFile, String title) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainView.class.getResource(fxmlFile));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 600);
        scene.getStylesheets().add(MainView.class.getResource("style.css").toExternalForm());

        if (primaryStage.getScene() != null) {
            sceneHistory.push(primaryStage.getScene());
        }

        primaryStage.setTitle(title);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void goBack() {
        if (!sceneHistory.isEmpty()) {
            Scene previousScene = sceneHistory.pop();
            primaryStage.setScene(previousScene);
            primaryStage.show();
        }
    }
}
