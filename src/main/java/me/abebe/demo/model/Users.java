package me.abebe.demo.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    public Users() {
        this.roles = new HashSet<>();
    }

    @NotEmpty

    @Column(unique = true)
    private String userName;
    private String lastName;
    @Email
    private String email;
    @CreationTimestamp
    Timestamp ceratedAt;


    @NotEmpty

    private String password;
    private String firstName;

    public Set<Roles> getRoles() {
        return roles;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Roles> roles;
    public void setRoles(Set<Roles> roles) {
        this.roles = roles;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
        this.password = password;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Timestamp getCeratedAt() {
        return ceratedAt;
    }

    public void setCeratedAt(Timestamp ceratedAt) {
        this.ceratedAt = ceratedAt;
    }
public void AddRole(Roles role){
        this.roles.add(role);

}


}
