import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class WaterPuzzle {
    private int[] pre = new int[54];

    private int end = -1;
    public WaterPuzzle(){
        for (int i = 0; i < 54; i++){
            pre[i] = -1;
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        boolean[] visited = new boolean[54];
        visited[0] = true;
        while (queue.isEmpty() == false){
            int current = queue.remove();
            int a = current / 10;
            int b = current % 10;
            ArrayList<Integer> nexts = new ArrayList<>();
            nexts.add(5 * 10 + b);
            nexts.add(a * 10 + 3);
            nexts.add(b);
            nexts.add(a * 10);
            int x = Math.min(a, 3 - b);
            int y = Math.min(5 - a, b);
            nexts.add((a - x) * 10 + (b + x));
            nexts.add((a + y) * 10 + (b - y));
            //TODO: nexts
            for (int next:nexts){
                pre[next] = current;
                if (visited[next] == false){
                    visited[next] = true;
                    if (next / 10 == 4 || next % 10 == 4){
                        end = next;

                        return;
                    }
                    queue.add(next);
                }
            }
        }
    }

    public Iterable<Integer> result(){
        ArrayList<Integer> res = new ArrayList<>();
        if (end == -1) return res;
        int cur;
        res.add(end);
        cur = pre[end];
        while (cur != 0){
            res.add(cur);
            cur = pre[cur];
        }
        res.add(0);
        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args){
        WaterPuzzle w = new WaterPuzzle();
        for (int v:w.result()){
            System.out.print(v + " > ");
        }
    }
}
