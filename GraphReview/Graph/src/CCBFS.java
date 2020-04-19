import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class CCBFS {
    private int[] visited;
    private Graph G;
    private int cccount = 0;

    public CCBFS(Graph G){
        this.G = G;
        visited = new int[G.V()];
        for (int i = 0; i < G.V(); i++){
            visited[i] = -1;
        }
        for (int v = 0; v < G.V(); v++){
            if (visited[v] == -1){
                bfs(v, cccount);
                cccount++;
            }
        }
    }

    public int count(){
        for (int v:visited){
            System.out.print(v+" ");
        }
        System.out.print("\n");
        return cccount;
    }

    private void bfs(int s, int ccid){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        visited[s] = ccid;
        while (queue.isEmpty() != true){
            int v = queue.remove();
            for (int w:G.adj(v)){
                if (visited[w] == -1){
                    queue.add(w);
                    visited[w] = ccid;
                }
            }
        }
    }

    public boolean isConnected(int v, int w){
        G.validateVertex(v);
        G.validateVertex(w);
        return visited[w] == visited[v];
    }

    public ArrayList<Integer>[] components(){
        ArrayList<Integer>[] res = new ArrayList[cccount];
        for (int i = 0; i < cccount; i++){
            res[i] = new ArrayList<>();
            for (int j = 0; j < G.V(); j++){
                if (visited[j] == i){
                    res[i].add(j);
                }
            }
        }
        return res;
    }

//    public Iterable<Integer> getComponenets(){
//        return components();
//    }

    public static void main(String[] args){
        Graph g = new Graph("g.txt");
        CCBFS ccbfs = new CCBFS(g);
        System.out.println(ccbfs.count());
        ArrayList<Integer>[] comp = ccbfs.components();
        for (int ccid = 0; ccid < comp.length; ccid++){
            System.out.print(ccid + " : ");
            for (int w:comp[ccid]){
                System.out.print(w + " ");
            }
            System.out.print("\n");
        }

    }
}
