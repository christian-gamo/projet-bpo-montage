/**
 * @file Extension.java
 * Projet BPO - IUT de Paris
 * @author Christian Gamo & Monica Huynh
 * @version 1 - 01/05/2020
 * @brief Type de donnée représentant un film composé d'un collage de deux films
 */

package bibliotheque;

import static org.junit.Assert.*;

import exemple.LaDiagonaleDuFou;

/** Type de donnée représentant un film composé d'un collage de deux films */

public class Extension implements FilmCopiable {
	/** Le film qui compose la première partie du film collé */
	private FilmCopiable partie1;
	/** Le film qui compose la deuxième partie du film collé */
	private FilmCopiable partie2;
	/** Booléan indiquant si la première partie a été vue */
	private boolean partie1Fini;

	/**
	 * Constructeur
	 * 
	 * @param premier  Le film qui compose la première partie du film collé
	 *                 (obligatoire)
	 * @param deuxieme Le film qui compose la deuxième partie du film collé
	 *                 (obligatoire)
	 */
	public Extension(FilmCopiable premier, FilmCopiable deuxieme) {
		assert (premier != null && deuxieme != null);
		this.partie1 = premier;
		this.partie2 = deuxieme;
		this.partie1Fini = false;
	}

	/**
	 * Méthode statique crée pour le test unitaire. Permet de vérifier la valeur
	 * de l'attribut partie1Fini avant et après rembobinage.
	 */
	public static void testRembobinerPartie1Fini() {
		FilmCopiable diag1 = new LaDiagonaleDuFou();
		FilmCopiable diag2 = new LaDiagonaleDuFou();
		Extension extend = new Extension(diag1, diag2);
		char[][] écran = new char[extend.hauteur()][extend.largeur()];
		
		while (extend.suivante(écran))
			;

		assertTrue(extend.partie1Fini);
		extend.rembobiner();
		assertFalse(extend.partie1Fini);
	}

	/**
	 * * Spécialise la méthode copie() de FilmCopiable
	 * 
	 * @return Retourne une copie d'une instance de Extension
	 */
	@Override
	public FilmCopiable copie() {
		return new Extension(partie1, partie2);
	}

	/**
	 * * Indique la hauteur des images de ce film (en nombre de caractères).
	 * 
	 * @return Hauteur minimale de l'écran pour pouvoir afficher les images de
	 *         ce film.
	 */
	@Override
	public int hauteur() {
		if (partie1.hauteur() > partie2.hauteur())
			return partie1.hauteur();
		else
			return partie2.hauteur();
	}

	/**
	 * Indique la largeur des images de ce film (en nombre de caractères).
	 * 
	 * @return largeur minimale de l'écran pour pouvoir afficher les images de
	 *         ce film.
	 */
	@Override
	public int largeur() {
		if (partie1.largeur() > partie2.largeur())
			return partie1.largeur();
		else
			return partie2.largeur();

	}

	/**
	 * Spécialise la méthode suivante() de FilmCopiable Obtenir l'image suivante
	 * (s'il y en a une).
	 * 
	 * @param écran L'écran où afficher l'image
	 * @return vrai Si l'image suivante a été affichée sur l'écran et faux si le
	 *         film est terminé (les deux parties sont terminés)
	 */
	@Override
	public boolean suivante(char[][] écran) {
		assert(écran != null);
		
		if (!partie1Fini) {
			if (partie1.suivante(écran))
				return true;
			else {
				partie1Fini = true;
				partie2.rembobiner();
				return partie2.suivante(écran);
			}
		} 
		else
			return partie2.suivante(écran);
	}

	/**
	 * Rembobine le film (en rembobinant les 2 parties) en permettant de rejouer
	 * le film dans sa totalité (via des appels successifs à la méthode
	 * suivante()).
	 */
	@Override
	public void rembobiner() {
		this.partie1.rembobiner();
		this.partie2.rembobiner();
		partie1Fini = false;
	}

	/**
	 * Spécialise la méthode equals() de la classe Object Vérifie que tous les
	 * attributs possèdent la même valeur
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Extension other = (Extension) obj;
		if (partie1 == null) {
			if (other.partie1 != null)
				return false;
		} 
		else if (!partie1.equals(other.partie1))
			return false;
		if (partie1Fini != other.partie1Fini)
			return false;
		if (partie2 == null) {
			if (other.partie2 != null)
				return false;
		} 
		else if (!partie2.equals(other.partie2))
			return false;
		return true;
	}

}
