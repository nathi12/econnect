package acsse.csc3a.graph;


public class Family extends Node implements Comparable<Node>{
	
	int numMembers; //number of family members
	ETYPEOFHELP typeOfhelp; // the type of help the need
	boolean isHelped ; //is the family got help yet
	String Surname ; //surname of the family
	
	public Family(int numMembers,ETYPEOFHELP typeOfhelp,String Surname,int x,int y) {
		super(Surname,typeOfhelp, x, y);
		this.numMembers=numMembers;

	}
	
	@Override
	public int compareTo(Node o) {
		return 0;
	}

	/**
	 * @return the numMembers
	 */
	public int getNumMembers() {
		return numMembers;
	}

	/**
	 * @param numMembers the numMembers to set
	 */
	public void setNumMembers(int numMembers) {
		this.numMembers = numMembers;
	}

	/**
	 * @return the isHelped
	 */
	public boolean isHelped() {
		return isHelped;
	}

	/**
	 * @param isHelped the isHelped to set
	 */
	public void setHelped(boolean isHelped) {
		this.isHelped = isHelped;
	}	
}
