package hashi;

import java.util.ArrayList;
import java.util.List;

public class StdHashiCreation implements HashiCreation {

	private Hashi hashi;

	private boolean[][] blockedWay;

	public Hashi newHashi(int dim) {
		hashi = new StdHashi(dim);
		blockedWay = new boolean[dim][dim];
		for (int i = 0; i < blockedWay.length; i++) {
			for (int j = 0; j < blockedWay[i].length; j++) {
				blockedWay[i][j] = false;
			}
		}

		// on commence la creation par des coordonnées random
		// pour ensuite explorer tous les voisins possibles
		int x = this.getRandom(dim - 1);
		int y = this.getRandom(dim - 1);
		List<Island> notSolvedIsland = new ArrayList<Island>();
		notSolvedIsland.add(new StdIsland(x, y, 0));
		while (notSolvedIsland.size() > 0) {
			int selection = this.getRandom(
					notSolvedIsland.size() - 1);
			Island n = notSolvedIsland.get(selection);
			List<Island> pI = allPossibleWays(
					n.x(), n.y());
			if (pI.isEmpty()) {
				notSolvedIsland.remove(n);
			} else {
				int newRand = this.getRandom(
						pI.size() - 1);
				Island newIsland = pI.get(newRand);
				notSolvedIsland.add(newIsland);

				int randLine = this.getRandom(1);
				Line line;
				if (randLine == 0) {
					line = new Line(n, newIsland, false);
				} else {
					line = new Line(n, newIsland, true);
				}
				hashi.addSolutionLine(line);
				blockWay(line);
			}
		}
		this.allPossibleWays(3, 4);
		hashi.created();
		return hashi;
	}

	public void blockWay(Line line) {
		int x1;
		int x2;
		if (line.x1() <= line.x2()) {
			x1 = line.x1();
			x2 = line.x2();
		} else {
			x1 = line.x2();
			x2 = line.x1();
		}
		for (int i = x1; i <= x2; i++) {
			blockedWay[i][line.y1()] = true;
		}
		int y1;
		int y2;
		if (line.y1() <= line.y2()) {
			y1 = line.y1();
			y2 = line.y2();
		} else {
			y1 = line.y2();
			y2 = line.y1();
		}
		for (int i = y1; i <= y2; i++) {
			blockedWay[line.x1()][i] = true;
		}
	}
	
	public boolean isUnblocked(int x, int y) {
		// si bloqué au nord
		if (y - 1 >= 0 && hashi.getIslandValue(x, y - 1) != 0) {
			return false;
		}
		// si bloqué a lEst
		if (x + 1 < hashi.getDim() 
				&& hashi.getIslandValue(x + 1, y) != 0) {
			return false;
		}
		// si bloqué au Sud
		if (y + 1 < hashi.getDim() 
				&& hashi.getIslandValue(x, y + 1) != 0) {
			return false;
		}
		// si bloqué a l'Ouest
		if (x - 1 >= 0 && hashi.getIslandValue(x - 1, y) != 0) {
			return false;
		}
		// sinon non bloqué
		return true;
	}

	public int getRandom(int max) {
		return (int) Math.round(Math.random() * max);
	}

	public List<Island> allPossibleWays(int x, int y) {
		List<Island> result = new ArrayList<Island>();
		int currentCoord;
		int dim = hashi.getDim();

		// Creation vers le Nord
		if (y >= 2) {
			currentCoord = -1;
			for (int i = y - 1; i >= 0; i--) {
				if (blockedWay[x][i]) {
					currentCoord = i;
					break;
				}
			}
			for (int i = y - 2; i > currentCoord; i--) {
				if (isUnblocked(x, i)) {
					result.add(new StdIsland(x, i, 0));
				}
			}
		}

		// Creation vers l'Est
		if (x + 2 < dim) {
			currentCoord = dim;
			for (int i = x + 1; i < dim; i++) {
				if (blockedWay[i][y]) {
					currentCoord = i;
					break;
				}
			}
			for (int i = x + 2; i < currentCoord; i++) {
				if (isUnblocked(i, y)) {
					result.add(new StdIsland(i, y, 0));
				}
			}
		}
		// Creation vers le Sud
		if (y + 2 < dim) {
			currentCoord = dim;
			for (int i = y + 1; i < dim; i++) {
				if (blockedWay[x][i]) {
					currentCoord = i;
					break;
				}
			}
			for (int i = y + 2; i < currentCoord; i++) {
				if (isUnblocked(x, i)) {
					result.add(new StdIsland(x, i, 0));
				}
			}
		}
		// Creation vers l'Ouest
		if (x >= 2) {
			currentCoord = -1;
			for (int i = x - 1; i >= 0; i--) {
				if (blockedWay[i][y]) {
					currentCoord = i;
					break;
				}
			}
			for (int i = x - 2; i > currentCoord; i--) {
				if (isUnblocked(i, y)) {
					result.add(new StdIsland(i, y, 0));
				}
			}
		}
		return result;
	}
}
