package gym_software.gym_software_third_project.model;

import java.sql.*;

public class UserManager {
    // Cambia il percorso del database a una directory con permessi di scrittura
    private static final String DATABASE_URL = "jdbc:h2:./db/db_user";

    public UserManager() {
        // Costruttore privato per impedire la creazione di istanze
    }

    public static int addUser(UserModel user) {
        int generatedId = -1;  // Valore di default nel caso in cui l'utente non venga aggiunto
        try (Connection connection = DriverManager.getConnection(DATABASE_URL)) {
            // Get the ID of the role first
            PreparedStatement roleStatement = connection.prepareStatement("SELECT id FROM roles WHERE ruolo = ?");
            roleStatement.setString(1, user.get_Role());
            ResultSet roleResultSet = roleStatement.executeQuery();
            int roleId = -1;
            if (roleResultSet.next()) {
                roleId = roleResultSet.getInt("id");
            } else {
                System.out.println("Role not found");
                return generatedId;
            }

            // Insert the user into the utenti table
            PreparedStatement statement = connection.prepareStatement("INSERT INTO utenti (username, password, ruolo) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.get_Username());
            statement.setString(2, user.get_Pwd());
            statement.setInt(3, roleId);
            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    generatedId = generatedKeys.getInt(1); // Retrieve the generated ID
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return generatedId;
    }

    public static void deleteUser(UserModel user) {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement statement = connection.prepareStatement("DELETE FROM utenti WHERE username = ?")) {
            statement.setString(1, user.get_Username());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void changeUserPermission(UserModel user, int role) {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement statement = connection.prepareStatement("UPDATE utenti SET ruolo = ? WHERE username = ?")) {
            statement.setInt(1, role);
            statement.setString(2, user.get_Username());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean containUser(String username) {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM utenti WHERE username = ?")) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean authentication(String username, String password) {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM UTENTI WHERE username = ? AND password = ?")) {
            statement.setString(1, username);
            statement.setString(2, password);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static UserModel getUser(String username) {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement statement = connection.prepareStatement("SELECT USERNAME, PASSWORD, ROLES.RUOLO as RUOLO_STRING FROM UTENTI JOIN ROLES ON UTENTI.RUOLO = ROLES.ID WHERE USERNAME = ?")) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String dbUsername = resultSet.getString("username");
                    String dbPwd = resultSet.getString("password");
                    String dbRole = resultSet.getString("ruolo_string");
                    UserModel user_temp = new UserModel(dbUsername, dbPwd);
                    user_temp.set_Role(dbRole);
                    return user_temp;
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int getUserID(String username) {
        int userID = -1;  // Valore di default nel caso in cui l'utente non venga trovato
        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement statement = connection.prepareStatement("SELECT ID FROM utenti WHERE username = ?")) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    userID = resultSet.getInt("ID");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userID;
    }

    // Change password
    public static void changePassword(int userID, String newPassword) {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement statement = connection.prepareStatement("UPDATE utenti SET password = ? WHERE ID = ?")) {
            statement.setString(1, newPassword);
            statement.setInt(2, userID);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
