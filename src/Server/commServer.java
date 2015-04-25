package Server;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import entity.Video;

public class commServer implements Runnable{
	serverGUI gui;
	final static int PORT = 3332; //main Server Connection
	static boolean keepRunning = true;
	static ServerSocket serverSocket;
	static Socket cSocket;

	ObjectOutputStream oos;
	ObjectInputStream ois;
	static Thread ftp;

	static String rootFolder = "C:\\Users\\slashmobile\\Desktop\\Final Presentation Stuff\\uploaded\\";

	public commServer(serverGUI serverGUI){
		this.gui = serverGUI;
	}

	@Override
	public void run() {
		try {
			while(keepRunning){
				Runnable ftpServer = new ftpServer();
				ftp = new Thread(ftpServer, "ftp");

				ftp.start();





				serverSocket = new ServerSocket(PORT);  

				//			System.out.println("From Comm: Im online");
				serverGUI.commOnline = true;

				while (keepRunning) {  
					cSocket = serverSocket.accept();
					listen();

				}  
			}
		} catch (Exception e) {  

		}  

	}



	public static void stopThread() throws SocketException {
		keepRunning = false;
		try {
			serverSocket.close();
			cSocket=null;
			serverGUI.ftpOnline = false;
		} catch (IOException e) {
			// TODO Auto-generated catch block

		}

	}


	void listen(){
		
		try{
			oos = new ObjectOutputStream(cSocket.getOutputStream());
			ois = new ObjectInputStream(cSocket.getInputStream());
			while(true){
				
				Video requested = (Video) ois.readObject();
				String theRequest = requested.getStatus();
				
				if(theRequest.startsWith("download")){
					System.out.println("We are in download checker");
					retrieveRequested(requested.getFilename());
					
				}

			}
		}catch(Exception e){}
	}



	void retrieveRequested(String reqFileName){
		
		Video completedRequest = new Video();
		File file = new File(rootFolder+reqFileName);
		
//		System.out.println(completedRequest.getFileData());
		
		if (file.isFile()) {
			try {
				DataInputStream diStream = new DataInputStream(new FileInputStream(file));
				long len = (int) file.length();
				byte[] fileBytes = new byte[(int) len];
				int read = 0;
				int numRead = 0;
				while (read < fileBytes.length && (numRead = diStream.read(fileBytes, read,
						fileBytes.length - read)) >= 0) {
					read = read + numRead;


					System.out.println(len);
				}
				System.out.println(fileBytes);


				completedRequest.setFileData(fileBytes);
				completedRequest.setStatus("Complete");
				send(completedRequest);
			} catch (Exception e) {
				e.printStackTrace();
				completedRequest.setStatus("Error");
			}
		}
		
	}


	synchronized void send(Video toSend){
		try {
			oos.writeObject(toSend);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
