import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int slidingPuzzle(int[][] board) {
        String initial = boardToString(board);
        if (initial.equals("123450")){
            return 0;
        }
        HashMap<String, Integer> visited = new HashMap<>();
        visited.put(initial, 0);
        Queue<String> queue = new LinkedList<>();
        queue.add(initial);

        while (queue.isEmpty() == false){
            String current = queue.remove();
            ArrayList<String> nexts = createNexts(current);
            for (String next:nexts){
                if (visited.containsKey(next) == false){
                    queue.add(next);
                    visited.put(next, visited.get(current) + 1);
                    if (next.equals("123450")){
                        return visited.get(next);
                    }
                }
            }
        }
        return -1;
    }

    private ArrayList<String> createNexts(String current){
        ArrayList<String> nexts = new ArrayList<>();
        char[] chars = current.toCharArray();
        for (int i = 0; i < 6; i++){
            if (chars[i] == '0'){
                int top, down, left, right;
                top = i - 3;
                down = i + 3;
                left = i - 1;
                right = i + 1;
                if (top >= 0 && top <= 5){
                    char temp = chars[i];
                    chars[i] = chars[top];
                    chars[top] = temp;
                    nexts.add(new String(chars));
                    chars = current.toCharArray();
                }
                if (down >= 0 && down <= 5){
                    char temp = chars[i];
                    chars[i] = chars[down];
                    chars[down] = temp;
                    nexts.add(new String(chars));
                    chars = current.toCharArray();
                }
                if (left >= 0 && left <= 5 && i != 3){
                    char temp = chars[i];
                    chars[i] = chars[left];
                    chars[left] = temp;
                    nexts.add(new String(chars));
                    chars = current.toCharArray();
                }
                if (right >= 0 && right <= 5 && i != 2){
                    char temp = chars[i];
                    chars[i] = chars[right];
                    chars[right] = temp;
                    nexts.add(new String(chars));
                    chars = current.toCharArray();
                }
            }
            chars = current.toCharArray();
        }
        return nexts;
    }

    private String boardToString(int[][] board){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 2; i ++){
            for (int j = 0; j < 3; j ++){
                sb.append((board[i][j]));
            }
        }
        return sb.toString();
    }

//    public static void main(String[] args){
//        Solution s = new Solution();
//        int [][] board = {{4, 1, 2},{5, 0 ,3}};
//        System.out.print(s.slidingPuzzle(board));
//    }
}