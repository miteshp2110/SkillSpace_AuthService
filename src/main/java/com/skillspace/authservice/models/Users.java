package com.skillspace.authservice.models;


import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class Users {
    @Id
    private long id;
    private String email;
    private String password;
    private String role;
    @Column(insertable = false,updatable = true)
    private boolean emailStatus;
    @Column(insertable = false,updatable = true)
    private boolean profileStatus;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isEmailStatus() {
        return emailStatus;
    }

    public void setEmailStatus(boolean emailStatus) {
        this.emailStatus = emailStatus;
    }

    public boolean isProfileStatus() {
        return profileStatus;
    }

    public void setProfileStatus(boolean profileStatus) {
        this.profileStatus = profileStatus;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", emailStatus=" + emailStatus +
                ", profileStatus=" + profileStatus +
                '}';
    }
}
