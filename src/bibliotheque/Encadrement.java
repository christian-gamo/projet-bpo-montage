/**
 * @file Encadrement.java
 * Projet BPO - IUT de Paris
 * @author Christian Gamo & Monica Huynh
 * @version 1 - 01/05/2020
 * @brief Type de donnée représentant un film encadré
 */

package bibliotheque;

import java.util.Arrays;

/** Type de donnée représentant un film encadré */

public class Encadrement implements FilmCopiable {
	/** Le film que l'on va encadrer */
	private FilmCopiable original;
	/** Nombre de caractères nécessaire pour le placement du cadre */
	private static final int BORDS = 2;
	/** Caractère formant le cadre */
	private static final char CADRE = '*';

	/**
	 * Constructeur
	 * 
	 * @param f Le film que l'on va encadrer (obligatoire)
	 */
	public Encadrement(FilmCopiable f) {
		assert (f != null);
		this.original = f;
	}

	/**
	 * Spécialise la méthode copie() de FilmCopiable
	 * 
	 * @return Retourne une copie d'une instance de Encadrement
	 */
	@Override
	public FilmCopiable copie() {
		return new Encadrement(original);
	}

	/**
	 * * Indique la hauteur des images de ce film (en nombre de caractères).
	 * 
	 * @return Hauteur minimale de l'écran pour pouvoir afficher les images de
	 *         ce film.
	 */
	@Override
	public int hauteur() {
		return this.original.hauteur() + BORDS;
	}

	/**
	 ** Indique la largeur des images de ce film (en nombre de caractères).
	 * 
	 * @return largeur minimale de l'écran pour pouvoir afficher les images de
	 *         ce film.
	 */
	@Override
	public int largeur() {
		return this.original.largeur() + BORDS;
	}

	/**
	 * Spécialise la méthode suivante() de FilmCopiable. Obtenir l'image
	 * suivante (s'il y en a une).
	 * 
	 * @param écran L'écran où afficher l'image
	 * @return vrai Si l'image suivante a été affichée sur l'écran et faux si le
	 *         film est terminé
	 */
	@Override
	public boolean suivante(char[][] écran) {
		assert(écran != null);
		char[][] écran2 = new char[original.hauteur()][original.largeur()];
		for (char[] ligne : écran2)
			Arrays.fill(ligne, ' ');

		if (!original.suivante(écran2))
			return false;

		for (int lig = 0; lig < original.hauteur(); ++lig) {
			for (int col = 0; col < original.largeur(); ++col) {
				écran[lig + 1][col + 1] = écran2[lig][col];
			}
		}

		for (int i = 0; i < largeur(); ++i) {
			écran[0][i] = CADRE; // Cadre du haut
			écran[hauteur() - 1][i] = CADRE; // Cadre du bas
		}

		for (int j = 0; j < hauteur(); ++j) {
			écran[j][0] = CADRE; // Cadre de gauche
			écran[j][largeur() - 1] = CADRE; // Cadre de droite
		}

		return true;
	}

	/**
	 * Rembobine le film en permettant de rejouer le film dans sa totalité (via
	 * des appels successifs à la méthode suivante()).
	 */
	@Override
	public void rembobiner() {
		this.original.rembobiner();
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
		Encadrement other = (Encadrement) obj;
		if (original == null) {
			if (other.original != null)
				return false;
		} 
		else if (!original.equals(other.original))
			return false;
		return true;
	}
}
