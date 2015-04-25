package Server;

import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.headless.HeadlessMediaPlayer;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;

public class videoStreaming {
	static MediaPlayerFactory mediaPlayerFactory;
	static HeadlessMediaPlayer mediaPlayer;
	public static void run(String name){
    	NativeLibrary.addSearchPath(
                RuntimeUtil.getLibVlcLibraryName(), "C:/Users/Nigel/Desktop/Resume Items/Past Projects.(Archive)(backup)/Final Presentation Project 1.2/java lib/NEE"
            );
            Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
		String media = name;
		String options = formatRtspStream("localhost",8554,"test");
		System.out.println("Streaming '" + media + "' to '" + options + "'");

        mediaPlayerFactory = new MediaPlayerFactory();
        mediaPlayer = mediaPlayerFactory.newHeadlessMediaPlayer();
        mediaPlayer.playMedia(media,
            options,":sout-keep"
        );
	}	
	private static String formatRtspStream(String serverAddress, int serverPort, String id) {
    StringBuilder sb = new StringBuilder(60);
    sb.append(":sout=#rtp{sdp=rtsp://@");
    sb.append(serverAddress);
    sb.append(':');
    sb.append(serverPort);
    sb.append('/');
    sb.append(id);
    sb.append("}");
    return sb.toString();
}
	
	public static void pause(){
		mediaPlayer.pause();
	}
}
