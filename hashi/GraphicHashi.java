package hashi;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Iterator;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JComponent;

public class GraphicHashi extends JComponent {
	
	private static final long serialVersionUID = -6577295268064498L;
	private static final int SCALE = 25;
	
	// ATTRIBUTS
	private DisplayModel model;
	private Hashi hashi;
	private Graphics graphique;
	
    // CONSTRUCTEURS 
    
    public GraphicHashi() {
    	model = new StdDisplayModel();
    	model.setLineDraw(false);
    	createController();
    }
    
    // REQUETES
    
    public DisplayModel getModel() {
    	return model;
    }
    
    // COMMANDES
    
    public void paintComponent(Graphics g) {
    	graphique = g;
    	super.paintComponent(g);
    	g.setColor(Color.WHITE);
    	g.fillRect(0, 0, getWidth(), getHeight());
    	Graphics2D g2d = (Graphics2D) g;
    	g2d.setFont(new Font("sansserif", Font.BOLD, SCALE));
    	if (model.isGenerate()) {
    		hashi = model.getHashi();
    	} else if (model.isFile()) {
    		hashi = model.getHashi();
    	} 
    	if (model.isGenerate() || model.isFile()) {
    		if (model.isLineDraw()) {    		
    			Map<String, Line> lines;
    			if (model.isFile()) {
    				lines = 
    				hashi.getCurrentLines();     			
    			} else {
    				lines = 
    				hashi.getSolutionLines(); 	
    			}
    			Iterator<Line> it = lines.values().iterator();
    			while (it.hasNext()) {
    				Line l = it.next();
    				model.drawLine(g2d, l);
    			}
    		}
    		model.setIsSolved(true);
    		Map<String, Island> nodes = hashi.getIsland();
    		Iterator<Island> it2 = nodes.values().iterator();
    		while (it2.hasNext()) {
    			Island n = it2.next();
    			if (n != null) {
    				model.drawIsland(g2d, n.x(),
    					n.y(), n.firstValue());
    			}
    		}
    	}         
    }

	private void createController() {
    	((Observable) model).addObserver(new Observer() {
            @Override
            public void update(Observable o, Object arg) {
                refresh();
            }
        });
    }
    
    private void refresh() {
    	repaint();
    }
    
    public Graphics getGraphe() {
		return graphique;
	}
}
