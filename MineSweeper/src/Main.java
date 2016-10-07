import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Main {
	static JLabel lblTime;
	static JFrame masterFrame;

	public static void main(String[] args) {
		masterFrame = initialize();
		masterFrame.setVisible(true);
	}

	private static JFrame initialize(){

		JFrame myFrame = new JFrame("Color Grid");
		myFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		myFrame.setLocation(400, 150);
		myFrame.setSize(600, 400);

		MyPanel myPanel = new MyPanel();
		myFrame.getContentPane().add(myPanel);
		myPanel.setLayout(null);

		JLabel lblFlags = new JLabel("Flags: ");
		lblFlags.setBounds(28, 8, 38, 15);
		lblFlags.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblFlags.setHorizontalAlignment(SwingConstants.LEFT);
		myPanel.add(lblFlags);

		TimerCounter tasknew = new TimerCounter();
		Timer timer = new Timer();		
		lblTime = new JLabel();
		timer.schedule(new TimerTask(){
			@Override
			public void run() {
				tasknew.doStuff();
				lblTime.setText("Time: " + TimerCounter.getTime());
			}
		},100, 1000); 

		JButton btnReset = new JButton();

		try {
			Image img = ImageIO.read(new File("Images/bobo.png")); 
			btnReset.setIcon(new ImageIcon(img));
		} catch (IOException ex) {}


		btnReset.setBounds(313, 25, 94, 94);
		btnReset.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnReset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (e.getSource() == btnReset) {
					timer.cancel();
					TimerCounter.seconds = 0;
					masterFrame.dispose();
					masterFrame = initialize();
					masterFrame.setVisible(true);
				}
			}
		});
		myPanel.add(btnReset);

		lblTime.setBounds(227, 8, 110, 15);
		lblTime.setHorizontalAlignment(SwingConstants.LEFT);
		lblTime.setFont(new Font("Tahoma", Font.BOLD, 12));
		myPanel.add(lblTime);

		MyMouseAdapter myMouseAdapter = new MyMouseAdapter();
		myFrame.addMouseListener(myMouseAdapter);

		return myFrame;
	}
}



