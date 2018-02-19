package me.abebe.demo.model;

import me.abebe.demo.model.Users;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotEmpty
    @Column(unique = true)
    private String roleName;
    @ManyToMany(mappedBy = "roles")
    Set<Users> users;
    @CreationTimestamp
    Timestamp ceratedAt;
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Set<Users> getUsers() {
        return users;
    }

    public void setUsers(Set<Users> users) {
        this.users = users;
    }

    public Timestamp getCeratedAt() {
        return ceratedAt;
    }

    public void setCeratedAt(Timestamp ceratedAt) {
        this.ceratedAt = ceratedAt;
    }



    public Roles() {
        this.users = new HashSet<>();

    }
    @Override
    public String toString(){
        return "Roles{"+"roleName='" + roleName +'\'' +'}';
    }
}
