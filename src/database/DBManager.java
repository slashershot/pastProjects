
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager 
{
	private static Connection con;
	private static String dbSource = "//localhost:3306/oop-project";
	private static String user = "root";
	private static String password = "xxxx";
	
	public static Connection getConnection()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql:" + dbSource;
			con = DriverManager.getConnection(url, user, password);
		}
		catch(SQLException e)
		{
			System.out.println("Connection failed->" + dbSource);
			e.printStackTrace();
		}
		catch(ClassNotFoundException e)
		{
			System.out.println(e);
		}
		return con;
	}
}
