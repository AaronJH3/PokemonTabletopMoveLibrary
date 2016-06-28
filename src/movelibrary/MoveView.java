package movelibrary;
import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * The MoveView class displays the four application panels and updates based on user input.
 * @author	Brian Tate 
 *
 */
@SuppressWarnings("serial")
public class MoveView extends JFrame
{
	/**
	 * The panels of our application
	 * panel1 - Contains user input text field
	 * panel2 - Contains move description textfield that will update based on user's query
	 * panel3 - Contains simulation button as well as simulation textfield that acts as though the attack is being used
	 * panel4 - Contains animated gif of pokemon
	 */
	private JPanel panel1, panel2, panel3, panel4;
	
	/**
	 * Located in panel1 - the user inputs the move that they want to find
	 */
	private JTextField inputfield;
	
	/**
	 * Located in panel2 and panel3 - These fields are simply used as output buffers
	 */
	private JTextArea  outputfield, simfield;
	
	/**
	 * Button that will simulate the move that the user has located
	 */
	private JButton button;
	
	/**
	 * Constructs the move view Frame
	 */
	public MoveView()
	{
		super("MoveSearcher");
		
		panel1 = new JPanel();
		JLabel aquestion = new JLabel("Enter a Move");
		inputfield = new JTextField(20);
		panel1.add(aquestion);
		panel1.add(inputfield);
		panel1.setAlignmentX(CENTER_ALIGNMENT);
		//add(aquestion, BorderLayout.NORTH);
		
		panel1.setBorder(BorderFactory.createLineBorder(Color.PINK, 5 , true));
		add(panel1,BorderLayout.NORTH);
		
		panel3 = new JPanel();
		button = new JButton("Simulate");
		simfield = new JTextArea(10,10);
		panel3.setLayout(new BoxLayout(panel3, BoxLayout.PAGE_AXIS));
		panel3.add(button,BorderLayout.SOUTH);
		
		panel3.add(simfield);
		JScrollPane scroll2 = new JScrollPane(simfield);
		simfield.setEditable(false);
		simfield.getBorder();
		simfield.setLineWrap(true);
		simfield.setWrapStyleWord(true);
		simfield.setDragEnabled(true);
		simfield.setText("Click the button above to simulate the move that you have successfully found in our library");
		panel3.add(scroll2);
		panel3.setBorder(BorderFactory.createLineBorder(Color.PINK, 5 , true));
		add(panel3, BorderLayout.SOUTH);
		
		panel2 = new JPanel();
		outputfield = new JTextArea(20,30);
		outputfield.setEditable(false);
		outputfield.getBorder();
		outputfield.setLineWrap(true);
		outputfield.setWrapStyleWord(true);
		outputfield.setDragEnabled(true);
		outputfield.setText("Example of moves to enter or test: \"quick attack\", \"tackle\",\"slash\", \"rest\", \"sky uppercut\"");
		JScrollPane scroll1 = new JScrollPane(outputfield);
		panel2.add(scroll1);
		panel2.setBorder(BorderFactory.createLineBorder(Color.PINK, 5 , true));
		add(panel2,BorderLayout.CENTER);
		
		
		java.net.URL url = this.getToolkit().getClass().getResource("/653.gif");
		
		ImageIcon ic = new ImageIcon(url); 
		panel4 = new PokemonGif(ic.getImage(), 300, 250);
        panel4.setLayout(new GridLayout(5,10,10,10));
        panel4.setBorder(new EmptyBorder(20,20,20,20));

		
		
		add(panel4, BorderLayout.WEST);
		panel4.setBorder(BorderFactory.createLineBorder(Color.PINK, 5 , true));
		
		
	}
	
	/**
	 * Updates the textfield found in panel2 with information in the MoveLib
	 * @param move - The move that we will update the output field with
	 */
	public void updateOutfield(Move move)
	{
		outputfield.setText(move.toString());
	}
	
	/**
	 * Updates the outfield with an "error" message, this is only used when the user requests for a move that ISN'T in our library
	 * @param s A string with an Error tag attached to it
	 */
	public void updateOutfield(String s) //this is only used for error cases
	{
		outputfield.setText(s);
	}
	
	/**
	 * Updates the simfield located in panel3 with a simulation of the move already found
	 * @param s a list of move actions that are done upon looking up the 'rules' of the move
	 */
	public void updateSimfield(String[] s)
	{
		String output = "";
		for(int i = 0; i < s.length; i++)
		{
			if(s[i] == null)
			 continue;
				
			output += s[i] + "\n";
		}
		
		simfield.setText(output);
		
	}
	
	/**
	 * Registers a relationship of listeners on various components in our application
	 * @param c The controller we wish to implement on our various components.
	 */
	public void register(MoveController c)
	{
		inputfield.addKeyListener(c);
		button.addActionListener(c);
	}
	
}
