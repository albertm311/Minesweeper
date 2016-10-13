import java.awt.Color;
import java.awt.Component;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MyMouseAdapter extends MouseAdapter {
	private Random generator = new Random();
	public static int flags = 10;

	public void mousePressed(MouseEvent e) {
		Component c = e.getComponent();
		while (!(c instanceof JFrame)) {
			c = c.getParent();
			if (c == null) {
				return;
			}
		}


		JFrame myFrame = (JFrame) c;
		MyPanel myPanel = (MyPanel) myFrame.getContentPane().getComponent(0);
		Insets myInsets = myFrame.getInsets();
		int x1 = myInsets.left;
		int y1 = myInsets.top;
		e.translatePoint(-x1, -y1);
		int x = e.getX();
		int y = e.getY();
		myPanel.x = x;
		myPanel.y = y;
		myPanel.mouseDownGridX = myPanel.getGridX(x, y);
		myPanel.mouseDownGridY = myPanel.getGridY(x, y);
		myPanel.repaint();

		switch (e.getButton()) {
		case 1: // Left mouse button

		case 3: // Right mouse button

			break;
		default: // Some other button (2 = Middle mouse button, etc.)
			// Do nothing
			break;
		}
	}

	public void mouseReleased(MouseEvent e) {
		Component c = e.getComponent();
		while (!(c instanceof JFrame)) {
			c = c.getParent();
			if (c == null) {
				return;
			}
		}
		JFrame myFrame = (JFrame) c;
		MyPanel myPanel = (MyPanel) myFrame.getContentPane().getComponent(0); 
		Insets myInsets = myFrame.getInsets();

		int x1 = myInsets.left;
		int y1 = myInsets.top;
		e.translatePoint(-x1, -y1);
		int x = e.getX();
		int y = e.getY();
		myPanel.x = x;
		myPanel.y = y;
		int gridX = myPanel.getGridX(x, y);
		int gridY = myPanel.getGridY(x, y);

		switch (e.getButton()) {
		case 1: // Left mouse button


			if ((myPanel.mouseDownGridX == -1) || (myPanel.mouseDownGridY == -1) || (myPanel.mouseDownGridX > 8)
					|| (myPanel.mouseDownGridY > 8)) {
				// Had pressed outside
				// Do nothing
			} else {
				if ((gridX == -1) || (gridY == -1)) {
					// Is releasing outside
					// Do nothing
				} else {
					if ((myPanel.mouseDownGridX != gridX) || (myPanel.mouseDownGridY != gridY)) {
						// Released the mouse button on a different cell where
						// it was pressed
						// Do nothing
					} else {
						// Released the mouse button on the same cell where it
						// was pressed
						if ((gridX > 8) && (gridY > 8)) {
						} else {
							if(myPanel.mineField[myPanel.mouseDownGridX][myPanel.mouseDownGridY].equals(Color.RED)){
								break;
							}else{
								if(myPanel.isMine(myPanel.mouseDownGridX, myPanel.mouseDownGridY)){

									Color newColor = Color.BLACK;

									for(int i = 0; i < 9; i ++){
										for( int j = 0; j < 9; j++){

											if(myPanel.minesOnField[i][j] == true){
												myPanel.mineField[i][j] = newColor;
												myPanel.repaint();	
											}
										}
									}


									JOptionPane.showMessageDialog(myFrame, "BOOM!...Game Over!");
									ActionListener();
									break;
								}
							}

							Color newColor = Color.GRAY;

							myPanel.mineField[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = newColor;
							myPanel.repaint();
						}
					}
				}
			}
			myPanel.repaint();
			break;
		case 3: // Right mouse button

			if ((myPanel.mouseDownGridX == -1) || (myPanel.mouseDownGridY == -1) || (myPanel.mouseDownGridX > 8)
					|| (myPanel.mouseDownGridY > 8)) {
				// Had pressed outside
				// Do nothing
			} else {
				if ((gridX == -1) || (gridY == -1)) {
					// Is releasing outside
					// Do nothing
				} else {
					if ((myPanel.mouseDownGridX != gridX) || (myPanel.mouseDownGridY != gridY)) {
						// Released the mouse button on a different cell where
						// it was pressed
						// Do nothing
					} else {
						// Released the mouse button on the same cell where it
						// was pressed
						if ((gridX > 8) && (gridY > 8)) {
						} else {

							if (myPanel.mineField[myPanel.mouseDownGridX][myPanel.mouseDownGridY].equals(Color.WHITE)){
								if (flags > 0){
									Color newColor = Color.RED;
									flags--;
									myPanel.mineField[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = newColor;
									myPanel.repaint();
								}
							} else if (myPanel.mineField[myPanel.mouseDownGridX][myPanel.mouseDownGridY].equals(Color.GRAY)){
								break;
							} else if(myPanel.mineField[myPanel.mouseDownGridX][myPanel.mouseDownGridY].equals(Color.RED)){
								flags ++;
								Color newColor = Color.WHITE;
								myPanel.mineField[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = newColor;
								myPanel.repaint();
							}
						}
					}
				}
			}
			myPanel.repaint();
			break;
		default: // Some other button (2 = Middle mouse button, etc.)
			// Do nothing
			break;
		}
	}
	private void ActionListener() {
		//System.exit(0);
		Main.masterFrame.dispose();
		MyMouseAdapter.flags = 10;
		TimerCounter.seconds = 0;
		Main.main(null);
		//Main.masterFrame.dispose();
		;

	}

	public static String getFlags(){
		if(flags <= 0){
			return "0";
		}
		return "" + flags;

	}
}