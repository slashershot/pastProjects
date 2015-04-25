package Client;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import entity.Video;
import ui.clientDownloader;
import ui.clientUploader;


public class clientWorker extends Thread{
	static ObjectInputStream ois;
	static ObjectOutputStream oos;
	static FileInputStream fis;
	static PrintWriter out;

	String filePath = null;
	String fileName = null;
	clientUploader clientUploader = null;
	clientDownloader client = null;
	String instructions;
	String destinationDirectory;
	File retrievedFile;

	Socket dSocket;
	Socket uSocket;
	
	public clientWorker(String filepath, String filename, clientUploader clientUploader, String instructions){
		this.filePath = filepath;
		this.fileName = filename;
		this.clientUploader = clientUploader;
		this.instructions = instructions;
	}
	
	public clientWorker(String fileName, String destinationDirectory, clientDownloader clientDownloader, String instructions){
		this.fileName = fileName;
		this.client = clientDownloader;
		this.instructions = instructions;
		this.destinationDirectory = destinationDirectory;
	}


	@Override
	public void run() {
		listen();
	}

	public void listen(){
		
		try{
			while(true){
				if(instructions.startsWith("download")){
					download();
					break;
				}else if(instructions.startsWith("upload")){
					upload();
					break;
				}

			}
		}catch(Exception e){}
	}
	
	synchronized void download(){
		Video toBeRetrieved = new Video();
		toBeRetrieved.setFilename(fileName);
		toBeRetrieved.setStatus("download");
//		System.out.println(toBeRetrieved.getFilename());
		
		try {
			dSocket = new Socket("localhost", 1111);
			oos = new ObjectOutputStream(dSocket.getOutputStream());
			oos.writeObject(toBeRetrieved);
			
			try{
				ois = new ObjectInputStream(dSocket.getInputStream());
				
				Video retrieved = (Video) ois.readObject();
				FileOutputStream fos;
				
				retrieved.setStatus("success");
				if (retrieved.getStatus().equalsIgnoreCase("Error")) {
					System.out.println("Error occurred ..So exiting");
					System.exit(0);
				}
				String outputFile = destinationDirectory + retrieved.getFilename();
				if (!new File(destinationDirectory).exists()) {
					new File(destinationDirectory).mkdirs();
				}
				retrievedFile = new File(outputFile);
				fos = new FileOutputStream(retrievedFile);
				fos.write(retrieved.getFileData());
				fos.flush();
				fos.close();
				System.out.println("Output file : " + outputFile + " is successfully saved ");
				client.lblStatus.setText("DONE!");
				
			}catch(Exception e){}
		} catch (IOException e) {
		
			e.printStackTrace();
		}
		
		
	}
	
	synchronized void upload(){
//		System.out.println("Hello!");
//		System.out.println(filePath +"\n"+fileName);
		Video toUpload = new Video();

		toUpload.setFilename(fileName);
		toUpload.setSourceDirectory(filePath);
		
		
		File file = new File(filePath);

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
				toUpload.setFileSize(len);
				clientUploader.pbStatus.setValue(95);
				toUpload.setFileData(fileBytes);
				toUpload.setStatus("upload");
				diStream.close();
			} catch (Exception e) {
				e.printStackTrace();
				toUpload.setStatus("Error");
			}
		} else {
			System.out.println("path specified is not pointing to a file");
			toUpload.setStatus("Error");
		}
		
		
		try{
			uSocket = new Socket("localhost", 1111);
			oos = new ObjectOutputStream(uSocket.getOutputStream()); 
			clientUploader.pbStatus.setValue(100);
			
			oos.writeObject(toUpload);
			
		}catch(IOException e){e.printStackTrace();}



	}
}
