/*
Name: Jillian Wee & Hong Ting
AdminNo: 133304F & 130205D
Class: IS1301
Date Created: 19/12/2013
 */

package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import entity.User;

public class UserDAO 
{
	private static Connection currentCon = null;
	private static ResultSet rs = null;
	private static PreparedStatement pstmt = null;

	private static Statement stmt = null;

	public static boolean isValid(User user)
	{
		boolean status = false;

		if(findUser(user) != null)
		{
			status = true;
		}
		return status;
	}

	public static User findUser(User user)
	{
		User targetedUser = null;

		try
		{
			currentCon = DBManager.getConnection();

			String query = "select * from user where username=? and password=?";

			pstmt = currentCon.prepareStatement(query);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());

			rs = pstmt.executeQuery();

			if(rs.next())
			{
				String username = rs.getString("username");
				String password = rs.getString("password");
				targetedUser = new User(username, password);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return targetedUser;
	}

	public static ArrayList<User> getAllContacts()
	{
		
		User users;
		ArrayList<User> adminViewUserList = new ArrayList<User>();
		String query = "select * from user";
		//connect to DB
		currentCon = DBManager.getConnection();
		
		try
		{
			stmt = currentCon.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()){
				String username = rs.getString("username");
				String Email = rs.getString("Email");
				String SecretQuestion = rs.getString("SecretQuestion");
				String SecretAnswer = rs.getString("SecretAnswer");
				int AccessLevel = Integer.parseInt(rs.getString("AccessLevel"));
				users = new User(username,Email,SecretQuestion,SecretAnswer,AccessLevel);
				adminViewUserList.add(users);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return adminViewUserList;
	}

	public static boolean create(User user)
	{
		boolean status = false;

		String query = "insert into user(username, password, email, secretQuestion, secretAnswer,AccessLevel)value(?,?,?,?,?,?)";

		currentCon = DBManager.getConnection();

		try
		{
			pstmt = currentCon.prepareStatement(query);

			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getEmail());
			pstmt.setString(4, user.getSecretQuestion());
			pstmt.setString(5, user.getSecretAnswer());
			pstmt.setString(6, Integer.toString(user.getId()));

			int no = pstmt.executeUpdate();

			if(no == 1)
			{
				status = true;
			}

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		return status;
	}

	public static User findUsersByUsername(String name)
	{
		User user = null;

		String query = "select * from user where username=?";

		currentCon = DBManager.getConnection();

		try
		{
			pstmt = currentCon.prepareStatement(query);

			pstmt.setString(1, name);

			rs = pstmt.executeQuery();

			if(rs.next())
			{
				String username = rs.getString("username");
				String password = rs.getString("password");
				String email = rs.getString("email");
				String secretQuestion = rs.getString("secretQuestion");
				String secretAnswer = rs.getString("secretAnswer");
				int AccessLevel = rs.getInt("AccessLevel");

				user = new User(username,password,email, secretQuestion, secretAnswer, AccessLevel);

			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		return user;
	}

	public static User retrieve(String name)
	{
		return findUsersByUsername(name);
	}

	public static boolean update(User user)
	{
		boolean status = false;

		String query = "update user set password=?, confirmPassword=?, email=?, secretQuestion=?, secretAnswer=?,AccessLevel=? where username=? ";

		currentCon = DBManager.getConnection();

		try
		{
			pstmt = currentCon.prepareStatement(query);

			pstmt.setString(1, user.getPassword());
			pstmt.setString(2, user.getEmail());
			pstmt.setString(3, user.getSecretQuestion());
			pstmt.setString(4, user.getSecretAnswer());
			pstmt.setString(5, user.getUsername());
			pstmt.setString(6, Integer.toString(user.getId()));

			int no = pstmt.executeUpdate();

			if(no == 1)
			{
				status = true;
			}

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		return status;
	}

	public static boolean delete(User user)
	{
		boolean status = false;

		String query = "delete from user where name=?";

		currentCon = DBManager.getConnection();

		try
		{
			pstmt = currentCon.prepareStatement(query);

			pstmt.setString(1, user.getUsername());

			int no = pstmt.executeUpdate();

			if(no == 1)
			{
				status = true;
			}
		}	
		catch(Exception e)
		{
			e.printStackTrace();
		}

		return status;
	}
}
