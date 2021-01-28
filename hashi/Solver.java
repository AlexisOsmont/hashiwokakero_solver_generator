package hashi;

public interface Solver {
	/**
	 * Retourne vrai si l'île <code> n </code> est résolue, faux sinon.
	 */
	boolean solveIsland(Island n);
	
	/**
	 * Retourne vrai si tout les îles sont résolues, faux sinon.
	 */
	boolean checkAll();
	
	/**
	 * Résout l'île <code> n </code> si elle est dans une 
	 * configuration simple
	 * (ex : bord du puzzle et il faut faire 2 connections.).
	 */
	void easyToSolve(Island n);
	
	/**
	 * 
	 */
	boolean backTracking(Island n);
	
	/**
	 * Supprime le(s) ligne(s) de l'île <code> n </code>
	 * avec son voisins dans la direction <code> indexOfOtherIsland </code>.
	 */
	void removeLines(Island n, int indexOfOtherIsland);
	
	/**
	 * Lie l'île <code> sourceIsland </code> au voisin dans la direction
	 * <code> indexOfDestIsland </code> avec une double liaison si 
	 * 	<code> doubleLine </code>vaut vrai, une simple sinon.
	 */
	void tryLines(Island sourceIsland,
    		int indexOfDestIsland, boolean doubleLine);
	
	/**
	 * Retourne vrai si le puzzle à été résolu, faux sinon.
	 */
	boolean solveHashi();
	
	/**
	 * Mécanisme de résolution d'un puzzle.
	 * Appliquant l'algorithme d'exploration avec rebroussement
	 */
	boolean solveGame();
	
	/**
	 * Lie correctement l'île <code> n </code> à ses voisins.
	 */
	boolean solveIslandsNeighbor(Island n);
	
	/**
	 * Cherche les voisins de l'île <code> n </code>.
	 */
	void tryNeighbor(Island n);
}
