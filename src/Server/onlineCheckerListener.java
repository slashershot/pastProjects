package Server;

import java.awt.Color;

public class onlineCheckerListener implements Runnable{
	boolean check1 = false;

	@Override
	public void run() {
		//		while(true){
		//		System.out.println("Listener Running.");
//		serverGUI.taLogger.setText("Listener is running.");
		if(serverGUI.ftpOnline==true){
			serverGUI.taLogger.append("FTP: Online");
			check1 = true;	
		}else{
			serverGUI.taLogger.append("\nFTP: Offline");
			check1=false;
		}

		if(serverGUI.commOnline==true){
			serverGUI.taLogger.append("\nComm: Online");
			check1= true;
		}else{
			serverGUI.taLogger.append("\nComm: Offline");
			check1=false;
		}

		if(serverGUI.procOnline==true){
			serverGUI.taLogger.append("\nLogin: Online");
			check1= true;
		}else{
			serverGUI.taLogger.append("\nLogin: Offline");
			check1=false;
		}
		
		if(check1==true){
			serverGUI.lblStatus.setText("Online");
			serverGUI.lblStatus.setForeground(Color.GREEN);
			serverGUI.btnActivate.setEnabled(false);
			serverGUI.btnDeactivate.setEnabled(true);
			serverGUI.taLogger.append("\nSTATUS: ONLINE");

		}else{
			serverGUI.lblStatus.setText("Offline");
			serverGUI.lblStatus.setForeground(Color.RED);
			serverGUI.btnActivate.setEnabled(true);
			serverGUI.btnDeactivate.setEnabled(false);
			serverGUI.taLogger.append("\nSTATUS: OFFLINE");

			//			}
		}
	}

}
