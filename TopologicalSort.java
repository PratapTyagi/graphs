import java.util.*;
import java.io.*;

public class TopologicalSort {
	LinkedList<Integer>[] graph;
	
	@SuppressWarnings("unchecked")
	public TopologicalSort(int v) {
		graph = new LinkedList[v];
		for(int i=0; i<v; i++)
			graph[i] = new LinkedList<Integer>();	
	}		

	public void addEdge(int s, int d) {
		graph[s].add(d);
	}

	public void sort(int src, boolean[] isVisited, Stack<Integer> stack) {
		isVisited[src] = true;
		for(Integer t: graph[src]) 
			if(!isVisited[t])
				sort(t, isVisited, stack);

		stack.push(src);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Enter no. of vertices and edges");	
		String[] s = br.readLine().split("\\s+");
		int v = Integer.parseInt(s[0]);
		int e = Integer.parseInt(s[1]);
		s = null;

		TopologicalSort t = new TopologicalSort(v);
		System.out.println("Enter edges");
		for(int i=0; i<e; i++) {
			s = br.readLine().split("\\s+");
			t.addEdge(Integer.parseInt(s[0]), Integer.parseInt(s[1]));
		}
		Stack<Integer> stack = new Stack<>();
		boolean[] isVisited = new boolean[v];
		for(int i=0; i<v; i++)
			if(!isVisited[i])
				t.sort(i, isVisited, stack);

		System.out.println("Topological sorted graph:");
		while(!stack.isEmpty()) 
			System.out.print(stack.pop()+ " ");
		
	}
}