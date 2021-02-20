/**
 * @file Repetition.java
 * Projet BPO - IUT de Paris
 * @author Christian Gamo & Monica Huynh
 * @version 1 - 01/05/2020
 * @brief Type de donnée représentant un film répété
 */

package bibliotheque;
import static org.junit.Assert.*;

import exemple.LaDiagonaleDuFou;
/** Type de donnée représentant un film répété */


public class Repetition implements FilmCopiable {
	/** Film que l'on va répéter */
	private FilmCopiable original;
	/** Nombre de répétitions voulu */
	private int nbRepet;
	/** Compteur du nombre de répétitions effectué */
	private int cptRepet;
	
	/**
	 * Constructeur
	 * @param f Le film que l'on va répété (obligatoire)
	 * @param nb Le nombre de répétitions voulu (obligatoire)
	 */
	public Repetition(FilmCopiable f, int nb) {
		assert(f != null);
		this.original = f;
		this.nbRepet = nb;
		this.cptRepet = 0;
		
	}
	
	/**
	  * Méthode statique crée pour le test unitaire.
	  * Permet de vérifier la valeur de l'attribut cptRepet 
	  * après instanciation
	  */
	public static void testBlancCptRepet0() {
		FilmCopiable diag = new LaDiagonaleDuFou();
	    Repetition repet = new Repetition(diag, 1);
	    assertEquals(0, repet.cptRepet);
	}
	
	/**
	  * Méthode statique crée pour le test unitaire.
	  * Permet de vérifier la valeur de l'attribut cpt 
	  * avant et après rembobinage.
	  */
	public static void testBlancCptRepet() {
	    FilmCopiable diag = new LaDiagonaleDuFou();
	    Repetition repet = new Repetition(diag, 1);
	    char[][] écran = new char[repet.hauteur()][repet.largeur()];
		while(repet.suivante(écran));
	    assertFalse(repet.cptRepet == 0);
    }
	

	/**
	 * Spécialise la méthode copie() de FilmCopiable
	 * @return Retourne une copie d'une instance de Repetition
	 */
	@Override
	public FilmCopiable copie() {
		return new Repetition(original, nbRepet);
	}
	
	/**
	 * Indique la hauteur des images de ce film (en nombre de caractères).
	 * 
	 * @return Hauteur minimale de l'écran pour pouvoir afficher les images de
	 *         ce film.
	 */
	@Override
	public int hauteur() {
		return this.original.hauteur();
	}

	/**
	 * Indique la largeur des images de ce film (en nombre de caractères).
	 * 
	 * @return Largeur minimale de l'écran pour pouvoir afficher les images de
	 *         ce film.
	 */
	@Override
	public int largeur() {
		return this.original.largeur();
	}
	
	/**
	 * Spécialise la méthode suivante() de FilmCopiable
	 * Obtenir l'image suivante (s'il y en a une).
	 * 
	 * @param écran
	 *            L'écran où afficher l'image
	 * @return vrai Si l'image suivante a été affichée sur l'écran 
	 * 		   et faux si le film est terminé, donc a atteint 
	 * 		   le nombre de répétitions souhaité
	 */
	@Override
	public boolean suivante(char[][] écran) {
		assert(écran != null);
		
		if (!original.suivante(écran)) {
			original.rembobiner();
			original.suivante(écran);
			++cptRepet;
		}
		return cptRepet < nbRepet;
	}

	/**
	 * Rembobine le film en permettant de rejouer le film dans sa totalité (via
	 * des appels successifs à la méthode suivante()).
	 */
	@Override
	public void rembobiner() {
		this.cptRepet = 0;
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
		Repetition other = (Repetition) obj;
		if (cptRepet != other.cptRepet)
			return false;
		if (nbRepet != other.nbRepet)
			return false;
		if (original == null) {
			if (other.original != null)
				return false;
		}
		else
			if (!original.equals(other.original))
				return false;
		return true;
	}
}