/*
Name: Hong Ting
AdminNo: 130250D
Class: IS1301
Date Created: 19/12/2013
 */

package ui;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Client.NewClient;
import Client.ProcessingClient;
import Client.TryClient;
import cryptography.Crypto;
import entity.User;


public class SignUpFrame extends JFrame 
{

	private JPanel contentPane;
	public static String getEmailRegex() {
		return EMAIL_REGEX;
	}

	private LoginFrame fLoginFrame;
	private Pattern pattern;
	private Matcher matcher;
 
	private JTextField txtName;
	private JPasswordField txtPw;
	private JPasswordField txtCfmPw;
	private JTextField txtEmail;
	private JTextField txtAns;
	private Component frame;
	private String qns;
	private JComboBox cbSecretQns;
	private String[] qnsList = {"What's your pet name?","What's your school name?","What's your favourite car brand?"};
	private static final String EMAIL_REGEX = 
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	 

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
					SignUpFrame frame = new SignUpFrame(null);
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
	public SignUpFrame(LoginFrame loginFrame) 
	{
		setTitle("Sign Up");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 476, 369);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblName = new JLabel("Username:");
		lblName.setBounds(31, 65, 78, 14);
		contentPane.add(lblName);
		
		txtName = new JTextField();
		txtName.setBounds(204, 62, 161, 20);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(31, 90, 79, 23);
		contentPane.add(lblPassword);
		
		txtPw = new JPasswordField(6);
		txtPw.setBounds(204, 91, 161, 20);
		contentPane.add(txtPw);
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password:");
		lblConfirmPassword.setBounds(31, 141, 109, 14);
		contentPane.add(lblConfirmPassword);
		
		txtCfmPw = new JPasswordField(6);
		txtCfmPw.setBounds(204, 138, 161, 20);
		contentPane.add(txtCfmPw);
		
		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setBounds(31, 166, 60, 14);
		contentPane.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(204, 163, 161, 20);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);
		
		JLabel lblSecretQuestion = new JLabel("Secret Question:");
		lblSecretQuestion.setBounds(31, 191, 109, 14);
		contentPane.add(lblSecretQuestion);
		
		JLabel lblSecretAnswer = new JLabel("Secret Answer:");
		lblSecretAnswer.setBounds(31, 216, 109, 14);
		contentPane.add(lblSecretAnswer);
		
		txtAns = new JTextField();
		txtAns.setBounds(204, 213, 161, 20);
		txtAns.setColumns(10);
		contentPane.add(txtAns);
		
		JButton btnSignUp = new JButton("Sign Up");
		btnSignUp.setBounds(45, 278, 89, 23);
		btnSignUp.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				try {
					NewClient.getOutObjects().writeObject("SignUpFrame");
					String username = txtName.getText();
					String email = txtEmail.getText();
					String password = String.valueOf(txtPw.getPassword());	
					String cfmPassword = String.valueOf(txtCfmPw.getPassword());
					String SecretQns = qns;
					String SecretAns = txtAns.getText();
					String hash = Crypto.convertToMD5(password);
					User serverCheck = new User(username,hash,email,SecretQns,SecretAns);
					/**if(serverCheck.getUsername().length()>0 && serverCheck.getEmail().matches(EMAIL_REGEX) && password.length()>=6 && password.equals(cfmPassword)){
						ProcessingClient.getOutObjects().writeObject(serverCheck);
					}else if(serverCheck.getUsername().length()<0){
						JOptionPane.showMessageDialog(contentPane, "Username found");
					}
					else if(serverCheck.getEmail().matches(EMAIL_REGEX)==false){
						JOptionPane.showMessageDialog(contentPane, "Email does not match");
					}
					else if(serverCheck.getPassword().length()<6){
						JOptionPane.showMessageDialog(contentPane, "Password below 6 characters!");
					}else if(password.equals(cfmPassword)==false){
						JOptionPane.showMessageDialog(contentPane, "Password do not match!");
					}
					if(ProcessingClient.getInObjects().readObject().equals("Created!")){
						JOptionPane.showMessageDialog(contentPane, "Account Created!");
						goBack();
					}**/
					if(serverCheck.getUsername().length()<=0){
						JOptionPane.showMessageDialog(contentPane, "Please enter your username!");
					}else if(serverCheck.getEmail().matches(EMAIL_REGEX)==false){
						JOptionPane.showMessageDialog(contentPane, "Email is not in a valid format");
					}else if(password.length()<6){
						JOptionPane.showMessageDialog(contentPane, "Password below 6 characters");
					}else if(password.equals(cfmPassword)==false){
						JOptionPane.showMessageDialog(contentPane, "Password do not match!");
					}else{
						ProcessingClient.getOutObjects().writeObject(serverCheck);
						if(ProcessingClient.getInObjects().readObject().equals("Created!")){
							JOptionPane.showMessageDialog(contentPane, "Account Created!");
							goBack();
						}
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
				/**try{
					TryClient.output(2);
				}catch(Exception e1){
					
				}
				try {
					boolean status = validationUser();
					if(status== true){
						addUsers();
						goBack();
					}
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}**/
			});
		
		contentPane.add(btnSignUp);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearButton();
			}
		});
		btnClear.setBounds(144, 278, 89, 23);
		contentPane.add(btnClear);
		
		JButton btnClose = new JButton("Close");
		btnClose.setBounds(255, 278, 89, 23);
		btnClose.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				goBack();
			}
		});
		contentPane.add(btnClose);
		
		JLabel lblMustBe = new JLabel("(Must be 6 or more characters)");
		lblMustBe.setBounds(31, 110, 161, 14);
		contentPane.add(lblMustBe);
		cbSecretQns = new JComboBox(qnsList);
		qns = qnsList[0];
		cbSecretQns.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				JComboBox cb = (JComboBox)e.getSource();
				qns = (String)cb.getSelectedItem();
			}
		});
		cbSecretQns.setBounds(204, 188, 193, 20);
		contentPane.add(cbSecretQns);
		
		fLoginFrame = loginFrame;
	}
	
	private void goBack()
	{
		LoginFrame backLoginFrame = new LoginFrame(this);
		backLoginFrame.setVisible(true);
		this.dispose();
	}
	public boolean validationUser() throws ClassNotFoundException{
		String username = txtName.getText();
		String email = txtEmail.getText();
		String password = String.valueOf(txtPw.getPassword());		
		String cfmPassword = String.valueOf(txtCfmPw.getPassword());
		String[] serverCheck = {username,email,password};
		Object status;
		boolean statusChecking = false;
		if(username!=null && password.equals(cfmPassword)){
			try {
				ObjectOutputStream outUsernameCheck = new ObjectOutputStream(TryClient.setup().getOutputStream());
				outUsernameCheck.writeObject(serverCheck);
				outUsernameCheck.flush();
				outUsernameCheck.close();
				ObjectInputStream statusCheck = new ObjectInputStream(TryClient.setup().getInputStream());
				status = statusCheck.readObject();
				statusCheck.close();
				if(status.equals("Accepted!")){
					statusChecking = true;
				}
				else
				{
					JOptionPane.showMessageDialog(contentPane, status);
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}catch(NullPointerException e){
				JOptionPane.showMessageDialog(null, "Server is down");
				System.exit(0);
			}
			
			}
		else{
		clearButton();
		JOptionPane.showMessageDialog(contentPane, "A field is blank or invalid");
		}
		return statusChecking;
	}
	public void addUsers() throws IOException, HeadlessException, ClassNotFoundException
	{
		String username = txtName.getText();
		String password = String.valueOf(txtPw.getPassword());
		String email = txtEmail.getText();
		String secretQns = qns;
		String secretAnswer = txtAns.getText();
		String hash = Crypto.convertToMD5(password);
		
			//create the object based on non-default constructor
			//name, email,  gender, phoneNum,  username, password
			
			User encryptUsers = new User(username, hash, email, secretQns, secretAnswer);
			ObjectOutputStream outputUser = new ObjectOutputStream(TryClient.setup().getOutputStream());
			outputUser.writeObject(encryptUsers);
			outputUser.close();
			ObjectInputStream signUpStatus = new ObjectInputStream(TryClient.setup().getInputStream());
			JOptionPane.showMessageDialog(contentPane, signUpStatus.readObject());
			signUpStatus.close();
			
	}
	public void clearButton(){
		txtName.setText("");
		txtPw.setText("");
		txtCfmPw.setText("");
		txtEmail.setText("");
		txtAns.setText("");
		cbSecretQns.setSelectedIndex(0);
	}
}
		