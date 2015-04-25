package ui;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Client.NewClient;
import Client.ProcessingClient;

import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;

import entity.User;
import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;
    public class PlayVideoFrame extends JFrame  {
    	
        public static EmbeddedMediaPlayerComponent mediaPlayerComponent;
        private String status = "Playing";
        PlayerControlsPanel panel;
        static JFrame frame= null;
        public static void main(String[] args) {
        	SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                	 new PlayVideoFrame(null);
                }
            });
        }

        public PlayVideoFrame(final User user) {
        	try {
				ProcessingClient.getOutObjects().writeObject("Playing");
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
            frame = new JFrame("vlcj Tutorial");
        	NativeLibrary.addSearchPath(
                    RuntimeUtil.getLibVlcLibraryName(),  "C:/Users/Nigel/Desktop/Resume Items/Past Projects.(Archive)(backup)/Final Presentation Project 1.2/java lib/NEE"
                );
                Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
            mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
            panel = new PlayerControlsPanel(mediaPlayerComponent.getMediaPlayer());

            frame.setContentPane(panel);
            frame.getContentPane().add(mediaPlayerComponent);
            frame.setLocation(100, 100);
            frame.setSize(1280, 768);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
            mediaPlayerComponent.getMediaPlayer().playMedia("rtsp://@localhost:8554/test");
            panel.btnClose.addActionListener(new ActionListener(){ public void actionPerformed(ActionEvent e){
            	
            	try {
                	ProcessingClient.getOutObjects().writeObject("Break");
					VideoSelectFrame frame1 = new VideoSelectFrame(user);
	            	frame1.setVisible(true);
	            	frame.setVisible(false);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            	mediaPlayerComponent.getMediaPlayer().pause();
            }
            });
        
        }
        
    } 