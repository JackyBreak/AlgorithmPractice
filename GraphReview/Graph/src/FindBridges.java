import java.util.ArrayList;

public class FindBridges {
    private boolean[] visited;
    private Graph G;
    private int[] ord;
    private int[] low;
    private int cnt;
    private ArrayList<Edge> res;

    public FindBridges(Graph G){
        this.G = G;
        visited = new boolean[G.V()];
        ord = new int[G.V()];
        low = new int[G.V()];
        cnt = 0;
        res = new ArrayList<>();
        for (int v = 0; v < G.V(); v++){
            if (visited[v] == false){
                dfs(v, v);
            }
        }
    }

    private void dfs(int v, int parent){
        visited[v] = true;
        ord[v] = cnt;
        cnt ++;
        low[v] = ord[v];
        for (int w : G.adj(v)){
            if (visited[w] == false){
                dfs(w, v);
                low[v] = Math.min(low[v], low[w]);
                if (low[w] > ord[v]){
                    res.add(new Edge(v, w));
                }
            }else if (w != parent){
                low[v] = Math.min(low[w], low[v]);
            }
        }
    }

    public ArrayList<Edge> result(){
        return res;
    }

    public static void main(String[] args){
        Graph g = new Graph("D:\\Algorithm\\GraphReview\\Graph\\g.txt");
        FindBridges f = new FindBridges(g);
        System.out.print(f.result());
    }
}
