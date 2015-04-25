package Server;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import cryptography.Crypto;
import database.AdminPanelDAO;
import database.DBManager;
import database.UserDAO;
import database.VideoDAO;
import entity.User;
import entity.Video;

public class ServerUi extends JFrame implements Runnable {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	String filepath = null;
	int counter = 0;
	ArrayList<Video> vidArr;
	private static final String EMAIL_PATTERN = 
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
					+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	//private static IMediaListener mediaListener = new MediaListenerAdapter() {

	/**public void onVideoPicture(IVideoPictureEvent event) {
	         try {
	            BufferedImage bi = event.getImage();
	            if (bi != null){
	            	testVideo.setIcon(new ImageIcon(bi));
	            }
	        }catch(Exception ex){
	            ex.printStackTrace();
	        }
	    }
	};**/
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) throws java.net.SocketException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServerUi frame = new ServerUi();
					frame.setVisible(true);
				}catch(java.net.SocketException e){System.exit(0);} catch (Exception e) {
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	@Override
	public void run(){
		serverGUI.procOnline = true;
		NewServer.setup();
		ProcessingServer.setup();
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		Object inputLine;
		try {
			try {
				while((inputLine=NewServer.getInObjects().readObject())!=null){
					if(inputLine.equals("LoginFrame")){
						User user = (User)(ProcessingServer.getInObjects().readObject());
						if(UserDAO.isValid(user)){
							User returnUser = UserDAO.findUsersByUsername(user.getUsername());
							ProcessingServer.getOutObjects().writeObject(returnUser);
						}else{
							ProcessingServer.getOutObjects().writeObject("Invalid Login!");
						}
					}
					if(inputLine.equals("SignUpFrame")){
						User checkUser = (User)ProcessingServer.getInObjects().readObject();
						User user = UserDAO.retrieve(checkUser.getUsername());
						if (user== null){
							UserDAO.create(checkUser);
							ProcessingServer.getOutObjects().writeObject("Created!");
						}
					}
					if(inputLine.equals("UsersList")){
						try {
							ProcessingServer.getOutObjects().writeObject(buildTableModel(populatejTable()));
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					if(inputLine.equals("UserProfileFrame")){
						//username,email,currentpw,newpw,cfmpw
						String[] validationChecking = (String[])ProcessingServer.getInObjects().readObject();
						User user = (User)ProcessingServer.getInObjects().readObject();
						String password =  passwordValidation(validationChecking,user);
						String email = emailValidation(validationChecking,user);
						String username = validationChecking[0];
						User updatedUser;
						if(email != null && password != null)
						{
							updatedUser = new User(username, password, email);
							AdminPanelDAO.update(updatedUser);
							ProcessingServer.getOutObjects().writeObject("All updated");
							//JOptionPane.showMessageDialog(null, "All updated!");
							user.setEmail(updatedUser.getEmail());
							user.setPassword(updatedUser.getPassword());
						}else if(email != null)
						{
							updatedUser = new User(username,user.getPassword(), email);
							AdminPanelDAO.update(updatedUser);
							user.setEmail(updatedUser.getEmail());
							//txtEmail.setText(user.getEmail());
							ProcessingServer.getOutObjects().writeObject("Email Saved");
							//JOptionPane.showMessageDialog(null, "Email Saved");
						}		else if(password != null)
						{
							updatedUser = new User(username,password,user.getEmail());
							AdminPanelDAO.update(updatedUser);
							user.setPassword(updatedUser.getPassword());
							//txtEmail.setText(user.getEmail());
							//JOptionPane.showMessageDialog(null, "New Password Saved");
							ProcessingServer.getOutObjects().writeObject("New Password Saved");
						}		else
							//JOptionPane.showMessageDialog(null, "There is an error in one or more textfields!");
							ProcessingServer.getOutObjects().writeObject("There is an error in one or more textfields!");
					}
					if(inputLine.equals("VideoSelectFrame")){
						vidArr = VideoDAO.getAllVideos();
						ProcessingServer.getOutObjects().writeObject(vidArr);
					}
					if(inputLine.equals("PlayVideo")){
						String name =  (String)ProcessingServer.getInObjects().readObject();
						String media = null;
						for(int i=0; vidArr.size()>i;i++){
							if(vidArr.get(i).getFilename().equals(name)){
								media = vidArr.get(i).getSourceDirectory();
								break;
							}
						}
						if(counter!=0){
							
					}else{
						videoStreaming.run(media);
						counter++;
					}
				}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch(Exception e){
				System.exit(0);
			}

		} catch(Exception e){
			System.exit(0);
		}
	}
	public ServerUi() throws IOException,java.net.SocketException {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
	}
	public ResultSet populatejTable(){
		ResultSet rs = null;
		try{
			java.sql.Connection currentCon = (java.sql.Connection) DBManager.getConnection();
			System.out.println("Connection to Database Success!");
			String query = "select username,Email,SecretQuestion,SecretAnswer,AccessLevel from user";
			PreparedStatement pstmt = (PreparedStatement) currentCon.prepareStatement(query);
			rs = pstmt.executeQuery();
		}
		catch(SQLException ex){
			System.out.println("Error encountered!");
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		return rs;
	}
	public static DefaultTableModel buildTableModel(ResultSet rs)
			throws SQLException {

		ResultSetMetaData metaData = (ResultSetMetaData) rs.getMetaData();  
		// names of columns
		Vector<String> columnNames = new Vector<String>();
		int columnCount = metaData.getColumnCount();
		for (int column = 1; column <= columnCount; column++) {
			columnNames.add(metaData.getColumnName(column));
		}    
		// data of the table
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();

		while (rs.next()) {
			Vector<Object> vector = new Vector<Object>();

			for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
				vector.add(rs.getObject(columnIndex));

			}
			data.add(vector);
		}
		return new DefaultTableModel(data, columnNames);  

	}
	public String passwordValidation(String[] validate,User user)
	{
		String password = null;
		String hash1;
		String hash2;
		//username,email,currentpw,newpw,cfmpw
		String hash = Crypto.convertToMD5(validate[2]);

		if(hash.equals((UserDAO.retrieve(user.getUsername())).getPassword()))
		{			
			if(hash.equals((UserDAO.retrieve(user.getUsername())).getPassword()))
			{				
				hash1 = Crypto.convertToMD5(validate[3]);
				hash2 = Crypto.convertToMD5(validate[4]);

				System.out.println(hash1);
				System.out.println(hash2);

				if(validate[3].length()>=6)
				{
					System.err.println(validate[3].length());

					if(hash1.equals(hash2))
					{
						password = (Crypto.convertToMD5(validate[3]));
						System.out.println("Password Inside here");
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Your new password and confirm password do not match!", "Password Warning", JOptionPane.WARNING_MESSAGE);
						//txtNewPassword.setText("");
						//txtConfirmPassword1.setText("");
					}

				}
				else
					JOptionPane.showMessageDialog(null, "The password must be 8 or more letters");

				System.err.println(validate[3].length());

			}			

		}

		return password;
	}
	public String emailValidation(String[] validate,User user)
	{
		String email = null;
		if(!(validate[1].equals((UserDAO.retrieve(user.getUsername())).getEmail())))
		{			
			if(validate[1].matches(EMAIL_PATTERN))
			{
				email = validate[1];
				System.out.println("Email successfully change");

			}
			else
				JOptionPane.showMessageDialog(null, "Please enter a valid email", "Invalid Email", JOptionPane.WARNING_MESSAGE);

		}

		return email;
	}
	/**public class videoStream extends Thread{
		public videoStream(){}
		public void run(){
			String media = "E:/English Shows + Derren Brown/the mentalist/The mentalist season 6/The.Mentalist.S06E01.HDTV.x264-LOL.mp4";
			String options = formatRtspStream("localhost",8554,"test");
			System.out.println("Streaming '" + media + "' to '" + options + "'");

	        MediaPlayerFactory mediaPlayerFactory = new MediaPlayerFactory();
	        HeadlessMediaPlayer mediaPlayer = mediaPlayerFactory.newHeadlessMediaPlayer();
	        mediaPlayer.playMedia(media,
	            options,":sout-keep"
	        );

	        //Don't exit;
		}	
		private String formatRtspStream(String serverAddress, int serverPort, String id) {
        StringBuilder sb = new StringBuilder(60);
        sb.append(":sout=#rtp{sdp=rtsp://@");
        sb.append(serverAddress);
        sb.append(':');
        sb.append(serverPort);
        sb.append('/');
        sb.append(id);
        sb.append("}");
        return sb.toString();
    }**/
	/**public void xugglerReading(String inputFileName){
			String url = "C:\\Users\\slashershot\\Desktop\\TestingVideos\\testing.mp4";
			IMediaReader mediaReader = ToolFactory.makeReader(url);
			mediaReader.addListener(ToolFactory.makeViewer());
			while(mediaReader.readPacket()== null){
				System.out.println("Hi");
			}**/
	//}
	//	  public class myThread extends Thread{
	//		  public myThread(){}
	//		  public void run(){
	//				String url = "C:\\Users\\slashershot\\Desktop\\TestingVideos\\testing.mp4";
	//				IMediaReader mediaReader = ToolFactory.makeReader(url);
	//				IMediaWriter mediaWriter = ToolFactory.makeWriter("rtsp://localhost:8554/testing",mediaReader);
	//				mediaReader.addListener(mediaWriter);
	//				while(mediaReader.readPacket()== null){
	//		  }
	//	  }

	public static void exit() {
		System.exit(0);
		// TODO Auto-generated method stub
		
	}
}
