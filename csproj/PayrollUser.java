/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package csproj;

/**
 *
 * @author X1
 */
public class PayrollUser {
    private int id;
    private float hours;
    private float pay;
    public PayrollUser(int id, float pay, float hours){
        this.id = id;
        this.hours = hours;
        this.pay = pay;
    }
    public int getId(){
        return this.id;
    }
    public float getHours(){
        return this.hours;
    }
    public float getPay(){
        return this.pay;
    }
}
