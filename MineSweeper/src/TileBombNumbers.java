import javax.swing.JLabel;

public class TileBombNumbers {
	private int numberInTile;
	public JLabel numbLabel;
	
	public int getNumberHere()
	{
		return numberInTile;
	}
	public void setNumber(int n)
	{
		numberInTile = n;
		numbLabel = new JLabel(Integer.toString(numberInTile));
		numbLabel.setSize(48, 48);
	}

}
