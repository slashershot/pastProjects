package ui;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;




import entity.User;
//import org.eclipse.wb.swing.FocusTraversalOnArray;
import Client.clientWorker;


public class clientUploader extends JFrame {

	private JPanel contentPane;
	private final JLabel lblClient = new JLabel("Syafiq's Ultimate Uploader");
	private final static JButton btnBrowse = new JButton("Browse..");
	private final JButton btnSend = new JButton("Send");

	private static String filePath = null;
	private static String fileName = null;
	private final JLabel lblVideoName = new JLabel("Selected Video");
	public static JTextField tbVideoName = new JTextField();

	static ObjectInputStream ois;
	static ObjectOutputStream oos;
	static FileInputStream fis;
	private final JLabel lblNewLabel = new JLabel("Save Name As");
	private final static JTextField tbChangeName = new JTextField();
	private final static JCheckBox chkboxChangeName = new JCheckBox("Save As");
	private final JLabel lblNewLabel_1 = new JLabel("Progress");
	public final JProgressBar pbStatus = new JProgressBar();

	static clientUploader clientGUI1;
	static boolean fileLoaded=false;

	static Socket socket = null;



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					clientUploader frame = new clientUploader(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public clientUploader(User user) {
		this.clientGUI1 = this;
		tbChangeName.setText("<not loaded>");
		tbChangeName.setEnabled(false);
		tbChangeName.setBounds(162, 103, 111, 22);
		tbChangeName.setColumns(10);
		tbVideoName.setText("Choose Video...");
		tbVideoName.setEnabled(false);
		tbVideoName.setBounds(162, 69, 111, 20);
		tbVideoName.setColumns(10);
		initGUI(user);

	}
	private void initGUI(final User user) {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter(){
			   public void windowClosing(WindowEvent e){
					VideoSelectFrame frame1;
					try {
						frame1 = new VideoSelectFrame(user);
		            	frame1.setVisible(true);
		            	dispose();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			   }
			}); 
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		lblClient.setHorizontalAlignment(SwingConstants.CENTER);
		lblClient.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblClient.setBounds(10, 11, 414, 43);

		contentPane.add(lblClient);
		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				openFileChooser();
			}
		});
		btnBrowse.setBounds(283, 68, 89, 23);



		contentPane.add(btnBrowse);
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				upload();
			}
		});
		btnSend.setBounds(108, 168, 193, 31);

		contentPane.add(btnSend);
		lblVideoName.setHorizontalAlignment(SwingConstants.CENTER);
		lblVideoName.setBounds(10, 68, 142, 23);

		contentPane.add(lblVideoName);

		contentPane.add(tbVideoName);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 102, 142, 23);

		contentPane.add(lblNewLabel);

		contentPane.add(tbChangeName);
		chkboxChangeName.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				changeName();
			}
		});
		chkboxChangeName.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				//				changeName();
			}
		});
		chkboxChangeName.setBounds(283, 102, 141, 23);

		contentPane.add(chkboxChangeName);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(20, 136, 141, 23);

		contentPane.add(lblNewLabel_1);
		pbStatus.setBounds(162, 136, 111, 23);

		contentPane.add(pbStatus);
		//		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{btnNewButton, chkboxChangeName, btnSend}));
	}

	static void openFileChooser(){

		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"Video Formats", "flv", "mov");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(null);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			setFilePath(chooser.getSelectedFile().getAbsolutePath());
			setFileName(chooser.getSelectedFile().getName());

		}
		fileLoaded = true;
		tbVideoName.setText(fileName);
		tbChangeName.setText(fileName);

	}

	static void upload(){
		System.out.println(filePath);
		fileName = tbChangeName.getText();


		String instructions = "upload";
		Thread worker =  new clientWorker(filePath, fileName, clientGUI1,instructions);
		worker.start();
	}

	static void changeName(){
		if(!fileLoaded){

			Object[] options = { "OK", "SHOW ME" };
			int i =JOptionPane.showOptionDialog(null, "Click OK to continue", "Warning",
					JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
					null, options, options[0]);

			if(i == 0){
				chkboxChangeName.setSelected(false);
				btnBrowse.requestFocus();
			}else{
				openFileChooser();
				chkboxChangeName.setSelected(false);
				
			}
		}else{
			if(chkboxChangeName.isSelected()){
				tbChangeName.setEnabled(true);

			}else{
				if(fileName != null){
					tbChangeName.setText(fileName);
					tbChangeName.setEnabled(false);
				}else {
					tbChangeName.setText("<not loaded>");
					tbChangeName.setEnabled(false);
				}
			}
		}
	}

	public static void setFilePath(String filepath) {
		clientUploader.filePath = filepath;
	}
	public static void setFileName(String filename){
		clientUploader.fileName = filename;
	}
}
