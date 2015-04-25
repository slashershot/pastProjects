/*
Name: Jillian Wee & Hong Ting
AdminNo: 133304F & 130250D
Class: IS1301
Date Created: 19/12/2013
 */

package entity;

public class User implements java.io.Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private  String username;
	private  String password;
	
	private String confirmPassword;
	private String email;
	private String secretQuestion;
	private String secretAnswer;
	private int accessLevel = 1;
	
	public User()
	{
		super();
	}
	
	public User(String username,String password)
	{
		this.username = username;
		this.password = password;
	}
	
	public User(String username, String password,String Email)
	{
		this.username = username;
		this.password = password;
		email = Email;
	}
	
	public User(int accessLevel)
	{
		this.accessLevel = accessLevel;
	}
	
	public User(String username, String password, String email, String secretQuestion, String secretAnswer)
	{
		this.username = username;
		this.password = password;
		this.email = email;
		this.secretQuestion = secretQuestion;
		this.secretAnswer = secretAnswer;
	}
	
	public User(String username, String password,String email, String secretQuestion, String secretAnswer,int accessLevel)
	{
		this.username = username;
		this.email = email;
		this.secretQuestion = secretQuestion;
		this.secretAnswer = secretAnswer;
		this.accessLevel = accessLevel;
		this.password = password;
	}
	public User(String username,String email, String secretQuestion, String secretAnswer,int accessLevel)
	{
		this.username = username;
		this.email = email;
		this.secretQuestion = secretQuestion;
		this.secretAnswer = secretAnswer;
		this.accessLevel = accessLevel;
	}
	
	public  void setUsername(String username) 
	{
		this.username = username;
	}

	public String getUsername() 
	{
		return username;
	}

	public  void setPassword(String password) 
	{
		this.password = password;
	}

	public String getPassword() 
	{
		return password;
	}

	public void setConfirmPassword(String confirmPassword) 
	{
		this.confirmPassword = confirmPassword;
	}
	
	public String getConfirmPassword() 
	{
		return confirmPassword;
	}

	public void setEmail(String email) 
	{
		this.email = email;
	}
	
	public String getEmail() 
	{
		return email;
	}

	public void setSecretQuestion(String secretQuestion) 
	{
		this.secretQuestion = secretQuestion;
	}

	public String getSecretQuestion() 
	{
		return secretQuestion;
	}


	public void setSecretAnswer(String secretAnswer) 
	{
		this.secretAnswer = secretAnswer;
	}
	
	public String getSecretAnswer() 
	{
		return secretAnswer;
	}
	
	public void setId(int accessLevel)
	{
		this.accessLevel = accessLevel;
	}
	
	public int getId()
	{
		return accessLevel;
	}
	
	
	
	
	
	
}
