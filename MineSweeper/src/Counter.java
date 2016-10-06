import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

public class Counter implements ActionListener 
{

	private JLabel bombsLeft;
	private JLabel timeRun = new JLabel(getTime());
	private int flagLabelCounter;
	private int actualTime= 0;


	public Counter(int  numberOfBombs)
	{
		flagLabelCounter=  numberOfBombs;
		timeRun = new JLabel(getTime());
		bombsLeft = new JLabel(Integer.toString(flagLabelCounter));
	}
	public JLabel getBombsLeft()
	{
		bombsLeft.setFont(new Font(null, Font.BOLD, 30));
		bombsLeft.setBackground(Color.black);
		bombsLeft.setForeground(Color.red);
		return bombsLeft;
	}

	public void setFlagsLabel(int increaseORDecrease)
	{
		flagLabelCounter -=  increaseORDecrease;
		bombsLeft.setText(Integer.toString(flagLabelCounter));
	}

	public JLabel timerFormat()
	{
		timeRun.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
		timeRun.setBackground(Color.black);
		timeRun.setForeground(Color.MAGENTA);
		return timeRun;
	}

	public String getTime()
	{	
		if(actualTime < 10)
			return "00" + actualTime;
		else if(actualTime < 100)
			return "0" + actualTime;
		else
			return "" + actualTime;
	}

	//Counts the seconds and sets the time to the timer.
	public void actionPerformed(ActionEvent arg0) 
	{	
		actualTime++;
		timeRun.setText(getTime());
	}
}

//-------------------------------------------------------------------

/*import javax.swing.JLabel;

public class Counter extends JLabel
{

	private static final long serialVersionUID = 1L;

	// data fields
	private int actualTime = 0;
	private  String DESCRIPTION;

	public Counter()
	{
		super();
		reset();
	}
	public void setDifficultyModeLabel(String difficultyMode){
		DESCRIPTION = "Turns Taken: ";
		setHorizontalTextPosition(JLabel.LEFT);
	}

	public int getNumOfTurns(){
		return this.actualTime;
		}

 *//**
 * Update the text label with the current counter value
 *//*
	private void update()
	{
		setText(DESCRIPTION + Integer.toString(this.actualTime));
		setHorizontalTextPosition(JLabel.LEFT);
	}

  *//**
  * Default constructor, starts counter at 0
  *//*


   *//**
   * Increments the counter and updates the text label
   *//*
	public void increment()
	{
		this.actualTime++;
		update();
	}

    *//**
    * Resets the counter to zero and updates the text label
    *//*
	public void reset()
	{
		this.actualTime = 0;
		update();
	}
}*/
