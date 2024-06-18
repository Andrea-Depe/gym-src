package gym_software.gym_software_third_project.model;

import java.sql.*;

public class InstrtuctorAnManager {
    private static String DATABASE_URL = "jdbc:h2:/Users/matteofranchini/Documents/gym_software_third_project/db/db_user";

    public InstrtuctorAnManager() {
    }

    public static void addInstructor(InstructorAnagraficaModel instructor) {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement statement = connection.prepareStatement("INSERT INTO ISTRUTTORI (CF, nome, cognome, ID_ISTRUTTORE) VALUES (?, ?, ?, ?)")) {
            statement.setString(1, instructor.getCF());
            statement.setString(2, instructor.getName());
            statement.setString(3, instructor.getSurname());
            statement.setInt(4, instructor.getID());
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static InstructorAnagraficaModel getInstructorBySurname(String surname) {
        InstructorAnagraficaModel instructor = null;
        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM ISTRUTTORI WHERE COGNOME = ?")) {
            statement.setString(1, surname);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                instructor = new InstructorAnagraficaModel();
                instructor.setCF(resultSet.getString("CF"));
                instructor.setName(resultSet.getString("NOME"));
                instructor.setSurname(resultSet.getString("COGNOME"));
                instructor.setID(resultSet.getInt("ID_ISTRUTTORE"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return instructor;
    }

    public static InstructorAnagraficaModel getInstructorByID(int ID) {
        InstructorAnagraficaModel instructor = new InstructorAnagraficaModel();
        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM ISTRUTTORI WHERE ID_ISTRUTTORE = ?")) {
            statement.setInt(1, ID);
            statement.executeQuery();
            instructor.setCF(statement.getResultSet().getString("CF"));
            instructor.setName(statement.getResultSet().getString("nome"));
            instructor.setSurname(statement.getResultSet().getString("cognome"));
            instructor.setID(statement.getResultSet().getInt("ID_ISTRUTTORE"));
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return instructor;
    }

    public static boolean containInstructor(String CF) {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM ISTRUTTORI WHERE CF = ?")) {
            statement.setString(1, CF);
            statement.executeQuery();
            if (statement.getResultSet().next()) {
                return true;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String[] getAllInstructorsSurname() {
        String[] instructors = new String[1000];
        int i = 0;
        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement statement = connection.prepareStatement("SELECT COGNOME FROM ISTRUTTORI")) {
            statement.executeQuery();
            while (statement.getResultSet().next()) {
                instructors[i] = statement.getResultSet().getString("cognome");
                i++;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return instructors;
    }
}
