package nl.oose.han.datalayer.DTO;

public class UserDTO {
    private String user;
    private String password;

    // Default constructor
    public UserDTO() {}

    // Getters and setters
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}