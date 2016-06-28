package movelibrary;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;

/**
 * Controls the view of our application
 * @author Aaron Huerta
 *
 */
public class MoveController implements KeyListener, ActionListener
{
	
	/**
	 * The model of our application
	 */
	private MoveModel model;
	
	/**
	 * The view of our application
	 */
	private MoveView view;
	
	/**
	 * curMove is the one that the model currently occupies - it is set initially to error to avoid searching for moves that aren't there
	 */
	private Move curMove = new Move("error");
	
	/**
	 * Move controllor requires the relationship between the model and the view - this is so we can update our application smoothly
	 * @param m The model of application
	 * @param v The view of application
	 */
	public MoveController(MoveModel m, MoveView v)
	{
		model = m;
		view = v;
	}


	@Override
	/**
	 * If an enter key is enterred - we search for our move and update it to our textfield
	 */
	public void keyPressed(KeyEvent e)
	{
		if( e.getKeyChar() == '\n' )
		{
			JTextField focus = (JTextField)e.getComponent();
			curMove = model.srchMoveList(focus.getText());
			focus.setText("");
			
			if(curMove.getName().equalsIgnoreCase("error"))
			{
				view.updateOutfield("Move not in library, please try again :D");
				return;
			}
			
			view.updateOutfield(curMove);
		}
		//System.out.println("ah well"); - debug checker leave here incase other may need to test
	}


	@Override
	/**
	 * This doesn't do anything
	 */
	public void keyReleased(KeyEvent e) 
	{
		
		
	}


	@Override
	/**
	 * This doesn't do anything
	 */
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	/**
	 * If a button is pushed - (in this case we only have one) - we will update the simulation textfield with information based on our curMove
	 */
	public void actionPerformed(ActionEvent e) 
	{
		String cmd = e.getActionCommand();
		String dmgOutput[];
		
		if( !curMove.getName().equalsIgnoreCase("error") && cmd.equalsIgnoreCase("simulate"))
		{
			dmgOutput = curMove.simDamage();
			view.updateSimfield(dmgOutput);
		}
		
		
		
	}


}
