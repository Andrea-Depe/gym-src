package gym_software.gym_software_third_project.model;

public class InstructorAnagraficaModel {
    private String CF;
    private String name;
    private String surname;
    private int ID;

    public InstructorAnagraficaModel() {}

    public InstructorAnagraficaModel (String CF, String name, String surname, int ID) {
        this.CF = CF;
        this.name = name;
        this.surname = surname;
        this.ID = ID;
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

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
