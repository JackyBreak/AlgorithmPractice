import java.util.ArrayList;

public class CycleDetection {
    private boolean[] visited;
    private Graph G;
    private boolean hasCycle;

    public CycleDetection(Graph G){
        this.G = G;
        visited = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++){
            if (visited[v] == false){
                if (dfs(v, v) == true){
                    hasCycle = true;
                    break;
                }
            }
        }
        for (int i=0; i< G.V(); i++){
            System.out.print(visited[i] + " ");
        }
    }

    private boolean dfs(int v, int parent){
        visited[v] = true;
        for (int w : G.adj(v)){
            if (visited[w] == false){
                if (dfs(w, v) == true){
                    return true;
                };
            }else if (visited[w] == true && parent != w){
                return true;
            }
        }
        return false;
    }

    public boolean hasCycle(){
        return this.hasCycle;
    }

    public static void main(String[] args){
        Graph g = new Graph("g2.txt");
        CycleDetection c = new CycleDetection(g);
        System.out.print("\n");
        System.out.print(c.hasCycle());
    }
}
