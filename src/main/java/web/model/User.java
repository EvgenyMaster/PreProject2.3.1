package web.model;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "users_jm", schema = "crud_spring_jm")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "pass")
    private String pass;

    @Column(name = "email", unique = true)
    private String email;

    public User() {
    }

    public User(String name, String pass) {
        this.name = name;
        this.pass = pass;
    }

    public User(String name, String pass, String email) {
        this(name, pass);
        this.email = email;
    }

    public User(long id, String name, String pass, String email) {
        this(name, pass, email);
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                " id= " + id +
                " name= " + name +
                " pass= " + pass +
                " email=" + email +
                '}';
    }
}