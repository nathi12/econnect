package acsse.csc3a.graph;

public class Sponsor extends Node implements Comparable<Node>{
	String Name;
	ETYPEOFHELP help; //type of help they offer
	int NumFamiliesHelped;
	
	public Sponsor(String Name,ETYPEOFHELP help,int x, int y) {
		super(Name,help, x, y);
	}
	
	/**
	 * updates the NumFamiliesHelped
	 */
	public void numFamilies() {
		NumFamiliesHelped++;
	}

	/**
	 * @return the numFamiliesHelped
	 */
	public int getNumFamiliesHelped() {
		return NumFamiliesHelped;
	}

	/**
	 * @param numFamiliesHelped the numFamiliesHelped to set
	 */
	public void setNumFamiliesHelped(int numFamiliesHelped) {
		NumFamiliesHelped = numFamiliesHelped;
	}

	@Override
	public int compareTo(Node o) {
		return 0;
	}
}
