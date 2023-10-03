package springdatamodelmapperex.entites.users;

public class LoginDTO {
    //TODO Validate email
    private String email;
    private String password;
    public LoginDTO(String[] commandParts) {
        this.email = commandParts[1];
        this.password = commandParts[2];
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
