import java.util.*;
import java.io.*;

class Pair implements Comparable<Pair> {
	int data;
	int av;
	int wt;
	public Pair(int d, int a, int w) {
		data = d;
		av = a;
		wt = w;
	}
}

class Edge {
	int source;
	int destination;
	String psf;
	public Edge(int s, int d, String p) {
		source = s;
		destination = d;
		psf = p;
	}
}

public class Prims {
	
	private LinkedList<Edge>[] graph;

	@SuppressWarnings("unchecked")
	public Prims(int v) {
		graph = new LinkedList[v];
		for(int i=0; i<v; i++)
			graph[i] = new LinkedList<Edge>();
	}		

	public void addEdge(int s, int d, int p) {
		graph[s].add(new Edge(s, d, p));
	}

	public void prims(int start, int v) {
		PriorityQueue<Pair> queue = new PriorityQueue<>();
		boolean[] isVisited = new boolean[v];

		queue.add(new Pair(start, -1, 0));

		while(!queue.isEmpty()) {
			Pair p = queue.poll();
		
			if(!isVisited[p.data])	continue;
			isVisited[p.data] = true;
			
			if(p.av != -1)
				System.out.println(p.data+" from "+p.av+" @ "+p.wt);

			for(Edge t: graph[p.data])
				if(!isVisited[t.data])
					queue.add(new Pair(t.data, p.data, t.wt));  
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Enter vertex and edges respectively:");
		
		String[] s1 = br.readLine().split("\\s+");
		
		int v = Integer.parseInt(s1[0]);
		int e = Integer.parseInt(s1[1]);
		
		Prims t = new Prims(v);
		
		System.out.println("Enter source(vertex) -> destination(vertex) @ weight corresponding the edges ex: 0 1 for 0->1 edge");
		System.out.println("Enter your "+e+" no. of edges");
		for(int i=0; i<e; i++) {
			String s2[] = br.readLine().split("\\s+");
			int source = Integer.parseInt(s2[0]);
			int destination = Integer.parseInt(s2[1]);
			int weight = Integer.parseInt(s2[2]);
			t.addEdge(source, destination, weight);
		}	

		t.prims(0, v);
	}
}