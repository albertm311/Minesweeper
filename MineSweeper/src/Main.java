import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;

public class Main {
	public static void main(String[] args) {
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
		
		JButton btnReset = new JButton("RESET");
		btnReset.setBounds(113, 0, 94, 23);
		btnReset.setFont(new Font("Tahoma", Font.BOLD, 12));
		myPanel.add(btnReset);
		
		
		JLabel lblTime = new JLabel("Time: " + myPanel.getTime()  + " seconds");
		lblTime.setBounds(235, 9, 174, 15);
		lblTime.setHorizontalAlignment(SwingConstants.LEFT);
		lblTime.setFont(new Font("Tahoma", Font.BOLD, 12));
		myPanel.add(lblTime);
		
		
		
		
		
		
		MyMouseAdapter myMouseAdapter = new MyMouseAdapter();
		myFrame.addMouseListener(myMouseAdapter);
		
		myFrame.setVisible(true);
	}

}