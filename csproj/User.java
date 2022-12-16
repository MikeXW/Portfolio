/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package csproj;

/**
 *
 * @author X1
 */
public class User {
    private String id;
    private String username;
    
    public User(String id, String username){
        this.id = id;
        this.username = username;
    }
    public String getId(){
        return id;
    }
    public String getUsername(){
        return username;
    }
}
