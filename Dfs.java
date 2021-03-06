import java.util.*;
import java.io.*;

class Pair {
	int data;
	int psf;
	public Pair(int d, int p){
		data = d;
		psf = p;
	}
}

public class Dfs {
	LinkedList<Integer>[] graph;
	
	@SupressWarnings("unchecked")
	public Dfs(int v) {
		graph = new LinkedList[v];
		for(int i=0; i<v; i++) {
			graph[i] = new LinkedList<Integer>();
		}
	}

	public void addEdge(int s, int d) {
		graph[s].add(d);	
	}

	public void dfs(int start, int v) {
		ArrayDeque<Pair> stack = new ArrayDeque<>();
		boolean[] isVisited = new boolean[v];

		stack.add(new Pair(start, ""));

		while(!queue.isEmpty()) {
			Pair p = stack.pop();

			if(isVisited[p.data]) continue;
			isVisited[p.data] = true;
			
			System.out.println(p.data+" "+p.psf);

			for(Integer e: graph[p.data]) 
				if(!isVisited[e])
					stack.push(new Pair(e, p.psf+e));
		}			
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		
		System.out.println("Enter no. of 'vertices edges'");
		String s[] = br.readLine().split("\\s+");
		int v = Integer.parseInt(s[0]);
		int e = Integer.parseInt(s[1]);
		s = null;

		Bfs t = new Bfs(v);

		System.out.println("Enter " + e + " no. of edges 'source -> destination'");		
				
		for(int i=0; i<e; i++) {
			s = br.readLine().split("\\s+");
			int source = Integer.parseInt(s[0]);
			int destination = Integer.parseInt(s[1]);
		
			t.addEdge(source, destination);				
		}

		t.dfs(0, v);
	}
}