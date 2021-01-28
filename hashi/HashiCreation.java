package hashi;

import java.util.List;

public interface HashiCreation {

	//	creer un nouveau jeu de hashi
	Hashi newHashi(int dim);
	
	//	creer un nombre random avec pour max l'argument
	int getRandom(int max);
	
	//	return les chemins possibles en fonction des
	//	nombres random et des île deja presente 
	List<Island> allPossibleWays(int x, int y);
	
	//	return si le chemin en x,y est bloqué ou non
	boolean isUnblocked(int x, int y);
	
	//	bloque les coordonnées de la ligne qui vient 
	//	d'etre créée
	void blockWay(Line line);
}
