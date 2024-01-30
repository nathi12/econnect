package acsse.csc3a.graph;
import java.util.ArrayList;

import com.jwetherell.algorithms.data_structures.Graph;
import com.jwetherell.algorithms.data_structures.Graph.TYPE;


/**
 * @author NS MOKWANA 220147785
 * 
 * Creates the graph ADT for the problem
 */
public class GraphCreation {
	
	Graph<Node> graph = null;
	static public ArrayList<Graph.Edge<Node>> edges =null ;
	static public ArrayList<Graph.Vertex<Node>> nodes =null;
		
	public GraphCreation() {
		//creating the list
		edges = new ArrayList<Graph.Edge<Node>>();
		nodes= new ArrayList<Graph.Vertex<Node>>();
		
		//calling the void function that will create the graph 
		CreateNodesAndEdges();
		
	}
	
	/**
	 * Create nodes and edges that will be pre-existing 
	 */
	public void CreateNodesAndEdges() {
		/*
		 * Creating Family Nodes
		 * */
		Family fam1 = new Family(3,ETYPEOFHELP.FINANCIAL,"Mokwana",4,8);
		Graph.Vertex<Node> fam1Node = new Graph.Vertex<Node>(fam1);
		
		Family fam2 = new Family(4,ETYPEOFHELP.FOOD,"Maphosa",6,2);
		Graph.Vertex<Node> fam2Node = new Graph.Vertex<Node>(fam2);
		
		Family fam3 = new Family(5,ETYPEOFHELP.SHELTER,"Zane",8,7);
		Graph.Vertex<Node> fam3Node = new Graph.Vertex<Node>(fam3);
		
		Family fam4 = new Family(5,ETYPEOFHELP.SHELTER,"Tim",9,9);
		Graph.Vertex<Node> fam4Node = new Graph.Vertex<Node>(fam4);
		
		Family fam5 = new Family(5,ETYPEOFHELP.SHELTER,"Luke",1,1);
		Graph.Vertex<Node> fam5Node = new Graph.Vertex<Node>(fam5);
		
		Family fam6 = new Family(5,ETYPEOFHELP.SHELTER,"Mark",2,5);
		Graph.Vertex<Node> fam6Node = new Graph.Vertex<Node>(fam6);
		
		/*
		 * Creating Sponsor Nodes
		 * */
	    Sponsor Absa = new Sponsor("Absa",ETYPEOFHELP.FINANCIAL,3,3);
		Graph.Vertex<Node> SponsorAbsa = new Graph.Vertex<Node>(Absa);
		
		Sponsor Nedback = new Sponsor("Nedback",ETYPEOFHELP.FINANCIAL,6,9);
		Graph.Vertex<Node> SponsorNedback = new Graph.Vertex<Node>(Nedback);
		
		Sponsor SAHelp = new Sponsor("SAHelp",ETYPEOFHELP.SHELTER,6,6);
		Graph.Vertex<Node> SponsorSAHelp = new Graph.Vertex<Node>(SAHelp);
		
		Sponsor UNICEF = new Sponsor("UNICEF",ETYPEOFHELP.FOOD,9,4);
		Graph.Vertex<Node> SponsorUNICEF = new Graph.Vertex<Node>(UNICEF);
		
		/*
		 * create a collection of the nodes add to it to the graph class
		 * */
		nodes.add(fam1Node);
		nodes.add(fam2Node);
		nodes.add(fam3Node);
		nodes.add(fam4Node);
		nodes.add(fam5Node);
		nodes.add(fam6Node);
		nodes.add(SponsorAbsa);
		nodes.add(SponsorSAHelp);
		nodes.add(SponsorUNICEF);
		nodes.add(SponsorNedback);
		
		/*
		 * Creates the edges
		 * */
		Graph.Edge<Node> edge1 = new Graph.Edge<Node>(1,SponsorAbsa,fam2Node);
		Graph.Edge<Node> edge2 = new Graph.Edge<Node>(2,SponsorSAHelp,fam2Node);
		Graph.Edge<Node> edge3 = new Graph.Edge<Node>(3,SponsorSAHelp,fam1Node);
		Graph.Edge<Node> edge4 = new Graph.Edge<Node>(4,SponsorSAHelp,fam3Node);
		Graph.Edge<Node> edge5 = new Graph.Edge<Node>(6,SponsorUNICEF,fam3Node);
		Graph.Edge<Node> edge6 = new Graph.Edge<Node>(3,SponsorNedback,fam3Node);
		Graph.Edge<Node> edge7 = new Graph.Edge<Node>(5,SponsorNedback,fam4Node);
		Graph.Edge<Node> edge8 = new Graph.Edge<Node>(5,SponsorAbsa,fam6Node);
		Graph.Edge<Node> edge9 = new Graph.Edge<Node>(5,SponsorAbsa,fam5Node);
		Graph.Edge<Node> edge10 = new Graph.Edge<Node>(5,SponsorAbsa,SponsorSAHelp);
		Graph.Edge<Node> edge11 = new Graph.Edge<Node>(5,SponsorUNICEF,fam2Node);
		Graph.Edge<Node> edge12 = new Graph.Edge<Node>(5,SponsorUNICEF,fam4Node);
		Graph.Edge<Node> edge13 = new Graph.Edge<Node>(5,fam6Node,fam1Node);
		
		/*
		 * Collection of edges
		 * */
		edges.add(edge1);
		edges.add(edge2);
		edges.add(edge3);
		edges.add(edge4);
		edges.add(edge5);
		edges.add(edge6);
		edges.add(edge7);
		edges.add(edge8);
		edges.add(edge9);
		edges.add(edge10);
		edges.add(edge11);
		edges.add(edge12);
		edges.add(edge13);
		
		
		
		/*
		 * connecting the nodes to edges
		 * 
		 * Every Node needs to know which edges it is connected to
		 * */
		fam1Node.addEdge(edge3);
		fam2Node.addEdge(edge1);
		fam2Node.addEdge(edge2);
		fam3Node.addEdge(edge4);
		fam3Node.addEdge(edge5);
		SponsorAbsa.addEdge(edge1);
		SponsorUNICEF.addEdge(edge5);
		SponsorSAHelp.addEdge(edge2);
		SponsorSAHelp.addEdge(edge3);
		SponsorSAHelp.addEdge(edge4);
		SponsorNedback.addEdge(edge6);
		SponsorNedback.addEdge(edge7);
		SponsorAbsa.addEdge(edge8);
		SponsorAbsa.addEdge(edge9);
		fam4Node.addEdge(edge7);
		fam3Node.addEdge(edge6);
		fam6Node.addEdge(edge8);
		fam5Node.addEdge(edge9);
		SponsorAbsa.addEdge(edge10);
		SponsorUNICEF.addEdge(edge11);
		SponsorUNICEF.addEdge(edge12);
		fam6Node.addEdge(edge13);
		
		
		
		
		/*
		 * Create a Directed Graph that has a collection of edges and nodes
		 * */
		graph=new Graph<Node>(TYPE.UNDIRECTED,nodes,edges);
	}
	
	/**
	 * @return list of Nodes
	 */
	public static ArrayList<Graph.Vertex<Node>> getNodes(){
		return nodes;
	}
	
	/**
	 * @return list of edges
	 */
	public static ArrayList<Graph.Edge<Node>> getEdges(){
		return edges;
	}
}
