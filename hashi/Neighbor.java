package hashi;

public class Neighbor {

    private Island island;
    private int possibleConnections;

    public Neighbor(Island island) {
        this(island, (island.value() == 1) ? 1 : 2);
    }

    public Neighbor(Island island, int possibleConnections) {
        this.island = island;
        this.possibleConnections = (possibleConnections == 1) ? 1 : 2;
    }
    
    public int possibleConnections() {
    	return possibleConnections;
    }
    
    public Island getIsland() {
    	return island;
    }
    
}
