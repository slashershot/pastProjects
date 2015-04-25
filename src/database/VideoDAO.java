package database;

import java.sql.*;
import java.util.ArrayList;

import database.DBManager;

import entity.Video;

//public class VideoDAO {
//	private static Connection currentCon = null;
//	private static ResultSet rs = null;
//	private static PreparedStatement pstmt = null;
//	private static Statement stmt = null;
//	
//	public static ArrayList<Video> getAllVideos(){
//		Video Video;
//		ArrayList<Video> vidList = new ArrayList<Video>();
//		String query = "Select * from video";
//		currentCon = DBManager.getConnection();
//		
//		try{
//			stmt=currentCon.createStatement();
//			rs = stmt.executeQuery(query);
//			while(rs.next()){
//				String name = rs.getString("videoname");
//				String location = rs.getString("videolocation");
//				Video = new Video(name,location);
//				vidList.add(Video);
//			}
//		}catch(Exception e){
//			
//		}
//	return vidList;
//	}
//	
//}

public class VideoDAO {

	private ArrayList<Video> vidList;
	private static Connection currentCon = null;
	private static ResultSet rs = null;
	private static Statement stmt = null;
	private static PreparedStatement pstmt = null;

	public static ArrayList<Video> getAllVideos(){
		Video Video;
		ArrayList<Video> vidList = new ArrayList<Video>();
		String query = "select * from video";

		currentCon = DBManager.getConnection();

		try{
			stmt = currentCon.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()){

				String sourceDirectory = rs.getString("sourceDirectory");
				String fileName = rs.getString("fileName");
				long fileSize = rs.getLong("fileSize");
				String status = rs.getString("status");

				Video = new Video();
				Video.setSourceDirectory(sourceDirectory);
				Video.setFilename(fileName);
				Video.setFileSize(fileSize);
				Video.setStatus(status);

				vidList.add(Video);

			}

		} catch (Exception e){
			e.printStackTrace();

		}
		return vidList;

	}

	public static Video getVideoDetails(String selVName){
		Video selVideo = null;
		String query = "select * from video where fileName = \"" + selVName +"\"";

		currentCon = DBManager.getConnection();
		String fileName = null, sourceDirectory = null, status = null;
		
		


		try{
			stmt = currentCon.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()){


				sourceDirectory = rs.getString("sourceDirectory");
				fileName = rs.getString("fileName");
				long fileSize = rs.getLong("fileSize");
				status = rs.getString("status");
				selVideo = new Video();
				selVideo.setSourceDirectory(sourceDirectory);
				selVideo.setFilename(fileName);
				selVideo.setFileSize(fileSize);
				selVideo.setStatus(status);


			}

		} catch (Exception e){
			e.printStackTrace();

		}


		return selVideo;

	}

	public static boolean create(Video video){
		boolean status = false;
		String query = "insert into video (sourceDirectory, fileName, fileSize, status) value (?,?,?,?)";

		currentCon = DBManager.getConnection();


		try{  
			pstmt = currentCon.prepareStatement(query);
			pstmt.setString(1, video.getSourceDirectory());
			pstmt.setString(2,  video.getFilename());
			pstmt.setLong(3, video.getFileSize());
			pstmt.setString(4, video.getStatus());

			int no = pstmt.executeUpdate();
			if(no==1){
				status = true;
			}

		} catch (Exception e){
			e.printStackTrace();
		}
		return status;
	}

	public static boolean update (Video video){

		boolean status = false;

		String query = "update video set sourceDirectory=?, fileName=?, fileSize=?, status=? where id=?";

		currentCon = DBManager.getConnection();

		try{
			pstmt = currentCon.prepareStatement(query);


			pstmt.setString(1, video.getSourceDirectory());
			pstmt.setString(2,  video.getFilename());
			pstmt.setLong(3, video.getFileSize());
			pstmt.setString(4, video.getStatus());
			
			int no=pstmt.executeUpdate();
			if(no ==1){
				status=true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}

		return status;

	}

	public static boolean delete(Video video){
		boolean status = false;
		String query = "delete from Videos where id=?";

		currentCon = DBManager.getConnection();

		try{  
			pstmt = currentCon.prepareStatement(query);
			pstmt.setString(1, String.valueOf(video.getId()));


			int no = pstmt.executeUpdate();
			if(no==1){
				status = true;
			}


		} catch(Exception e){
			e.printStackTrace();
		}
		return status;
	}

	public static Video findVideoByName(String vName){
		Video video = null;

		String query = "select from videos where fileName = ?;";

		currentCon = DBManager.getConnection();
		try{
			pstmt = currentCon.prepareStatement(query);
			pstmt.setString(1, vName);

			rs = pstmt.executeQuery();

			if (rs.next()){
				String sourceDirectory = rs.getString("sourceDirectory");
				long fileSize = rs.getLong("fileSize");
				String status = rs.getString("status");

				video.setSourceDirectory(sourceDirectory);
				video.setFilename(vName);
				video.setFileSize(fileSize);
				video.setStatus(status);

			} 
		}catch (Exception e){
			e.printStackTrace();

		}
		return video;
	}



}
