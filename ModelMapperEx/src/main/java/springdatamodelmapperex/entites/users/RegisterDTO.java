package springdatamodelmapperex.entites.users;

import springdatamodelmapperex.exceptions.ValidationException;

/**
 * Validates the data for registering a user.
 * Email must be ..
 * Password must be ..
 *
 * commandParts[0] is skipped because it contains the command name
 * which is not needed in the user object
 *
 */
public class RegisterDTO {
    private String email;
    private String password;
    private String confirmPassword;
    private String fullName;

    public RegisterDTO(String[] commandParts) {
        this.email = commandParts[1];
        this.password = commandParts[2];
        this.confirmPassword = commandParts[3];
        this.fullName = commandParts[4];

        this.validate();
    }

//    If we can have set operations, we can make validation in setters, else we can make them in validate method(setters must be private).
    public void validate() {
        int indexOfAt = email.indexOf("@");
        int indexOfDot = email.lastIndexOf(".");
        if(indexOfAt < 0 || indexOfDot < 0 || indexOfAt > indexOfDot){
            throw new ValidationException("Email must contain @ and .");
        }
//        TODO: Validate password
        if(!password.equals(confirmPassword)){
            throw new ValidationException("Password and confirm password must match!");
        }
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public String getFullName() {
        return fullName;
    }
}
