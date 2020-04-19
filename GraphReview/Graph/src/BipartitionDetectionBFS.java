import java.net.InterfaceAddress;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BipartitionDetectionBFS {
    private boolean[] visited;
    private Graph G;
    private int[] colors;
    private boolean isBipartite = true;

    public BipartitionDetectionBFS(Graph G){
        this.G = G;
        visited = new boolean[G.V()];
        colors = new int[G.V()];
        for (int i = 0; i< colors.length; i++){
            colors[i] = -1;
        }
        for (int v = 0; v < G.V(); v++){
            if (visited[v] == false){
                isBipartite = bfs(v, 0);
                if (isBipartite == false){
                    break;
                }
            }
        }
    }

    private boolean bfs(int s, int color){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        visited[s] = true;
        colors[s] = 0;
        while (queue.isEmpty() != true){
            int v = queue.remove();
            for (int w:G.adj(v)){
                if (visited[w] != true){
                    queue.add(w);
                    visited[w] = true;
                    colors[w] = 1 - colors[v];
                }else if (visited[w] == true && colors[w] == colors[v]){
                    return false;
                }
            }
        }
        return true;
    }
    public boolean isBipartite(){
        return isBipartite;
    }

    public void colors(){
        ArrayList<Integer>[] parts = new ArrayList[2];
        for (int i = 0; i < 2; i ++){
            parts[i] = new ArrayList<>();
        }
        if (isBipartite == false){
            System.out.print("Current graph is not Bipartite");
        }else{
            for (int i = 0; i < colors.length; i++){
                if (colors[i] == 0){
                    parts[0].add(i);
                }else{
                    parts[1].add(i);
                }
            }
        }
        System.out.print("part one has nodes: ");
        for (int i = 0; i < parts[0].size(); i++){
            System.out.print(parts[0].get(i));
        }
        System.out.println();
        System.out.print("part two has nodes: ");
        for (int i = 0; i < parts[1].size(); i++){
            System.out.print(parts[1].get(i));
        }
    }

    public static void main(String[] args){
        Graph g = new Graph("g.txt");
        BipartitionDetectionBFS b = new BipartitionDetectionBFS(g);
        b.colors();
    }
}
