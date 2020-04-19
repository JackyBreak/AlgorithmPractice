import java.util.ArrayList;

public class GraphDFS {
    private ArrayList<Integer> pre = new ArrayList<>();
    private ArrayList<Integer> post = new ArrayList<>();
    private boolean[] visited;
    private Graph G;

    public GraphDFS(Graph G){
        this.G = G;
        visited = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++){
            if (visited[v] == false){
                dfs(v);
            }
        }
    }

    private void dfs(int v){
        visited[v] = true;
        pre.add(v);
        for (int w : G.adj(v)){
            if (visited[w] == false){
                dfs(w);
            }
        }
        post.add(v);
    }

    public Iterable<Integer> pre(){
        return pre;
    }

    public Iterable<Integer> post(){
        return post;
    }

    public static void main(String[] args){
        Graph g = new Graph("g.txt");
        GraphDFS graphDFS = new GraphDFS(g);
        System.out.print(graphDFS.pre());
        System.out.print(graphDFS.post());
    }
}
