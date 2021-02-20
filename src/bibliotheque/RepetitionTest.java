package bibliotheque;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


import exemple.LaDiagonaleDuFou;
import film.Films;

class RepetitionTest {

	@Test
	void testRembobiner() {
		FilmCopiable diag = new LaDiagonaleDuFou();
		Repetition repet = new Repetition(diag.copie(), 1);
		char[][] écran = Films.getEcran(repet);
		char[][] écran2 = Films.getEcran(repet);
		Repetition.testBlancCptRepet0();
		Repetition.testBlancCptRepet();
		repet.rembobiner();
		Repetition.testBlancCptRepet0();
		
		Films.effacer(écran);
		repet.suivante(écran);
		diag.suivante(écran2);
		
		assertEquals(Films.toString(écran2),Films.toString(écran));
		
	}

	@Test
	void testSuivante() {
		int nbRepet = 4;
		FilmCopiable diag = new LaDiagonaleDuFou();
		Repetition repet = new Repetition(diag.copie(), nbRepet);
		char[][] écran = Films.getEcran(repet);
		
		int cptOriginal = 0;
		int cptRepet = 0;
		while (diag.suivante(écran))
			++cptOriginal;
		while(repet.suivante(écran))
			++cptRepet;
		assertTrue(cptRepet == nbRepet*cptOriginal);
	}
	
	@Test
	void testClone() {
		int nbRepet = 4;
		FilmCopiable diag = new LaDiagonaleDuFou();
		Repetition repet = new Repetition(diag, nbRepet);
		FilmCopiable copie = repet.copie();
		assertTrue(copie.getClass() == repet.getClass() 
				   && copie != repet && copie.equals(repet));
	}
	
	
	@Test
	void testHauteur() {
		FilmCopiable diag = new LaDiagonaleDuFou();
		Repetition repet = new Repetition(diag, 2);
		
		assertEquals(diag.hauteur(), repet.hauteur());
	}
	
	@Test
	void testLargeur() {
		FilmCopiable diag = new LaDiagonaleDuFou();
		Repetition repet = new Repetition(diag, 2);
		
		assertEquals(diag.largeur(), repet.largeur());
	}
	
}
