package Server;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import database.VideoDAO;
import entity.Video;

public class ftpServer implements Runnable {  
	public static final int PORT = 1111;  
	public static final int BUFFER_SIZE = 100; 
	public static int count = 0;
//	Video toWrite;
	File toWriteFile;
	static boolean keepRunning = true;
	static ServerSocket serverSocket;
	Socket s;
	String fileName;
	String filePath;

	static Thread listen;

	ObjectInputStream ois;
	ObjectOutputStream oos;
	Video sample;
	String serverRoot = "C:\\Users\\slashmobile\\Desktop\\Final Presentation Stuff\\uploaded\\";

	@Override  
	public void run() { 



		try {  
			while(keepRunning){
				serverSocket = new ServerSocket(PORT);  
				//			System.out.println("From FTP: Im online");
				serverGUI.ftpOnline = true;

				Runnable listener = new onlineCheckerListener();
				listen = new Thread(listener);
				listen.start();

				while (keepRunning) {  
					s = serverSocket.accept();  
					listen();
				}

			}
		} catch (Exception e) {  

		} 

	}  
	public void listen(){
		try{
			while(true){
				ois = new ObjectInputStream(s.getInputStream());

				sample = (Video) ois.readObject();
				String instructions = sample.getStatus();

				if(instructions.startsWith("download")){
					upload(s);
					break;
				}else if(instructions.startsWith("upload")){
					download();
					break;
				}else{System.out.println(sample.getStatus()+ " : not understood");}
			}
		}catch(Exception e){}

	}

	synchronized void download() throws Exception {  //Client uploading

		System.out.println("in DOwnload Servr");
		FileOutputStream fos;
		
//		toWrite =(Video) ois.readObject();
		if (sample.getStatus().equalsIgnoreCase("Error")) {
			System.out.println("Error occurred ..So exiting");
			System.exit(0);
		}
		String outputFile = serverRoot +sample.getFilename();
		System.out.println(outputFile);
		if (!new File(serverRoot).exists()) {
			new File(serverRoot).mkdirs();
		}
		
		toWriteFile = new File(outputFile);
		System.out.println(sample.getFileData());
		sample.setSourceDirectory(serverRoot+sample.getFilename());
		sample.setStatus("success");
		fos = new FileOutputStream(toWriteFile);
		fos.write(sample.getFileData());
		fos.flush();
		fos.close();
		System.out.println("Output file : " + outputFile + " is successfully saved ");

		saveToDatabase(sample);

	}  

	synchronized void upload(Socket socket){
		this.fileName = sample.getFilename();
		
		System.out.println(serverRoot+fileName);

		File file = new File(serverRoot+fileName);

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
					

//					System.out.println(len);
				}
				sample.setFileSize(len);
				sample.setFileData(fileBytes);
				sample.setStatus("success");
				oos = new ObjectOutputStream(s.getOutputStream());
				oos.writeObject(sample);
			} catch (Exception e) {
				e.printStackTrace();
				sample.setStatus("Error");
			}
		} else {
			System.out.println("path specified is not pointing to a file");
			sample.setStatus("Error");
		}
	}

	public static void throwException(String message) throws Exception {  
		throw new Exception(message);  
	}  

	void saveToDatabase(Video toWrite){
		Video toSave = new Video();
		toSave.setSourceDirectory(toWrite.getSourceDirectory());
		toSave.setFilename(toWrite.getFilename());
		toSave.setFileSize(toWrite.getFileSize());
		toSave.setStatus(toWrite.getStatus());

		VideoDAO.create(toSave);
	}

	public static void stopThread() {
		keepRunning = false;
		try {
			serverSocket.close();
			serverGUI.commOnline = false;
		} catch (IOException e) {

		}
	}
}    