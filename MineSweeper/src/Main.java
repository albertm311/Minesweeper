import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;

public class Main {
	static JLabel lblTime;
	private static JLabel lblFlags;
	static JFrame masterFrame;
	public static AudioInputStream audioStream;
	public static Clip audioClip;
	public static File audioFile;
	
	

	public static void main(String[] args) throws IOException {
		masterFrame = initialize();
		masterFrame.setVisible(true);
		
		
		
		
		
		
		
		
	}

	private static JFrame initialize() throws IOException{
		
		//allows music to be played while playing
	    audioFile = new File("AudioFiles/song.wav");
	    try {
	        audioStream = AudioSystem.getAudioInputStream(audioFile);
	    } catch (UnsupportedAudioFileException e) {
	        e.printStackTrace();
	    }
	    
	    AudioFormat format = audioStream.getFormat();
	    DataLine.Info info = new DataLine.Info(Clip.class, format);
	    
	    try {
	        audioClip = (Clip) AudioSystem.getLine(info);
	        audioClip.open(audioStream);
	        audioClip.start();
	        audioClip.loop(Clip.LOOP_CONTINUOUSLY);
	    } catch (LineUnavailableException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	    
	    

		JFrame myFrame = new JFrame("MineSweeper");
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setLocation(400, 150);
		myFrame.setSize(465, 400);

		MyPanel myPanel = new MyPanel();
		myFrame.getContentPane().add(myPanel);
		myPanel.setLayout(null);

		MyMouseAdapter myMouseAdapter = new MyMouseAdapter();
		MyMouseAdapter.getFlags();
		myFrame.addMouseListener(myMouseAdapter);

		lblFlags = new JLabel();		
		lblFlags.setForeground(new Color(100, 149, 237));
		lblFlags.setBounds(313, 175, 126, 15);
		lblFlags.setFont(new Font("Goudy Stout", Font.PLAIN, 12));
		lblFlags.setHorizontalAlignment(SwingConstants.LEFT);
		myPanel.add(lblFlags);

		TimerCounter tasknew = new TimerCounter();
		Timer timer = new Timer();		
		lblTime = new JLabel();
		lblTime.setForeground(new Color(100, 149, 237));
		timer.schedule(new TimerTask(){
			@Override
			public void run() {
				tasknew.doStuff();
				lblTime.setText("Time: " + TimerCounter.getTime());
			}
		},100, 1000); 
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				lblFlags.setText("Flags: " + MyMouseAdapter.getFlags() );	
			}
		}, 100,100);
		JButton btnReset = new JButton();
		btnReset.setToolTipText("RESET");

		try {
			Image img = ImageIO.read(new File("Images/blue.png")); 
			btnReset.setIcon(new ImageIcon(img));
		} catch (IOException ex) {}


		btnReset.setBounds(313, 54, 110, 110);
		btnReset.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnReset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (e.getSource() == btnReset) {
					MyMouseAdapter.flags = 10;
					
					timer.cancel();
					TimerCounter.seconds = 0;
					masterFrame.dispose();
					audioClip.stop();
					try {
						masterFrame = initialize();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					masterFrame.setVisible(true);
				}
			}
		});
		myPanel.add(btnReset);

		lblTime.setBounds(313, 28, 136, 15);
		lblTime.setHorizontalAlignment(SwingConstants.LEFT);
		lblTime.setFont(new Font("Goudy Stout", Font.PLAIN, 12));
		myPanel.add(lblTime);	
		
		return myFrame;
	}
	
}



