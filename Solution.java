import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    public static int V = 0;
    public static void main(String args[] ) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
        String numofedges = "";
        Scanner sc = new Scanner(System.in);
        numofedges = sc.nextLine();
        int s = 0;
       
        String patString = "";
        ArrayList<GraphModel> graphModels = new ArrayList<>();
        for (int i = 0; i < Integer.parseInt(numofedges); i++) {    
            String temp =  sc.nextLine();
            graphModels.add(new GraphModel(temp.split(" ")[0],temp.split(" ")[1],temp.split(" ")[2]));
            if (!patString.contains(temp.split(" ")[0])){
                patString += temp.split(" ")[0];
            }
            if (!patString.contains(temp.split(" ")[1])){
                patString += temp.split(" ")[1];
            }
        }
        V  = patString.length();
       
        
        for (int i = 0; i < patString.length(); i++) {
            for (int j = 0; j < graphModels.size(); j++) {
        
                if(((patString.charAt(i))+"").equalsIgnoreCase(graphModels.get(j).gets())){
                    graphModels.set(j,new GraphModel(""+i,graphModels.get(j).gett(),graphModels.get(j).getedge()));
                    
                }
                if(((patString.charAt(i))+"").equalsIgnoreCase(graphModels.get(j).gett())){
                    graphModels.set(j,new GraphModel(graphModels.get(j).gets(),""+i,graphModels.get(j).getedge()));
                    
                }
            }
        }
        
        
        int graph[][] = new int[V][V];
        for(int i = 0; i< graph.length; i++){
            for(int j = 0; j< graph.length; j++){
            graph[i][j] = 0;
            }
        }
        
        for(int i = 0; i< graphModels.size(); i++){
         graph[Integer.parseInt(graphModels.get(i).gets())][Integer.parseInt(graphModels.get(i).gett())] = Integer.parseInt(graphModels.get(i).getedge());
        }
        
        System.out.print(fordfulkson(graph, s, patString.length() -1));
        
        
        // for(int i = 0; i< graphModels.size(); i++){
        //    System.out.println(graphModels.get(i).gets()+" "+graphModels.get(i).gett()+" "+graphModels.get(i).getedge()); 
        // }
        
        
    }
    
    
    public static int fordfulkson(int graph[][], int s, int t){
        int u,v;
        
        int rGraph[][] = new int[V][V];
        
        for(u=0; u<V; u++){
            for(v=0; v<V; v++){
                rGraph[u][v] = graph[u][v];
            }
        }
            int parent[] = new int[V];
            int maxflow = 0;
            
            while(bfs(rGraph,s,t,parent)){
                int pathflow = Integer.MAX_VALUE;
                
                for(v=t; v!=s; v=parent[v]){
                    u = parent[v];
                    pathflow = Math.min(pathflow, rGraph[u][v]);
                    
                }
                
                for(v=t; v!=s; v=parent[v]){
                    u = parent[v];
                   rGraph[u][v] -= pathflow;
                   rGraph[v][u] += pathflow;
                }
                
                maxflow += pathflow;
            }
            
            return maxflow;
        }
    
        
        public static boolean bfs(int rGraph[][], int s, int t, int parent[]){
            
            boolean visited[] = new boolean[V];
            for(int i=0; i<V; ++i){
                visited[i] = false;
            }
            
            LinkedList<Integer> queue = new LinkedList<>();
            queue.add(s);
            visited[s] = true;
            parent[s]  = -1;
            
            while(queue.size()!=0){
                int u = queue.poll();
                
                for(int v = 0; v<V; v++){
                    if(visited[v] == false && rGraph[u][v] > 0){
                        queue.add(v);
                        parent[v] = u;
                        visited[v] = true;
                    }
                }
            }
            
            return (visited[t]==true);
        }
        
    
    
    
   public static class GraphModel{
      
       String s = "";
       String t = "";
       String edge = "";
       
       public GraphModel(String s, String t, String edge){
           this.s= s;
           this.t = t;
           this.edge = edge;
       }
       
       public String gets(){
           return s;
       }
       public String gett(){
           return t;
       }
       public String getedge(){
           return edge;
       }
   }
   
}
