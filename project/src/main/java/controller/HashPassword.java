package controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashPassword {
	public static String hash(String hasher) {
		String hash = hasher;
		String generatedPassword = null;
		try {
			MessageDigest mdi = MessageDigest.getInstance("MD5");
			mdi.update(hasher.getBytes());
			byte[] bytes =mdi.digest();
			StringBuilder coverthex = new StringBuilder();
			for(int i = 0; i < bytes.length; i++) {
				coverthex.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
				generatedPassword = coverthex.toString();
			}
		}catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return generatedPassword;
	}
}
