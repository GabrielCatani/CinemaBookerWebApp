package cinema.models;

import java.util.ArrayList;
import java.util.List;

public class User {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String password;

    private List<UserLoggingInfo> nbrOfLogings;

    public User() {
        this.nbrOfLogings = new ArrayList<>();
    }

    public User(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<UserLoggingInfo> getNbrOfLogings() {
        return nbrOfLogings;
    }

    public void setNbrOfLogings(List<UserLoggingInfo> nbrOfLoggings) {
        this.nbrOfLogings = nbrOfLoggings;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                ", nbrOfLogings='" + nbrOfLogings.size() + '\'' +
                '}';
    }


}
