import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class SingleSourcePathBFS {
    private Graph G;
    private boolean[] visited;
    private int[] pre;
    private int[] dis;
    private int s;

    public SingleSourcePathBFS(Graph G, int s){
        this.s = s;
        this.G = G;
        visited = new boolean[G.V()];
        pre = new int[G.V()];
        dis = new int[G.V()];
        for (int i = 0; i < G.V(); i++){
            pre[i] = -1;
            dis[i] = -1;
        }
        bfs(s);
        for (int i=0; i< G.V(); i++){
            System.out.print(dis[i] + " ");
        }
    }

    private void bfs(int s){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        visited[s] = true;
        pre[s] = s;
        dis[s] = 0;
        while (queue.isEmpty() != true){
            int v = queue.remove();
            for (int w:G.adj(v)){
                if (visited[w] != true){
                    queue.add(w);
                    visited[w] = true;
                    pre[w] = v;
                    dis[w] = dis[v] + 1;
                }
            }
        }
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

    public int dis(int t){
        return dis[t];
    }

    public static void main(String[] args){
        Graph g = new Graph("g.txt");
        SingleSourcePathBFS singleSourcePathBFS = new SingleSourcePathBFS(g, 0);
        System.out.println(singleSourcePathBFS.path(6));
        System.out.println(singleSourcePathBFS.dis(6));

    }

}
