package movelibrary; 

import javax.swing.JFrame;

/**
 * Initializes our application with controllers a model and a view
 * @author Aaron Huerta
 *
 */
public class MoveLib{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Sets up our application
	 * @param args command line arguements - none are needed.
	 * @return 
	 */
	public static void main( String args[]) {
        MoveModel model = new MoveModel();
        MoveView view = new MoveView();
	    MoveController controller = new MoveController(model, view);
        
        view.register(controller);

        view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        view.setSize(425,555);
        view.setResizable(false);
        view.setVisible(true);
        view.pack();
        
        
    }
}
