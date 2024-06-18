package gym_software.gym_software_third_project.model;

public class UserModel {
    private String username;
    private String role;
    private String pwd;

    public UserModel (String username, String pwd) {
        this.username = username;
        this.pwd = pwd;
    }

    public UserModel() {};

    public void set_Role (String role) {
        this.role = role;
    }

    public String get_Role () {
        return this.role;
    }

    public String get_Username () {
        return this.username;
    }

    public String get_Pwd () {
        return this.pwd;
    }
}
