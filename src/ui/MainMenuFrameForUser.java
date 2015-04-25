package ui;


import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Client.NewClient;

import entity.User;

public class MainMenuFrameForUser extends JFrame 
{

	private JPanel contentPane;
	JMenuItem videoList;
	JMenuBar menubar;
	JMenuItem exit;
	JMenu user;
	JMenuItem logout;
	JMenu view;
	JMenu help;
	JMenuItem small;
	JMenuItem largeMode;
	JMenuItem settings;
	JMenuItem privacyPolicy;
	JMenuItem aboutApplication;
	JMenuItem systemInfo;
	JMenu contactUs;
	JMenu videoSites;
	JMenu faq;
	JMenuItem contactUss;
	JMenuItem youtube;
	JMenuItem yahooScreen;
	JMenuItem dailyMotion;
	Window device;
	JMenuItem faqList;
	JMenuItem profile;
	
	
	
	
	public static void main(String[] args) 
	{	
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					MainMenuFrameForUser frame = new MainMenuFrameForUser(null);
					frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}
	
	
	
	
 
	/**
	 * Create the frame.
	 * @wbp.parser.constructor
	 */
	public MainMenuFrameForUser(final User users) 
	{
		menubar = new JMenuBar();
		setJMenuBar(menubar);

		user = new JMenu("User");
		menubar.add(user);

		contactUs = new JMenu("Contact Us");
		contactUs.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				goContactUsPage(users);
			}
		});
		menubar.add(contactUs);

		videoSites = new JMenu("Other Video Sites");
		menubar.add(videoSites);

		logout = new JMenuItem("Logout");
		logout.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				goBack();
			}
		});
		user.add(logout);

		profile = new JMenuItem("Admin User List");
		profile.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				goUserList(users);
			}
		});
		user.add(profile);

		

		contactUss = new JMenuItem("Contact Us Page");
		contactUss.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{

				goContactUsPage(users);
			}
		});
		contactUs.add(contactUss);

		youtube = new JMenuItem("Youtube link");
		youtube.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				try {
					Desktop.getDesktop().browse(new URI("http://www.youtube.com"));
				} 
				catch (IOException e1) 
				{
					e1.printStackTrace();
				}
				catch (URISyntaxException e1) 
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		videoSites.add(youtube);

		dailyMotion = new JMenuItem("Daily Motion link");
		dailyMotion.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					Desktop.getDesktop().browse(new URI("http://www.dailymotion"));
				} 
				catch (IOException e1) 
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
				catch (URISyntaxException e1) 
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		videoSites.add(dailyMotion);

		yahooScreen = new JMenuItem("Yahoo Screen link");
		yahooScreen.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					Desktop.getDesktop().browse(new URI("http://screen.yahoo.com/"));
				} catch (IOException e1) 
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (URISyntaxException e1) 
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		videoSites.add(yahooScreen);

		getContentPane().setLayout(new FlowLayout());
		setTitle("Menu Frame");
		menubar = new JMenuBar();
		setJMenuBar(menubar);

		user = new JMenu("User");
		menubar.add(user);
		
		
		
		profile = new JMenuItem("Profile");
		profile.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				openDetails(users);
			}
		});
		user.add(profile);
		
		contactUs = new JMenu("Contact Us");
		contactUs.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				goContactUsPage(users);
			}
		});
		menubar.add(contactUs);
		
		videoSites = new JMenu("Other Video Sites");
		menubar.add(videoSites);
		
		
		
		
		
		logout = new JMenuItem("Logout");
		logout.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				goBack();
			}
		});
		user.add(logout);
		
		videoList = new JMenuItem("Video List");
		videoList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//goVideoList();
			}
		});
		
		
		
		
		
		contactUss = new JMenuItem("Contact Us Page");
		contactUss.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				goContactUsPage(users);
			}
		});
		contactUs.add(contactUss);
		
		youtube = new JMenuItem("Youtube link");
		youtube.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					Desktop.getDesktop().browse(new URI("http://www.youtube.com"));
				} 
				catch (IOException e1) 
				{
					e1.printStackTrace();
				} 
				catch (URISyntaxException e1) 
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		videoSites.add(youtube);
		
		dailyMotion = new JMenuItem("Daily Motion link");
		dailyMotion.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					Desktop.getDesktop().browse(new URI("http://www.dailymotion"));
				} 
				catch (IOException e1) 
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
				catch (URISyntaxException e1) 
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		videoSites.add(dailyMotion);
		
		yahooScreen = new JMenuItem("Yahoo Screen link");
		yahooScreen.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					Desktop.getDesktop().browse(new URI("http://screen.yahoo.com/"));
				} catch (IOException e1) 
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
				catch (URISyntaxException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		videoSites.add(yahooScreen);
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 505, 319);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnUserProfile = new JButton("");
		btnUserProfile.setIcon(new ImageIcon(MainMenuFrameForUser.class.getResource("/icons/userImageFrame1.jpg")));
		btnUserProfile.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				openDetails(users);
			}
		});
		btnUserProfile.setBounds(72, 70, 89, 105);
		contentPane.add(btnUserProfile);
		
		JButton btnVideoList = new JButton("Video List");
		btnVideoList.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				goVideoList(users);
			}
		});
		btnVideoList.setBounds(255, 70, 149, 105);
		contentPane.add(btnVideoList);
		
		JLabel lblWelcomeUser = new JLabel("Welcome " + users.getUsername());
		lblWelcomeUser.setFont(new Font("Arial", Font.BOLD, 20));
		lblWelcomeUser.setBounds(134, 22, 256, 37);
		contentPane.add(lblWelcomeUser);
		
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				goBack();
			}
		});
		btnLogOut.setBounds(186, 228, 89, 23);
		contentPane.add(btnLogOut);
		
		JLabel lblUserList = new JLabel("User Profile");
		lblUserList.setBounds(80, 186, 68, 14);
		contentPane.add(lblUserList);
	}

	private void goBack(){
		if (JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?")==0)
		{
		
		LoginFrame frame = new LoginFrame();
		user = null;
		frame.setVisible(true);
		this.dispose();
	}
	}
	
	private void goVideoList(User user){
		VideoSelectFrame frame;
		try {
			frame = new VideoSelectFrame(user);
			frame.setVisible(true);
			this.setVisible(false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	private void goContactUsPage(User user)
	{
		ContactUsPage contactUs = new ContactUsPage(user);
		contactUs.setVisible(true);
		this.dispose();
	}
	
	public void openDetails(User users)
	{
		UserProfileFrame frame = new UserProfileFrame(users);
		frame.setVisible(true);
		this.dispose();		
	}
	
	private void goUserList(User user)
	{
		
		UsersList frame = new UsersList(user);
		frame.setVisible(true);
		this.setVisible(false);
	}
	
	
	

}
