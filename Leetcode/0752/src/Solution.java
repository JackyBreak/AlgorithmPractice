import java.util.*;

class Solution {
    public int openLock(String[] deadends, String target) {
        HashSet<String> deadset = new HashSet<>();
        for (String v:deadends){
            deadset.add(v);
        }
        HashMap<String, Integer> visited = new HashMap<>();
        visited.put("0000", 0);
        Queue<String> queue = new LinkedList<>();
        queue.add("0000");
        if (deadset.contains("0000")) return -1;
        if (deadset.contains(target)) return -1;
        if (target == "0000") return 0;

        while (queue.isEmpty() == false){
            String current = queue.remove();
            ArrayList<String> nexts = new ArrayList<>();
            char[] cur = current.toCharArray();
            for (int i = 0; i < 4; i++){
                char o = cur[i];
                cur[i] = Character.forDigit((cur[i] - '0' + 1) % 10, 10);
                nexts.add(new String(cur));
                cur[i] = o;
                cur[i] = Character.forDigit((cur[i] - '0' + 9) % 10, 10);
                nexts.add(new String(cur));
                cur[i] = o;
            }

            for (String next:nexts){
                if (deadset.contains(next) == false && visited.containsKey(next) == false){
                    visited.put(next, visited.get(current) + 1);
                    queue.add(next);
                    if (target.equals(next)){
                        return visited.get(next);
                    }
                }
            }
        }
        return -1;
    }

//    public static void main(String[] args){
//        String[] deadend = {"0201","0101","0102","1212","2002"};
//        Solution s = new Solution();
//        System.out.print(s.openLock(deadend, "0202"));
//    }
}