package acsse.csc3a.gui;
import acsse.csc3a.graph.ShortestPath;

import java.util.ArrayList;

import com.jwetherell.algorithms.data_structures.Graph;

import acsse.csc3a.graph.Family;
import acsse.csc3a.graph.GraphCreation;
import acsse.csc3a.graph.Node;
import acsse.csc3a.graph.Sponsor;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class GraphCanvas extends Canvas {

	static GraphicsContext gc=null;
	public final int WIDTH= 500 ;
	public final int HEIGHT = 500 ; 

	GraphCreation graph =null ;

	public GraphCanvas() {
		super(700,700);
		gc = getGraphicsContext2D();
		
		graph = new GraphCreation(); //create the graph, then connecting the graph with UI
		
		//run the closest path
		ShortestPath path = new ShortestPath();
	}

	/**
	 * draw function of the canvas
	 */
	public void draw() {
		redrawCanvas();
	}

	/**
	 * helper class to draw canvas contains redraw
	 */
	private void redrawCanvas() {
		gc.clearRect(0, 0, WIDTH, HEIGHT);

		for(int x =0;x<15;x++) {
			for (int y =0;y<15;y++) {
				gc.strokeRect(x*50, y*50, 50, 50);
			}
		}

		drawGraph();
	}

	/**
	 * function that will draw the edges and the graph
	 */
	public void drawGraph() {
		//draw nodes
		ArrayList<Graph.Vertex<Node>> nodes = GraphCreation.getNodes();

		for(Graph.Vertex<Node> node :nodes) {
			if(node.getValue() instanceof Family) {
				DN(Color.GREEN,node.getValue().getX(),node.getValue().getY(),node.getValue().getName());
				System.out.println("Family "+node.getValue().getX()+":"+node.getValue().getY()+":"+node.getValue().getName());
			}else if(node.getValue() instanceof Sponsor){
				DN(Color.YELLOW,node.getValue().getX(),node.getValue().getY(),node.getValue().getName());
				System.out.println("Sponsor "+node.getValue().getX()+":"+node.getValue().getY()+":"+node.getValue().getName());
			}
		}


		//draw egdes that connect the nodes
		ArrayList<Graph.Edge<Node>> edges = GraphCreation.getEdges();	 
		for(Graph.Edge<Node> edge :edges) {
			int x1 =edge.getFromVertex().getValue().getX();
			int y1 =edge.getFromVertex().getValue().getY();


			int x2=edge.getToVertex().getValue().getX();
			int y2=edge.getToVertex().getValue().getY();
			System.out.println("{"+x1+","+y1+"}->{"+x2+","+y2+"}"+"Cost :"+edge.getCost());
			drawLine(x1,y1,x2,y2,Integer.toString(edge.getCost()));
		}
	}

	/**
	 * Function that draw a node
	 * @param p
	 * @param x
	 * @param y
	 * @param Text
	 * @param gc
	 */
	public static void DN(Paint p,int x,int y,String Text) {
		gc.setFill(p);
		gc.fillRect(x*50,y*50, 50, 50);
		gc.setFill(Color.BLACK);
		gc.fillText(Text,x*50,y*50+20);
	}
	
	public static void RemoveNode(int x,int y) {
		gc.setFill(Color.WHITE);
		gc.fillRect(x*50,y*50, 50, 50);	
	}

	/**
	 * function for drawing a line
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 */
	public static void drawLine(double x1, double y1, double x2, double y2,String text) {
		// Set the stroke color to red and the line width to 3
		int val =50 ;
		x1=x1*val;
		x2=x2*val;
		y1=y1*val;
		y2=y2*val;
		gc.setStroke(Color.GREY);
		gc.setLineWidth(3);

		// Draw a line from (x1, y1) to (x2, y2)
		gc.strokeLine(x1, y1, x2, y2);
		
		// Calculate the midpoint of the line
	    double midX = (x1 + x2) / 2;
	    double midY = (y1 + y2) / 2;
	    
		//adding text at the middle of the line
		gc.fillText(text, midX, midY);
	}
}
