package hashi;

import java.awt.Graphics2D;
import java.io.File;

public interface DisplayModel extends ObservableModel {
    /**
     * Change le fichier du graphe.
     * @pre <pre>
     *     file != null </pre>
     * @post <pre>
     *     getFile().equals(file) </pre>
     */
    void changeFor(File file);

    /**
     * Retourne le fichier courant.
     */
    File getFile();

    /**
     * Dessine deux ou une ligne(s) entre deux points de <code> l </code>.
     */
    void drawLine(Graphics2D g, Line l);

    /**
     * Dessine une île de valeur <code> value </code> de coordonnées
     * <code> x, y </code>.
     */
    void drawIsland(Graphics2D g, int x, int y, int value);

    /**
     * Indique si une ligne est dessiné.
     */
    boolean isLineDraw();

    /**
     * Change le statut d'une ligne dessiné.
     */
    void setLineDraw(boolean bool);

    /**
     * Retourne le puzzle.
     */
    Hashi getHashi();

    /**
     * Modifie le puzzle par le puzzle <code> h </code>.
     */
    void setHashi(Hashi h);

    /**
     * Change l'état de résolution du puzzle à <code> sol </code>.
     */
    void setIsSolved(boolean sol);

    /**
     * Déclenche l'action de génération d'un puzzle.
     */
     void doGeneration(int x);

     /**
      * Retourne vrai si la génération à été produite, faux sinon.
      */
     boolean isGenerate();

     /**
      * Retourne le fichier courant.
      */
     boolean isFile();

     /**
      * Déclenche le mécanisme de résolution du puzzle.
      */
     void solve();

     /**
      * Retourne vrai si le puzzle est résolu, faux sinon.
      */
     boolean isSolved();
}
