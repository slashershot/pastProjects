/*
Name: Jillian Wee
AdminNo: 133304F
Class: IS1301
Date Created: 19/12/2013
*/
package cryptography;

import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;

import entity.User;

public class Crypto 
{
	//private static String password = "xxxx";
	private static String hash;
	
	private static String algorithm1 = "DES";
	private static String algorithm2 = "AES";
	private static String algorithm3 = "RSA";
	
	private static User user;
	
	public static void main(String [] args) throws Exception
	{
		
		
		KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA"); 
		keyGen.initialize(1024); //key size 
		
		KeyPair keyPair = keyGen.genKeyPair();
		
		//hash = convertToMD5(password);
		//System.out.println(hash);
		//ea416ed0759d46a8de58f63a59077499
	}
	
	public static Cipher cipher(String algorithm) throws NoSuchAlgorithmException, NoSuchPaddingException
	{
		Cipher c =  Cipher.getInstance(algorithm); //type of algorithm to make cipher text
		
		return c;
	}
	
	public static Key symKey() throws NoSuchAlgorithmException
	{
		Key symKey = KeyGenerator.getInstance(algorithm1).generateKey(); //getting type of key of the algorithm
		
		return symKey;
	}
	
	public static byte [] encryptin(String input, Key tKey, Cipher c) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException
	{
		c.init(Cipher.ENCRYPT_MODE, tKey); 
		
		byte inputBytes [] = input.getBytes();
		
		return c.doFinal(inputBytes);//encrypt the plain text into cipher text with the key
	}
	
	public static String decryptin(byte encryptionBytes [], Key pKey, Cipher c) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException
	{
		c.init(Cipher.DECRYPT_MODE, pKey); 
		
		byte decrypt[] = c.doFinal(encryptionBytes);//decrypt the cipher text into plain text
		
		String decrypted = new String(decrypt);
		
		return decrypted;
	}
	
	public static PrivateKey privateKey(KeyPair p2)
	{
		PrivateKey privateKey = p2.getPrivate();
		
		return privateKey;
	}
	
	public static PublicKey publicKey(KeyPair p2)
	{
		PublicKey publicKey = p2.getPublic();
		
		return publicKey;
	}
	
	public static String convertToMD5(String password)
	{
		String digest = null;
		
		try
		{
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte hash[]= md.digest(password.getBytes("UTF-8"));
			
			StringBuilder sb = new StringBuilder(2 * hash.length);
			for(byte b : hash)
			{
				sb.append(String.format("%02x", b&0xff));
			}
			
			digest = sb.toString();
		}
		catch(UnsupportedEncodingException ex)
		{
			Logger.getLogger(StringReader.class.getName()).log(Level.SEVERE,null,ex);
		}
		catch(NoSuchAlgorithmException ex)
		{
			Logger.getLogger(StringReader.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		return digest;
	}
}
