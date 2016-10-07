import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;

//import Minesweeper.Counter;

public class Main {
	public static void main(String[] args) {
		JFrame myFrame = new JFrame("Color Grid");
		myFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		myFrame.setLocation(400, 150);
		myFrame.setSize(400, 400);

		MyPanel myPanel = new MyPanel();
		myFrame.getContentPane().add(myPanel);
		
		JLabel lblFlagsLeft = new JLabel("Flags Left: ");
		myPanel.add(lblFlagsLeft);
		
		JButton btnReset = new JButton("RESET");
		myPanel.add(btnReset);
		
		JLabel lblTime = new JLabel("Time: ");
		myPanel.add(lblTime);

		MyMouseAdapter myMouseAdapter = new MyMouseAdapter();
		myFrame.addMouseListener(myMouseAdapter);

//		Counter counter = new Counter();
//		myFrame.add(counter);
		
		myFrame.setVisible(true);


	}
}