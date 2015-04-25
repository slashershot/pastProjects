package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import database.DBManager;

import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import entity.User;
import database.DBManager;
import database.AdminPanelDAO;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JLabel;

import Client.NewClient;
import Client.ProcessingClient;

public class UsersList extends JFrame implements java.io.Serializable {

	private JPanel contentPane;
	private JTable table;
	private static Connection currentCon = null;
	private static ResultSet rs = null;
	private static PreparedStatement pstmt = null;
	private static Statement stmt = null;
	private JTextField searchField_1;
	private JTextField txtUser;
	private JTextField txtEmail;
	private JTextField txtSecretQn;
	private JTextField txtSecretAns;
	private JTextField txta;
	private JButton searchField;
	private JButton txtEdit;
	private JButton txtSave;
	private JButton txtBan;
	private DefaultTableModel dataModel;
	protected entity.User users;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UsersList frame = new UsersList(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	
	
	
	public static DefaultTableModel buildTableModel(ResultSet rs)
            throws SQLException {
         
        ResultSetMetaData metaData = rs.getMetaData();  
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
			
	
	  public void populatejTable(){
	        try{
	        	currentCon = DBManager.getConnection();
	            System.out.println("Connection to Database Success!");
	            stmt = currentCon.createStatement();
	            String query = "select username,Email,SecretQuestion,SecretAnswer,AccessLevel from user";
	            pstmt = currentCon.prepareStatement(query);
				rs = pstmt.executeQuery();
	            table.setModel(buildTableModel(rs));
	        }
	        catch(SQLException ex){
	            System.out.println("Error encountered!");
	            System.out.println("SQLException: " + ex.getMessage());
	            System.out.println("SQLState: " + ex.getSQLState());
	            System.out.println("VendorError: " + ex.getErrorCode());
	        }
	  }    
	  
	    private void loadJBDCDriver(){
	        try {
	            // The newInstance() call is a work around for some
	            // broken Java implementations
	            Class.forName("com.mysql.jdbc.Driver").newInstance();
	        } catch (Exception ex) {
	            // handle the error
	            System.out.println("Error encountered!");
	            System.out.println(ex.getMessage());
	        }
	    }
	public UsersList(final User user) {
		try {
			NewClient.getOutObjects().writeObject("UsersList");
			dataModel = (DefaultTableModel)ProcessingClient.getInObjects().readObject();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 669, 505);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 248, 633, 208);
		contentPane.add(scrollPane);
		
		
		
		table = new JTable(){
			public boolean isCellEditable(int row, int column){
				return false;
			}
		};
		setTitle("Users List");
		table.setModel(dataModel);
		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				 try{
						DefaultTableModel model = (DefaultTableModel) table.getModel();
						txtUser.setText(model.getValueAt(table.getSelectedRow(),0).toString());
						txtEmail.setText(model.getValueAt(table.getSelectedRow(),1).toString());
						txtSecretQn.setText(model.getValueAt(table.getSelectedRow(),2).toString());
						txtSecretAns.setText(model.getValueAt(table.getSelectedRow(),3).toString());
						txta.setText(model.getValueAt(table.getSelectedRow(),4).toString());
						txtBan.setEnabled(true);
			
						if(txtUser.getText().equals(""))
						{
							txtEdit.setEnabled(false);
						}
						else
						{
							txtEdit.setEnabled(true);
						}
						
					}catch(Exception e){
						
					}
			}
		});
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {   
				
			}
			@Override
			public void mousePressed(MouseEvent arg1) {
				try{
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					txtUser.setText(model.getValueAt(table.getSelectedRow(),0).toString());
					
					txtEmail.setText(model.getValueAt(table.getSelectedRow(),1).toString());
					txtSecretQn.setText(model.getValueAt(table.getSelectedRow(),2).toString());
					txtSecretAns.setText(model.getValueAt(table.getSelectedRow(),3).toString());
					txta.setText(model.getValueAt(table.getSelectedRow(),4).toString());
					txtBan.setEnabled(true);
			
					if(txtUser.getText().equals(""))
					{
						txtEdit.setEnabled(false);
						
					}
					else
					{
						txtEdit.setEnabled(true);
					}
					
				}catch(Exception e){
					
				}
			}
		});
		scrollPane.setViewportView(table);
		
		searchField = new JButton("Search Username");
		searchField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String value = searchField_1.getText();
				
				int row;
				
				
				
				if (value.equals("")){
					JOptionPane.showMessageDialog(null, "Please Enter a username to search");
				}
				
				else
					
				for(row = 0; row<(table.getRowCount());row++)
				{
					if(value.equals(table.getValueAt(row, 0)))
					{
						table.setRowSelectionInterval(row, row);
						table.setSelectionBackground(Color.red);
						DefaultTableModel model = (DefaultTableModel) table.getModel();
						txtUser.setText(model.getValueAt(table.getSelectedRow(),0).toString());
						
						txtEmail.setText(model.getValueAt(table.getSelectedRow(),1).toString());
						txtSecretQn.setText(model.getValueAt(table.getSelectedRow(),2).toString());
						txtSecretAns.setText(model.getValueAt(table.getSelectedRow(),3).toString());
						txta.setText(model.getValueAt(table.getSelectedRow(),4).toString());
						//txtEdit.setEnabled(true);
					}
				}
			
			}
				
			
		});
		searchField.setBounds(411, 11, 152, 23);
		contentPane.add(searchField);
		
		
		
		
		
		
		searchField_1 = new JTextField();
		searchField_1.setBounds(175, 12, 219, 20);
		contentPane.add(searchField_1);
		searchField_1.setColumns(10);
		
		txtUser = new JTextField();
		txtUser.setEnabled(false);
		txtUser.setEditable(false);
		txtUser.setBounds(222, 58, 260, 20);
		contentPane.add(txtUser);
		txtUser.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setEnabled(false);
		txtEmail.setEditable(false);
		txtEmail.setColumns(10);
		txtEmail.setBounds(222, 94, 260, 20);
		contentPane.add(txtEmail);
		
		txtSecretQn = new JTextField();
		txtSecretQn.setEnabled(false);
		txtSecretQn.setEditable(false);
		txtSecretQn.setColumns(10);
		txtSecretQn.setBounds(222, 125, 260, 20);
		contentPane.add(txtSecretQn);
		
		txtSecretAns = new JTextField();
		txtSecretAns.setEditable(false);
		txtSecretAns.setEnabled(false);
		txtSecretAns.setColumns(10);
		txtSecretAns.setBounds(222, 156, 260, 20);
		contentPane.add(txtSecretAns);
		
		txta = new JTextField();
		txta.setEnabled(false);
		txta.setEditable(false);
		txta.setColumns(10);
		txta.setBounds(222, 192, 260, 20);
		contentPane.add(txta);
		
		txtEdit = new JButton("Edit");
		txtEdit.setEnabled(false);
		txtEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtEmail.setEnabled(true);
				txtEmail.setEditable(true);
				txtSecretQn.setEnabled(true);
				txtSecretQn.setEditable(true);
				txtSecretAns.setEnabled(true);
				txtSecretAns.setEditable(true);
				txta.setEnabled(true);
				txta.setEditable(true);
				txtSave.setEnabled(true);
				txtBan.setEnabled(false);
			
			}
		});
		txtEdit.setBounds(518, 68, 89, 23);
		contentPane.add(txtEdit);
		
		txtSave = new JButton("Save");
		txtSave.setEnabled(false);
		txtSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				saveDets();
				txtEmail.setEnabled(false);
				txtEmail.setEditable(false);
				txtSecretQn.setEnabled(false);
				txtSecretQn.setEditable(false);
				txtSecretAns.setEnabled(false);
				txtSecretAns.setEditable(false);
				txta.setEnabled(false);
				txta.setEditable(false);
				
				txtSave.setEnabled(false);
				txtUser.setText("");
				txtEmail.setText("");
				txtSecretQn.setText("");
				txtSecretAns.setText("");
				txta.setText("");
				searchField_1.setText("");
				
				populatejTable();
				try {
					NewClient.getOutObjects().writeObject("UsersList");
					dataModel=((DefaultTableModel)ProcessingClient.getInObjects().readObject());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		txtSave.setBounds(518, 121, 89, 23);
		contentPane.add(txtSave);
		
		txtBan = new JButton("Ban");
		txtBan.setEnabled(false);
		txtBan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this ?")==0)
				{
					System.out.println("Yes");
				
				int row = table.getSelectedRow();
				txtBan.setEnabled(false);
				AdminPanelDAO.delete(txtUser.getText());
				populatejTable();
				
				txtUser.setText("");
				txtEmail.setText("");
				txtSecretQn.setText("");
				txtSecretAns.setText("");
				txta.setText("");
				searchField_1.setText("");
				
				try {
					NewClient.getOutObjects().writeObject("UsersList");
					dataModel = ((DefaultTableModel)ProcessingClient.getInObjects().readObject());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					} 
				}
				
			}
		});
		txtBan.setBounds(518, 164, 89, 23);
		contentPane.add(txtBan);
		
		JLabel lblUsername = new JLabel("Username :");
		lblUsername.setBounds(117, 61, 114, 14);
		contentPane.add(lblUsername);
		
		JLabel lblNewLabel = new JLabel("Email :");
		lblNewLabel.setBounds(125, 97, 106, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Secret Question : ");
		lblNewLabel_1.setBounds(106, 130, 110, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Secret Answer : ");
		lblNewLabel_2.setBounds(106, 156, 125, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Access Level :");
		lblNewLabel_3.setBounds(115, 195, 101, 14);
		contentPane.add(lblNewLabel_3);
		
		JButton txtBack = new JButton("Back");
		txtBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goBack(user);
			}
		});
		txtBack.setBounds(518, 214, 89, 23);
		contentPane.add(txtBack);

		
		
	}
	
	
	public void saveDets()
	{
		String Email = txtEmail.getText();
		
		String username = txtUser.getText();
		String SecretQn = txtSecretQn.getText();
		String SecretAns = txtSecretAns.getText();
		int alvl = Integer.parseInt(txta.getText());
		
		User fag = new User(username,Email,SecretQn,SecretAns,alvl);
		AdminPanelDAO.updateManually(fag);
		JOptionPane.showMessageDialog(null, "Saved");
	}
	
	
	private void goBack(User users)
	{
		MainMenuFrame frame = new MainMenuFrame(users);
		frame.setVisible(true);
		this.dispose();
	}
	
	
}
