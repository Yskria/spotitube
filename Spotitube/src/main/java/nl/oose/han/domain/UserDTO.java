package nl.oose.han.domain;

public class UserDTO {
    private String user;
    private String password;
    private String token;

    public UserDTO(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public UserDTO(){

    }

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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}