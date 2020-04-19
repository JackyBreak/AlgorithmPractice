import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class CycleDetectionBFS {
    private boolean[] visited;
    private Graph G;
    private boolean hasCycle;
    private int[] pre;

    public CycleDetectionBFS(Graph G){
        this.G = G;
        visited = new boolean[G.V()];
        pre = new int[G.V()];
        for (int i = 0; i < G.V(); i++){
            pre[i] = -1;
        }
        for (int v = 0; v < G.V(); v++){
            if (visited[v] == false){
                if (bfs(v) == true){
                    hasCycle = true;
                    break;
                }
            }
        }
//        for (int i=0; i< G.V(); i++){
//            System.out.print(visited[i] + " ");
//        }
    }

    private boolean bfs(int s){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        visited[s] = true;
        pre[s] = s;
        while (queue.isEmpty() != true){
            int v = queue.remove();
            for (int w:G.adj(v)){
                if (visited[w] != true){
                    queue.add(w);
                    visited[w] = true;
                    pre[w] = v;
                }else if (visited[w] == true && w != pre[v]){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean hasCycle(){
        return this.hasCycle;
    }

    public static void main(String[] args){
        Graph g = new Graph("g2.txt");
        CycleDetectionBFS c = new CycleDetectionBFS(g);
        System.out.print("\n");
        System.out.print(c.hasCycle());
    }
}
