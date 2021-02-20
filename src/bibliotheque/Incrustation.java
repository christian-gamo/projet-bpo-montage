/**
 * @file Incrustation.java
 * Projet BPO - IUT de Paris
 * @author Christian Gamo & Monica Huynh
 * @version 1 - 01/05/2020
 * @brief Type de donnée représentant l'incrustation d'un film sur un autre
 */

package bibliotheque;

import java.util.Arrays;

/** Type de donnée représentant l'inscrustation d'un film sur un autre */

public class Incrustation implements FilmCopiable{
	/** Le film servant de fond */
	private FilmCopiable base;
	/** Le film inscrusté */
	private FilmCopiable aIncruste;
	/** Numéro de la ligne du point d'incrustation du film aIncruste */
	private int ligne;
	/** Numéro de la colonne du point d'incrustation du film aIncruste */
	private int colonne;
	
	/**
	 * Constructeur
	 * @param f1 Le film servant de base
	 * @param f2 Le film à incruster dans la base
	 * @param x Le numéro de la ligne 
	 * du point d'incrustation du film à incruster
	 * @param y Le numéro de la colonne 
	 * du point d'incrustation du film à incruster
	 */
	public Incrustation(FilmCopiable f1, FilmCopiable f2, int x, int y) {
		assert(f1 != null && f2 != null);
		base = f1;
		aIncruste = f2;
		ligne = x;
		colonne = y;
	}
	
	/**
	 * Spécialise la méthode copie() de FilmCopiable
	 * @return Retourne une copie d'une instance de Incrustation
	 */
	@Override
	public FilmCopiable copie() {
			return new Incrustation(base, aIncruste, ligne, colonne);
	}
	
	/**
	 * Indique la hauteur des images de ce film (en nombre de caractères).
	 * 
	 * @return Hauteur minimale de l'écran pour pouvoir afficher les images de
	 *         ce film.
	 */
	@Override
	public int hauteur() {
		return base.hauteur();
	}

	/**
	 * Indique la largeur des images de ce film (en nombre de caractères).
	 * 
	 * @return Largeur minimale de l'écran pour pouvoir afficher les images de
	 *         ce film.
	 */
	@Override
	public int largeur() {
		return base.largeur();
	}
		
	/**
	 * Spécialise la méthode suivante() de FilmCopiable.
	 * Obtenir l'image suivante (s'il y en a une).
	 * 
	 * @param écran
	 *            L'écran où afficher l'image
	 * @return vrai Si l'image suivante a été affichée sur l'écran et faux si le
	 *         film est terminé
	 */
	@Override
	public boolean suivante(char[][] écran) {
		assert(écran != null);
		if (!base.suivante(écran))
			return false;
		
		char[][] écran2 = new char[aIncruste.hauteur()][aIncruste.largeur()];
		for (char[] lignes : écran2)
            Arrays.fill(lignes, ' ');
		
		int ligneIncrus = 0;
		int colIncrus = 0;
		
		if (aIncruste.suivante(écran2) && ligne>=0 && colonne>=0) {
			for (int i = ligne; i<hauteur(); ++i, ++ligneIncrus, colIncrus = 0){
				for (int j = colonne; j<largeur(); ++j, ++colIncrus) {
					écran[i][j]=écran2[ligneIncrus][colIncrus];
				}
			}
		}
		return true;
	}

	/**
	 * Rembobine le film en permettant de rejouer le film dans sa totalité (via
	 * des appels successifs à la méthode suivante()).
	 */
	@Override
	public void rembobiner() {
		base.rembobiner();
		aIncruste.rembobiner();
	}
	
	/**
	 * Spécialise la méthode equals() de la classe Object
	 * Vérifie que tous les attributs possèdent la même valeur
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Incrustation other = (Incrustation) obj;
		if (aIncruste == null) {
			if (other.aIncruste != null)
				return false;
		} 
		else 
			if (!aIncruste.equals(other.aIncruste))
				return false;
			if (base == null) {
				if (other.base != null)
					return false;
		}
		else
			if (!base.equals(other.base))
				return false;
			if (colonne != other.colonne)
				return false;
			if (ligne != other.ligne)
				return false;
			return true;
	}
}
