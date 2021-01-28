package hashi;

import java.util.Map;

public interface Hashi {
	
    //	REQUETES
    
	//	retourne la liste des lignes courrantes
    Map<String, Line> getSolutionLines();

    //	retourne la liste des lignes de solution
    Map<String, Line> getCurrentLines();

    //	retournes la liste d'îles qui composent le jeu
    Map<String, Island> getIsland();
    
    //	ajoute une île au jeu aux coordonnées et avec
    //	pour valeur amount
    void addIsland(int x, int y, int amount);
    
    //	retourne le x y de la dimension du jeu
    int getDim();


    //	indique si le jeu est crée
    void created();
    
    //	COMMANDES
	
    //	Formate les coordonées en 0x0y pour une lecture plus simple 
    //	et pour simplifier le code
	String formatInt(int x, int y);
	
	//	Ajoute de lignes de solution
    void addSolutionLine(Line line);
    
    //	Ajoute de ligne courrantes
    void addCurrentLine(Line line);

    //	Retourne l'île au coordonnées en paramtre
    int getIslandValue(int x, int y);

    //	Incrémente l'île aux coordonnées pour resoudre le jeu
    void increaseIsland(int x, int y, int amount);

    //	Drécrémente l'île aux coordonnées pour resoudre le jeu
    void decreaseIsland(int x, int y, int amount);
    
    //	Indique si le jeu est resolu (
    //	si toute le iles sont 0 apres Décrémentation)
    boolean isWin();
}
