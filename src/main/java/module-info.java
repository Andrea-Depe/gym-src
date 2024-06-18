module gym_software.gym_software_third_project {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens gym_software.gym_software_third_project to javafx.fxml;

    exports gym_software.gym_software_third_project.view;
    exports gym_software.gym_software_third_project.controller;
    opens gym_software.gym_software_third_project.controller to javafx.fxml;
    exports gym_software.gym_software_third_project;
}