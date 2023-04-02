package com.project.onlineStore.User;

import jakarta.persistence.*;

@Entity
@Table(name="users")
public class User {

    @Id
    @SequenceGenerator(
            name = "users_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator ="users_sequence",
            strategy = GenerationType.SEQUENCE
    )

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;



    public User() {
    }

    public User(String firstName, String lastName, String password, String email) {
        this.email=email;
        this.firstName=firstName;
        this.lastName = lastName;
        this.password = password;
    }


    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getPassword() {
        return this.password;
    }


    public String getEmail() {
        return this.email;
    }



    public String toString() {
        return "First Name: " + this.getFirstName() + "\t\t" +
                "Last Name: " + this.getLastName() + "\t\t" +
                "Email: " + this.getEmail();
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public void setPassword(String password) {
        if(password == null)
            return;
        this.password = password;
    }


    public void setEmail(String newEmail) {
        if(newEmail == null)
            return;
        this.email = newEmail;
    }


    public Long getId() {
        return id;
    }

}
