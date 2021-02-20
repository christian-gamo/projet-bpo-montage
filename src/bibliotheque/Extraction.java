/**
 * @file Extraction.java
 * Projet BPO - IUT de Paris
 * @author Christian Gamo & Monica Huynh
 * @version 1 - 01/05/2020
 * @brief Type de donnée représentant un extrait de film
 */

package bibliotheque;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import exemple.LaDiagonaleDuFou;

/** Type de donnée représentant un extrait de film */

public class Extraction implements FilmCopiable {

	/** Film dont on va extraire une partie */
	private FilmCopiable original;
	/** Numéro de la première frame de l'extrait */
	private int noDebut;
	/** Numéro de la dernière frame de l'extrait */
	private int noFin;
	/** Compteur du numéro de frame */
	private int cpt;

	/**
	 * Constructeur
	 * @param f Le film dont on va extraire une partie (obligatoire)
	 * @param noDeb Le numéro de frame du début de l'extrait (obligatoire)
	 * @param noFin Le numéro de frame de la fin de l'extrait (obligatoire)
	 */
	public Extraction(FilmCopiable f, int noDeb, int noFin) {
		assert(f != null);
		this.original = f;
		this.noDebut = noDeb;
		this.noFin = noFin;
		this.cpt = 0;
	}
	
	 /**
	  * Méthode statique crée pour le test unitaire.
	  * Permet de vérifier la valeur de l'attribut cpt 
	  * avant et après rembobinage.
	  */
	 public static void testRembobinerCpt() {
		 FilmCopiable diag = new LaDiagonaleDuFou();
		 int noDeb = 2;
		 int noFin = 18;
		 int compteur = 0;
		 Extraction extraction = new Extraction(diag,noDeb,noFin);
		 char[][] écran = new char[extraction.hauteur()][extraction.largeur()];
		 
		 while(extraction.suivante(écran));
		 compteur = noFin + 1;
	     //= aux nombres de frames avant et pendant l'extraction du film de base
		 
		 assertEquals(extraction.cpt,compteur);
		 extraction.rembobiner();
		 assertEquals(extraction.cpt,0);
	   }
	
	/**
	 * Spécialise la méthode copie() de FilmCopiable
	 * @return Retourne une copie d'une instance de Extraction
	 */
	@Override
	public FilmCopiable copie(){
		return new Extraction(original, noDebut, noFin);
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
	 * 
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
		// écran2 est une variable qui va contenir les images
		// dont on a pas besoin pour l'extrait
		char[][] écran2 = new char[hauteur()][largeur()];
		for (char[] lignes : écran2)
            Arrays.fill(lignes, ' ');
		
		while (cpt < noDebut) {
			++cpt;
			original.suivante(écran2);
		}

		if (cpt <= noFin) {
			++cpt;
			return original.suivante(écran);
		}
		else
			return false;
	}

	/**
	 * Rembobine le film en permettant de rejouer le film dans sa totalité (via
	 * des appels successifs à la méthode suivante()).
	 */
	@Override
	public void rembobiner() {
		this.cpt = 0;
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
		Extraction other = (Extraction) obj;
		if (cpt != other.cpt)
			return false;
		if (noDebut != other.noDebut)
			return false;
		if (noFin != other.noFin)
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
