package hashi;

/**
 *  Une interface qui permet de construire un graphe à partir d'un fichier.
 *  @cons 
 *     $ARGS$ 
 *     $PRE$
 *         file != null
 *     $POST$

 */
public interface Graph {

    /**
     * Retourne une matrice.
     */
    int[][] matrice();

    /**
     * Retourne la hauteur de la matrice.
     */
    int getHeight();

    /**
     * Retourne la largeur d'une matrice.
     */
    int getWidth();

    /**
     * Retourne le noeud d'abscisse <code> i </code>
     * et d'ordonnée <code> y </code>.
     */
    int nodes(int i, int y);

    /**
     * Création du puzzle.
     */
    Hashi matriceToHashi();
}
