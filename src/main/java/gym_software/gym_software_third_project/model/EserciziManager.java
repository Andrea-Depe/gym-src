package gym_software.gym_software_third_project.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class EserciziManager {
    private static String DATABASE_URL = "jdbc:h2:/Users/matteofranchini/Documents/gym_software_third_project/db/db_user";

    public static void addEsercizio(EserciziModel eserciziModel) {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement statement = connection.prepareStatement("INSERT INTO ESERCIZI (ID_UTENTE, ID_ISTRUTTORE, ESERCIZIO, RIPETIZIONI, SERIE) VALUES (?, ?, ?, ?, ?)")) {
            statement.setInt(1, eserciziModel.getId_utente());
            statement.setInt(2, eserciziModel.getId_istruttore());
            statement.setString(3, eserciziModel.getNome_esercizio());
            statement.setInt(4, eserciziModel.getRipetizioni());
            statement.setInt(5, eserciziModel.getSerie());
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // crea un metodo che restituisca una lista di EserciziModel per utente

    public static ObservableList<EserciziModel> getEserciziByUser(int ID_utente) {
        ObservableList<EserciziModel> esercizi = FXCollections.observableArrayList();
        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM ESERCIZI WHERE ID_UTENTE = ?")) {
            statement.setInt(1, ID_utente);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                EserciziModel eserciziModel = new EserciziModel();
                eserciziModel.setId_utente(resultSet.getInt("ID_UTENTE"));
                eserciziModel.setId_istruttore(resultSet.getInt("ID_ISTRUTTORE"));
                eserciziModel.setNome_esercizio(resultSet.getString("ESERCIZIO"));
                eserciziModel.setRipetizioni(resultSet.getInt("RIPETIZIONI"));
                eserciziModel.setSerie(resultSet.getInt("SERIE"));
                esercizi.add(eserciziModel);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return esercizi;
    }

    public static void getEserciziByInstructor(int ID_istruttore) {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM ESERCIZI WHERE ID_ISTRUTTORE = ?")) {
            statement.setInt(1, ID_istruttore);
            statement.executeQuery();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void getAllEsercizi() {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM ESERCIZI")) {
            statement.executeQuery();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
