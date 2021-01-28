package hashi;

public interface Island {
	
	/**.
	 * Retourne les coordonnées sous forme de chaîne de caractère
	 */
	String toString();
	
	/**
	 * Retourne l'entier i sous forme de 
	 * chaîne de caractère composé de 2 chiffres.
	 */ 
	String formatInt(int i);
	
	/**.
	 * Indique si elle est créée
	 */
	void created();
	
	/**
	 * Indique sa première valeur.
	 */
	int firstValue();

	/**
	 * Retourne vrai si elle à été créée faux sinon.
	 */
	boolean isCreated();
	
	/**
	 * Retourne l'abscisse.
	 */
	int x();

	/**
	 * Retourne l'ordonne.
	 */
	int y();
	
	/**
	 * Retourne le nombre de connexion.
	 */
	int value();
	
	/**
	 * Ajoute <code> add </code> à sa valeur.
	 */
	void addValue(int add);

	/**
	 * Retire <code> add </code> à sa valeur.
	 */
	void removeValue(int add);
	
	/**
	 * Remplace la valeur par <code> v </code>.
	 */
	void changeValueTo(int v);
	
	/**
	 * Retourne le voisin dans la direction représenté
	 * par <code> i </code>.
	 */
	Island getNeighborIndex(int i);
	
	/**
	 * Retourne les voisins.
	 */
	Island[] getNeighbor();
	
	/**.
	 * Retire le voisin dans la direction <code> i </code>
	 */
	Island setNeighbor(int i);
	
	/**.
	 * Initialise et conserve le voisin <code> island </code>
	 * dans la direction <code> i </code>
	 */
	Island nextNeighbor(int i, Island island);
	
	/**
	 * Initialise la première valeur à <code> v </code>.
	 */
	void setInitialValue(int v);
}
