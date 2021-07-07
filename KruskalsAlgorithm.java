import java.util.*;
import java.io.*;

class Edge implements Comparable<Edge> {
	int source;
	int destination;
	int weight;
	public Edge(int s, int d, int wt) {
		source = s;
		destination = d;
		weight = wt;
	}

	public int compareTo(Edge a) {
		return this.weight - a.weight;
	}
}

class Subset {
	int parent;
	int size;
}

public class KruskalsAlgorithm {
	int V;
	LinkedList<Edge>[] graph;
	PriorityQueue<Edge> allEdges;

	@SuppressWarnings("unchecked")
	public Test(int v) {
		V = v;
		graph = new LinkedList[v];
		allEdges = new PriorityQueue<Edge>();
		for(int i=0; i<v; i++) 
			graph[i] = new LinkedList<Edge>();
	}

	public void addEdge(int s, int d, int wt) {
		Edge edge = new Edge(s, d, wt);
		graph[s].add(edge);
		allEdges.add(edge);
	}

	public void makeSet(Subset[] s) {
		for(int i=0; i<V; i++) {
			s[i] = new Subset();
			s[i].parent = i;
			s[i].size = 1;
		}
	}

	public int find(Subset[] s, int v) {
		if(s[v].parent != v)
			s[v].parent = find(s, s[v].parent);
		return s[v].parent;
	}

	public void union(Subset[] s, int a, int b) {
		int x = find(s, a);
		int y = find(s, b);

		if(x != y) {
			if(s[x].size < s[y].size) {
				s[x].parent = y;
				s[x].size += s[y].size;
			}
			else if(s[x].size >= s[y].size) {
				s[y].parent = x;
				s[y].size += s[x].size;
			}
		}
	}

	public int minimumSpanningTree() {
		Subset[] s = new Subset[V];
		makeSet(s);
		
		int answer = 0;		

		while(!allEdges.isEmpty()) {
			Edge e = allEdges.remove();
			int x = find(s, e.source);
			int y = find(s, e.destination);

			if(x == y)	continue;
			else {			
				answer += e.weight;
				union(s, x, y);
			}
		}
		return answer;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("Enter no. vertices and edges");
		String s[] = br.readLine().split("\\s++");
		int v = Integer.parseInt(s[0]);
		int e = Integer.parseInt(s[1]);		
		Test t = new Test(v);

		System.out.println("Enter "+ e +" no. of edges like: (source -> destination -> weight)");	
		for(int i=0; i<e; i++)  {
			s = br.readLine().split("\\s++");
			t.addEdge(Integer.parseInt(s[0]), Integer.parseInt(s[1]), Integer.parseInt(s[2]));
		}
		
		System.out.println(t.minimumSpanningTree());
	}

}