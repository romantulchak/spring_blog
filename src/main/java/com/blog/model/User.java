package com.blog.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "usr")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;

    private String password;

    private int age;
    private boolean active;


    @OneToMany(mappedBy = "user")
    private List<Post> posts;





    @Email
    private String email;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name="user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    private String getEmail(){
        return this.email;
    }


    public void setEmail(String email){
        this.email = email;
    }
    public void setPosts(List<Post>posts){
        this.posts = posts;
    }

    public List<Post> getPosts(){
        return this.posts;
    }
}
