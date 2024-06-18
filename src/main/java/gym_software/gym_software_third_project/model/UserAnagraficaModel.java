package gym_software.gym_software_third_project.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;

public class UserAnagraficaModel {
    private String CF;
    private String name;
    private String surname;
    private int peso;
    private int age;
    private int altezza;
    private int id_utente;
    private int id_istruttore;

    public UserAnagraficaModel () {}

    public UserAnagraficaModel (String cf, String name, String surname,
                         int peso, int age, int altezza, int id_utente,
                         int id_istruttore) {
        this.CF = cf;
        this.name = name;
        this.surname = surname;
        this.peso = peso;
        this.age = age;
        this.altezza = altezza;
        this.id_utente = id_utente;
        this.id_istruttore = id_istruttore;
    }

    public String getCF() {
        return CF;
    }

    public void setCF(String CF) {
        this.CF = CF;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAltezza() {
        return altezza;
    }

    public void setAltezza(int altezza) {
        this.altezza = altezza;
    }

    public int getId_istruttore() {
        return id_istruttore;
    }

    public void setId_istruttore(int id_istruttore) {
        this.id_istruttore = id_istruttore;
    }

    public int getID_utente() {
        return id_utente;
    }

    public void setID_utente(int id_utente) {
        this.id_utente = id_utente;
    }

    public String toString() {
        return "UserAnagraficaModel{" +
                "CF='" + CF + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", peso=" + peso +
                ", age=" + age +
                ", altezza=" + altezza +
                ", id_istruttore=" + id_istruttore +
                '}';
    }

    public ObservableValue<String> userNameProperty() {
        return new SimpleStringProperty(name);
    }

    public ObservableValue<String> userSurnameProperty() {
        return new SimpleStringProperty(surname);
    }

    public ObservableValue<String> userHeightProperty() {
        return new SimpleStringProperty(Integer.toString(altezza));
    }

    public ObservableValue<String> userWeightProperty() {
        return new SimpleStringProperty(Integer.toString(peso));
    }

    public ObservableValue<String> userAgeProperty() {
        return new SimpleStringProperty(Integer.toString(age));
    }

}
