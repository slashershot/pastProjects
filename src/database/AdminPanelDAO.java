/*
Name: Chew Wei Bin
AdminNo: 134572Q
Class: IS1301
Date Created: 19/12/2013
 */

package database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;



import entity.User;

public class AdminPanelDAO 
{

	private static Connection currentCon = null;
	private static ResultSet rs = null;
	private static PreparedStatement pstmt = null;
	private static Statement stmt = null;

	public static ArrayList<User> getAllContacts()
	{

		User User;
		
		ArrayList<User> adminViewUserList = new ArrayList<User>();
		
		String query = "select * from User";
		
		//connect to DB
		currentCon = DBManager.getConnection();

		try
		{
			stmt = currentCon.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next())
			{
				String username = rs.getString("username");
				String Email = rs.getString("Email");
				String SecretQuestion = rs.getString("SecretQuestion");
				String SecretAnswer = rs.getString("SecretAnswer");
				int AccessLevel = Integer.parseInt(rs.getString("Access Level"));
				User= new User(username,Email,SecretQuestion,SecretAnswer,AccessLevel);
				adminViewUserList.add(User);
			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return adminViewUserList;
	}

	public static User findUserByUsername(String name) 
	{
		User User = null;

		String query = "select * from User where username=?";
		
		//connect to DB
		currentCon = DBManager.getConnection();

		try
		{
			pstmt = currentCon.prepareStatement(query);
			pstmt.setString(1,name);
			rs=pstmt.executeQuery();

			if(rs.next())
			{
				/**String username = rs.getString("Username");
				int accCreated = rs.getInt("accCreated");
				int IPAddress = rs.getInt("IPAddress");
				String accessLvl = rs.getString("accessLvl");
				String lastLogin = rs.getString("lastLogin");
				String email = rs.getString("email");
**/
				//User = new User(username,accCreated,IPAddress,accessLvl,lastLogin,email);

			}

		} 	
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return User;
	}	

	public static User retrieveUsername(String name)
	{
		return findUserByUsername(name);
	}



	public static boolean BanUserAccount(User user)
	{
		boolean status = false;
		
		String query = "delete from User where username=?";
		
		//connect to DB
		currentCon = DBManager.getConnection();

		try
		{
			pstmt = currentCon.prepareStatement(query);
			pstmt.setString(1, user.getUsername());

			int no=pstmt.executeUpdate();
			
			if (no==1)
			{
				status = true;
			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return status;
	}

	public static boolean create(User User)
	{
		boolean status = false;
		
		String query = "insert into User(username,password,Email,SecretQuestion,SecretAnswer,Access Level value(?,?,?,?,?,?)";
		
		//connect to DB
		currentCon = DBManager.getConnection();

		try
		{
			pstmt = currentCon.prepareStatement(query);
			pstmt.setString(1, User.getUsername());
			pstmt.setString(2, User.getPassword());
			pstmt.setString(3, User.getEmail());
			pstmt.setString(4, User.getSecretQuestion());
			pstmt.setString(5, User.getSecretAnswer());
			pstmt.setString(6, Integer.toString(User.getId()));
			pstmt.setString(7, User.getUsername());
			int no=pstmt.executeUpdate();
			if (no==1){
				status = true;
			}
		} catch (Exception e){
			e.printStackTrace();
		}

		return status;
	}


	public static boolean update(User user)
	{
		boolean status = false;
		String query = "update user set password=?,Email=? where username=?";
		
		currentCon = DBManager.getConnection();
		try
		{
			pstmt = currentCon.prepareStatement(query);

			pstmt.setString(1, user.getPassword());
			pstmt.setString(2, user.getEmail());
			pstmt.setString(3, user.getUsername());

			int no = pstmt.executeUpdate();
			
			if(no==1)
				status = true;
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return status;
	}

	public static boolean updateManually(User user)
	{
		boolean status = false;
		
		String query = "update User set Email=?,SecretQuestion=?,SecretAnswer=?,`AccessLevel`=? where username=?;";
		
		currentCon = DBManager.getConnection();
		
		try
		{
			pstmt=currentCon.prepareStatement(query);
			pstmt.setString(1, user.getEmail());
			pstmt.setString(2, user.getSecretQuestion());
			pstmt.setString(3,user.getSecretAnswer());
			pstmt.setString(4,(Integer.toString(user.getId())));
			pstmt.setString(5,user.getUsername());


			int no = pstmt.executeUpdate();
			if(no==1)
				status = true;

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return status;
	}




	public static boolean updateByCWB(String columnToBeUpdated,String valueToBeSet,String whereToFind)
	{
		boolean status = false;
		
		String query  = "update User set "+"`"+columnToBeUpdated+"`"+"=? where username=?";
		
		currentCon = DBManager.getConnection();
		
		try
		{
			pstmt=currentCon.prepareStatement(query);
			pstmt.setString(1, valueToBeSet);
			pstmt.setString(2, whereToFind);
			
			int no=pstmt.executeUpdate();
			
			if(no==1)
				status = true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return status;
	}

	public static boolean delete(String inUser)
	{
		boolean status = false;
		
		String query = "delete from User where username=?";
		
		//connect to DB
		currentCon = DBManager.getConnection();

		try
		{
			pstmt = currentCon.prepareStatement(query);
			pstmt.setString(1, inUser);
			int no=pstmt.executeUpdate();

			if (no==1)
			{
				status = true;
			}
			
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return status;
	}

	public static DefaultTableModel populate_jtable(ResultSet rs) throws SQLException
	{
		ResultSetMetaData metaData = rs.getMetaData();

		Vector<String> columnNames = new Vector<String>();
		
		int columnCount = metaData.getColumnCount();
		
		for (int column = 1; column<=columnCount;column++)
		{
			columnNames.add(metaData.getColumnName(column));
		}
		
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();

		while(rs.next())
		{
			Vector<Object>vector = new Vector<Object>();
			for(int columnIndex = 0; columnIndex<=columnCount;columnIndex++)
			{
				vector.add(rs.getObject(columnIndex));
			}
			data.add(vector);
		}
		return null;
	}

	public static User findUser(User user)
	{
		User Username = null;
		
		try 
		{
			currentCon = DBManager.getConnection();
			
			String query = "select * from User where username=? and password=?";
			
			pstmt = currentCon.prepareStatement(query);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			rs = pstmt.executeQuery();
			
			if (rs.next())
			{
				String username = rs.getString("username");
				String password = rs.getString("password");
				String email = "nigel@hotmail.com";
				Username = new User(username, password,email);
			}


		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return Username;
	}

	public static boolean isValid(User user)
	{
		boolean status = false;

		if(findUser(user)!= null)
		{
			status=true;
		}
		
		return status;
	}

}






