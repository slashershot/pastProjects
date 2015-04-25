package Server;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.SocketException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Server.ServerUi;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class serverGUI extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	final static JLabel lblNewLabel = new JLabel("Server Control Center");
	private final JLabel lblNewLabel_1 = new JLabel("Server Status:");
	final static JButton btnActivate = new JButton("Activate");

	static boolean commOnline = false;
	static boolean ftpOnline = false;
	static boolean procOnline = false;

	final static JButton btnDeactivate = new JButton("Deactivate");
	private final JLabel label_1 = new JLabel("");
	final static JLabel lblStatus = new JLabel("Offline"); 
	final static JTextArea taLogger = new JTextArea();
	private final JButton btnNewButton = new JButton("Open Root Folder");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) throws java.net.SocketException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					serverGUI frame = new serverGUI();
					frame.setVisible(true);
				}catch(java.net.SocketException e){
					System.exit(0);
				}
					catch (Exception e) {
				
					System.exit(0);
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public serverGUI() throws java.net.SocketException {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				ServerUi.exit();
			}
		});
		initGUI();

	}
	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 628, 354);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 591, 42);

		contentPane.add(lblNewLabel);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(177, 80, 123, 25);

		contentPane.add(lblNewLabel_1);
		btnActivate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					getOnline();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SocketException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnActivate.setBounds(155, 150, 113, 23);

		contentPane.add(btnActivate);
		btnDeactivate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					try {
						getOffline();
					} catch (SocketException e) {


					}
				} catch (InterruptedException e) {

				}
			}
		});
		btnDeactivate.setEnabled(false);
		btnDeactivate.setBounds(293, 150, 113, 23);



		contentPane.add(btnDeactivate);
		label_1.setBounds(209, 100, 17, 25);

		contentPane.add(label_1);
		lblStatus.setForeground(Color.RED);
		lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
		lblStatus.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblStatus.setBounds(346, 80, 113, 25);

		contentPane.add(lblStatus);
		taLogger.setBounds(92, 186, 405, 77);

		contentPane.add(taLogger);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnNewButton.setBounds(469, 279, 132, 25);

		contentPane.add(btnNewButton);
	}



	void getOnline() throws InvocationTargetException, InterruptedException, SocketException, IOException{
		
		Runnable commServer = new commServer(this);
		Thread comm =  new Thread(commServer, "comm");
		comm.start();
		Runnable procServer = new ServerUi();
		Thread proc = new Thread(procServer, "ServerUi");
		proc.start();
	}



	void getOffline() throws InterruptedException, SocketException{
		commServer.stopThread();
		ftpServer.stopThread();


		serverGUI.lblStatus.setText("Offline");
		serverGUI.lblStatus.setForeground(Color.RED);
		serverGUI.btnActivate.setEnabled(true);
		serverGUI.btnDeactivate.setEnabled(false);
		serverGUI.taLogger.setText("");
	}
}
