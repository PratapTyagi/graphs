import java.util.*;
import java.io.*;

class Pair implements Comparable<Pair> {
	int data;
	String psf;
	int wt;
	public Pair(int d, String p, int w) {
		data = d;
		psf = p;
		wt = w;
	}
	public int compareTo(Pair a) {
		return this.wt - a.wt;
	}
}

class Edge {
	int source;
	int destination;
	int wt;
	public Edge(int s, int d, int w) {
		source = s;
		destination = d;
		wt = w;
	}
}

public class Dijkstra {
	private LinkedList<Edge>[] graph;
	@SuppressWarnings("unchecked")
	public Dijkstra (int v) {
		graph = new LinkedList[v];
		for(int i=0; i<v; i++)
			graph[i] = new LinkedList<Edge>();
	}
	
	public void addEdge(int source, int destination, int wt) {
		graph[source].add(new Edge(source, destination, wt));
	}

	public void dijkstra(int start, int v) {
		PriorityQueue<Pair> queue = new PriorityQueue<>();
		boolean[] isVisited = new boolean[v];
		
		queue.add(new Pair(start, start+"", 0));
					
		while(!queue.isEmpty()) {
			Pair p = queue.removeFirst();
			
			if(isVisited[p.data])	continue;
			isVisited[p.data] = true;
			
			System.out.println(p.data+" -> "+p.psf+" @ "+p.wt);

			for(Edge t: graph[p.data]) 
				if(!isVisited[t.data])
					queue.add(new Pair(t.data, p.psf+t.data, p.wt+t.wt));
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Enter vertex and edges respectively:");
		
		String[] s1 = br.readLine().split("\\s+");
		
		int v = Integer.parseInt(s1[0]);
		int e = Integer.parseInt(s1[1]);
		
		Dijkstra t = new Dijkstra(v);
		
		System.out.println("Enter source(vertex) -> destination(vertex) @ weight corresponding the edges ex: 0 1 for 0->1 edge");
		System.out.println("Enter your "+e+" edges");
		for(int i=0; i<e; i++) {
			String s2[] = br.readLine().split("\\s+");
			int source = Integer.parseInt(s2[0]);
			int destination = Integer.parseInt(s2[1]);
			int weight = Integer.parseInt(s2[2]);
			t.addEdge(source, destination, weight);
		}

		t.dijkstra(0, v);
}