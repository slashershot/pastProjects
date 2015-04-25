package ui;


import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Client.NewClient;
import Client.ProcessingClient;
import database.VideoDAO;
import entity.User;
import entity.Video;


public class VideoSelectFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private ArrayList<Video> vidList;
	private JList<String> list;
	private final JButton btnNewButton = new JButton("Download");
	private User userz=null;
	static int counter =0;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
//					cSocket = new Socket("localhost", 3332);//commServer socket
					
					VideoSelectFrame frame = new VideoSelectFrame(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 * 
	 */
	public VideoSelectFrame(User user) throws IOException {
		if(userz==null){
			userz=user;
		}
		initGUI(userz);
		
	}
	@SuppressWarnings("unchecked")
	private void initGUI(final User user) {
		try {
			NewClient.getOutObjects().writeObject("VideoSelectFrame");
			vidList = (ArrayList<Video>) ProcessingClient.getInObjects().readObject();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
				JButton btnSearch = new JButton("Search");
		btnSearch.setBounds(308, 35, 89, 23);
		contentPane.add(btnSearch);
		
				textField = new JTextField();
		textField.setBounds(55, 36, 220, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
				JButton btnNewButton_2 = new JButton("Close");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goBack(user);
			}
		});
		btnNewButton_2.setBounds(163, 228, 89, 23);
		contentPane.add(btnNewButton_2);
		
		list = new JList<String>();
		DefaultListModel<String> model = new DefaultListModel<String>();
		for(int i=0; vidList.size()>i;i++){
			model.addElement(vidList.get(i).getFilename());
		}
		list.setModel(model);
		list.setSelectedIndex(-1);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent evt){
				JList<String> list = (JList<String>)evt.getSource();
				try {
					ProcessingClient.getOutObjects().writeObject(list.getSelectedValue());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(evt.getClickCount()==2){
					goNext(user);
				}
			}
		});
		/**list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
			 if(e.getValueIsAdjusting()==true){
				 try {
					ProcessingClient.getOutObjects().writeObject(list.getSelectedValue());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				 goNext(user);
			 }
			}
		});**/
	
		list.setSelectedIndex(0);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//		list.addListSelectionListener(new ListSelectionListener() {
		//			public void valueChanged(ListSelectionEvent e) {
		//				if(e.getValueIsAdjusting()==true){
		////					goNext();
		//				}
		//			}
		//		});
				list.setBounds(55, 93, 220, 104);
		contentPane.add(list);
		
				JButton btnAdd = new JButton("Upload");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				JFileChooser openFile = new JFileChooser();
//				openFile.showOpenDialog(null);
				 goToUploader(user);
				
			}
		});
		btnAdd.setBounds(308, 139, 89, 23);
		contentPane.add(btnAdd);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				goToDownloader();
			}
		});
		btnNewButton.setBounds(308, 190, 89, 23);
		
		contentPane.add(btnNewButton);
		
		JButton btnTest = new JButton("Resume");
		btnTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PlayVideoFrame.frame.setVisible(true);
				PlayVideoFrame.mediaPlayerComponent.getMediaPlayer().play();
			}
		});
		btnTest.setBounds(35, 228, 89, 23);
		contentPane.add(btnTest);
	}
	
	void goNext(User user){
		try {
			NewClient.getOutObjects().writeObject("PlayVideo");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PlayVideoFrame frame = new PlayVideoFrame(user);
		this.setVisible(false);
		
	}
	void goToUploader(User user){
		clientUploader frame = new clientUploader(user);
		frame.setVisible(true);
	}
	
	void goToDownloader(){
		String fileName = list.getSelectedValue().toString();
		
		clientDownloader frame = new clientDownloader(fileName);
		frame.setVisible(true);
	}
	void goBack(User users){
		if(users.getId() == 1){
		MainMenuFrameForUser parentFrame = new MainMenuFrameForUser(users);
		this.dispose();
		parentFrame.setVisible(true);
		}
		else{
			MainMenuFrame frame = new MainMenuFrame(users);
			this.dispose();
			frame.setVisible(true);
		}
		this.dispose();	
	}
}
