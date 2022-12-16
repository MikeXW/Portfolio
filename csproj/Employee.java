/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package csproj;

/**
 *
 * @author X1
 */
public class Employee {
    private String id;
    private String fname;
    private String lname;
    private String dept;
    private String title;
    private String email;
    private String phone;
    private String pay;
    private String married;
    
    public Employee(String id, String fname,String lname,String dept,String title,String email,String phone,String pay,String married){
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.dept = dept;
        this.title = title;
        this.email = email;
        this.phone = phone;
        this.pay = pay;
        this.married = married;
    }
    public String getId(){
        return id;
    }
    public String getFname(){
        return fname;
    }
    public String getLname(){
        return lname;
    }
    public String getDept(){
        return dept;
    }
    public String getTitle(){
        return title;
    }
    public String getEmail(){
        return email;
    }
    public String getPhone(){
        return phone;
    }
    public String getPay(){
        return pay;
    }
    public String getMarried(){
        return married;
    }
}
