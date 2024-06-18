package gym_software.gym_software_third_project.model;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AgendaManager {
    private static String DATABASE_URL = "jdbc:h2:/Users/andre/IdeaProjects/gym_software_project/db/db_init.sql";

    public AgendaManager() {
    }

    public static String getTimeByID(int ID) {
        String time = null;
        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement statement = connection.prepareStatement("SELECT FASCIA_ORARIA FROM FASCE_ORARIE WHERE ID = ?")) {
            statement.setInt(1, ID);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                time = resultSet.getString("FASCIA_ORARIA");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return time;
    }

    public static String[] getAgenda() {
        String[] agenda = new String[50];
        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement statement = connection.prepareStatement("SELECT FASCIA_ORARIA FROM FASCE_ORARIE")) {
            ResultSet resultSet = statement.executeQuery();
            int i = 0;
            while (resultSet.next()) {
                agenda[i] = resultSet.getString("FASCIA_ORARIA");
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return agenda;
    }

    public static void setAgegna(int ID_istruttore, List<Integer> fasce_orarie) {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL)) {
            for (int i = 0; i < fasce_orarie.size(); i++) {
                PreparedStatement statement = connection.prepareStatement("INSERT INTO LEZIONI (ID_ISTRUTTORE, ID_FASCIA) VALUES (?, ?)");
                statement.setInt(1, ID_istruttore);
                statement.setInt(2, fasce_orarie.get(i));
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<UserAnagraficaModel> get_user_by_admin (int ID_istruttore) {
        List<UserAnagraficaModel> userAnagraficaModelList = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM CLIENTI WHERE ID_ISTRUTTORE = ?")) {
            statement.setInt(1, ID_istruttore);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                UserAnagraficaModel userAnagraficaModel = new UserAnagraficaModel();
                userAnagraficaModel.setName(resultSet.getString("NOME"));
                userAnagraficaModel.setSurname(resultSet.getString("COGNOME"));
                userAnagraficaModel.setAltezza(resultSet.getInt("ALTEZZA"));
                userAnagraficaModel.setPeso(resultSet.getInt("PESO"));
                userAnagraficaModel.setAge(resultSet.getInt("ETÀ"));
                userAnagraficaModelList.add(userAnagraficaModel);
                //userAnagraficaModelList.addFirst(userAnagraficaModel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userAnagraficaModelList;
    }

    // crea un'istruzione che mi dà le lezioni libere di un istruttore dato il suo ID
    public static String[] getFreeLessons(int ID_istruttore) {
        List<String> freeLessons = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement statement = connection.prepareStatement("SELECT FASCIA_ORARIA FROM FASCE_ORARIE WHERE ID NOT IN (SELECT ID_FASCIA FROM LEZIONI WHERE ID_ISTRUTTORE = ?)")) {
            statement.setInt(1, ID_istruttore);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                freeLessons.add(resultSet.getString("FASCIA_ORARIA"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return freeLessons.toArray(new String[0]);
    }

    // crea una funzione che data la fascia oraria come stringa va a riempire il campo con l'ID_utente che viene passato come argomento
    public static void setLesson(String fascia_oraria, int ID_utente) {
            try (Connection connection = DriverManager.getConnection(DATABASE_URL);
                 PreparedStatement statement = connection.prepareStatement("SELECT ID FROM FASCE_ORARIE WHERE FASCIA_ORARIA = ?")) {
                statement.setString(1, fascia_oraria);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    int ID_fascia = resultSet.getInt("ID");
                    PreparedStatement statement1 = connection.prepareStatement("INSERT INTO LEZIONI (ID_CLIENTE) VALUES (?)");
                    statement1.setInt(1, ID_utente);
                    statement1.executeUpdate();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }





}
