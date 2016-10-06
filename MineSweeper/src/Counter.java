
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class Counter extends JLabel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4417929666170287931L;


	private JButton reset;
	private int actualMines = 10;
	private  String DESCRIPTION;

	public Counter()
	{
		super();
		reset();
	}
	public void setMineLabel(String mines){
		DESCRIPTION = "Mines: ";
		setHorizontalTextPosition(JLabel.LEFT);
	}

	public int getNumOfMines() {
		return this.actualMines;
	}

	/**
	 * Update the text label with the current counter value
	 */
	private void update()
	{
		setText(DESCRIPTION + Integer.toString(this.actualMines));
		setHorizontalTextPosition(JLabel.LEFT);
	}


	/**
	 * Increments the counter 
	 */
	public void decrement()
	{
		
		this.actualMines--;
		update();
	}

	/**
	 * Resets the counter of mines to zero 
	 */
	public void reset()
	{
		this.actualMines = 10;
		update();
	}

	public void resetButton(){
		Icon p = new ImageIcon(getClass().getResource("blue.png"));
		reset = new JButton("Reset" + p);
		reset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				reset.setText("Reset");
			}

		});
		add(reset);
	}
}
