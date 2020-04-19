class Solution {
    private boolean[] visited;
    private int[] colors;

    public boolean isBipartite(int[][] graph) {
        int V = graph.length;
        visited = new boolean[V];
        colors = new int[V];
        if (V == 1){
            return true;
        }else{
            for (int v = 0; v < V; v++){
                if (visited[v] == false){
                    if (dfs(v, 1, graph) == false){
                        return false;
                    }
                }
            }
            return true;
        }
    }

    public boolean dfs(int v, int color, int[][] graph){
        colors[v] = color;
        visited[v] = true;
        for (int w:graph[v]){
            if (visited[w] == false){
                if (dfs(w, -1 * colors[v], graph) == false){
                    return false;
                }
            }else if (colors[w] == colors[v]){
                return false;
            }
        }
        return true;
    }

//    public static void main(String[] args){
//        Solution s = new Solution();
//        int [][] t = {{}};
//        System.out.print(s.isBipartite(t));
//    }
}