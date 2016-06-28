package movelibrary;
import java.util.Scanner;


/**
 * The model of move allows updates to the view - this model also implements a search to find queried moves
 * @author Aaron Huerta
 *
 */
public class MoveModel 
{
	/**
	 * The move that the model currently contains details for
	 */
	private Move move;
	
	/**
	 * This constructor serves as a placeholder to manipulate the type of move.
	 */
	public MoveModel()
	{
		
	}
	
	/**
	 * Searches through a move list to bring back the requested move and all of its details
	 * @param query The move that the user requested to be found
	 * @return The Move that user asked for
	 */
	public Move srchMoveList(String query)
	{
		Scanner in = null;
		Move errmov = new Move("error"); //gets rid of bug
		
	    in = new Scanner(this.getClass().getResourceAsStream("/test.txt")); //contains move library
	    
		
		String searchedline = "";
		
		while( in.hasNextLine() ) //looking for matching request
		{
			searchedline = in.nextLine();
			//System.out.println(s);
			
			if( searchedline.equalsIgnoreCase(query))
			{
//				System.out.println("This is working");
				
				this.move = new Move(query);
				
				//move.setName(s);
				move.setType(in.nextLine() + "\n"); 
				move.setDamage(in.nextLine()+ "\n");
				move.setFrequency(in.nextLine()+ "\n");
				move.setAccuracy(in.nextLine()+ "\n");
				move.setTypeofatk(in.nextLine()+ "\n");
				move.setRange(in.nextLine()+ "\n");
				move.setFieldeffect(in.nextLine()+ "\n\n");
				move.setDescription(in.nextLine()+ "\n\n");
				move.setContest( in.nextLine()+ "\n");

				//Display output with 
				//System.out.println(move.toString());
				
				in.close();
				return move;
			}
			
			
		}
		
		in.close();
		return errmov; //didn't find anything returning a blank move with error as its name
		
	}
	
}
