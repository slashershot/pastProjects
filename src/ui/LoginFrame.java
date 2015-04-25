/*
Name: Jillian Wee
AdminNo: 133304F
Class: IS1301
Date Created: 19/12/2013
*/

package ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Client.NewClient;
import Client.ProcessingClient;
import cryptography.Crypto;
import entity.User;

public class LoginFrame extends JFrame 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private SignUpFrame fSignUpFrame;
	
	private JPasswordField txtPassword;
	private JTextField txtUsername;
	private User user;
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
					LoginFrame frame = new LoginFrame();
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
	public LoginFrame() 
	{
		if(NewClient.getStatus()==false){
		NewClient.setup();
		ProcessingClient.setup();
		}
		LoginFraming();
	}
	
	public void LoginFraming() 
	{
		user = null;
		setTitle("Login Frame");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(105, 94, 68, 23);
		contentPane.add(lblUsername);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(183, 135, 104, 20);
		contentPane.add(txtPassword);
		
		JLabel lblPassword = new JLabel("Password: ");
		lblPassword.setBounds(105, 137, 68, 17);
		contentPane.add(lblPassword);
		
		
		txtUsername = new JTextField();
		txtUsername.setBounds(183, 95, 104, 20);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				Object obj = null;
				try {
				outUser();
				  obj = ProcessingClient.getInObjects().readObject();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if((obj instanceof String == false)){
					user = (User)obj;
					if(user.getId()==1){
						System.out.println(user.getPassword());
						MainMenuFrameForUser goMenuFrame = new MainMenuFrameForUser(user);
						JOptionPane.showMessageDialog(null,  "You have successfully login");
						goMenuFrame.setVisible(true);
						dispose();
					}
					else{
						MainMenuFrame goMenuFrame = new MainMenuFrame(user);
						JOptionPane.showMessageDialog(null,  "You have successfully login");
						goMenuFrame.setVisible(true);
						dispose();	
					}
				}else{
					JOptionPane.showMessageDialog(contentPane, "Invalid Login!");
				}
				
			
				/**try {
					TryClient.output(1);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				User user =  validateUser();
				if(user!=null){
				if(user.getId() == 1){
					MainMenuFrameForUser goMenuFrame = new MainMenuFrameForUser(user);
					JOptionPane.showMessageDialog(null,  "You have successfully login");
					goMenuFrame.setVisible(true);
					dispose();
				}
				else
				{
					MainMenuFrame goMenuFrame = new MainMenuFrame(user);
					JOptionPane.showMessageDialog(null,  "You have successfully login");
					goMenuFrame.setVisible(true);
					dispose();	
				}
			}
			else{
				JOptionPane.showMessageDialog(contentPane, "Invalid Login!");
			}**/
			}
		});
		btnLogin.setBounds(288, 190, 89, 23);
		contentPane.add(btnLogin);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				txtUsername.setText("");
				txtPassword.setText("");
			}
		});
		btnClear.setBounds(72, 190, 89, 23);
		contentPane.add(btnClear);
		
		JButton btnNotAnUsersign = new JButton("Not an user? Sign up here!");
		btnNotAnUsersign.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				signUpFraming();
			}
		});
		btnNotAnUsersign.setBounds(72, 224, 305, 26);
		contentPane.add(btnNotAnUsersign);
	
	}
	
	public void outUser() throws IOException, ClassNotFoundException
	{
		String username = txtUsername.getText();
		String password = String.valueOf(txtPassword.getPassword());
		String hash = Crypto.convertToMD5(password);
		User encryptinUser = new User(username, hash);
		if(username!= null &&  password != null){
		try {
			NewClient.getOutObjects().writeObject("LoginFrame");
			ProcessingClient.getOutObjects().writeObject(encryptinUser);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}else{
			JOptionPane.showMessageDialog(contentPane, "Please enter your username & password!");
			txtUsername.setText("");
			txtPassword.setText("");
		}
	}
	
	/**public User validateUser(){
		User user = null;
		while(user==null){
			try {
				outUser();
				user = TryClient.inUser();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
		return user;
	}**/
	public void signUpFraming()
	{
		SignUpFrame signUpFrame = new SignUpFrame(this);
		signUpFrame.setVisible(true);
		this.dispose();
	}
	
	public LoginFrame(SignUpFrame fSignUpFrame) 
	{
		this.fSignUpFrame = fSignUpFrame;
		LoginFraming();
	}
}
