import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class SingleSourcePath {
    private boolean[] visited;
    private Graph G;
    private int s;
    private int[] pre;

    public SingleSourcePath(Graph G, int s){
        this.G = G;
        G.validateVertex(s);
        this.s = s;
        visited = new boolean[G.V()];
        pre = new int[G.V()];
        for (int i = 0; i < pre.length; i++){
            pre[i] = -1;
        }
        dfs(s, s);
    }

    public boolean isConnectedTo(int t){
        G.validateVertex(t);
        return visited[t];
    }

    public Iterable<Integer> path(int t){
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

    private void dfs(int v, int parent){
        visited[v] = true;
        pre[v] = parent;
        for (int w : G.adj(v)){
            if (visited[w] == false){
                dfs(w, v);
            }
        }
    }

    public static void main(String[] args){
        Graph g = new Graph("g.txt");
        SingleSourcePath ssp = new SingleSourcePath(g, 0);
        System.out.print(ssp.path(0));
    }
}
