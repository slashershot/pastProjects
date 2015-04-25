package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class NewServer {
	private static Socket clientSocket;
	private static ServerSocket serverSocket;
	private static ObjectOutputStream outObjects;
	private static ObjectInputStream inObjects;
	private static PrintWriter outStatus;
	private static BufferedReader inStatus;
	public static void setup(){
		int portNumber = 22224;
		try{
				serverSocket = new ServerSocket(portNumber);
				clientSocket = serverSocket.accept();
				//Deals with input Objects
				outObjects = new ObjectOutputStream(clientSocket.getOutputStream());
				//Deals with output Objects
				inObjects = new ObjectInputStream(clientSocket.getInputStream());
				//Deals with status sending
				outStatus = new PrintWriter(clientSocket.getOutputStream(),true);
				//Deals with status recieving
				inStatus = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		}catch(java.net.ConnectException e){
			System.exit(0);
		}catch(IOException e){
			
		}
	}
	public static Socket getClientSocket() {
		return clientSocket;
	}

	public static ServerSocket getServerSocket() {
		return serverSocket;
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
