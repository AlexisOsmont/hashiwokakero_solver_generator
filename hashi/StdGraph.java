package hashi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import util.Contract;

import javax.swing.JOptionPane;

public class StdGraph implements Graph {
	
	private static final int TAILLE_MAX = 20;
	// ATTRIBUTS
	
	private int hauteur;
	private int largeur;
	private Hashi hashi;

	
	private int[][] matrice = new int[TAILLE_MAX][TAILLE_MAX];
	
	// CONSTRUCTEUR
	
	public StdGraph(File file) {
		Contract.checkCondition(file != null, "StdGraph: Invalid file");
		
		for (int i = 0; i < matrice.length; i++) {
            matrice[i] = new int[TAILLE_MAX];
        }
		int c = 0;
		int i = 0;
		int j = 0;
		int k;
		try {
			BufferedReader br 
			= new BufferedReader(new FileReader(file));
			while ((c = br.read()) != -1) {
				if (c == ';') {
					break;
				}
				if (Character.getNumericValue(c) == 9) {
					JOptionPane.showMessageDialog(null,
						"Erreur fichier",
						"Erreur !",
						JOptionPane.ERROR_MESSAGE);
				}
				if ((char) c == '\n') {
					c = br.read();
					i++;
					for (k = 0; k < j; ++k) {
						matrice[i][k] =  0;
					}
					i++;
					j = 0;
				}
				if (j > 0) {
					matrice[i][j] = 0;
					++j;
				}
				matrice[i][j] = Character.getNumericValue(c);
				++j;
			}
			hauteur = i + 1;
			largeur = j;
			br.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"Erreur fichier",
					"Erreur !", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	// REQUETES
	
	public int[][] matrice() {
		int[][] trueMatrice = new int[getHeight()][getWidth()];
		for (int i = 0; i < getHeight(); ++i) {
			for (int j = 0; j < getWidth(); ++j) {
				trueMatrice[i][j] = matrice[i][j];
			}
		}
		
		return trueMatrice;
	}
	
	public int nodes(int x, int y) {
		int [][] mat = matrice();
		return mat[x][y];
	}
	
	public int getHeight() {

		return hauteur;
	}
	
	public int getWidth() {

		return largeur;
	}
	
	public Hashi matriceToHashi() {
		hashi = new StdHashi(getHeight());
		for (int i = 0; i < getHeight(); i++) {
			for (int j = 0; j < getWidth(); j++) {

				if (nodes(i, j) != 0) {
					hashi.addIsland(j, i, nodes(i, j));
				}
			}
		}
		return hashi;
	}
}
