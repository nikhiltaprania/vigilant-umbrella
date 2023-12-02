package com.tripsage.model;

import java.io.Serializable;

public class Administrator implements Serializable {
    private final String fullName;
    private final String email;
    private final String password;
    private final Role role;
    private boolean isLoggedIn;

    public Administrator() {
        this.fullName = "Nikhil Kumar";
        this.email = "admin@gmail.com";
        this.password = "admin";
        this.role = Role.ADMINISTRATOR;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }
}
