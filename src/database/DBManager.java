
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBManager 
{
	private static Properties prop = new Properties();
	private static Connection con;
	private static String dbSource = "//localhost:3306/oop-project";
	public static Connection getConnection()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql:" + dbSource;
			prop.load(DBManager.class.getClassLoader().getResourceAsStream("config.properties"));
			con = DriverManager.getConnection(url, prop.getProperty("dbUser"), prop.getProperty("dbPassword"));
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
		catch(Exception e){
			e.printStackTrace();
		}
		return con;
	}
}
