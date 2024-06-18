package gym_software.gym_software_third_project.controller;



import gym_software.gym_software_third_project.MainView;
import gym_software.gym_software_third_project.model.UserManager;
import gym_software.gym_software_third_project.model.UserModel;
import gym_software.gym_software_third_project.view.RootAdminView;
import gym_software.gym_software_third_project.view.RootUserView;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RootHomeController {
    private UserManager userManager = new UserManager();

    private static int ID_roleToChange;

    private int ID_roleNewUser;

    @FXML
    private TextField usernameSearch;

    @FXML
    private Button GoBackButton;

    @FXML
    private Label usernameSearchResult;

    @FXML
    private Label searchMessage;

    @FXML
    private ComboBox<String> dropdownMenu;

    @FXML
    private TextField createUser;

    @FXML
    private ComboBox<String> dropdownMenuCreateUser;

    @FXML
    private Label creationUserResult;

    @FXML
    private TextField deleteUser;

    @FXML
    private Label deleteUserResult;

    @FXML
    private void initialize() {
        initializeDropdownMenuCreateUser();
    }

    @FXML
    private void handleBackButtonClick() {
        MainView.goBack();
    }

    @FXML
    private void handleSearch() {
        System.out.println("ROOT HOME CONTROLLER: User search in progress...");

        String temp_username = usernameSearch.getText();
        UserModel userModel = new UserModel();
        searchMessage.setVisible(true);

        if (userManager.containUser(temp_username)) {
            userModel = userManager.getUser(temp_username);

            System.out.println("ROOT HOME CONTROLLER: user: " + userModel.get_Username() + " searched");
            usernameSearchResult.setVisible(true);
            usernameSearchResult.setText("UTENTE: " + userModel.get_Username() + " RUOLO: " + userModel.get_Role());
        }
        else {
            usernameSearchResult.setVisible(true);
            usernameSearchResult.setText("user not found");
            System.out.println("ROOT HOME CONTROLLER: user not found");
        }
    }

    @FXML
    private void handleCreate() {
        System.out.println("ROOT HOME CONTROLLER: Creation of new user...");

        String temp_username = createUser.getText();

        if (!userManager.containUser(temp_username)) {
            if (ID_roleToChange == 2) {
                UserModel new_user = new UserModel(temp_username, "1234");
                new_user.set_Role("admin");

                RootAdminController rootAdminController = new RootAdminController();
                rootAdminController.id_istr = userManager.addUser(new_user);

                System.out.println("ROOT HOME CONTROLLER: user: " + new_user.get_Username() + " role: "
                        + new_user.get_Role() + " created");

                try {
                    RootAdminView rootAdminView = new RootAdminView();
                    rootAdminView.start(new Stage());
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else if (ID_roleToChange == 3) {
                UserModel new_user = new UserModel(temp_username, "1234");
                new_user.set_Role("user");

                RootUserController rootUserController = new RootUserController();
                rootUserController.id_user = userManager.addUser(new_user);

                System.out.println("ROOT HOME CONTROLLER: user: " + new_user.get_Username() + " role: "
                        + new_user.get_Role() + " created");

                try {
                    RootUserView rootUserView = new RootUserView();
                    rootUserView.start(new Stage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("ROOT HOME CONTROLLER: role not found");
                return;
            }
            creationUserResult.setVisible(true);
            creationUserResult.setText("User created");
        } else {
            creationUserResult.setVisible(true);
            creationUserResult.setText("User already exist, change username!");
        }
    }

    @FXML
    private void handleDelete() {
        System.out.println("ROOT HOME CONTROLLER: user deletion...");

        String temp_username = deleteUser.getText();

        if (userManager.containUser(temp_username)) {
            UserModel temp_user = new UserModel();
            temp_user = userManager.getUser(temp_username);

            userManager.deleteUser(temp_user);

            System.out.println("ROOT HOME CONTROLLER: user: " + temp_username + " deleted");
            deleteUserResult.setVisible(true);
            deleteUserResult.setText("User deleted");
        }
        else {
            System.out.println("ROOT HOME CONTROLLER: user: " + temp_username + " not found");
            deleteUserResult.setVisible(true);
            deleteUserResult.setText("User not found");
        }


    }

    private void initializeDropdownMenuCreateUser () {
        // Aggiungi opzioni al menù a tendina
        dropdownMenuCreateUser.setItems(FXCollections.observableArrayList("Admin", "User"));


        // Aggiungi un listener per gestire gli eventi di selezione
        dropdownMenuCreateUser.setOnAction(event -> {
            String selectedOption = dropdownMenuCreateUser.getSelectionModel().getSelectedItem();

            System.out.println("ROOT HOME CONTOROLLER: Opzione selezionata: " + selectedOption);
            switch (selectedOption) {
                case "Admin":
                    ID_roleToChange = 2;
                    System.out.println("ROOT HOME CONTROLLER: " + ID_roleToChange);
                    break;
                case "User":
                    ID_roleToChange = 3;
                    System.out.println("ROOT HOME CONTROLLER: " + ID_roleToChange);
                    break;

                default:
                    System.out.println("ROOT HOME CONTROLLER: role not found");
                    break;
            }
        });
    }
}