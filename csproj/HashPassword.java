/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package csproj;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

public class HashPassword
{
  public static String hash(String hash_pd1) 
  {
    String hash_pd = hash_pd1;
    String generatedPassword = null;

    try 
    {

      MessageDigest mdi = MessageDigest.getInstance("MD5");

      mdi.update(hash_pd.getBytes());

      byte[] bytes = mdi.digest();

      StringBuilder coverthex = new StringBuilder();
      for (int i = 0; i < bytes.length; i++) {
        coverthex.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
      }

      generatedPassword = coverthex.toString();
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    return generatedPassword;
  }
}
