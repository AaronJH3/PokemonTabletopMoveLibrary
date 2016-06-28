package movelibrary;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


//Database based on http://pokemontabletop.wikidot.com/dragon
/**
 * Move data type gives the description and rules of a move - whether it needs accuracy or not; and whether or not it adds any special effects to another target
 * @author Aaron Huerta
 *
 */
public class Move 
{
	//Private data types are listed in the order they appear in the library.
	private String name;
	private String type;
	private String damage;
	private String frequency; //Frequency a pokemon could make an attack per turn
					          // Battle (once per battle) EOT (every other turn) 
	private String accuracy; //Must roll higher than this number to land
	private String typeofatk;
	private String range;
	private String fieldeffect;
	private String description; //Description is just a long string with an ending /n
	private String contest; //The effect of the move in a contest
	
	/**
	 * Move constructor
	 * @param n The name of the move
	 */
	public Move(String n)
	{
		name = n;
	}

	/**
	 * Returns the name of the move
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * Changes the name of the move - In this program it is only used for error handling
	 * @param name The new name of the move
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Every move has a type connected to it Fire,Water,Electricity,Dragon, or Ghost 
	 * @return The moves type
	 */
	public String getType() {
		return type;
	}


	/**
	 * Changes the moves type
	 * @param type The new type of the move
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Some moves inflict a calculation of damage - usually in the format xdx+x - (the x's are integers)
	 * @return the damage calculation of the move
	 */
	public String getDamage() {
		return damage;
	}

	/**
	 * Changes the damage of the move
	 * @param damage the new damage calculation of the move
	 */
	public void setDamage(String damage) {
		this.damage = damage;
	}

	/**
	 * Every move can only be used a certain amount of times - EOT means every other turn - Battle means once per battle - Daily mean once per entire day
	 * @return the frequency of the move
	 */
	public String getFrequency() {
		return frequency;
	}

	/**
	 * Changes the frequency of the move
	 * @param frequency the new frequency of the move
	 */
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	/**
	 * Every move requires a accuracy roll (done with a 1d20 dice (a dice with 20 faces) - if you roll higher than the move's accuracy, that is considered a hit
	 * @return moves accuracy
	 */
	public String getAccuracy() {
		return accuracy;
	}


	/**
	 * Changes the accuracy of the move
	 * @param the new accuracy of the move
	 */
	public void setAccuracy(String accuracy) {
		this.accuracy = accuracy;
	}

	/**
	 * A move can be two types - Special Attack or Attack, this determines what defense we will be attacking against
	 * @return 
	 */
	public String getTypeofatk() {
		return typeofatk;
	}


	public void setTypeofatk(String typeofatk) {
		this.typeofatk = typeofatk;
	}


	public String getRange() {
		return range;
	}


	public void setRange(String range) {
		this.range = range;
	}


	public String getFieldeffect() {
		return fieldeffect;
	}


	public void setFieldeffect(String fieldeffect) {
		this.fieldeffect = fieldeffect;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getContest() {
		return contest;
	}


	public void setContest(String contest) {
		this.contest = contest;
	}
	
	public String[] simDamage()
	{
		Random numGen = new Random();
		
		String[] actions = new String[15];
		
		String buf1,buf2,buf3 = "";
		
		int numOfDice = 0; // amount of time we need to roll
		int diceType = 0; //d10, d12, d6 and so on
		int addition = 0; //Some moves require addition on top of the dice rolls ex. 1d4+20 - the 20 being our example
		
		actions[0] = "No damage inflicted or may have special effects (automatic hit?), read description "; //initial value of move action list will have this default message
		
		int totalDamage = 0;
		
		
		//Accuracy calculations below -------------------------------------------
		if(!(accuracy.equalsIgnoreCase("none\n"))) //moves with NO accuracy tend to have no damage (There may be exceptions to this; but I haven't found any).
		{
			int toHit = Integer.parseInt(accuracy.substring(0, accuracy.length()-1)); //how high we have to roll to hit
			actions[0] = damage; //First element will always be the original description of the damage
			actions[1] = "Accuracy: " + accuracy.substring(0, accuracy.length()-1); // trimming off /n
			
			actions[2] = String.valueOf((numGen.nextInt(1000+1)%20)+1); // 20 sided die are used for accuracy attacks
		
			if( Integer.parseInt(actions[2]) > toHit )
			{
				actions[2] = "Rolling for accuracy: " + actions[2];
				actions[2] = actions[2] + "-- A HIT!";
			}
			else
			{
				actions[2] = "Rolling for accuracy: " + actions[2];
				actions[2] = actions[2] + "-- A MISS!";
			}	
			//System.out.println(actions[i+1]);
		}
		
		
		Pattern p1 = Pattern.compile("(\\d+)d(\\d+)\\+*(\\d*)\n");
		Pattern p2 = Pattern.compile("(\\d+)\\+(\\d)d(\\d+)\n");
		Matcher m1 = p1.matcher(damage);
		Matcher m2 = p2.matcher(damage);
		
		
		if(m1.matches() || m2.matches())
		{
			int i = 0; //iterator
			if(m1.matches())
			{
				buf1 = m1.group(1);
				buf2 = m1.group(2);
				buf3 = m1.group(3);
			}
			else
			{
				buf1 = m2.group(2);
				buf2 = m2.group(3);
				buf3 = m2.group(1);
			}
			
			numOfDice = Integer.parseInt(buf1);
			diceType = Integer.parseInt(buf2);
			if(buf3.equalsIgnoreCase(""))
			{
				addition = 0;
			}
			else
			{
				addition = Integer.parseInt(buf3);
			}
			
			//debug code
			//System.out.println(numOfDice);
			//System.out.println(diceType);
			//System.out.println(addition);
			
			for(i = 0; i < numOfDice; i++)
			{
				actions[3+i] = String.valueOf((numGen.nextInt(1000+1)%diceType)+1); 
				totalDamage +=  Integer.parseInt(actions[3+i]) ;
				actions[3+i] = "Roll "+ (1+i) + ": " + actions[3+i]; 
				System.out.println(actions[3+i]);
			}
			
			totalDamage = totalDamage + addition;
			actions[3+i] = " + " + addition;
			
			actions[4+i] = String.valueOf(totalDamage);
			System.out.println(totalDamage);
			
			return actions;
		}
		
		//System.out.println("Miss");
		//System.out.println(dice);
		return actions;
	}

	public String toString()
	{
		String s = ""; 
		
		s += "\n" + name + " ";
		s += "\n Type: " + type + " ";
		s += "Damage: " + damage + " ";
		s += "Frequency: " + frequency + " ";
		s += "Accuracy: " + accuracy + " ";
		if( typeofatk.equalsIgnoreCase("attack\n") )
		{
			typeofatk = "Vs Defense ";
		}
		else
		{
			typeofatk = "Vs Special Defense "; 
		}
		s += typeofatk + " ";
		s += "Range: " + range + " ";
		s += "Field Details: " + fieldeffect + " ";
		s += "Description/Rules: " + description + " ";
		s += "Contest Rules: " + contest + " ";
		return s;
	}


	

}
