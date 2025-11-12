package com.canteen.model;

public class User {
    private int id;
    private String username;
    private String name;
    public User() {}
    public User(int id, String username, String name){this.id=id;this.username=username;this.name=name;}
    public int getId(){return id;} public String getUsername(){return username;} public String getName(){return name;}
}
