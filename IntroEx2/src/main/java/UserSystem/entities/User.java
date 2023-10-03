package UserSystem.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import org.hibernate.annotations.AnyDiscriminator;
import org.hibernate.annotations.BatchSize;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String userName;
    private String password;

    @Email()
    private String email;
    @Column(name = "registered_on")
    private LocalDateTime registeredOn;
    @Column(name = "last_time_logged_in")
    private LocalDateTime lastTimeLoggedIn;
    private int age;
    @Column(name = "is_deleted")
    private boolean isDeleted;

    public User() {}

    public User(String userName, String password, String email, LocalDateTime registeredOn, LocalDateTime lastTimeLoggedIn, int age, boolean isDeleted) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.registeredOn = registeredOn;
        this.lastTimeLoggedIn = lastTimeLoggedIn;
        this.age = age;
        this.isDeleted = isDeleted;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if(password.length() < 6 || password.length() > 50){
            throw new IllegalArgumentException("Invalid length of password!");
        }
        boolean hasDigit = false;
        boolean hasLowerCase = false;
        boolean hasUpperCase = false;
        boolean hasSpecialSymbol = false;
        for (int i = 0; i < password.length(); i++) {
            char currChar = password.charAt(i);
            if(Character.isDigit(currChar)){
                hasDigit = true;
            }
            if(Character.isLowerCase(currChar)){
                hasLowerCase = true;
            }
            if(Character.isUpperCase(currChar)){
                hasUpperCase = true;
            }
            List<Character> characters =
                    List.of('!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '_', '+', '<', '>', '?');
            hasSpecialSymbol = characters.stream()
                    .noneMatch(c -> c.equals(currChar));
        }
        if(!hasSpecialSymbol || !hasUpperCase || !hasLowerCase || !hasDigit){
            throw new IllegalArgumentException("Invalid password!");
        }
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        String regex = "\\b(?<=\\s)^([a-zA-Z\\d]+[.|\\-|_][a-zA-Z\\d]+)@([a-zA-Z\\d]+([\\-\\.][a-zA-Z\\d]+)+)\\b";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        while(!matcher.find()) {
            throw new IllegalArgumentException("Invalid email!");
        }
        this.email = email;
    }

    public LocalDateTime getRegisteredOn() {
        return registeredOn;
    }

    public void setRegisteredOn(LocalDateTime registeredOn) {
        this.registeredOn = registeredOn;
    }

    public LocalDateTime getLastTimeLoggedIn() {
        return lastTimeLoggedIn;
    }

    public void setLastTimeLoggedIn(LocalDateTime lastTimeLoggedIn) {
        this.lastTimeLoggedIn = lastTimeLoggedIn;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if(age < 1 || age > 120){
            throw new IllegalArgumentException("Invalid age!");
        }
        this.age = age;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
