package gym_software.gym_software_third_project.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;

public class EserciziModel {
    private int id_utente;
    private int id_istruttore;
    private String nome_esercizio;
    private int ripetizioni;
    private int serie;

    public EserciziModel () {}

    public EserciziModel(int id_utente, int id_istruttore, String nome_esercizio, int ripetizioni, int serie) {
        this.id_utente = id_utente;
        this.id_istruttore = id_istruttore;
        this.nome_esercizio = nome_esercizio;
        this.ripetizioni = ripetizioni;
        this.serie = serie;
    }

    public int getId_utente() {
        return id_utente;
    }

    public void setId_utente(int id_utente) {
        this.id_utente = id_utente;
    }

    public int getId_istruttore() {
        return id_istruttore;
    }

    public void setId_istruttore(int id_istruttore) {
        this.id_istruttore = id_istruttore;
    }

    public String getNome_esercizio() {
        return nome_esercizio;
    }

    public void setNome_esercizio(String nome_esercizio) {
        this.nome_esercizio = nome_esercizio;
    }

    public int getRipetizioni() {
        return ripetizioni;
    }

    public void setRipetizioni(int ripetizioni) {
        this.ripetizioni = ripetizioni;
    }

    public int getSerie() {
        return serie;
    }

    public void setSerie(int serie) {
        this.serie = serie;
    }

    public ObservableValue<String> esercizeProperty() {
        return new SimpleStringProperty(nome_esercizio);
    }

    public ObservableValue<String> serieProperty() {
        return new SimpleStringProperty(String.valueOf(serie));
    }

    public ObservableValue<String> ripetizioniProperty() {
        return new SimpleStringProperty(String.valueOf(ripetizioni));
    }

}
