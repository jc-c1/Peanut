package model.user;

import java.time.LocalDateTime;

// represents User interface, inherited by Parent and Peanut
// Each user has a first name, last name, and date of birth
public abstract class User {
    protected String firstName;
    protected String lastName;
    protected LocalDateTime dateOfBirth;

    // REQUIRES: Non-null/non-empty fn and ln
    // EFFECTS: Constructs a User abstract class when called by subclasses with firstName of fn and lastName of ln
    public User(String fn, String ln) {
        this.firstName = fn;
        this.lastName = ln;
    }

    // REQUIRES: dob is earth time
    // MODIFIES: this
    // EFFECTS: adds dateOfBirth
    public void addDateOfBirth(LocalDateTime dob) {
        this.dateOfBirth = dob;
    }

    // REQUIRES: non-empty fn
    // MODIFIES: this
    // EFFECTS: updates firstName
    public void updateFirstName(String fn) {
        this.firstName = fn;
    }

    // REQUIRES: non-empty ln
    // MODIFIES: this
    // EFFECTS: updates lastName
    public void updateLastName(String ln) {
        this.lastName = ln;
    }

    // EFFECTS: return firstName
    public String getFirstName() {
        return this.firstName;
    }

    // EFFECTS: return lastName
    public String getLastName() {
        return this.lastName;
    }

    // EFFECTS: return dateOfBirth
    public LocalDateTime getDateOfBirth() {
        return this.dateOfBirth;
    }

}
