package bibliotheque;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import exemple.LaDiagonaleDuFou;
import film.Films;

class IncrustationTest {

	@Test
	void testSuivante() {
		FilmCopiable normal = new LaDiagonaleDuFou();
		FilmCopiable normal2 = new LaDiagonaleDuFou();
		int ligne = 2;
		int colonne = 3;
		FilmCopiable incrustation = new Incrustation(normal.copie(),
				normal2.copie(), ligne, colonne);
		
		// Film avec incrustation
		char[][] écranIncruste = Films.getEcran(incrustation);
		// Film de base
		char[][] écranBase = Films.getEcran(normal);
		// Film à incruster
		char[][] écranAIncruste = Films.getEcran(normal2);
		boolean egal = true;
		int colIncrus = colonne;
		int ligIncrus = ligne;

		while (normal2.suivante(écranAIncruste) && normal.suivante(écranBase)) {
			// Le film de base au point d'incrustation, ne possède pas le film à
			// incruster
			for (int i = 0; ligIncrus < normal.hauteur()
					- ligne; ++i, ++ligIncrus, colIncrus = colonne) {
				for (int j = 0; j < normal.largeur()
						- colonne; ++j, ++colIncrus) {
					if(écranAIncruste[i][j] != écranBase[ligIncrus][colIncrus])
						egal = false;
	
				}
			}
			Films.effacer(écranIncruste);
			Films.effacer(écranBase);
		}
		assertEquals(egal, false);

		normal2.rembobiner();
		colIncrus = colonne;
		ligIncrus = ligne;

		Films.effacer(écranIncruste);
		Films.effacer(écranAIncruste);
		incrustation.suivante(écranIncruste);
		normal2.suivante(écranAIncruste);
		while (incrustation.suivante(écranIncruste)
				&& normal2.suivante(écranAIncruste)) {
			// Le film de base avec normalement l'incrustation, possède le film
			// à incruster
			for (int i = 0; ligIncrus < normal.hauteur() - ligne; 
				 ++i, ++ligIncrus, colIncrus = colonne) {
				for (int j = 0; j < normal.largeur()
						- colonne; ++j, ++colIncrus) {
					if ((écranAIncruste[i][j] 
						== écranIncruste[ligIncrus][colIncrus]))
						egal = true;
 
					else 
						egal = false;
				}
			}
			Films.effacer(écranIncruste);
			Films.effacer(écranAIncruste);
		}
		assertEquals(egal, true);
	}

	@Test
	void testRembobiner() {
		FilmCopiable diag1 = new LaDiagonaleDuFou();
		FilmCopiable diag2 = new LaDiagonaleDuFou();
		int ligne = 2;
		int colonne = 3;
		FilmCopiable incrustation = new Incrustation(diag1.copie(),
				diag2.copie(), ligne, colonne);

		char[][] écran = Films.getEcran(incrustation);
		char[][] frame1 = Films.getEcran(incrustation);

		incrustation.suivante(frame1);
		incrustation.rembobiner();
		incrustation.suivante(écran);

		assertEquals(Films.toString(écran), Films.toString(frame1));

	}

	@Test
	void testClone() {
		FilmCopiable diag1 = new LaDiagonaleDuFou();
		FilmCopiable diag2 = new LaDiagonaleDuFou();
		int ligne = 2;
		int colonne = 3;
		FilmCopiable incrustation = new Incrustation(diag1.copie(),
				diag2.copie(), ligne, colonne);

		FilmCopiable copie = incrustation.copie();
		assertTrue(copie.getClass() == incrustation.getClass()
				&& copie != incrustation && copie.equals(incrustation));
	}

	@Test
	void testHauteur() {
		FilmCopiable diag1 = new LaDiagonaleDuFou();
		FilmCopiable diag2 = new LaDiagonaleDuFou();
		int ligne = 2;
		int colonne = 3;
		FilmCopiable incrustation = new Incrustation(diag1.copie(),
				diag2.copie(), ligne, colonne);

		assertEquals(diag1.hauteur(), incrustation.hauteur());
	}

	@Test
	void testLargeur() {
		FilmCopiable diag1 = new LaDiagonaleDuFou();
		FilmCopiable diag2 = new LaDiagonaleDuFou();
		int ligne = 2;
		int colonne = 3;
		FilmCopiable incrustation = new Incrustation(diag1.copie(),
				diag2.copie(), ligne, colonne);

		assertEquals(diag1.largeur(), incrustation.largeur());
	}

}
