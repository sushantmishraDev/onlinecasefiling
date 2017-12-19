package com.dms.utility;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.web.client.RestTemplate;


public class GlobalFunction {

	public String encryptedCode="shcil@1234";
	/*
	 * Covert to json
	 */
	public String convert_to_json(Object object){
		ObjectMapper mapper = new ObjectMapper();
		String jsonData = null;
		try {			 
			//System.out.println(mapper.writeValueAsString(object));
			jsonData = mapper.writeValueAsString(object);	
		} catch (JsonGenerationException e) {	 
			e.printStackTrace();	 
		} catch (JsonMappingException e) {	 
			e.printStackTrace();	 
		} catch (IOException e) {	 
			e.printStackTrace();	 
		}	 
		return jsonData;		
	}
	
	/*
	 * Generate random Integer
	 */  
	public String RandomInteger(){
		log("Generating Random Number");	
		String random_no = null;
		Random random = new Random();	      
	    // get next long value 
	    long LOWER_RANGE = 10000000000L; //assign lower range value
	    long UPPER_RANGE = 100000000000L; //assign upper range value
	    long value = LOWER_RANGE + (long)(random.nextDouble()*(UPPER_RANGE - LOWER_RANGE));
		log("Generated : " + value+"");
		random_no = value+"";
		return random_no;
	}
	
	public static void log(String aMessage){
	    System.out.println(aMessage);
	}
	/*
	 * Covert String date into Date
	 */
	public Date convertDate(String stringDate,String format){
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		Date date = null;
		try {	 
			date = formatter.parse(stringDate);			 
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(date);	
		return date;
	}
	
	public String getRandomString() {
        String SALTCHARS = "1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 6) {
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();       
        return saltStr;
    }

	public String RandomSmallInteger(){
		log("Generating Random Number");	
		String random_no = null;
		Random random = new Random();	      
	    // get next long value 
	    long LOWER_RANGE = 1000L; //assign lower range value
	    long UPPER_RANGE = 10000L; //assign upper range value
	    long value = LOWER_RANGE + (long)(random.nextDouble()*(UPPER_RANGE - LOWER_RANGE));
		log("Generated : " + value+"");
		random_no = value+"";
		return random_no;
	}
	
	
	public String getamountindigits(String amount)
	{
		String amountindigits="";
		String x=amount;
		if(x.length()<=3)
		{
			amountindigits= x;
		}else
		{
        x=x.toString();
        String lastThree = x.substring(x.length()-3);
        String otherNumbers = x.substring(0,x.length()-3);
        if(otherNumbers != "")
            lastThree = "," + lastThree;
        
        amountindigits = otherNumbers.replaceAll("(?<=\\d)(?=(\\d{2})+$)", ",") + lastThree;
		}
		return amountindigits;
	}

	public Boolean createfolder(String path) {
		// TODO Auto-generated method stub
		Boolean result=false;
		File theDir = new File(path);
		if (!theDir.exists()) {
			try {
				if(theDir.mkdir()){
					result=true;
				}else{
					System.out.println("Error occured while creating folder ="+path);
				}
			} catch (SecurityException se) {
			}
		}else{
			System.out.println("folder exist="+path);
		}
		return result;
	}
	
	public String md5encryption(String password)
	{
		String encryptpwd="";
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());

		    byte byteData[] = md.digest();
		    
		    StringBuffer sb = new StringBuffer();
	        for (int i = 0; i < byteData.length; i++) {
	         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
	        }
	        
	       // System.out.println("Digest(in hex format):: " + sb.toString());
	        
	      //convert the byte to hex format method 2
	        StringBuffer hexString = new StringBuffer();
	    	for (int i=0;i<byteData.length;i++) {
	    		String hex=Integer.toHexString(0xff & byteData[i]);
	   	     	if(hex.length()==1) hexString.append('0');
	   	     	hexString.append(hex);
	    	}
	    	
	    	encryptpwd = hexString.toString();
	    	//System.out.println("Digest(in hex format):: " + hexString.toString());
		    
		    
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		
		return encryptpwd;
	}
	
	public static void encrypt(String key, InputStream is, OutputStream os) throws Throwable {
		encryptOrDecrypt(key, Cipher.ENCRYPT_MODE, is, os);
	}
	
	public static void decrypt(String key, InputStream is, OutputStream os) throws Throwable {
		encryptOrDecrypt(key, Cipher.DECRYPT_MODE, is, os);
	}
	
	public static void encryptOrDecrypt(String key,int mode,InputStream is,OutputStream os) throws Throwable {
		DESKeySpec dks = new DESKeySpec(key.getBytes());
		SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
		SecretKey desKey = skf.generateSecret(dks);
		Cipher cipher = Cipher.getInstance("DES");	//DES/ECB/PKCS5Padding for SunJCE
		
		if(mode==Cipher.ENCRYPT_MODE){
			cipher.init(Cipher.ENCRYPT_MODE, desKey);
			CipherInputStream cis=new CipherInputStream(is, cipher);
			doCopy(cis,os);
		}else if(mode==Cipher.DECRYPT_MODE){
			cipher.init(Cipher.DECRYPT_MODE, desKey);
			CipherOutputStream cos = new CipherOutputStream(os, cipher);
			doCopy(	is, cos);
		}
	}
	
	public static void doCopy(InputStream is,OutputStream os) throws IOException{
		byte[] bytes = new byte[64];
		int numBytes;
		while((numBytes = is.read(bytes)) != -1){
			os.write(bytes,0,numBytes);
		}
		os.flush();
		os.close();
		is.close();
	}
	
	public static String getFolderSize(long bytes, boolean si) {
	    int unit = si ? 1000 : 1024;
	    if (bytes < unit) return bytes + " B";
	    int exp = (int) (Math.log(bytes) / Math.log(unit));
	    String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp-1) + (si ? "" : "i");
	    return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
	}
	
	public Integer generateOTP()
	{
		 String numbers = "123456789"; 
	        // Using random method
	     Random rndm_method = new Random();
	 
	     char[] otpchar = new char[4];
	 
	      for (int i = 0; i < 4; i++)
	      {
	            // Use of charAt() method : to get character value
	            // Use of nextInt() as it is scanning the value as int
	            otpchar[i] =numbers.charAt(rndm_method.nextInt(numbers.length()));
	      }
	      Integer otp=Integer.parseInt(String.copyValueOf(otpchar));
	      return otp;
	}
	
	public String generatePassword()
	{
        String Capital_chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String Small_chars = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "123456789";
        String symbols = "@#$%&";
 
        String values = Capital_chars + Small_chars + numbers + symbols;
 
        // Using random method
        Random rndm_method = new Random();
 
        char[] password = new char[6];
 
        for (int i = 0; i < 6; i++)
        {
            // Use of charAt() method : to get character value
            // Use of nextInt() as it is scanning the value as int
            password[i] =values.charAt(rndm_method.nextInt(values.length()));
        }
        return String.valueOf(password);
	}
	
	public String sendSMS(String url,String mob_no,String smstext)
	{
		String result=null;
	    RestTemplate restTemplate=new RestTemplate();
	    result=restTemplate.getForObject(url+"?validmob="+mob_no+"&validmsg="+smstext, String.class);
	    return result;
	}
}
