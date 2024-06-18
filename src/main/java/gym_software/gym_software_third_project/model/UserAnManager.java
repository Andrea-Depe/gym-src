package gym_software.gym_software_third_project.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserAnManager {
    private static final String DATABASE_URL = "jdbc:h2:/Users/matteofranchini/Documents/gym_software_third_project/db/db_user";

    public UserAnManager () {}

    public static void addUser(UserAnagraficaModel user) {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL)) {
            // Get ID_ISTRUTTORE value from ISTRUTTORI table based on ruolo value
            PreparedStatement idStatement = connection.prepareStatement("SELECT ID_ISTRUTTORE FROM ISTRUTTORI WHERE ID_ISTRUTTORE = ?");
            idStatement.setInt(1, user.getId_istruttore());
            ResultSet idResult = idStatement.executeQuery();
            int id_istruttore = -1;
            if (idResult.next()) {
                id_istruttore = idResult.getInt("ID_ISTRUTTORE");
            } else {
                System.out.println("USER ANAGRAFICA MANAGER: instructor doesn't exist in the database");
                return;
            }

            // Insert user into CLIENTI table with ID_ISTRUTTORE value
            PreparedStatement statement = connection.prepareStatement("INSERT INTO CLIENTI (CF, nome, cognome, peso, altezza, ETÀ, ID_UTENTE, ID_ISTRUTTORE) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            statement.setString(1, user.getCF());
            statement.setString(2, user.getName());
            statement.setString(3, user.getSurname());
            statement.setInt(4, user.getPeso());
            statement.setInt(5, user.getAltezza());
            statement.setInt(6, user.getAge());
            statement.setInt(7, user.getID_utente());
            statement.setInt(8, id_istruttore);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static UserAnagraficaModel getClienteByID(int ID) {
        UserAnagraficaModel user = new UserAnagraficaModel();
        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM CLIENTI WHERE ID_UTENTE = ?")) {
            statement.setInt(1, ID);
            statement.executeQuery();
            user.setCF(statement.getResultSet().getString("CF"));
            user.setName(statement.getResultSet().getString("nome"));
            user.setSurname(statement.getResultSet().getString("cognome"));
            user.setPeso(statement.getResultSet().getInt("peso"));
            user.setAltezza(statement.getResultSet().getInt("altezza"));
            user.setAge(statement.getResultSet().getInt("ETÀ"));
            user.setId_istruttore(statement.getResultSet().getInt("ID_ISTRUTTORE"));
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public static boolean ContainUser (String CF) {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM CLIENTI WHERE CF = ?")) {
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

    public static String[] getAllClientiByInstructor(int ID_istruttore) {
        List<String> clientiList = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement statement = connection.prepareStatement("SELECT CF FROM CLIENTI WHERE ID_ISTRUTTORE = ?")) {
            statement.setInt(1, ID_istruttore);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                clientiList.add(resultSet.getString("CF"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientiList.toArray(new String[0]);
    }

    public static int getIDByCF(String CF) {
        int ID = -1;
        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement statement = connection.prepareStatement("SELECT ID_UTENTE FROM CLIENTI WHERE CF = ?")) {
            statement.setString(1, CF);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                ID = resultSet.getInt("ID_UTENTE");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ID;
    }

    public static int getInstructorByUserID (int userID) {
        int ID = -1;
        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement statement = connection.prepareStatement("SELECT ID_ISTRUTTORE FROM CLIENTI WHERE ID_UTENTE = ?")) {
            statement.setInt(1, userID);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                ID = resultSet.getInt("ID_ISTRUTTORE");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ID;
    }
}