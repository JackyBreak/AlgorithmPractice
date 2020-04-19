public class AllPairsPathBFS {
    private SingleSourcePathBFS[] paths;
    private Graph g;
    public AllPairsPathBFS(Graph g){
        this.g = g;
        paths = new SingleSourcePathBFS[g.V()];
        for (int i = 0; i < g.V(); i++){
            paths[i] = new SingleSourcePathBFS(g, i);
        }
    }
}
