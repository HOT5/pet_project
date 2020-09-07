package com.epam.rd.models.buiders;

import com.epam.rd.models.User;

public class UserBuilder {
    public int id;
    public String firstName;
    public String lastName;
    public String email;
    public String login;
    public String password;
    public User.Role role;

    public UserBuilder() {
    }

    public UserBuilder id(int id) {
        this.id = id;
        return this;
    }

    public UserBuilder firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserBuilder lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserBuilder email(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder login(String login) {
        this.login = login;
        return this;
    }

    public UserBuilder password(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder role(User.Role role) {
        this.role = role;
        return this;
    }


    public User createUser() {
        return new User(this);
    }
}
