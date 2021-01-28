package hashi;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.File;
import java.util.Observable;

import javax.swing.JOptionPane;

import util.Contract;

public class StdDisplayModel extends Observable implements DisplayModel {	
	
	private static final int CIRCLE_SIZE = 45;
	private static final int LINE_SIZE = 30;
	private static final int SCALE = 50;
	private static final int SCALEBIS = 13;

	
	private File f;
	private boolean isLineDraw;
	private Hashi hashi;
	private Hashi hashiFile;
	private HashiCreation maker;
	private boolean isGenerate;
	private boolean isFile;
	private boolean isSolved;
	private Graph graphe;
	
	public StdDisplayModel() {
		f = null;
		isLineDraw = false;
		isGenerate = false;
		isFile = false;
	}
	
	public StdDisplayModel(File file) {
		Contract.checkCondition(file != null,
				"StdDisplayModel: file invalid");

		f = file;
		isLineDraw = false;
		isGenerate = false;
		isFile = false;
	}
	
	// REQUETES
	
	public File getFile() {
		return f;
	}
	
	@Override
	public void changeFor(File file) {
		Contract.checkCondition(file 
				!= null, "changeFor: file invalid");
        isGenerate = false;

		isLineDraw = false;
		f = file;
		graphe = new StdGraph(f);
		hashi = graphe.matriceToHashi();
		hashi = new StdHashi(hashi);
		isFile = true;
		
		setChanged();
		notifyObservers();
	}
	
    public void drawLine(Graphics2D g, Line l) {
    	if (l.x1() == l.x2() && l.y1() == l.y2() && isFile()) {
    		JOptionPane.showMessageDialog(null,
    				"Ce jeu ne peut etre resolu", 
    	            "Essayez encore", JOptionPane.ERROR_MESSAGE);
    	}
        boolean hasTwo = l.hasTwo();
        g.setColor(Color.black);
        if (l.x1() == l.x2()) {
            g.drawLine(l.x1() * SCALE + SCALEBIS,
                l.y1() * SCALE + CIRCLE_SIZE,
                l.x2() * SCALE + SCALEBIS, l.y2() * SCALE);
            if (hasTwo) {
                g.drawLine(l.x1() * SCALE + LINE_SIZE, l.y1() * SCALE 
                		+ CIRCLE_SIZE,
                		l.x2() * SCALE + LINE_SIZE, l.y2() * SCALE);
            }
        } else {
            g.drawLine(l.x1() * SCALE + CIRCLE_SIZE,
                l.y1() * SCALE + SCALEBIS,
                l.x2() * SCALE, l.y2() * SCALE + SCALEBIS);
            if (hasTwo) {
                g.drawLine(l.x1() * SCALE + CIRCLE_SIZE, l.y1() * SCALE 
                		+ LINE_SIZE,
                		l.x2() * SCALE, l.y2() * SCALE + LINE_SIZE);
            }
        }
        
    }

    public void drawIsland(Graphics2D g, int x, int y, int value) {
        g.setColor(Color.black);
        if (isFile()) {
        	g.drawOval(x * SCALE, y * SCALE, CIRCLE_SIZE, CIRCLE_SIZE);
        	g.drawString("" + value, x * SCALE + SCALEBIS,
        		y * SCALE + (CIRCLE_SIZE - SCALEBIS));
        } else {
        	g.drawOval(x * SCALE, y * SCALE, CIRCLE_SIZE, CIRCLE_SIZE);
        	g.drawString("" + value, x * SCALE + SCALEBIS,
        		y * SCALE + (CIRCLE_SIZE - SCALEBIS));
        }
    }
    
    public boolean isLineDraw() {
    	return isLineDraw;
    }
    
    public void setLineDraw(boolean bool) {
    	isLineDraw = bool;
    }

    public void setHashi(Hashi h) {
        this.hashi = h;
    }

	public Hashi getHashi() {
		return this.hashi;
	}
	
	public void doGeneration(int x) {
		if (x <= 4 || x > SCALEBIS + 3) {
			JOptionPane.showMessageDialog(null,
				"Dimension incorrecte", 
	            "Essayez encore", JOptionPane.ERROR_MESSAGE);
		} else {
			isLineDraw = false;
			isFile = false;
	
			maker = new StdHashiCreation();
	        hashi = maker.newHashi(x);
	        isGenerate = true;
			setChanged();
			notifyObservers();
		}
	}
	
	public boolean isGenerate() {
		return isGenerate;
	}
	
	public boolean isFile() {
		return isFile;
	}

	public void solve() {
    	Solver solved = new StdSolver(hashi);
    	solved.solveGame();
		isSolved = false;
		isLineDraw = true;
		setChanged();
		notifyObservers();
	}
	
	public boolean isSolved() {
		return isSolved;
	}
	
	public void setIsSolved(boolean sol) {
		isSolved = sol;
	}
}
