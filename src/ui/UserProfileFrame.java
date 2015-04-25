/*
Name: Chew Wei Bin
AdminNo: 134572Q
Class: IS1301
Date Created: 19/12/2013
 */
package ui;

import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Client.NewClient;
import Client.ProcessingClient;
import cryptography.Crypto;
import database.AdminPanelDAO;
import database.UserDAO;
import entity.User;

public class UserProfileFrame extends JFrame 
{

	private JPanel txtConfirmPassword;
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
	JTextField userTxt;
	private JPasswordField currentPw;
	private JButton btnSave;
	private JButton btnEdit;
	private JTextField txtEmail;
	private MainMenuFrameForUser parentFrame;
	
	private JPasswordField txtNewPassword;
	private JPasswordField txtCurrentPassword;
	private JPasswordField txtConfirmPassword1;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try {
					User user = null;
					UserProfileFrame frame = new UserProfileFrame(user);
					frame.setVisible(true);
				} catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UserProfileFrame(final User users) 
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
		
		setTitle("User Profile");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 484, 336);
		txtConfirmPassword = new JPanel();
		txtConfirmPassword.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(txtConfirmPassword);
		txtConfirmPassword.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username : ");
		lblUsername.setBounds(23, 54, 108, 14);
		txtConfirmPassword.add(lblUsername);
		
		JLabel lblEmail = new JLabel("E-mail :");
		lblEmail.setBounds(23, 82, 108, 14);
		txtConfirmPassword.add(lblEmail);
		
		JLabel lblUserProfile = new JLabel("User Profile");
		lblUserProfile.setFont(new Font("Arial", Font.BOLD, 24));
		lblUserProfile.setBounds(120, 0, 185, 43);
		txtConfirmPassword.add(lblUserProfile);
		
		JLabel lblChangePassword = new JLabel("Change Password");
		lblChangePassword.setBounds(23, 107, 108, 14);
		txtConfirmPassword.add(lblChangePassword);
		
		userTxt = new JTextField();
		userTxt.setEnabled(false);
		
		userTxt.setText(users.getUsername());
		userTxt.setEditable(false);
		userTxt.setBounds(158, 51, 233, 20);
		txtConfirmPassword.add(userTxt);
		userTxt.setColumns(10);
		
		btnEdit = new JButton("Edit");
		
		btnSave = new JButton("Save");
		
		btnEdit.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				iniEditFrame();
			}
		});
		
		btnEdit.setBounds(66, 221, 70, 30);
		txtConfirmPassword.add(btnEdit);
		
		btnSave.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				btnSave.setEnabled(false);
				
				txtEmail.setEnabled(false);
				txtEmail.setEditable(false);
				
				txtCurrentPassword.setEnabled(false);
				txtCurrentPassword.setEditable(false);
				
				txtNewPassword.setEnabled(false);
				txtNewPassword.setEditable(false);
				
				txtConfirmPassword1.setEnabled(false);
				txtConfirmPassword1.setEditable(false);
				
				saveDets(users);
				btnEdit.setEnabled(true);
			}
		});
		btnSave.setEnabled(false);
		btnSave.setBounds(168, 221, 70, 30);
		txtConfirmPassword.add(btnSave);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				goBack(users);
			}
		});
		btnBack.setBounds(281, 221, 70, 30);
		txtConfirmPassword.add(btnBack);
		
		txtEmail = new JTextField();
		txtEmail.setText(users.getEmail());
		txtEmail.setEditable(false);
		txtEmail.setEnabled(false);
		txtEmail.setBounds(158, 76, 233, 20);
		txtConfirmPassword.add(txtEmail);
		txtEmail.setColumns(10);
		
		JLabel lblCurrentPassword = new JLabel("Current Password: ");
		lblCurrentPassword.setBounds(23, 135, 125, 14);
		txtConfirmPassword.add(lblCurrentPassword);
		
		JLabel lblNewPassword = new JLabel("New Password: ");
		lblNewPassword.setBounds(23, 159, 125, 14);
		txtConfirmPassword.add(lblNewPassword);
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password:");
		lblConfirmPassword.setBounds(23, 184, 125, 14);
		txtConfirmPassword.add(lblConfirmPassword);
		
		txtNewPassword = new JPasswordField();
		txtNewPassword.setBounds(158, 157, 233, 20);
		txtNewPassword.setEditable(false);
		txtConfirmPassword.add(txtNewPassword);
		
		txtCurrentPassword = new JPasswordField();
		txtCurrentPassword.setBounds(158, 132, 233, 20);
		txtCurrentPassword.setEditable(false);
		txtConfirmPassword.add(txtCurrentPassword);
		
		txtConfirmPassword1 = new JPasswordField();
		txtConfirmPassword1.setBounds(158, 181, 233, 20);
		txtConfirmPassword1.setEditable(false);
		txtConfirmPassword.add(txtConfirmPassword1);
	}	
	
	public void iniEditFrame()
	{
		btnSave.setEnabled(true);
		
		txtEmail.setEnabled(true);
		txtEmail.setEditable(true);
		
		txtCurrentPassword.setEnabled(true);
		txtCurrentPassword.setEditable(true);
		
		txtNewPassword.setEnabled(true);
		txtNewPassword.setEditable(true);
		
		txtConfirmPassword1.setEnabled(true);
		txtConfirmPassword1.setEditable(true);
		
		btnEdit.setEnabled(false);
		
	}
	
	public void goMenuFrame()
	{
		User user = null;
		MainMenuFrameForUser menuFrame = new MainMenuFrameForUser(user);
		menuFrame.setVisible(true);
		this.dispose();
	}
	
	public void goBack(User users)
	{	
		parentFrame = new MainMenuFrameForUser(users);
		parentFrame.setVisible(true);
		this.dispose();		
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
		
	public void saveDets(User user)
	{
		String[] fieldsArr = {userTxt.getText(),txtEmail.getText(),String.valueOf(txtCurrentPassword.getPassword()),String.valueOf(txtNewPassword.getPassword()),String.valueOf(txtConfirmPassword1.getPassword())};
		try {
			NewClient.getOutObjects().writeObject("UserProfileFrame");
			ProcessingClient.getOutObjects().writeObject(fieldsArr);
			ProcessingClient.getOutObjects().writeObject(user);
			String message = (String) ProcessingClient.getInObjects().readObject();
			JOptionPane.showMessageDialog(null, message);
			user.setEmail(txtEmail.getText());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/**String email = emailValidation(user);
		String password = passwordValidation(user);
		String username = userTxt.getText();
		User updatedUser;
		System.out.println(password);
		if(email != null && password != null)
		{
			updatedUser = new User(username, password, email);
			AdminPanelDAO.update(updatedUser);
			JOptionPane.showMessageDialog(null, "All updated!");
			user.setEmail(updatedUser.getEmail());
			user.setPassword(updatedUser.getPassword());
			txtCurrentPassword.setText("");
			txtNewPassword.setText("");
			txtConfirmPassword1.setText("");		
		}
		else if(email != null)
		{
			updatedUser = new User(username,user.getPassword(), email);
			AdminPanelDAO.update(updatedUser);
			user.setEmail(updatedUser.getEmail());
			txtEmail.setText(user.getEmail());
			JOptionPane.showMessageDialog(null, "Email Saved");
		}
		else if(password != null)
		{
			updatedUser = new User(username,password,user.getEmail());
			AdminPanelDAO.update(updatedUser);
			user.setPassword(updatedUser.getPassword());
			txtEmail.setText(user.getEmail());
			JOptionPane.showMessageDialog(null, "New Password Saved");
			txtCurrentPassword.setText("");
			txtNewPassword.setText("");
			txtConfirmPassword1.setText("");
		}
		else
			JOptionPane.showMessageDialog(null, "There is an error in one or more textfields!");
		
		btnEdit.setEnabled(true);**/
	}
	
	/**public String emailValidation(User user)
	{
		String email = null;
		if(!(txtEmail.getText().equals((UserDAO.retrieve(user.getUsername())).getEmail())))
		{			
			if(txtEmail.getText().matches(SignUpFrame.getEmailRegex()))
			{
				  email = String.valueOf(txtEmail.getText());
				  System.out.println("Email successfully change");
				  
			}
			else
				JOptionPane.showMessageDialog(null, "Please enter a valid email", "Invalid Email", JOptionPane.WARNING_MESSAGE);
				
		}
		
		return email;**/
	//}
	
	/**public String passwordValidation(User user)
	{
		String password = null;
		String hash1;
		String hash2;
		
		String hash = Crypto.convertToMD5(String.valueOf(txtCurrentPassword.getPassword()));
		
		if(hash.equals((UserDAO.retrieve(user.getUsername())).getPassword()))
		{			
			if(hash.equals((UserDAO.retrieve(user.getUsername())).getPassword()))
			{				
				hash1 = Crypto.convertToMD5(String.valueOf(txtNewPassword.getPassword()));
				hash2 = Crypto.convertToMD5(String.valueOf(txtConfirmPassword1.getPassword()));
				
				System.out.println(hash1);
				System.out.println(hash2);
				
				if(((txtNewPassword.getPassword()).length)>7)
				{
					System.err.println(((txtNewPassword.getPassword()).length));
				
					if(hash1.equals(hash2))
					{
						password = (Crypto.convertToMD5(String.valueOf(txtNewPassword.getPassword())));
						System.out.println("Password Inside here");
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Your new password and confirm password do not match!", "Password Warning", JOptionPane.WARNING_MESSAGE);
						txtNewPassword.setText("");
						txtConfirmPassword1.setText("");
					}
					
				}
				else
					JOptionPane.showMessageDialog(null, "The password must be 8 or more letters");
				
				System.err.println(((txtNewPassword.getPassword()).length));
				
			}			
			
		}
		
		return password;
	}**/
}

