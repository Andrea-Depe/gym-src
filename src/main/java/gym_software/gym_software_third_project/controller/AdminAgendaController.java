package gym_software.gym_software_third_project.controller;

import gym_software.gym_software_third_project.MainView;
import gym_software.gym_software_third_project.model.AgendaManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.List;


public class AdminAgendaController {
    private final int FASCE_ORARIE = 20;

    private static int current_ID;

    private AgendaManager agendaManager = new AgendaManager();

    @FXML
    private CheckBox lun1, lun2, lun3, lun4;

    @FXML
    private CheckBox mar1, mar2, mar3, mar4;

    @FXML
    private CheckBox mer1, mer2, mer3, mer4;

    @FXML
    private CheckBox gio1, gio2, gio3, gio4;

    @FXML
    private CheckBox ven1, ven2, ven3, ven4;

    @FXML
    private AnchorPane rootLayout;

    @FXML
    private Button GoBackButton;

    @FXML
    private Button saveButton;

    @FXML
    private void handleBackButtonClick() {
        MainView.goBack();
    }

    private final CheckBox[] check_array = new CheckBox[FASCE_ORARIE];

    private void initializeCheckArray() {
        check_array[0] = lun1;
        check_array[1] = lun2;
        check_array[2] = lun3;
        check_array[3] = lun4;
        check_array[4] = mar1;
        check_array[5] = mar2;
        check_array[6] = mar3;
        check_array[7] = mar4;
        check_array[8] = mer1;
        check_array[9] = mer2;
        check_array[10] = mer3;
        check_array[11] = mer4;
        check_array[12] = gio1;
        check_array[13] = gio2;
        check_array[14] = gio3;
        check_array[15] = gio4;
        check_array[16] = ven1;
        check_array[17] = ven2;
        check_array[18] = ven3;
        check_array[19] = ven4;
    }

    public static void set_current_ID(int ID) {
        current_ID = ID;
        System.out.println("ADMIN AGENDA CONTROLLER: current_ID set to " + current_ID);
    }

    @FXML
    private void saveAgenda() {
        System.out.println("ADMIN AGENDA CONTROLLER: Save button pressed");

        List<Integer> checked = new ArrayList<Integer>();
        initializeCheckArray();

        for (int i = 0; i < FASCE_ORARIE; i++) {
            if (check_array[i].isSelected()) {
                System.out.println("ADMIN AGENDA CONTROLLER: check_array[" + (i+1) + "] is selected");
                checked.add(i+1);
            }
        }

        agendaManager.setAgegna(current_ID, checked);

    }
}
