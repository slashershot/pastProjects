package Client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;

import entity.User;

public class TryClient {
	private static int Login = 1;
	private static int Signup = 2;
	public static Socket setup(){
		Socket kkSocket = null;
		String hostName = "localhost";
		int portNumber = 22225;
		try{
			kkSocket = new Socket(hostName,portNumber);
		}catch(Exception e){
			
		}
		return kkSocket;
	}
	public static User inUser() throws IOException, ClassNotFoundException{
		User user = null;
		ObjectInputStream inputUser;
		inputUser = new ObjectInputStream(TryClient.setup().getInputStream());
		user =  (User)inputUser.readObject();
		inputUser.close();
		return user;
		}
	public static void outUser(User encryptinUser) throws IOException, ClassNotFoundException
	{
		ObjectOutputStream outputUser = null;
		try{
		outputUser = new ObjectOutputStream(TryClient.setup().getOutputStream());
		}catch(NullPointerException e){
			JOptionPane.showMessageDialog(null, "Server is down");
			System.exit(0);
		}
		outputUser.writeObject(encryptinUser);
		
		outputUser.flush();
		outputUser.close();
	}
	public static void output(int tag) throws IOException{
		DataOutputStream data = new DataOutputStream(TryClient.setup().getOutputStream());
		if(tag == 1){
			data.write(Login);
		}
		if(tag == 2){
			data.write(Signup);
		}
		data.flush();
		data.close();
	}
}
