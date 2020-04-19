import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class PathBFS {
    private boolean[] visited;
    private Graph G;
    private int s;
    private int t;
    private int[] pre;

    public PathBFS(Graph G, int s, int t){
        this.G = G;
        G.validateVertex(s);
        this.s = s;
        G.validateVertex(t);
        this.t = t;
        visited = new boolean[G.V()];
        pre = new int[G.V()];
        for (int i = 0; i < pre.length; i++){
            pre[i] = -1;
        }
        bfs(s, t);
        for (int i=0; i< G.V(); i++){
            System.out.print(visited[i] + " ");
        }
    }

    public boolean isConnectedTo(int t){
        G.validateVertex(t);
        return visited[t];
    }

    private void bfs(int s, int t){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        visited[s] = true;
        pre[s] = s;
        if (s == t){
            return;
        }
        while (queue.isEmpty() != true){
            int v = queue.remove();
            if (v == t){
                return;
            }
            for (int w:G.adj(v)){
                if (visited[w] != true){
                    queue.add(w);
                    visited[w] = true;
                    pre[w] = v;
                }
            }
        }
    }


    public Iterable<Integer> path(){
        ArrayList<Integer> res = new ArrayList<>();
        if (isConnectedTo(t) == false){
            return res;
        }else{
            int cur = t;
            while (cur != s){
                res.add(cur);
                cur = pre[cur];
            }
            res.add(s);
            Collections.reverse(res);
            return res;
        }
    }


    public static void main(String[] args){
        Graph g = new Graph("g.txt");
        PathBFS p = new PathBFS(g, 0, 6);
        System.out.print(p.path());
        PathBFS p1 = new PathBFS(g, 0, 5);
        System.out.print(p1.path());
        PathBFS p2 = new PathBFS(g, 0, 1);
        System.out.print(p2.path());
    }
}
