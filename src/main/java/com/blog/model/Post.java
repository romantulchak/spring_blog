package com.blog.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    private String text;
    private LocalDate date;
    private String fileName;
    public long getId(){
        return this.id;
    }
    public String getName(){
        return this.name;
    }
    public String getText(){
        return this.text;
    }
    public String getFileName(){
        return this.fileName;
    }
    public void setFileName(String name){
        this.fileName = name;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setText(String text){
        this.text = text;
    }

    @ManyToOne
    @JoinColumn(name ="user_id", nullable = false)
    private User user;


    public LocalDate getDate(){
        return this.date;
    }
    public void setDate(LocalDate date){
        this.date = date;
    }
    public Post(){

    }
    public Post(String name, String text, String fileName, User user){
        this.name = name;
        this.text = text;
        this.date = LocalDate.now();
        this.fileName = fileName;
        this.user = user;

    }
    public void setUser(User user){
        this.user = user;
    }
    public User getUser(){
        return this.user;
    }
}
