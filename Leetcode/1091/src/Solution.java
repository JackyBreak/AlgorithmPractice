import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    private Queue<int[]> queue = new LinkedList<>();
    private boolean [][] visited;
    private int R, C;
    private int[][] grid;
    private int[][] dirs = {{-1, 0},{-1, 1},{0, 1},{1, 1},{1, 0},{1, -1},{0, -1},{-1, -1}};
    private int[][] dis;
    public int shortestPathBinaryMatrix(int[][] grid) {
        this.R = grid.length;
        this.C = R;
        this.visited = new boolean[R][C];
        this.grid = grid;
        this.dis = new int[R][C];
        if (grid[0][0] == 1){
            return -1;
        }
        if (R == 1 && C == 1){
            return 1;
        }
        int path = bfs(0, 0);
        return path;
    }

    private int bfs(int i, int j){
        int[] coordinate = {i, j};
        visited[i][j] = true;
        queue.add(coordinate);
        int maxx, maxy;
        maxx = grid.length-1;
        maxy = maxx;
        dis[0][0] = 1;
        dis[maxx][maxy] = -1;
        while (queue.isEmpty() == false){
            int x, y, nextx, nexty;
            int[] coor = queue.remove();
            x = coor[0];
            y = coor[1];
            if (x == maxx && y == maxy){
                return dis[x][y];
            }
            for (int d = 0; d < 8; d++){
                nextx = x + dirs[d][0];
                nexty = y + dirs[d][1];
                if (inRange(nextx, nexty) && grid[nextx][nexty] == 0 && visited[nextx][nexty] == false){
                    visited[nextx][nexty] = true;
                    dis[nextx][nexty] = dis[x][y] + 1;
                    int[] next_coor = {nextx, nexty};
                    queue.add(next_coor);
                }
            }
        }
        return dis[maxx][maxy];
    }

    private boolean inRange(int x, int y){
        return x >= 0 && y >= 0 && x < R && y < C;
    }

    public static void main(String[] args){
        int[][] t = {{0,0,0},{1,1,0},{1,1,0}};
//        int [][] t = {{0, 1}, {1, 0}};
        Solution s = new Solution();
        System.out.print(s.shortestPathBinaryMatrix(t));
    }
}