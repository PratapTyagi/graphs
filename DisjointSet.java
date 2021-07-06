import java.util.*;
import java.io.*;

class Edge {
	int source;
	int destination;
	public Edge(int s, int d) {
		source = s;
		destination = d;
	}
}

class Subset {
	int parent;
	int size;
}

public class DisjointSet {
	int V;	
	LinkedList<Edge>[] graph;
	ArrayList<Edge> allEdges = new ArrayList<>();
	
	@SuppressWarnings("unchecked")
	public DisjointSet(int v) {
		V = v;	
		graph = new LinkedList[v];
		for(int i=0; i<v; i++)
			graph[i] = new LinkedList<Edge>();			
	}

	void addEdge(int s, int d) {
		Edge edge = new Edge(s, d);
		graph[s].add(edge);
		allEdges.add(edge);
	}

	void makeSet(Subset[] s) {
		for(int i=0; i<V; i++) {
			s[i] = new Subset();
			s[i].parent = i;
			s[i].size = 1;
		}	
	}

	int find(Subset[] subSet, int v) {
		if(v != subSet[v].parent)	
			subSet[v].parent = find(subSet, subSet[v].parent);
		return subSet[v].parent;
	}

	void union(Subset[] subSet, int a, int b) {
		int x = find(subSet, a);
		int y = find(subSet, b);
		
		if(x != y) {
			if(subSet[x].size > subSet[y].size) {
				subSet[y].parent = x;
				subSet[y].size += subSet[x].size;	
			}
			else if(subSet[y].size > subSet[x].size) {
				subSet[x].parent = y;
				subSet[x].size += subSet[y].size;	
			}
			else {
				subSet[x].parent = y;
				subSet[x].size += subSet[y].size;
			}
		}		
	}

	boolean detectCycle() {
	        Subset[] s = new Subset[V];

		makeSet(s);
		
		for(int i=0; i<allEdges.size(); i++) {
			Edge edge = allEdges.get(i);
			int x = find(s, edge.source);
			int y = find(s, edge.destination);

			if(x == y)
				return true;
			else {
				union(s, x, y);
			}
		}
		return false;
	}		

	public static void main(String[] a) {
		Scanner sc = new Scanner(System.in);
		
		int v = sc.nextInt();
		int e = sc.nextInt();

		DisjointSet graph = new DisjointSet(v);
		
		System.out.println("Enter "+ e+ " no. of edges");
		
		for(int i=0; i<e; i++) 
			graph.addEdge(sc.nextInt(), sc.nextInt());
		
		System.out.println(graph.detectCycle());
	}	
	
}