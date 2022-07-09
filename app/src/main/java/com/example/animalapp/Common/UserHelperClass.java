package com.example.animalapp.Common;

public class UserHelperClass {

    String firstName, lastName, username, email, date,
            phoneNumber, utaID, password, UID, birthday;

    public UserHelperClass(){}

    public UserHelperClass(String firstName, String lastName, String username, String email, String date,
                           String phoneNumber, String utaID, String password, String UID, String birthday) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.date = date;
        this.phoneNumber = phoneNumber;
        this.utaID = utaID;
        this.password = password;
        this.UID = UID;
        this.birthday = birthday;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUtaID() {
        return utaID;
    }

    public void setUtaID(String utaID) {
        this.utaID = utaID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUID() { return UID; }

    public void setUID(String UID) { this.UID = UID; }

    public String getBirthday() { return birthday; }

    public void setBirthday(String birthday) { this.birthday = birthday; }

}
