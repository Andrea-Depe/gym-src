package gym_software.gym_software_third_project.controller;



import gym_software.gym_software_third_project.MainView;
import gym_software.gym_software_third_project.model.AgendaManager;
import gym_software.gym_software_third_project.model.EserciziManager;
import gym_software.gym_software_third_project.model.UserAnManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class UserBookingController {
    @FXML
    private void handleBackButtonClick() {
        MainView.goBack();
    }

    EserciziManager eserciziManager = new EserciziManager();
    AgendaManager agendaManager = new AgendaManager();

    private static int currentUserID;

    @FXML
    private Button GoBackButton;

    @FXML
    private ComboBox<String> bookingComboBox;

    public void setCurrentUserID(int userID) {
        currentUserID = userID;
        System.out.println("USER BOOKING CONTROLLER: current_ID set to " + currentUserID);
    }

    public void handleBooking() {
        System.out.println("USER BOOKING CONTROLLER: Prenotazione button pressed");
        String booking = bookingComboBox.getValue();
        agendaManager.setLesson(booking, currentUserID);
    }


    private void initializeComboBox() {
        UserAnManager userAnManager = new UserAnManager();

        int ID_instr_temp = userAnManager.getInstructorByUserID(currentUserID);

        System.out.println("USER BOOKING CONTROLLER: initializeComboBox");
        ObservableList<String> bookingList = FXCollections.observableArrayList();
        System.out.println("USER BOOKING CONTROLLER: ID_instr_temp " + ID_instr_temp);
        String[] freeLessons = agendaManager.getFreeLessons(ID_instr_temp);
        System.out.println("USER BOOKING CONTROLLER: freeLessons " + freeLessons.length);
        for (String lesson : freeLessons) {
            System.out.println("USER BOOKING CONTROLLER: lesson " + lesson);
            bookingList.add(lesson);
        }
        bookingComboBox.setItems(bookingList);
    }

    @FXML
    private void initialize() {
        System.out.println("USER BOOKING CONTROLLER: initialize");
        initializeComboBox();
    }

}
