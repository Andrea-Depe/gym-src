package gym_software.gym_software_third_project.controller;



import gym_software.gym_software_third_project.MainView;
import gym_software.gym_software_third_project.model.UserManager;
import gym_software.gym_software_third_project.model.UserModel;
import gym_software.gym_software_third_project.view.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Objects;

public class UserController {
    @FXML
    private PasswordField passwordFieldControl;

    @FXML
    private Label registrationText;

    @FXML
    private Button registraionButton;

    @FXML
    private Button GoBackButton;

    @FXML
    private Label RegisterText;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label wrongAuth;

    @FXML
    private Label mismatchPwd;

    @FXML
    private void onLogInButtonClick() {
        // Chiamare il metodo per avviare la vista di login
        showLoginView();
    }
    @FXML
    private void handleBackButtonClick() {
        MainView.goBack();
    }

    @FXML
    protected void onRegisterButtonClick() {
        showRegistrationView();
    }

    // Metodo per istanziare e avviare la vista di login
    private void showLoginView() {
        try {
            // Istanzia la classe LoginView
            LoginView loginView = new LoginView();

            // Avvia la vista
            loginView.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
            // Gestisci l'eccezione, se necessario
        }
    }

    private void showRegistrationView () {
        try {
            // Istanzia la classe
            RegistrationView registrationView = new RegistrationView();

            // avvia la vista
            registrationView.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleLogin() {
        System.out.println("USER CONTROLLER: login button pressed");

        String username = usernameField.getText();
        String password = passwordField.getText();

        System.out.println("USER CONTROLLER: user: " + username + " tries to log in with password: " + password);

        // li setto a false qui così quando schiaccio il pulsante li leva per
        // un prossimo tentativo di login

        wrongAuth.setVisible(false);
        registrationText.setVisible(false);
        registraionButton.setVisible(false);

        if (UserManager.authentication(username, password)) {
            System.out.println("USER CONTROLLER: user authentication successful");

            UserModel user_logged = new UserModel();
            UserManager userManager = new UserManager();
            user_logged = userManager.getUser(username);

            if (Objects.equals(user_logged.get_Role(), "root")) {
                System.out.println("USER CONTROLLER: the connected user has root privileges");

                try {
                    RootHomePage rootHomePage = new RootHomePage();
                    rootHomePage.start(new Stage());
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else if (Objects.equals(user_logged.get_Role(), "admin")) {
                System.out.println("USER CONTROLLER: the connected user has admin privileges");

                 int logged_ID = userManager.getUserID(user_logged.get_Username());

                 System.out.println("USER CONTROLLER: logged_ID: " + logged_ID);

                AdminHomeController adminHomeController = new AdminHomeController();

                adminHomeController.currentAdminLogged(logged_ID);

                try {
                    AdminHomePageView adminHomePageView = new AdminHomePageView();
                    adminHomePageView.start(new Stage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else if (Objects.equals(user_logged.get_Role(), "user")) {
                System.out.println("USER CONTROLLER: the connected user has user privileges");

                int logged_ID = userManager.getUserID(user_logged.get_Username());

                System.out.println("USER CONTROLLER: logged_ID: " + logged_ID);

                UserHomeController userHomeController = new UserHomeController();

                userHomeController.currentLogged(logged_ID);

                try {
                    UserHomeView userHomeView = new UserHomeView();
                    userHomeView.start(new Stage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
        else {
            System.out.println("USER CONTROLLER: user authentication failed");
            wrongAuth.setVisible(true);

            if (UserManager.containUser(username)) {
                System.out.println("USER CONTROLLER: registered user, wrong password");
                wrongAuth.setText("registered user, wrong password");
            }
            else {
                System.out.println("USER CONTROLLER: non-registered user");
                wrongAuth.setText("non-registered user");
                registraionButton.setVisible(true);
                registrationText.setVisible(true);
            }
        }

    }


    @FXML
    private void handleRegister() {
        UserManager userManager = new UserManager();
        System.out.println("USER CONTROLLER: registration button pressed");

        String username = usernameField.getText();
        String password = passwordField.getText();
        String check_pwd = passwordFieldControl.getText();

        if (Objects.equals(password, check_pwd)) {
            System.out.println("USER CONTROLLER: creating user...\n User: " + username + "\nPassword: " + password);
            UserModel user = new UserModel(username, password);
            user.set_Role("user");
            UserManager.addUser(user);
        }
        else {
            System.out.println("USER CONTROLLER: password don't match");
            mismatchPwd.setVisible(true);
        }
    }
}
