package hashi;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class StdHashi implements Hashi {

	//	ATTRIBUTS
	
    private Map<String, Island> island;
    private Map<String, Line> solutionLines;
    private Map<String, Line> currentLines;
    private int dim;

    //	CONSTRUCTEURS
    
    public StdHashi(int dim) {
        this.dim = dim;
        solutionLines = new HashMap<String, Line>();
        currentLines = new HashMap<String, Line>();
        island = new HashMap<String, Island>();
    }

    public StdHashi(Hashi h) {
        this.dim = h.getDim();
        
        //	initialisation des lignes courrantes, solutionnées et des îles.
        this.currentLines = new HashMap<String, Line>(h.
        		getCurrentLines().size());
        this.island = new HashMap<String, Island>(h.getIsland().size());
        this.solutionLines = new HashMap<String, Line>(h.
        		getSolutionLines().size());
        for (String s : h.getIsland().keySet()) {
            this.island.put(s, new StdIsland(h.getIsland().get(s)));
        }
        for (String s : h.getSolutionLines().keySet()) {
            this.solutionLines.put(s, new Line(h.getSolutionLines().get(s)));
        }
        for (String s : h.getCurrentLines().keySet()) {
            this.currentLines.put(s, new Line(h.getCurrentLines().get(s)));
        }
    }
    
    //	REQUETES
    
    public Map<String, Line> getSolutionLines() {
        return solutionLines;
    }

    public Map<String, Line> getCurrentLines() {
        return currentLines;
    }

    public Map<String, Island> getIsland() {
        return island;
    }
    
    public void addIsland(int x, int y, int amount) {
    	Island n = new StdIsland(x, y, amount);
    	n.setInitialValue(amount);
        island.put(formatInt(x, y), n);
    }

    public void created() {
        Iterator<Island> it = island.values().iterator();
        while (it.hasNext()) {
            Island n = it.next();
            n.created();
        }
    }
    
    public int getDim() {
    	return this.dim;
    }

    
    public boolean isWin() {
        Iterator<Island> it = island.values().iterator();
        while (it.hasNext()) {
            Island n = it.next();
            if (n.value() != 0) {
                return false;
            }
        }
        return true;
    }

    //	COMMANDES

    public String formatInt(int x, int y) {
        return "0" + x + "0" + y;
    }

    public void addSolutionLine(Line line) {
        solutionLines.put(line.toString(), line);
        int lineStrength = 1;
        if (line.hasTwo()) {
            lineStrength = 2;
        }

        this.increaseIsland(line.x1(), line.y1(), lineStrength);
        this.increaseIsland(line.x2(), line.y2(), lineStrength);
    }

    public void addCurrentLine(Line line) {
        String format = line.toString();
        Line l = currentLines.get(format);
        if (l == null) {
            int start = this.getIslandValue(line.x1(), line.y1());
            int end = this.getIslandValue(line.x2(), line.y2());

            if (start > 0 && end > 0) {
                //	ajoute une ligne simple
                line.setHasTwo(false);
                currentLines.put(format, line);

                this.decreaseIsland(line.x1(), line.y1(), 1);
                this.decreaseIsland(line.x2(), line.y2(), 1);
            }
        } else if (!l.hasTwo()) {
            int start = this.getIslandValue(l.x1(), l.y1());
            int end = this.getIslandValue(l.x2(), l.y2());
            if (start > 0 && end > 0) {
                //	ajoute une ligne car double
                l.setHasTwo(true);

                this.decreaseIsland(l.x1(), l.y1(), 1);
                this.decreaseIsland(l.x2(), l.y2(), 1);
            } else {
                //	supprime les lignes simples
                getCurrentLines().remove(format);

                this.increaseIsland(line.x1(), line.y1(), 1);
                this.increaseIsland(line.x2(), line.y2(), 1);
            }
        } else {
            //	supprime les lignes doubles
        	getCurrentLines().remove(format);

            this.increaseIsland(line.x1(), line.y1(), 2);
            this.increaseIsland(line.x2(), line.y2(), 2);
        }

    }

    public synchronized int getIslandValue(int x, int y) {
        String format = this.formatInt(x, y);
        Island n = island.get(format);
        if (n != null) {
            return n.value();
        } else {
        	return 0;
        }
    }

    public void increaseIsland(int x, int y, int amount) {
        String format = this.formatInt(x, y);
        if (getIsland().containsKey(format)) {
            Island n = getIsland().get(format);
            n.addValue(amount);
            if (n.isCreated() && n.value() > n.firstValue()) {
                n.changeValueTo(n.firstValue());
            }
        } else {
            Island n = new StdIsland(x, y, amount);
            getIsland().put(format, n);
        }
    }

    public void decreaseIsland(int x, int y, int amount) {
        String format = this.formatInt(x, y);
        if (getIsland().containsKey(format)) {
            Island n = getIsland().get(format);
            if (n.value() >= amount) {
            	n.removeValue(amount);    
            }
        } else {
            Island n = new StdIsland(x, y, amount);
            island.put(format, n);
        }
    }
}
