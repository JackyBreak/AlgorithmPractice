public class AllPairsPath {
    private SingleSourcePath[] paths;
    private Graph g;

    public AllPairsPath(Graph g){
        this.g = g;
        this.paths = new SingleSourcePath[g.V()];

        for (int i = 0; i < g.V(); i++){
            this.paths[i] = new SingleSourcePath(g, i);
        }
    }
}

