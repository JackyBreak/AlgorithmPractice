import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Path {
    private boolean[] visited;
    private Graph G;
    private int s;
    private int t;
    private int[] pre;

    public Path(Graph G, int s, int t){
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
        dfs(s, s);
        for (int i=0; i< G.V(); i++){
            System.out.print(visited[i] + " ");
        }
    }

    public boolean isConnected(){
        G.validateVertex(t);
        return visited[t];
    }

    public Iterable<Integer> path(){
        ArrayList<Integer> res = new ArrayList<>();
        if (isConnected() == false){
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

    private boolean dfs(int v, int parent){
        visited[v] = true;
        pre[v] = parent;
        if (this.t == v){
            return true;
        }else{
            for (int w : G.adj(v)){
                if (visited[w] == false){
                    dfs(w, v);
                }
            }
        }
        return false;
    }

    public static void main(String[] args){
        Graph g = new Graph("g.txt");
//        Path p = new Path(g, 0, 6);
//        System.out.print(p.path());
//        Path p1 = new Path(g, 0, 1);
//        System.out.print(p1.path());
        Path p2 = new Path(g, 0, 5);
        System.out.print(p2.path());
    }
}
