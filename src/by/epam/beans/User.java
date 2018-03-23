package by.epam.beans;

public class User {

    private String login;
    private String password;
    private String email;
    private int role;
    private String dob;


    public User() {
    }

    public User(String login, String password, String email, int role, String dob) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.role = role;
        this.dob = dob;
    }

    public User(String login, String password, String email, String dob) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.dob = dob;

    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }


    @Override
    public String toString() {
        return login + " " + password + " " + email + " " + role + " " + dob;
    }
}
