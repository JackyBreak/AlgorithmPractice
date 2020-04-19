import java.util.HashSet;

class Solution2 {
    private int [][] dirs = {{-1,0},{0, 1},{1, 0},{0, -1}};
    private int R, C;
    private boolean[][] visited;
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
        int result=0;
        visited = new boolean[R][C];
        for (int v = 0; v < R; v++){
            for (int w = 0; w < C; w++){
                if (visited[v][w] == false && grid[v][w] == 1){
                    result = Math.max(result, dfs(v, w));
                }
            }
        }
        return result;
    }

    private int dfs(int v, int w){
        visited[v][w] = true;
        int result = 1;
        for (int d = 0; d < 4; d ++){
            int nextx, nexty;
            nextx = v + dirs[d][0];
            nexty = w + dirs[d][1];
            if (inRange(nextx, nexty) && visited[nextx][nexty] == false && grid[nextx][nexty] == 1){
                result += dfs(nextx, nexty);
            }

        }
        return result;
    }


    private boolean inRange(int nextx, int nexty){
        return nextx >= 0 && nexty >= 0 && nextx < R && nexty < C;
    }

//    public static void main(String[] args){
//        Solution2 s = new Solution2();
//        int t[][] = {{1,1,0,0,0}, {1,1,0,0,0}, {0,0,0,1,1}, {0,0,0,1,1}};
//        System.out.print(s.maxAreaOfIsland(t));
//    }
}