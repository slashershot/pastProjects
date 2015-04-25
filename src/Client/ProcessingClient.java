package Client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;

public class ProcessingClient {
	private static Socket kkSocket;
	private static ObjectOutputStream outObjects;
	private static ObjectInputStream inObjects;
	private static PrintWriter outStatus;
	private static BufferedReader inStatus;
	public static void setup(){
		String hostName = "localhost";
		int portNumber = 22225;
		try{
			//Construct socket
			kkSocket = new Socket(hostName,portNumber);
			//Recieve Objects
			outObjects = new ObjectOutputStream(kkSocket.getOutputStream());
			//Sends Objects
			inObjects = new ObjectInputStream(kkSocket.getInputStream());
			//Seperate pipeline to recieve and send instructions
			 outStatus = new PrintWriter(kkSocket.getOutputStream(),true);
			inStatus = new BufferedReader(new InputStreamReader(kkSocket.getInputStream()));
	}catch(ConnectException e){
	}catch(Exception e){
		e.printStackTrace();
	}
	}
	public static Socket getkkSocket() {
		return kkSocket;
	}
	public static ObjectOutputStream getOutObjects() {
		return outObjects;
	}

	public static ObjectInputStream getInObjects() {
		return inObjects;
	}

	public static PrintWriter getOutStatus() {
		return outStatus;
	}

	public static BufferedReader getInStatus() {
		return inStatus;
	}
}
