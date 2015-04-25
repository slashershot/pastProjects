package ui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JCheckBox;

import Client.clientWorker;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class clientDownloader extends JFrame {

	private JPanel contentPane;
	private final JLabel lblSyafiqsUltimateDownloader = new JLabel("Syafiq's Ultimate Downloader");
	private final JLabel lblNewLabel = new JLabel("Video Name");
	private final static JTextField tbSelectedFile = new JTextField();
	static String fileName;
	static String filePath;
	


	private final JButton btnDownload = new JButton("Download");

	ObjectOutputStream oos;
	private final JLabel lblNewLabel_1 = new JLabel("Directory");
	private final static JTextField tbDirectory = new JTextField();
	public final JLabel lblStatus = new JLabel("Not Done");
	private final JButton btnBrowse = new JButton("Browse..");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					fileName = "output.flv";
					clientDownloader frame = new clientDownloader(fileName);
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
	public clientDownloader(String fileName) {
		tbSelectedFile.setEnabled(false);
		tbSelectedFile.setText(fileName);
		tbDirectory.setText("C:\\Users\\slashmobile\\Desktop\\Final Presentation Stuff\\DOWNLOAD");
		tbDirectory.setEditable(false);
		tbDirectory.setBounds(101, 107, 298, 27);
		tbDirectory.setColumns(10);
		tbSelectedFile.setHorizontalAlignment(SwingConstants.CENTER);
		tbSelectedFile.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tbSelectedFile.setBounds(101, 70, 152, 27);
		tbSelectedFile.setColumns(10);
		this.fileName = fileName;
		initGUI();
	}	
	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 592, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		lblSyafiqsUltimateDownloader.setHorizontalAlignment(SwingConstants.CENTER);
		lblSyafiqsUltimateDownloader.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblSyafiqsUltimateDownloader.setBounds(85, 11, 414, 47);

		contentPane.add(lblSyafiqsUltimateDownloader);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 69, 99, 27);

		contentPane.add(lblNewLabel);

		contentPane.add(tbSelectedFile);
		btnDownload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				download();
			}
		});
		btnDownload.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnDownload.setBounds(101, 212, 281, 38);

		contentPane.add(btnDownload);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(10, 107, 99, 27);

		contentPane.add(lblNewLabel_1);

		contentPane.add(tbDirectory);
		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				openFileChooser();
			}
		});
		btnBrowse.setBounds(411, 107, 99, 27);

		contentPane.add(btnBrowse);
		
		lblStatus.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblStatus.setBounds(111, 148, 263, 53);
		contentPane.add(lblStatus);
	}
	
	void download(){
		setFileName(tbSelectedFile.getText());
		setFilePath(tbDirectory.getText()+"\\");

		String instructions = "download";
		Thread worker =  new clientWorker(fileName, filePath, this, instructions);
		worker.start();
	}

	static void openFileChooser(){

		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

		int returnVal = chooser.showOpenDialog(null);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			setFilePath(chooser.getSelectedFile().getAbsolutePath());
		}
	
		tbDirectory.setText(filePath);
	}



	public static void setFileName(String fileName) {
		clientDownloader.fileName = fileName;
	}



	public static void setFilePath(String filePath) {
		clientDownloader.filePath = filePath;
	}
}
