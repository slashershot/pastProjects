package Client;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;

import entity.Video;

public class Client {
	private static ArrayList<Video> vidList = new ArrayList<Video>();
	private static String hostName = "localhost";
	private static int portNumber = 22224;
	private static Socket kkSocket = setup();
	public static Socket setup(){
		Socket kkSocket=null;
		try{
			kkSocket = new Socket(hostName,portNumber);
		}catch(Exception e){
			
		}
		return kkSocket;
	}
	public static ArrayList<Video> getVideos() throws IOException{
		
		try
				
		{
			System.out.println(kkSocket);
			ObjectInputStream input = new ObjectInputStream(kkSocket.getInputStream());
			DataInputStream inputSize = new DataInputStream(kkSocket.getInputStream());
			int size = inputSize.readInt();
			for(int i=0; size>i; i++){
			Video video = (Video)input.readObject();
			vidList.add(video);
			}
		}catch(Exception e){
			
		}
			

	return vidList;
	}
	public static Video findVideoByName(String name){
		Video video = null;
		for(int i=0; vidList.size()>i;i++){
		if(name!=null && vidList.get(i).getName().equals(name)){
			video = vidList.get(i);
			break;
		}
		}
		return video;
	}
	
	public static void returnName(String name){
		
		try{
		    BufferedWriter d = new BufferedWriter(new OutputStreamWriter(kkSocket.getOutputStream()));
		    System.out.println(name);
			d.write(name);
			d.newLine();
			d.flush();
		}catch(Exception e){
			
		}
	}
}