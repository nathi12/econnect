package acsse.csc3a.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.*;

import com.jwetherell.algorithms.data_structures.Graph;
import com.jwetherell.algorithms.data_structures.Graph.Vertex;

/**
 * @author NATHI
 * 
 * Calculate the  closest sponsor to a family
 */

public class ShortestPath {

	public ShortestPath() {

		//calculate the  distance
		CalcDistance();
	}

	/**
	 * calculate distances of nodes connected by the edges and
	 * store that as a cost
	 */
	public void CalcDistance() {	
		ArrayList<Graph.Edge<Node>> edges = GraphCreation.getEdges();	 
		for(Graph.Edge<Node> edge :edges) {
			int x1 =edge.getFromVertex().getValue().getX();
			int y1 =edge.getFromVertex().getValue().getY();


			int x2=edge.getToVertex().getValue().getX();
			int y2=edge.getToVertex().getValue().getY();

			int distance =calculateDistance(x1,y1,x2,y2);
			edge.setCost(distance);

		}
	}

	/**
	 * Calculates distance between two coordinates
	 * 
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @return
	 */
	public static  int calculateDistance(int x1, int y1, int x2, int y2) {
		int distance = (int) Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
		return distance;
	}


	/*
	 * implementing the algorithm
	 * */
	public static double getDistance(Vertex<Node> source, Vertex<Node> destination) {
		for (Graph.Edge<Node> edge : GraphCreation.edges) {
			if (edge.getFromVertex() == source && edge.getToVertex() == destination) {
				int x1 =edge.getFromVertex().getValue().getX();
				int y1 =edge.getFromVertex().getValue().getY();


				int x2=edge.getToVertex().getValue().getX();
				int y2=edge.getToVertex().getValue().getY();

				int distance =calculateDistance(x1,y1,x2,y2);
				return distance;
			}
		}
		return Double.POSITIVE_INFINITY;
	}

	/**
	 * Dijkstra Algorithm for finding the closest path
	 * @param start
	 * @param end
	 * @return List<Vertex<Node>>
	 */
	public static List<Vertex<Node>> findShortestPath(Vertex<Node> start, Vertex<Node> end) {
		Map<Vertex<Node>, Double> distances = new HashMap<>();
		Map<Vertex<Node>, Vertex<Node>> previous = new HashMap<>();
		Set<Vertex<Node>> visited = new HashSet<>();

		for (Vertex<Node> node : GraphCreation.nodes) {
			distances.put(node, Double.POSITIVE_INFINITY);
		}
		distances.put(start, 0.0);

		PriorityQueue<Vertex<Node>> queue = new PriorityQueue<>(Comparator.comparingDouble(distances::get));
		queue.add(start);

		while (!queue.isEmpty()) {
			Vertex<Node> current = queue.poll();
			visited.add(current);

			if (current == end) {
				break;
			}

			for (Vertex<Node> neighbor : GraphCreation.nodes) {
				if (!visited.contains(neighbor)) {
					double distance = getDistance(current, neighbor);
					double totalDistance = distances.get(current) + distance;

					if (totalDistance < distances.get(neighbor)) {
						distances.put(neighbor, totalDistance);
						previous.put(neighbor, current);
						queue.add(neighbor);
					}
				}
			}
		}

		List<Vertex<Node>> shortestPath = new ArrayList<>();
		Vertex<Node> current = end;

		while (current != null) {
			shortestPath.add(0, current);
			current = previous.get(current);
		}

		return shortestPath;
	}
}
