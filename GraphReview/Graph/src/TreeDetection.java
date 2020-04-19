public class TreeDetection {
    private Graph G;
    private boolean isTree;
    private CycleDetection cycleDetection;
    private CC cc;

    public TreeDetection(Graph g){
        this.G = g;
        this.cycleDetection = new CycleDetection(this.G);
        this.cc = new CC(this.G);
        this.isTree = ((cycleDetection.hasCycle()==false) && (cc.count() == 1));
    }

    public boolean isTree(){
        return isTree;
    }

    public static void main(String[] args){
        Graph g = new Graph("tree.txt");
        TreeDetection t = new TreeDetection(g);
        System.out.print("current graph is tree: " + t.isTree());
    }
}
