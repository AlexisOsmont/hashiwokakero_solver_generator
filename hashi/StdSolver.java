package hashi;

public class StdSolver implements Solver {
	private Hashi hashi;

	public StdSolver(Hashi h) {
		this.hashi = h;
		for (Island n : this.hashi.getIsland().values()) {
			tryNeighbor(n);
		}
		for (Island n : this.hashi.getIsland().values()) {
			easyToSolve(n);
		}
	}

	public boolean solveIsland(Island n) {
		return backTracking(n) || solveIslandsNeighbor(n);
	}

	public boolean checkAll() {
		boolean b = false;
		for (Island n : this.hashi.getIsland().values()) {
			b = solveIsland(n);
		}
		return b;
	}

	public boolean solveHashi() {
		boolean isNotTheSame = true;
		while (isNotTheSame) {
			isNotTheSame = checkAll();
		}
		return isNotTheSame;
	}

	public boolean solveGame() {
		solveHashi();
		if (this.hashi.isWin()) {
			return true;
		} else {
			Hashi save = new StdHashi(this.hashi);
			for (Island n : save.getIsland().values()) {
				this.tryNeighbor(n);
			}

			for (Island n : save.getIsland().values()) {
				if (n.value() > 0) {
					for (int i = 0; i < 4; i++) {
						if (n.getNeighborIndex(i) 
							!= null && n.
							getNeighborIndex(i).
							value() > 0) {
							this.tryLines(n,
								i, false);
							if (this.
								hashi.
								isWin()) {
								return true;
							} else {
								if (
								solveGame()) {
									return 
									true;
								} else {
									this.
									hashi =
									save;
								}
							}
						}
					}
				}
			}
		}
		return false;
	}

    public boolean solveIslandsNeighbor(Island n) {
        if (n.value() == 0) {
            return false;
        }
        boolean isNotTheSame = false;
        int pL = 0;
        int nValue = (n.value() == 1) ? 1 : 2;
        for (int i = 0; i < 4; i++) {
            if (n.getNeighborIndex(i) != null) {
                if (n.getNeighborIndex(i).value() == 0) {
                	removeLines(n, i);
                    continue;
                }
                int neighbourValue = (n.getNeighborIndex(i).value() 
                		== 1) ? 1 : 2;
                pL += Math.min(nValue, neighbourValue);
            }
        }
        if (n.value() + 1 == pL) {
            for (int i = 0; i < 4; i++) {
                if (n.getNeighborIndex(i) != null 
                		&& n.getNeighborIndex(i).value() >= 2) {
                    this.tryLines(n, i, false);
                    isNotTheSame = true;
                }
            }
        }
        return isNotTheSame;
    }

    public void easyToSolve(Island n) {
        if (n.firstValue() == 0) {
            return;
        }
        if (n.firstValue() == 1) {
            for (int i = 0; i < 4; i++) {
                if (n.getNeighborIndex(i) != null 
                		&& n.getNeighborIndex(i).firstValue() 
                		== 1) {
                	removeLines(n, i);
                }
            }
        }
    }

    public boolean backTracking(Island n) {
        if (n.value() == 0) {
            return false;
        }
        boolean isNotTheSame = false;
        int pL = 0;
        int nValue = (n.value() == 1) ? 1 : 2;
        for (int i = 0; i < 4; i++) {
            if (n.getNeighborIndex(i) != null) {
                if (n.getNeighborIndex(i).value() == 0) {
                	removeLines(n, i);
                    continue;
                }
                int neighbourValue =
                		(n.getNeighborIndex(i).value() == 1) ? 1 : 2;
                pL += Math.min(nValue, neighbourValue);
            }
        }
        if (n.value() == pL) {
            for (int i = 0; i < 4; i++) {
                if (n.getNeighborIndex(i) != null) {
                    tryLines(n, i, (n.value() >= 2 
                    		&& n.getNeighborIndex(i).value() >= 2));
                    isNotTheSame = true;
                }
            }
        }
        return isNotTheSame;
    }

    //	Supprime les lignes

    public void removeLines(Island i, int y) {
        if (y <= 1) {
            i.getNeighborIndex(y)
            		.setNeighbor(y + 2);
        } else {
        	i.getNeighborIndex(y)
    		.setNeighbor(y - 2);
        }
        i.setNeighbor(y);
    }

    public void tryLines(Island i,
    		int y, boolean hT) {
        Island destI = i.getNeighborIndex(y);
        //	Crée les lignes courantes pour resoudre le jeu
        this.hashi.addCurrentLine(new Line(i, destI, false));
        if (i.getNeighborIndex(y).value() == 0 
        		|| destI.value() == 0) {
        	removeLines(i, y);
        }
        if (hT) {
            this.hashi.addCurrentLine(new Line(i,
            		destI, hT));
            removeLines(i, y);
        }
        //	sonde les voisins pour pouvoir continuer la resolution
        for (Island nextIsland : this.hashi.getIsland().values()) {
            if (nextIsland == i || nextIsland == destI) {
                continue;
            }
            if ((y == 0 && nextIsland.y() 
            		< i.y() 
            		&& nextIsland.y() > destI.y()) 
            		|| (y == 2 
            		&& nextIsland.y() > i.y() 
            		&& nextIsland.y() < destI.y())) {
                if (nextIsland.getNeighborIndex(1) != null 
                		&& nextIsland.x() < i.x()
                		&& nextIsland.getNeighborIndex(1).x() 
                		> i.x()) {
                	removeLines(nextIsland, 1);
                } else if (nextIsland.getNeighborIndex(3) != null 
                		&& nextIsland.x() > i.x()
                		&& nextIsland.getNeighborIndex(3).x()
                		< i.x()) {
                	removeLines(nextIsland, 3);
                }
            }
            if ((y == 1 
            		&& nextIsland.x() > i.x() 
            		&& nextIsland.x() < destI.x()) 
            		|| (y == 3 
            		&& nextIsland.x() < i.x()
            		&& nextIsland.x() > destI.x())) {
                if (nextIsland.getNeighborIndex(0) != null 
                		&& nextIsland.y() > i.y() 
                		&& nextIsland.getNeighborIndex(0).y()
                		< i.y()) {
                	removeLines(nextIsland, 0);
                } else if (nextIsland.getNeighborIndex(2) != null 
                		&& nextIsland.y() < i.y() 
                		&& nextIsland.getNeighborIndex(2).y() 
                		> i.y()) {
                	removeLines(nextIsland, 2);
                }
            }
        }
    }

    public void tryNeighbor(Island i) {
        for (Island nextIsland : this.hashi.getIsland().values()) {
            if (nextIsland == i) {
                continue;
            }
            if (nextIsland.x() == i.x()) {
                if (nextIsland.y() < i.y()) {
                    if (i.getNeighborIndex(0) == null 
                    		|| nextIsland.y() > i.getNeighborIndex(0).y()) {
                        i.nextNeighbor(0, nextIsland);
                    }
                } else {
                    if (i.getNeighborIndex(2) == null 
                    		|| nextIsland.y() < i.getNeighborIndex(2).y()) {
                       i.nextNeighbor(2, nextIsland);
                    }
                }
            } else if (nextIsland.y() == i.y()) {
                if (nextIsland.x() < i.x()) {
                    if (i.getNeighborIndex(3) == null 
                    		|| nextIsland.x() > i.getNeighborIndex(3).x()) {
                        i.nextNeighbor(3, nextIsland);
                    }
                } else {
                    if (i.getNeighborIndex(1) == null 
                    		|| nextIsland.x() < i.getNeighborIndex(1).x()) {
                        i.nextNeighbor(1, nextIsland);
                    }
                }
            }
        }
    }
}
