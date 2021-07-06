import java.util.*;

class Floyd {
	public static void main(String[] args) {
	/*
		(0)------->(3)
	         |         /|\
        	5|          | 1
        	 |          | 
        	\|/         |
        	(1)------->(2)
	
	*/   		
		int max = 99999;
		int graph[][] = { {0,   5,  max, 10},
                	          {max, 0,   3, max},
                        	  {max, max, 0,   1},
                          	  {max, max, max, 0}
                        	};
		
		int n = graph.length;
		int[][] distance = new int[n][n];

		for(int k=0; k<n; k++) 
			for(int i=0; i<n; i++) 
				distance[k][i] = graph[k][i];		
		
		for(int k=0; k<n; k++) {
			for(int i=0; i<n; i++) {
				  if (distance[k][i]==max)
                    			System.out.print("INF ");
                		  else
 		                        System.out.print(distance[k][i]+"   ");		
			}
			System.out.println();		
		}

		for(int k=0; k<n; k++) {
			for(int i=0; i<n; i++){ 
				for(int j=0; j<n; j++) {
					if (distance[i][k] + distance[k][j] < distance[i][j])
                        distance[i][j] = distance[i][k] + distance[k][j];
				}
			}
		}
		for(int k=0; k<n; k++) {
			for(int i=0; i<n; i++) {
				  if (distance[k][i]==max)
                    			System.out.print("INF ");
                		  else
 		                        System.out.print(distance[k][i]+"   ");		
			}
			System.out.println();		
		}	
	}
}