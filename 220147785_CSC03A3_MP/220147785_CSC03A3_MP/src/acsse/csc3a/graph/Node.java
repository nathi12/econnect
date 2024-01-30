package acsse.csc3a.graph;

public abstract class Node implements Comparable<Node>{
	public String name;
	ETYPEOFHELP typeOfhelp;
	int x,y ;
	
	public Node(String name,ETYPEOFHELP typeOfhelp,int x ,int y) {
		this.name =name;
		this.typeOfhelp=typeOfhelp;
		this.x=x;
		this.y=y;
	}
	
	/**
	 * @return the typeOfhelp
	 */
	public ETYPEOFHELP getTypeOfhelp() {
		return typeOfhelp;
	}

	/**
	 * @param typeOfhelp the typeOfhelp to set
	 */
	public void setTypeOfhelp(ETYPEOFHELP typeOfhelp) {
		this.typeOfhelp = typeOfhelp;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
}
