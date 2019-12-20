import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
 

 class DisjointSet
{
    
    public DisjointSet( int numberOfElements )
    {
        set = new int [numberOfElements];
        for( int i = 0; i < set.length; i++ )
            set[ i ] = -1;
    }

    
    public void union( int a, int b )
    {
        if( set[b] < set[a] ) 
            set[a] = b;       
        else
        {
            if( set[a] == set[b] )
                set[a]--;     
            set[b] = a;       
        }
    }

   
    public int find( int x )
    {
        if( set[ x ] < 0 )
            return x;
        else
            return set[ x ] = find( set[ x ] );
    }

    private int [ ] set;

}
class KruskalFind 
{
	public void find()
	{
    	BufferedReader fileReader = null;
    	ArrayList<Edge> list = new ArrayList<>();
		ArrayList<String> Vertices = new ArrayList<>();
        try
        {
			int count = 0;
            String row = "";
            
            fileReader = new BufferedReader(new FileReader("./Data.csv"));  
           
            while ((row = fileReader.readLine()) != null)
            {
                
                String[] tokenize = row.split(",");
                Vertices.add(tokenize[0]);
                list.add( new Edge(tokenize[0],tokenize[1],Integer.parseInt(tokenize[2])));
                	for (int i=3 ; i<tokenize.length ; i++)
                	{
                		list.add(new Edge(tokenize[0],tokenize[i],Integer.parseInt(tokenize[i+1])));
                		i++ ;
                	} 
                count++ ;             
            }
        }
        
        catch (Exception exp) {
            exp.printStackTrace();
        }	 
        int distance = 0;
		int isEdge = 0 ;
		DisjointSet disjointSet = new DisjointSet(Vertices.size());
		PriorityQueue<Edge> queue = new PriorityQueue<>();
		
		for (Edge edge : list)
		{
			queue.add(edge);	
		}
		
		while (isEdge < Vertices.size()-1 )
		{
			Edge edg = queue.poll();	
         
         			
			int v1=disjointSet.find(Vertices.indexOf(new String(edg.v1)));
			int v2=disjointSet.find(Vertices.indexOf(new String(edg.v2)));
			if (v1 != v2)
			{
				isEdge++ ;
				disjointSet.union(v1,v2);
				System.out.println(edg.v1 + " to " + edg.v2 );
				distance = distance + edg.distance ;
				System.out.println("Distance = " + edg.distance);
				System.out.println();
			}
		}
      
		System.out.println();
	  System.out.println("Total distance is " + distance);
		

	}
}
class Edge implements Comparable<Edge>
	{
		int distance;
		String v1 , v2;
		
		Edge(String s1 , String s2,int d)
		{
			this.v1 = s1;
			this.v2 = s2;
			this.distance = d;
		}
		public int compareTo(Edge edg)
		{
			 if (this.distance > edg.distance)
				return 1;
			else if (this.distance < edg.distance)
				return -1;
			else 
				return 0;
		}
	}

public class Kruskal {
public static void main(String args[])
{
	KruskalFind KruskalFind = new KruskalFind();
    System.out.println("The Minimum Spanning Tree is formed as follows");
    KruskalFind.find();  
}
}

