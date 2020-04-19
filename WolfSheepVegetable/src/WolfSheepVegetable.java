import java.util.*;

public class WolfSheepVegetable {
    HashMap<String, String> visited = new HashMap<>();
    public WolfSheepVegetable(){
        // "[farmer][wolf][sheep][vegetable]"
        HashSet<String> deadend = new HashSet<>();
        deadend.add("0110");
        deadend.add("0011");
        deadend.add("1001");
        deadend.add("1100");
        deadend.add("0111");
        visited.put("1111", "1111");
        Queue<String> queue = new LinkedList<>();
        queue.add("1111");

        while (queue.isEmpty() == false){
            String current_state = queue.remove();
            char[] chars = current_state.toCharArray();
            ArrayList<String> nexts = new ArrayList<>();
            chars[0] = Character.forDigit('1' - chars[0], 10);
            nexts.add(new String(chars));
            if (chars[0] == '0'){
                for (int i = 1; i < 4; i ++){
                    char o = chars[i];
                    chars[i] = Character.forDigit('1' - chars[i], 10);
                    nexts.add(new String(chars));
                    chars[i] = o;
                }
            }else if (chars[0] == '1'){
                for (int i = 1; i < 4; i ++){
                    char o = chars[i];
                    if (chars[i] == '0'){
                        chars[i] = Character.forDigit('1' - chars[i], 10);
                        nexts.add(new String(chars));
                    }
                    chars[i] = o;
                }
            }
            for (String v:nexts){
                if (visited.containsKey(v) == false && deadend.contains(v) == false){
                    visited.put(v, current_state);
                    queue.add(v);
                    if (v == "0000"){
                        return;
                    }
                }
            }
        }
    }

    public Iterable<String> getResult(){
        ArrayList<String> res = new ArrayList<>();
        String cur = "0000";
        res.add("0000");
        while (cur != "1111"){
            res.add(visited.get(cur));
            cur = visited.get(cur);
        }
//        res.add("0000");
        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args){
        WolfSheepVegetable w = new WolfSheepVegetable();
        for (String v:w.getResult()){
            System.out.print( v + " > ");
        }
    }
}
