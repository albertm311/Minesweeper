import javax.swing.JFrame;

//import Minesweeper.Counter;

public class Main {
	public static void main(String[] args) {
		JFrame myFrame = new JFrame("Color Grid");
		myFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		myFrame.setLocation(400, 150);
		myFrame.setSize(400, 400);

		MyPanel myPanel = new MyPanel();
		myFrame.add(myPanel);

		MyMouseAdapter myMouseAdapter = new MyMouseAdapter();
		myFrame.addMouseListener(myMouseAdapter);

		Counter counter = new Counter();
		myFrame.add(counter);
		
		myFrame.setVisible(true);


	}
}