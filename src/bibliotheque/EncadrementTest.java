package bibliotheque;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import exemple.LaDiagonaleDuFou;
import film.Films;

class EncadrementTest {

	@Test
	void testSuivante() {
		FilmCopiable film = new LaDiagonaleDuFou();
		FilmCopiable cadre = new Encadrement(film.copie());
		char[][] écran1 = Films.getEcran(film);
		char[][] écran2 = Films.getEcran(cadre);
		boolean egal = true;

		while (film.suivante(écran1) && cadre.suivante(écran2)) {
			// Vérification que le film a bien été encadré
			for (int col = 0; col < cadre.largeur(); ++col) {
				// Première ligne d'étoiles
				assertEquals(écran2[0][col], '*');
				//Dernière ligne
				assertEquals(écran2[cadre.hauteur() - 1][col], '*');
			}
			
			// Les autres lignes
			for (int lig = 1; lig < cadre.hauteur(); ++lig) {
				assertEquals(écran2[lig][0], '*');
				assertEquals(écran2[lig][cadre.largeur() - 1], '*');
			}

			// Vérification que l'intérieur du cadre correspond bien au film
			for (int i = 0; i < film.hauteur(); ++i) {
				for (int j = 0; j < film.largeur(); ++j) {
					if (écran1[i][j] != écran2[i + 1][j + 1]) {
						egal = false;
					}
				}
			}
			assertTrue(egal);
			Films.effacer(écran1);
			Films.effacer(écran2);
		}
	}

	@Test
	void testRembobiner() {
		FilmCopiable film = new LaDiagonaleDuFou();
		FilmCopiable cadre = new Encadrement(film);
		char[][] écran = Films.getEcran(cadre);
		char[][] frame1 = Films.getEcran(cadre);

		cadre.suivante(frame1);
		cadre.rembobiner();
		cadre.suivante(écran);

		assertEquals(Films.toString(écran), Films.toString(frame1));

	}

	@Test
	void testClone() {
		FilmCopiable film = new LaDiagonaleDuFou();
		FilmCopiable cadre = new Encadrement(film);
		FilmCopiable copie = cadre.copie();
		assertTrue(copie.getClass() == cadre.getClass() && copie != cadre
				&& copie.equals(cadre));
	}

	@Test
	void testHauteur() {
		FilmCopiable film = new LaDiagonaleDuFou();
		FilmCopiable cadre = new Encadrement(film);

		assertEquals(film.hauteur() + 2, cadre.hauteur());
	}

	@Test
	void testLargeur() {
		FilmCopiable film = new LaDiagonaleDuFou();
		FilmCopiable cadre = new Encadrement(film);

		assertEquals(film.largeur() + 2, cadre.largeur());
	}
}