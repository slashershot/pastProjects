/*
Name: Chew Wei Bin
AdminNo: 134572Q
Class: IS1301
Date Created: 19/12/2013
 */

package ui;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Color;

import javax.swing.JButton;

import Client.NewClient;

import entity.User;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class ContactUsPage extends JFrame 
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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					ContactUsPage frame = new ContactUsPage(null);
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
	 */
	public ContactUsPage(final User users) 
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

		profile = new JMenuItem("profile");
		profile.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				openDetails(users);
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
		setTitle("Contact Us Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPleaseEmailTo = new JLabel("Please Email to nigelgroup@hotmail.com");
		lblPleaseEmailTo.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblPleaseEmailTo.setBounds(23, 73, 361, 52);
		contentPane.add(lblPleaseEmailTo);
		
		JLabel lblIfYouHave = new JLabel("If you have any enquiries");
		lblIfYouHave.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblIfYouHave.setBounds(28, 36, 292, 46);
		contentPane.add(lblIfYouHave);
		
		JLabel lblOrYouMay = new JLabel("Or you may want to contact this number(12345678)");
		lblOrYouMay.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblOrYouMay.setBounds(21, 109, 509, 60);
		contentPane.add(lblOrYouMay);
		
		JLabel lblNewLabel = new JLabel("Contact Us Page");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setBounds(97, 11, 243, 33);
		contentPane.add(lblNewLabel);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				goMainMenuFrame(users);
			}
		});
		btnBack.setBounds(140, 212, 89, 23);
		contentPane.add(btnBack);
	}
	
	
	public void openDetails(User users)
	{
		UserProfileFrame frame = new UserProfileFrame(users);
		frame.setVisible(true);
		this.dispose();		
	}
	
	private void goBack(){
		if (JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?")==0)
		{
			System.out.println("Yes");
		
		LoginFrame frame = new LoginFrame();
		try {
			NewClient.getOutObjects().writeObject("LoginFrame");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		frame.setVisible(true);
		this.dispose();
	}
	}
	
	private void goContactUsPage(User user)
	{
		ContactUsPage contactUs = new ContactUsPage(user);
		contactUs.setVisible(true);
		this.dispose();
	}
	
	private void goMainMenuFrame(User user)
	{
		if(user.getId()==5){
		MainMenuFrame frame = new MainMenuFrame(user);
		frame.setVisible(true);
		this.dispose();
		}else{
			MainMenuFrameForUser frame = new MainMenuFrameForUser(user);
			frame.setVisible(true);
			this.dispose();
		}
	
		
		
		
	}
}
