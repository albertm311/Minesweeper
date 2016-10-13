import java.awt.Color;
import java.awt.Component;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class MyMouseAdapter extends MouseAdapter {
	public static int flags = 14;
	//public static JLabel numbers;

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
									for(int i = 0; i < 9 ; i++){
										for (int j = 0; j < 9 ; j++){
											if (myPanel.minesOnField[i][j] == true){
												myPanel.mineField[i][j] = newColor;
												myPanel.repaint();
											}
										}
									}

									final JOptionPane pane = new JOptionPane("      Game Over!");
									final JDialog d = pane.createDialog("      BOOM!");
									d.setLocation(myFrame.getHeight() + 330, myFrame.getWidth()-100);
									d.setSize(100, 100);
									d.setVisible(true);
									try {
										ActionListener();
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									break;
								}
							}
							Color newColor = Color.GRAY;
							myPanel.mineField[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = newColor;
							checkBombs(myPanel, myPanel.mouseDownGridX, myPanel.mouseDownGridY);
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
	private void ActionListener() throws IOException {
		//System.exit(0);
		Main.masterFrame =  Main.reinitialize();
		Main.masterFrame.setVisible(true);
	}

	public static String getFlags(){
		if(flags <= 0){
			return "0";
		}
		return "" + flags;
	}

	public void checkBombs (MyPanel panel, int x, int y){

		int counter = 0;

		if((x -1 >= 0 && x -1 < 9)
				&&  (y >= 0 && y < 9) 
				&& panel.isMine(x -1, y)){
			counter ++;
		} 
		if((x -1 >= 0 && x -1 < 9)
				&&  (y-1 >= 0 && y -1 < 9) 
				&& panel.isMine(x -1, y -1 )){
			counter ++;
		} 

		if((x  >= 0 && x < 9)
				&&  (y-1 >= 0 && y-1 < 9) 
				&& panel.isMine(x, y-1)){
			counter ++;
		} 
		if((x +1 >= 0 && x + 1< 9)
				&&  (y >= 0 && y < 9) 
				&& panel.isMine(x + 1, y)){
			counter ++;
		} 
		if((x + 1 >= 0 && x + 1 < 9)
				&&  (y + 1>= 0 && y + 1 < 9) 
				&& panel.isMine(x + 1, y + 1)){
			counter ++;
		} 
		if((x >= 0 && x < 9)
				&&  (y + 1>= 0 && y + 1< 9) 
				&& panel.isMine(x, y + 1)){
			counter ++;
		} 
		if((x -1 >= 0 && x -1 < 9)
				&&  (y + 1 >= 0 && y + 1 < 9) 
				&& panel.isMine(x -1, y + 1)){
			counter ++;
		} 
		if((x + 1 >= 0 && x + 1 < 9)
				&&  (y - 1 >= 0 && y - 1 < 9) 
				&& panel.isMine(x + 1, y - 1)){
			counter ++;
		} 

		//Set The Number on Tile
		if (counter > 0) {

			Color newColor =  Color.BLUE;
			panel.mineField[x][y] = newColor;




			JLabel numbers = new JLabel("" + counter);
			//numbers.setText("" + counter);
			numbers.setLocation(x,y);
			numbers.setVisible(true);
			panel.add(numbers);
			panel.repaint();
			System.out.println(counter);

		}else {
			//If Not Mine Found
			if((x - 1 >= 0 && x - 1 < 9)
					&&  (y >= 0 && y < 9) 
					&& !panel.mineField[x - 1][y].equals(Color.GRAY) 
					&& !panel.mineField[x - 1][y].equals(Color.RED) 
					&& !panel.isMine(x - 1, y)){
				Color newColor =  Color.GRAY;
				panel.mineField[x - 1][y] = newColor;
				checkBombs(panel, x - 1, y);	
			} 
			if((x - 1 >= 0 && x - 1 < 9)
					&&  (y -1 >= 0 && y -1 < 9) 
					&& !panel.mineField[x - 1][y -1].equals(Color.GRAY) 
					&& !panel.mineField[x - 1][y -1].equals(Color.RED) 
					&& !panel.isMine(x - 1, y -1)){
				Color newColor =  Color.GRAY;
				panel.mineField[x - 1][y -1] = newColor;
				checkBombs(panel, x - 1, y -1);
			} 
			if((x - 1 >= 0 && x - 1 < 9)
					&&  (y + 1 >= 0 && y + 1 < 9) 
					&& !panel.mineField[x - 1][y + 1].equals(Color.GRAY) 
					&& !panel.mineField[x - 1][y + 1].equals(Color.RED) 
					&& !panel.isMine(x - 1, y + 1)){
				Color newColor =  Color.GRAY;
				panel.mineField[x - 1][y + 1] = newColor;
				checkBombs(panel, x - 1, y + 1);
			} 
			if((x >= 0 && x < 9)
					&&  (y -1 >= 0 && y -1 < 9) 
					&& !panel.mineField[x][y -1].equals(Color.GRAY) 
					&& !panel.mineField[x][y -1].equals(Color.RED) 
					&& !panel.isMine(x, y -1)){
				Color newColor =  Color.GRAY;
				panel.mineField[x][y -1] = newColor;
				checkBombs(panel, x, y -1);
			} 
			if((x >= 0 && x < 9)
					&&  (y + 1 >= 0 && y + 1 < 9) 
					&& !panel.mineField[x][y + 1].equals(Color.GRAY) 
					&& !panel.mineField[x][y + 1].equals(Color.RED) 
					&& !panel.isMine(x, y + 1)){
				Color newColor =  Color.GRAY;
				panel.mineField[x][y + 1] = newColor;
				checkBombs(panel, x, y + 1);
			} 
			if((x + 1 >= 0 && x + 1 < 9)
					&&  (y >= 0 && y < 9) 
					&& !panel.mineField[x + 1][y].equals(Color.GRAY) 
					&& !panel.mineField[x + 1][y].equals(Color.RED) 
					&& !panel.isMine(x + 1, y)){
				Color newColor =  Color.GRAY;
				panel.mineField[x + 1][y] = newColor;
				checkBombs(panel, x + 1, y);
			} 
			if((x + 1 >= 0 && x + 1 < 9)
					&&  (y - 1>= 0 && y -1 < 9) 
					&& !panel.mineField[x + 1][y -1].equals(Color.GRAY) 
					&& !panel.mineField[x + 1][y - 1].equals(Color.RED) 
					&& !panel.isMine(x + 1, y - 1)){
				Color newColor =  Color.GRAY;
				panel.mineField[x + 1][y - 1] = newColor;
				checkBombs(panel, x + 1, y - 1);
			} 
			if((x + 1 >= 0 && x + 1 < 9)
					&&  (y + 1 >= 0 && y + 1 < 9) 
					&& !panel.mineField[x + 1][y + 1].equals(Color.GRAY) 
					&& !panel.mineField[x + 1][y + 1].equals(Color.RED) 
					&& !panel.isMine(x + 1, y + 1)){
				Color newColor =  Color.GRAY;
				panel.mineField[x + 1][y + 1] = newColor;
				checkBombs(panel, x + 1, y + 1);
			} 
		}
	}
}


