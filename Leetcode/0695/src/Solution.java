import java.util.HashSet;

class Solution {
    private int R, C;
    private HashSet<Integer>[] G;
    private int [][] dirs = {{-1,0},{0, 1},{1, 0},{0, -1}};
    private boolean[] visited;
    private int[][] grid;
    public int maxAreaOfIsland(int[][] grid) {
        if (grid.length == 0){
            return 0;
        }
        R = grid.length;
        if (R == 0) return 0;
        C = grid[0].length;
        if (C == 0) return 0;
        this.grid = grid;
        this.G = constructGraph();
        visited = new boolean[G.length];
        int result=0;
        for (int v = 0; v < visited.length; v++){
            if (visited[v] == false && grid[v/C][v%C] == 1){
                result = Math.max(result, dfs(v, result));
            }
        }
        return result;
    }

    private int dfs(int v, int numOfComp){
        numOfComp = 1;
        visited[v] = true;
        for (int w:G[v]){
            if (visited[w] == false){
                numOfComp += dfs(w, numOfComp);
            }
        }
        return numOfComp;
    }

    private HashSet<Integer>[] constructGraph(){
        HashSet<Integer>[] g = new HashSet[R * C];
        for (int i = 0; i < g.length; i++){
            g[i] = new HashSet<>();
        }
        for (int v = 0; v < g.length; v ++){
            int x,y;
            x = v / C;
            y = v % C;
            if (grid[x][y] == 1){
                int nextx, nexty;
                for (int d = 0; d < 4; d ++){
                    nextx = x + dirs[d][0];
                    nexty = y + dirs[d][1];
                    if (inRange(nextx, nexty) && grid[nextx][nexty] == 1){
                        g[v].add(C * nextx + nexty);
                        g[C * nextx + nexty].add(v);
                    }
                }
            }
        }
        return g;
    }

    private boolean inRange(int nextx, int nexty){
        return nextx >= 0 && nexty >= 0 && nextx < R && nexty < C;
    }

//    public static void main(String[] args){
//        Solution s = new Solution();
//        int t[][] = {{1,1,0,0,0}, {1,1,0,0,0}, {0,0,0,1,1}, {0,0,0,1,1}};
//        System.out.print(s.maxAreaOfIsland(t));
//    }
}